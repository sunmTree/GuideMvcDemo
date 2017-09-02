package com.sunm.guide.init.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
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

public class FirstPageView extends FrameLayout implements InitPage {

    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "FirstPageView";

    private Context mContext;
    private ImageView mPageIcon;
    private TextView mPageText;
    private InitAnimUtils mInitAnimUtils;
    private AnimHelperInter mAnimHelperInter;

    public FirstPageView(@NonNull Context context) {
        super(context);
        this.mContext = context;
        mInitAnimUtils = new InitAnimUtils();
        initView();
    }

    private void initView() {
        setWillNotDraw(true);
        LayoutInflater.from(mContext).inflate(R.layout.init_first_page, this);

        this.mPageIcon = (ImageView) findViewById(R.id.first_page_icon);
        this.mPageText = (TextView) findViewById(R.id.first_page_title);
    }

    @Override
    public void pageEnterAnim() {
        if (DEBUG) {
            Log.d(TAG, this.getClass().getName() + " pageAnimEnter ");
        }
        float[] iconFloat = {0f, 0.4f, 1f};
        Animator iconAnim = mInitAnimUtils.ScaleAndAlphaAnim(mPageIcon, iconFloat);
        float[] titleFloat = {-90f, 0f};
        Animator titleAnim = mInitAnimUtils.TranslateAnim(mPageText, titleFloat, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(iconAnim, titleAnim);
        animatorSet.start();
    }

    @Override
    public void pageOutAnim() {
        if (DEBUG) {
            Log.d(TAG, this.getClass().getName() + " pageOutAnim ");
        }

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        float[] outFloats = {0f, widthPixels};
        Animator iconAnimOut = mInitAnimUtils.TranslateAnim(mPageIcon, outFloats, 0);
        Animator titleAnimOut = mInitAnimUtils.TranslateAnim(mPageText, outFloats, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(iconAnimOut, titleAnimOut);
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
