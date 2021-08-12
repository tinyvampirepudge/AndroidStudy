package com.tinytongtong.androidstudy.nestedscrolling;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

import java.util.Arrays;

/**
 * @Description: 实现了 NestedScrollingParent 接口的一个 Layout。
 * - 作为一个 nested parent，能接收其子 View (实现了NestedScrollingChild) 传递来的嵌套滚动事件；
 * - 子View 可以是任意实现了 NestedScrollingChild 接口的对象，比如 RecyclerView, DGPNestedScrollFrameLayout ...
 * - 可以设置三个滚动档位：expanded, collapse, anchored.
 * @Author wangjianzhou
 * @Date 2021/8/12 11:37 AM
 * @Version
 */
public class CustomNestedScrollParent extends FrameLayout implements NestedScrollingParent {

    public static final int STATE_EXPANDED = 0;
    public static final int STATE_COLLAPSED = 1;
    public static final int STATE_ANCHORED = 2;
    public static final int STATE_SCROLLING = 4;
    public static final int STATE_SCROLL_DOWN_BOUND = 5; // 从属于STATE_SCROLLING态，代表往下滑到底部的时候再往下滑的状态
    public static final int STATE_SCROLL_TO_EXPANDED = 6;// 从属于STATE_SCROLLING态，代表往即将上滑

    public static final float ANCHOR_POINT = 1 - 0.618f;
    public static final float COLLAPSE_POINT = 0.9f;

    public static final int DEFAULT_SCROLL_DURATION = 600;

    private int mState = STATE_ANCHORED;

    /**
     * 顶部阴影
     */
    private Drawable mShadowDrawable;
    /**
     * 顶部阴影高度
     */
    private int mShadowHeight;

    private Scroller mScroller;

    /**
     * 正在进行嵌套滚动过程
     */
    private boolean mIsDoingNestedScroll = false;

    private float mAnchorPoint = ANCHOR_POINT;
    private float mCollapsePoint = COLLAPSE_POINT;

    private SlideListener mListener;
    private boolean isNeedShadow = false;
    private boolean isScrollEnable = true;

    public CustomNestedScrollParent(Context context) {
        super(context);
        init(context);
    }

    public CustomNestedScrollParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomNestedScrollParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mScroller = new Scroller(context);
        if (context instanceof Activity) {
            int authorWidth = ScreenUtils.dip2px(context, 200);
            int widowWidth = getWindowWidth((Activity) context);
            float rateAnchor = (float) authorWidth / widowWidth;
            mAnchorPoint = 1 - rateAnchor;
            log(String.format("init authorWidth:%s, widowWidth:%s, mAnchorPoint:%s", authorWidth, widowWidth, mAnchorPoint));
        }

        mShadowDrawable = context.getResources().getDrawable(R.drawable.bg_shadow_up_2);

        float density = context.getResources().getDisplayMetrics().density;

        // 4dp
        mShadowHeight = (int) (density * 4);

