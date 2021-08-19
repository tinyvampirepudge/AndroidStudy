package com.tinytongtong.androidstudy.nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.Px;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;

import com.tinytongtong.tinyutils.LogUtils;

import java.util.Arrays;

/**
 * @Description: 支持嵌套滑动，使用 {@link androidx.core.view.NestedScrollingParent} 和 {@link androidx.core.view.NestedScrollingChild} 实现。
 * - 实现了 NestedScrollingParent 接口的一个 Layout。
 * - 作为一个 nested parent，能接收其子 View (实现了{@link androidx.core.view.NestedScrollingChild}) 传递来的嵌套滚动事件；
 * - 子View 可以是任意实现了 {@link androidx.core.view.NestedScrollingChild} 接口的对象，比如 RecyclerView ...
 * - 可以设置两个滚动档位：expand, collapse.
 * @Author wangjianzhou
 * @Date 2021/8/12 11:37 AM
 * @Version
 */
public class CustomDrawerLayoutParent extends FrameLayout implements NestedScrollingParent {

    /**
     * 展开状态
     */
    private static final int STATE_EXPAND = 0;
    /**
     * 收起状态
     */
    private static final int STATE_COLLAPSE = 1;
    /**
     * 滑动状态
     */
    private static final int STATE_SCROLLING = 4;
    private static final int STATE_SCROLL_DOWN_BOUND = 5; // 从属于STATE_SCROLLING态，代表往下滑到底部的时候再往下滑的状态
    private static final int STATE_SCROLL_TO_EXPANDED = 6;// 从属于STATE_SCROLLING态，代表往即将上滑

    private static final float COLLAPSE_POINT = 0.9f;

    private static final int DEFAULT_SCROLL_DURATION = 600;

    /**
     * 默认状态
     */
    private int mState = STATE_EXPAND;

    private Scroller mScroller;

    /**
     * 正在进行嵌套滚动过程
     */
    private boolean mIsDoingNestedScroll = false;

    private float mCollapsePoint = COLLAPSE_POINT;

    private DrawerListener mListener;
    private boolean isScrollEnable = true;

    public CustomDrawerLayoutParent(Context context) {
        super(context);
        init(context);
    }

