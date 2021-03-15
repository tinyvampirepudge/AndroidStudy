package com.tinytongtong.androidstudy.anim.breath;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @Description: 心跳动画
 * https://medium.com/@betakuang/android-%E5%AE%9E%E7%8E%B0%E5%BF%83%E8%B7%B3%E5%8A%A8%E7%94%BB-b274c0bb875e
 * @Author tinytongtong
 * @Date 3/14/21 10:36 PM
 * @Version
 */
public class HeartBeatAnimationUtil {
    private View mTarget;
    private long mDuration = 100;
    private long mDelay = 1200;
    private float mFromScale = 1.0f;
    private float mToScale = 0.8f;

    private HeartBeatAnimationUtil(View target) {
        mTarget = target;
    }

    /**
     * Creates a new instance and specifies a View to apply animation.
     *
     * @param target View to apply animation
     * @return The instance itself
     */
    public static HeartBeatAnimationUtil with(View target) {
        return new HeartBeatAnimationUtil(target);
    }

    /**
     * Specifies the duration.
     *
     * @param duration Duration of the animation
     * @return The instance itself
     */
    public HeartBeatAnimationUtil in(long duration) {
        mDuration = duration;
        return this;
    }

    /**
     * Specifies the time of delay.
     *
     * @param delay Time of delay between two animations
     * @return The instance itself
     */
    public HeartBeatAnimationUtil after(long delay) {
        mDelay = delay;
        return this;
    }

    /**
     * Sets the original scale.
     *
     * @param fromScale The original scale of the view
     * @return The instance itself
     */
    public HeartBeatAnimationUtil scaleFrom(float fromScale) {
        mFromScale = fromScale;
        return this;
    }

    /**
     * Sets the targeted scale.
     *
     * @param toScale The targeted scale of the view
     * @return The instance itself
     */
    public HeartBeatAnimationUtil scaleTo(float toScale) {
        mToScale = toScale;
        return this;
    }

    /**
     * Starts the animation.
     */
    public void start() {
        PropertyValuesHolder pvhIncreaseScaleX =
                PropertyValuesHolder.ofFloat("scaleX", mFromScale, mToScale);
        PropertyValuesHolder pvhIncreaseScaleY =
                PropertyValuesHolder.ofFloat("scaleY", mFromScale, mToScale);
        PropertyValuesHolder pvhDecreaseScaleX =
                PropertyValuesHolder.ofFloat("scaleX", mToScale, mFromScale);
        PropertyValuesHolder pvhDecreaseScaleY =
                PropertyValuesHolder.ofFloat("scaleY", mToScale, mFromScale);

        ObjectAnimator heartBeatIncreaseAnimator = ObjectAnimator.ofPropertyValuesHolder(
                mTarget, pvhIncreaseScaleX, pvhIncreaseScaleY
        );
        heartBeatIncreaseAnimator.setStartDelay(mDelay);
        heartBeatIncreaseAnimator.setDuration(mDuration);
        heartBeatIncreaseAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator heartBeatDecreaseAnimator = ObjectAnimator.ofPropertyValuesHolder(
                mTarget, pvhDecreaseScaleX, pvhDecreaseScaleY
        );
        heartBeatDecreaseAnimator.setDuration(mDuration);
        heartBeatDecreaseAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet heartBeatAnimatorSet = new AnimatorSet();
        heartBeatAnimatorSet.playSequentially(heartBeatIncreaseAnimator, heartBeatDecreaseAnimator);
        heartBeatAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        heartBeatAnimatorSet.start();
    }
}
