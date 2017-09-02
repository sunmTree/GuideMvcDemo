package com.sunm.guide.init;


import android.view.View;

public interface InitPage {

    void pageEnterAnim();

    void pageOutAnim();

    View getPageView();

    void onAnimEndListener(AnimHelperInter inter);

}
