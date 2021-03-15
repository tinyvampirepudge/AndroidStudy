package com.tinytongtong.androidstudy.anim.breath;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.AnimRes;


/**
 * @Description: View动画帮助类
 * @Author wangjianzhou@didichuxing.com
 * @Date 3/11/21 5:04 PM
 */

/**
 * @Description: View动画帮助类
 * @Author tinytongtong
 * @Date 3/14/21 10:25 PM
 * @Version
 */
public class BreathAnimHelper {

    public static final String TAG = BreathAnimHelper.class.getSimpleName();

    private Context mContext;
    private View mAnimTarget;
    @AnimRes
    private int id;

    /**
     * 默认为0，表示不重复
     */
    private int repeatCount = 0;
    private int currentCount = 0;

    private boolean isRunning = false;
    /**
     * 动画总时长
     */
    private int duration;

    private long start;
    private long end;

    private Handler mHandler = new Handler();

    private Runnable animRunnable = new Runnable() {
        @Override
        public void run() {
            if (!checkViewVisible()) {
                return;
            }
            if (mAnimTarget.getAnimation() != null) {
                end = SystemClock.uptimeMillis();
                if (currentCount < repeatCount) {
                    Log.e(TAG, String.format("onAnimationEnd, 本次累计耗时:%s, currentCount:%s, repeatCount:%s", end - start, currentCount, repeatCount));
                    mAnimTarget.getAnimation().reset();
                    mAnimTarget.getAnimation().start();
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.postDelayed(animRunnable, duration);
                    currentCount++;
                } else {
                    Log.e(TAG, String.format("onAnimationEnd, 动画总耗时:%s, currentCount:%s, repeatCount:%s", end - start, currentCount, repeatCount));
                    stopAnim();
                }
            }
        }
    };

    public BreathAnimHelper(Context mContext, View mAnimTarget, int id, int duration) {
        this.mContext = mContext;
        this.mAnimTarget = mAnimTarget;
        this.id = id;
        this.duration = duration;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    /**
     * 开启动画
     */
    public void startAnim() {
        Log.e(TAG, String.format("startAnim"));
        if (!checkViewVisible()) {
            Log.e(TAG, String.format("startAnim return !checkViewVisible()"));
            return;
        }
        if (isRunning) {
            Log.e(TAG, String.format("startAnim return isRunning"));
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, id);
        isRunning = true;
        mAnimTarget.startAnimation(animation);
        // 因为Animation.AnimationListener#onAnimationEnd方法不靠谱，这里选择使用Handler实现
        start = SystemClock.uptimeMillis();
        end = SystemClock.uptimeMillis();
        mHandler.postDelayed(animRunnable, duration);
    }

    /**
     * 结束动画
     */
    public void stopAnim() {
        Log.e(TAG, String.format("stopAnim"));
        reset();
        mHandler.removeCallbacksAndMessages(null);
        if (!checkViewVisible()) {
            return;
        }
        /**
         * 清理动画。避免onAnimationEnd多次调用。这里使用View#setAnimation(null)来停止动画
         * Animation#cancel和View#clearAnimation方法都会调用触发onAnimationEnd回调。
         * onAnimationEnd回调时，animation.hasEnded()的值为false。
         */
//        mAnimTarget.setAnimation(null);
        // View#setAnimation(null)可能会导致View#startAnimation(animation)不生效。
        mAnimTarget.clearAnimation();
    }

    /**
     * 检查控件可见性
     *
     * @return
     */
    private boolean checkViewVisible() {
        if (mAnimTarget != null && mAnimTarget.getVisibility() == View.VISIBLE) {
            return true;
        }
        return false;
    }

    /**
     * 重置数据
     */
    public void reset() {
        isRunning = false;
        currentCount = 0;
        repeatCount = 0;
    }

    /**
     * 释放资源
     */
    public void release() {
        stopAnim();
    }
}