    public CustomDrawerLayoutParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomDrawerLayoutParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mScroller = new Scroller(context);
    }


    public void setPanelStatePrivate(int state) {
        if (mState != state) {
            final int o = this.mState;
            mState = state;
            if (mListener != null) {
                mListener.onStateChanged(o, state);
            }
        }
    }

    public void addPanelSlideListener(DrawerListener listener) {
        mListener = listener;
    }

    public int getPanelState() {
        return mState;
    }

    public void setExpandState() {
        setExpandStateWithDuration(DEFAULT_SCROLL_DURATION);
    }

    public void setExpandStateWithDuration(int duration) {
        setPanelState(STATE_EXPAND, duration);
    }

    public void setCollapseState() {
        setCollapseStateWithDuration(DEFAULT_SCROLL_DURATION);
    }

    public void setCollapseStateWithDuration(int duration) {
        setPanelState(STATE_COLLAPSE, duration);
    }

    public void setPanelState(int state, int duration) {
        log(String.format("setPanelState state:%s, duration:%s", state, duration));
        if (state == STATE_SCROLLING) {
            log(String.format("setPanelState state error return, state:%s, duration:%s", state, duration));
            return;
        }

        setPanelStatePrivate(state);

        final int width = this.getMeasuredWidth();
        log(String.format("setPanelState width:%s", width));
        if (width <= 0) { // 初始化还未完成
            return;
        }

        if (state == STATE_EXPAND
                || state == STATE_COLLAPSE) {
            smoothScrollToState(state, duration);
        }
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return isScrollEnable;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        log(String.format("onNestedPreScroll dx:%s, dy:%s, consumed:%s", dx, dy, Arrays.toString(consumed)));
        float scrollY = getScrollY();

        /*
        mScrollX为 正 代表着当前内容相对于初始位置 向左 偏移了mScrollX的距离，方向 --> 从左向右;
        mScrollX为 负 表示当前内容相对于初始位置 向右 偏移了mScrollX的距离，方向 --> 从右向左.
         */
        float scrollX = getScrollX();
        log(String.format("onNestedPreScroll scrollX:%s, scrollY:%s", scrollX, scrollY));
        // 向右滑动收起。
        boolean needToHideRight = dx > 0 && scrollX < 0;
        // 向左滑动展开。
        boolean needToShowLeft = dx < 0 && scrollY > -getCollapseX() && !ViewCompat.canScrollHorizontally(target, -1);

        log(String.format("onNestedPreScroll needToHideRight:%s, needToShowLeft:%s", needToHideRight, needToShowLeft));

        if (needToHideRight || needToShowLeft) {
            mIsDoingNestedScroll = true;
            scrollBy(dx, 0);
            consumed[0] = dx;
        }

        log(String.format("onNestedPreScroll mState:%s", mState));
        if ((mState == STATE_COLLAPSE || mState == STATE_SCROLL_DOWN_BOUND) && dx < 0) {
            setPanelStatePrivate(STATE_SCROLL_DOWN_BOUND);
            return;
        }
        if ((mState == STATE_COLLAPSE || mState == STATE_SCROLL_TO_EXPANDED) && dx > 0) {
            setPanelStatePrivate(STATE_SCROLL_TO_EXPANDED);
            return;
        }
        setPanelStatePrivate(STATE_SCROLLING);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        log("onNestedPreFling() : " + getScrollX() + ", v = " + velocityX);
        if (getScrollX() < 0) {
            onFingerReleased(velocityX);
            mIsDoingNestedScroll = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public void onStopNestedScroll(View child) {
        log("onStopNestedScroll() --------- stop!! " + mIsDoingNestedScroll);
        if (mIsDoingNestedScroll) {
            onFingerReleased(0);
            mIsDoingNestedScroll = false;
            computeScroll();
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    @Override
    public int getNestedScrollAxes() {
        return ViewCompat.SCROLL_AXIS_HORIZONTAL;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        // 控制滚动的边界值：
        if (x > 0) {
            x = 0;
        }
        if (x < -getCollapseX()) {
            x = -getCollapseX();
        }

        if (mListener != null && getMeasuredWidth() != 0) {
            mListener.onScrollOffset((-x) / (float) getMeasuredWidth());
        }

        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {

        log("computeScroll() enter");
        if (mScroller.computeScrollOffset()) {
            log("computeScroll() scroll to : " + mScroller.getCurrX());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else {
            if (mIsDoingNestedScroll) {
                log(String.format("computeScroll() else return mIsDoingNestedScroll is true"));
                return;
            }
            int finalX = mScroller.getFinalX();
            int width = this.getMeasuredWidth();
            int collapseX = -getCollapseX();

            log(String.format("computeScroll() else finalX:%s, width:%s,collapseX:%s", finalX, width, collapseX));
            if (finalX == 0) {
                setPanelStatePrivate(STATE_EXPAND);
            } else if (finalX == collapseX) {
                setPanelStatePrivate(STATE_COLLAPSE);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        log(String.format("onLayout changed:%s,left:%s,top:%s,right:%s,bottom:%s",
                changed, left, top, right, bottom));
        // 布局宽度变化的时候，需要手动调整 mScrollX 的位置
        if (changed) {
            adjustScroller();
        }
    }

    /**
     * 手指松开时。
     *
     * @param velX x轴的速度;
     */
    private void onFingerReleased(float velX) {

        int currentX = getScrollX();
        int collapseX = -getCollapseX();

        log(String.format("onFingerReleased velX:%s, currentX:%s, collapseX:%s", velX, currentX, collapseX));

        int targetState = STATE_EXPAND;

        if (velX > 0) { // 向坐滑
            targetState = STATE_EXPAND;
        } else if (velX < 0) { // 向右滑
            targetState = STATE_COLLAPSE;
        } else {  // 没滑动
            if (currentX > collapseX / 2) {
                targetState = STATE_EXPAND;
            } else {
                targetState = STATE_COLLAPSE;
            }
        }
        if (mListener != null) {
            mListener.onSlideReleased(velX, 0, targetState);
        }

        smoothScrollToState(targetState, DEFAULT_SCROLL_DURATION);
    }

    private void smoothScrollToState(int state, int duration) {
        log(String.format("smoothScrollToState state:%s, duration:%s", state, duration));
        int width = this.getMeasuredWidth();
        if (width <= 0) {
            log(String.format("smoothScrollToState return width:%s", width));
            return;
        }

        int x = 0;
        if (state == STATE_EXPAND) {
            x = 0;
        } else if (state == STATE_COLLAPSE) {
            x = -getCollapseX();
        } else {
            log(String.format("smoothScrollToState return state else"));
            return;
        }
        smoothScrollTo(x, duration);
    }

    private void smoothScrollTo(int targetX, int duration) {
        log("smoothScrollTo() " + targetX + ", " + duration);
        log(String.format("smoothScrollTo targetX:%s, duration:%s", targetX, duration));
        int currentX = getScrollX();
        // TODO-wjz: 2021/8/16 4:53 PM
        int dx = targetX - currentX;
        log(String.format("smoothScrollTo currentX:%s, dx:%s", currentX, dx));
        mScroller.startScroll(currentX, 0, dx, 0, duration);
        invalidate();
    }


    public void appearAnimation(int state) {
        this.setScrollY(-this.getMeasuredHeight());
        smoothScrollToState(state, DEFAULT_SCROLL_DURATION);
        this.setVisibility(VISIBLE);
    }

    /**
     * 控件宽度变化后，需要手动调整 mScrollX 的位置
     * 不然 mScrollX 的值跟当前的宽度不匹配，下次滚动，起点就不是当前View真实的起点，就可能会导致页面上额外的闪烁。
     */
    private void adjustScroller() {
        log(String.format("adjustScroller start getPanelState():%s, getScrollX():%s", getPanelState(), getScrollX()));
        if (mScroller == null) {
            log(String.format("adjustScroller return mScroller == null"));
            return;
        }

        if (getPanelState() == STATE_EXPAND || getPanelState() == STATE_COLLAPSE) {
            /*
            校验当前的state 和 新宽度下的 mScrollX 是否匹配
            如果不匹配，就更新 mScrollX 的值。
             */
            // ①expand模式下，偏移量是0
            if (getPanelState() == STATE_EXPAND && getScrollX() != 0) {
                setScrollX(0);
            } else if (getPanelState() == STATE_COLLAPSE) {
                int width = this.getMeasuredWidth();
                int newScrollX = -getCollapseX();
                log(String.format("adjustScroller start newScrollX:%s, getScrollX():%s", newScrollX, getScrollX()));
                if (newScrollX != getScrollX()) {
                    setScrollX(newScrollX);
                }
            }
        }
    }

    /**
     * 获取展开状态下，对应的水平位置
     *
     * @return
     */
    private int getExpandX() {
        return 0;
    }

    /**
     * 获取收起状态下，对应的水平位置
     *
     * @return
     */
    private int getCollapseX() {
        return (int) (mCollapsePoint * getMeasuredWidth());
    }

    private void log(String s) {
        LogUtils.INSTANCE.d("NestedScroll-Parent", s);
    }

    public interface DrawerListener {
        void onStateChanged(int oldState, int newState);

        void onScrollOffset(float offset);

        void onSlideReleased(float dx, float dy, int state);
    }
}
