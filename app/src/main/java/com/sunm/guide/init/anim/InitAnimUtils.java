package com.sunm.guide.init.anim;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class InitAnimUtils {

    private static final Long DEFAULT_ANIMATION_DURATION = 300L;

    public Animator ScaleAndAlphaAnim(final View view, float[] animValues) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(animValues);
        valueAnimator.setDuration(DEFAULT_ANIMATION_DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
                view.setAlpha(value);
            }
        });
        return valueAnimator;
    }

    public Animator TranslateAnim(final View view, float[] animValues, final int axis) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(animValues);
        valueAnimator.setDuration(DEFAULT_ANIMATION_DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                if (axis == 0) {
                    view.setTranslationX(value);
                } else {
                    view.setTranslationY(value);
                }
            }
        });
        return valueAnimator;
    }

    public AnimatorSet getTransWithAlphaAnim(final View view, float[] animValues, final int axis) {
        ValueAnimator transAnim = ValueAnimator.ofFloat(animValues);
        transAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                if (axis == 0) {
                    view.setTranslationX(value);
                } else {
                    view.setTranslationY(value);
                }
            }
        });

        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(transAnim, alphaAnim);
        animatorSet.setDuration(DEFAULT_ANIMATION_DURATION);
        animatorSet.setInterpolator(new LinearInterpolator());
        return animatorSet;
    }

}
