package com.sunm.guide.init.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunm.guide.BuildConfig;
import com.sunm.guide.R;
import com.sunm.guide.init.AnimHelperInter;
import com.sunm.guide.init.InitPage;
import com.sunm.guide.init.anim.InitAnimUtils;

public class SecondPageView extends FrameLayout implements InitPage {

    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "SecondPageView";

    private Context mContext;
    private ImageView mPageIcon;
    private TextView mPageText;
    private InitAnimUtils mInitAnimUtils;
    private AnimHelperInter mAnimHelperInter;

    public SecondPageView(@NonNull Context context) {
        super(context);
        this.mContext = context;
        mInitAnimUtils = new InitAnimUtils();
        initView();
    }

    private void initView() {
        setWillNotDraw(true);
        LayoutInflater.from(mContext).inflate(R.layout.init_second_page, this);

        mPageIcon = (ImageView) findViewById(R.id.second_wallpaper);
        mPageText = (TextView) findViewById(R.id.second_title);
    }

    @Override
    public void pageEnterAnim() {
        float[] transValues = {-90f, 0};
        Animator transAnim = mInitAnimUtils.TranslateAnim(mPageText, transValues, 1);
        float[] transValues2 = {90f, 0};
        AnimatorSet transWithAlphaAnim = mInitAnimUtils.getTransWithAlphaAnim(mPageIcon, transValues2, 1);
        transWithAlphaAnim.playTogether(transAnim);
        transWithAlphaAnim.start();
    }

    @Override
    public void pageOutAnim() {
        float[] scaleValues = {1f, 0f};
        Animator scaleAnim1 = mInitAnimUtils.ScaleAndAlphaAnim(mPageIcon, scaleValues);
        Animator scaleAnim2 = mInitAnimUtils.ScaleAndAlphaAnim(mPageText, scaleValues);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleAnim1, scaleAnim2);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mAnimHelperInter != null) {
                    mAnimHelperInter.onAnimEndListener();
                }
            }
        });
        animatorSet.start();
    }

    @Override
    public View getPageView() {
        return this;
    }

    @Override
    public void onAnimEndListener(AnimHelperInter inter) {
        this.mAnimHelperInter = inter;
    }

}
