package com.tinytongtong.androidstudy.nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 就是一个实现了 {@link NestedScrollingChild} 接口的 FrameLayout.
 * - 他自身并不能滚动，但能把 touch 事件传递给父容器（支持 nested scroll 的父容器）
 * - 这里主要用于作为 {@link CustomDrawerLayoutParent} 的直接子元素
 * @Author wangjianzhou
 * @Date 2021/8/12 11:40 AM
 */
public class CustomDrawerLayoutChild extends FrameLayout implements NestedScrollingChild {

    private float mStartX = 0;
    private float mStartY = 0;
    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private boolean mIsBeingDragged;
    private VelocityTracker mVelocityTracker;
    private boolean mDisallowInterceptScroll = false;
    private NestedScrollingChildHelper mNestedChildHelper;


    public CustomDrawerLayoutChild(Context context) {
        super(context);
        init(context);
    }

    public CustomDrawerLayoutChild(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomDrawerLayoutChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration v = ViewConfiguration.get(context);
        mTouchSlop = v.getScaledTouchSlop();
        mMinimumVelocity = v.getScaledMinimumFlingVelocity();

        mNestedChildHelper = new NestedScrollingChildHelper(this);
        mNestedChildHelper.setNestedScrollingEnabled(true);
    }

    /**
     * 不要拦截本次事件（由其子View调用）
     */
    public void requestDisallowInterceptScroll(boolean disallowIntercept) {
        mDisallowInterceptScroll = disallowIntercept;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        int index = event.getActionIndex();
        mActivePointerId = event.getPointerId(index);

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        MotionEvent vtev = MotionEvent.obtain(event);
        vtev.setLocation(event.getRawX(), event.getRawY());
        mVelocityTracker.addMovement(vtev);
        vtev.recycle();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                int nestedScrollAxis = ViewCompat.SCROLL_AXIS_HORIZONTAL;
                startNestedScroll(nestedScrollAxis);
                mStartX = event.getX();
                mStartY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                log("onInterceptTouchEvent MOVE : " + mDisallowInterceptScroll);
                if (mDisallowInterceptScroll) {
                    break;
                }

                int dx = (int) (event.getX() - mStartX);
                int dy = (int) (event.getY() - mStartY);
                // 判断是否处于拖动状态
                if (Math.abs(dx) >= mTouchSlop && Math.abs(dx) > Math.abs(dy)) {
                    mIsBeingDragged = true;
                    mStartX = event.getX();
                    return true;
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                recycleVelocityTracker();
                stopNestedScroll();
                mIsBeingDragged = false;
                break;

            case MotionEvent.ACTION_UP:
                if (!mDisallowInterceptScroll && mIsBeingDragged) {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float vx = mVelocityTracker.getXVelocity(mActivePointerId);
                    log("onTouchEvent-UPUP " + vx + ", " + mMinimumVelocity);
                    if (Math.abs(vx) > mMinimumVelocity) {
                        dispatchNestedPreFling(-vx, 0);
                    }
                }
                recycleVelocityTracker();
                mIsBeingDragged = false;
                stopNestedScroll();
                break;

        }

        boolean r = super.onInterceptTouchEvent(event);
        log("onInterceptTouchEvent r: " + r);
        return r;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        int index = event.getActionIndex();
        mActivePointerId = event.getPointerId(index);

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        MotionEvent vtev = MotionEvent.obtain(event);
        vtev.setLocation(event.getRawX(), event.getRawY());
        mVelocityTracker.addMovement(vtev);
        vtev.recycle();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                // 水平
                int nestedScrollAxis = ViewCompat.SCROLL_AXIS_HORIZONTAL;
                startNestedScroll(nestedScrollAxis);
                mStartX = event.getX();
                mStartY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                log("onTouchEvent MOVE : " + mDisallowInterceptScroll);
                if (mDisallowInterceptScroll) {
                    break;
                }

                // 水平滑动
                int dx = -(int) (event.getX() - mStartX);
                if (mIsBeingDragged) {
                    dispatchNestedPreScroll(dx, 0, new int[]{0, 0}, new int[]{0, 0});
                    return true;
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                recycleVelocityTracker();
                mIsBeingDragged = false;
                stopNestedScroll();
                break;

            case MotionEvent.ACTION_UP:
                if (!mDisallowInterceptScroll && mIsBeingDragged) {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float vx = mVelocityTracker.getXVelocity(mActivePointerId);
                    log("onTouchEvent-UPUP " + vx + ", " + mMinimumVelocity);
                    if (Math.abs(vx) > mMinimumVelocity) {
                        dispatchNestedPreFling(-vx, 0);
                    }
                }
                recycleVelocityTracker();
                mIsBeingDragged = false;
                stopNestedScroll();
                break;

        }

        boolean r = super.onTouchEvent(event);
        log("onTouchEvent : r" + r);
        return r;
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }


    /* ------------------------------------------
     *    实现 NestedScrollingChild 接口方法：
     * ------------------------------------------ */

    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return mNestedChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return mNestedChildHelper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        mNestedChildHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return mNestedChildHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mNestedChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mNestedChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mNestedChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mNestedChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    private void log(String s) {
        LogUtils.INSTANCE.d("NestedScroll-Child", s);
    }

}