        // 使 onDraw() 方法被调用
        this.setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (isNeedShadow && mShadowDrawable != null) {
//            // 在最顶部画上阴影
//            mShadowDrawable.setBounds(0, -mShadowHeight, this.getMeasuredWidth(), 0);
//            mShadowDrawable.draw(canvas);
//        }
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
        boolean needToShowLeft = dx < 0 && scrollY > -(mCollapsePoint * getMeasuredWidth()) && !ViewCompat.canScrollHorizontally(target, -1);

        log(String.format("onNestedPreScroll needToHideRight:%s, needToShowLeft:%s", needToHideRight, needToShowLeft));

        if (needToHideRight || needToShowLeft) {
            mIsDoingNestedScroll = true;
            scrollBy(dx, 0);
            consumed[0] = dx;
        }

        log(String.format("onNestedPreScroll mState:%s", mState));
        if ((mState == STATE_COLLAPSED || mState == STATE_SCROLL_DOWN_BOUND) && dx < 0) {
            setPanelStatePrivate(STATE_SCROLL_DOWN_BOUND);
            return;
        }
        if ((mState == STATE_COLLAPSED || mState == STATE_ANCHORED || mState == STATE_SCROLL_TO_EXPANDED) && dx > 0) {
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
        if (x < -(mCollapsePoint * getMeasuredWidth())) {
            x = -(int) (mCollapsePoint * getMeasuredWidth());
        }

        if (mListener != null && getMeasuredWidth() != 0) {
            mListener.onScrollOffset((-x) / (float) getMeasuredWidth());
        }

        super.scrollTo(x, y);
    }

    public float getStatePoint(int state) {
        if (state == STATE_ANCHORED) {
            return mAnchorPoint;
        }
        return mCollapsePoint;
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
                return;
            }
            int finalX = mScroller.getFinalX();
            int width = this.getMeasuredWidth();
            int anchorX = -(int) (mAnchorPoint * width);
            int collapseX = -(int) (mCollapsePoint * width);

            if (finalX == 0) {
                setPanelStatePrivate(STATE_EXPANDED);
            } else if (finalX == anchorX) {
                setPanelStatePrivate(STATE_ANCHORED);
            } else if (finalX == collapseX) {
                setPanelStatePrivate(STATE_COLLAPSED);
            }
        }
    }

    /**
     * 手指松开时。
     *
     * @param velX x轴的速度;
     */
    private void onFingerReleased(float velX) {

        int currentX = getScrollX();
        int anchorX = -(int) (mAnchorPoint * this.getMeasuredWidth());
        int collapseX = -(int) (mCollapsePoint * this.getMeasuredWidth());

        log(String.format("onFingerReleased mAnchorPoint:%s, mCollapsePoint:%s, this.getMeasuredWidth():%s", mAnchorPoint, mCollapsePoint, this.getMeasuredWidth()));
        log(String.format("onFingerReleased velX:%s, currentX:%s, anchorX:%s, collapseX:%s", velX, currentX, anchorX, collapseX));

        int targetState = STATE_EXPANDED;

        if (velX > 0) { // 向上滑
            if (currentX > anchorX) {
                targetState = STATE_EXPANDED;
            } else {
                targetState = STATE_ANCHORED;
            }
        } else if (velX < 0) { // 向下滑
            if (currentX < anchorX) {
                targetState = STATE_COLLAPSED;
            } else {
                targetState = STATE_ANCHORED;
            }
        } else {  // 没滑动

            if (currentX > anchorX / 2) {
                targetState = STATE_EXPANDED;
            } else if (currentX > (anchorX + collapseX) / 2) {
                targetState = STATE_ANCHORED;
            } else {
                targetState = STATE_COLLAPSED;
            }
        }
        if (mListener != null) {
            mListener.onSlideReleased(velX, 0, targetState);
        }

        smoothScrollToState(targetState, DEFAULT_SCROLL_DURATION);
    }

    private void smoothScrollToState(int state, int duration) {
        int width = this.getMeasuredWidth();
        if (width <= 0) {
            return;
        }

        int x = 0;
        if (state == STATE_EXPANDED) {
            x = 0;
        } else if (state == STATE_ANCHORED) {
            x = -(int) (mAnchorPoint * width);
        } else if (state == STATE_COLLAPSED) {
            x = -(int) (mCollapsePoint * width);
        } else {
            return;
        }
        smoothScrollTo(x, duration);
    }

    private void smoothScrollTo(int targetX, int duration) {
        log("smoothScrollTo() " + targetX + ", " + duration);
        int currentX = getScrollX();
        mScroller.startScroll(currentX, 0, targetX - currentX, 0, duration);
        invalidate();
    }

    public int getPanelHeight() {
        return (int) ((1 - mCollapsePoint) * this.getMeasuredHeight());
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

    /**
     * just for test.
     */
    public static String stateString(int state) {
        switch (state) {
            case STATE_EXPANDED:
                return "expanded";
            case STATE_ANCHORED:
                return "anchored";
            case STATE_COLLAPSED:
                return "collapsed";
            default:
                return "" + state;
        }
    }


    public interface SlideListener {
        void onStateChanged(int oldState, int newState);

        void onScrollOffset(float offset);

        void onSlideReleased(float dx, float dy, int state);
    }

    public void addPanelSlideListener(SlideListener listener) {
        mListener = listener;
    }

    public void appearAnimation(int state) {
        this.setScrollY(-this.getMeasuredHeight());
        smoothScrollToState(state, DEFAULT_SCROLL_DURATION);
        this.setVisibility(VISIBLE);
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

    private void log(String s) {
        LogUtils.INSTANCE.d("NestedScroll-Parent", s);
    }


    public float getAnchorPoint() {
        return mAnchorPoint;
    }

    public void setAnchorPoint(float point) {
        this.mAnchorPoint = point;
    }

    public float getCollapsePoint() {
        return mCollapsePoint;
    }

    public void setPanelHeight(int height) {
        if (height <= 0) return;

        float h = this.getMeasuredHeight();
        float p = (h - height) / h;

        if (p != mCollapsePoint) {
            mCollapsePoint = p;
            if (mState == STATE_COLLAPSED) {
                smoothScrollToState(STATE_COLLAPSED, 100);
            }
        }
    }


    public int getPanelState() {
        return mState;
    }

    public void setPanelState(int state) {
        setPanelState(state, DEFAULT_SCROLL_DURATION);
    }

    public void setPanelState(int state, int duration) {
        if (state == STATE_SCROLLING) {
            throw new IllegalStateException("state error");
        }

        final int width = this.getMeasuredWidth();
        if (width <= 0) { // 初始化还未完成
            setPanelStatePrivate(state);
            return;
        }

        if (state == STATE_EXPANDED
                || state == STATE_COLLAPSED
                || state == STATE_ANCHORED) {
            smoothScrollToState(state, duration);
        }
    }

    public void setScrollEnable(boolean scrollEnable) {
        isScrollEnable = scrollEnable;
    }

    /**
     * 得到手机屏幕高
     *
     * @return
     */
    public static int getWindowWidth(@Nullable Activity window) {
        if (window == null || window.isFinishing()) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
