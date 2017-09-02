package com.sunm.guide.init;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sunm.guide.init.view.FirstPageView;
import com.sunm.guide.init.view.SecondPageView;

import java.util.ArrayList;
import java.util.List;

public class InitPageControl {

    private List<InitPage> mPageList;
    private Context mContext;
    private int mCurrentPage;
    private ViewGroup mContainerView;
    // 页面切换的回调
    private OnPageChangeCallback mCallback;

    public InitPageControl(Context context, ViewGroup parent) {
        this.mContext = context;
        this.mContainerView = parent;
        mPageList = new ArrayList<>(4);
        addViews();
        addViewAndEnter();
    }

    public void setPageChangeCallback(OnPageChangeCallback callback) {
        this.mCallback = callback;
    }

    private void addViews() {
        mPageList.add(new FirstPageView(mContext));
        mPageList.add(new SecondPageView(mContext));
    }

    private void addViewAndEnter() {
        InitPage initPage = mPageList.get(mCurrentPage);
        if (initPage.getPageView().getVisibility() != View.VISIBLE) {
            initPage.getPageView().setVisibility(View.VISIBLE);
        }
        mContainerView.addView(initPage.getPageView());
        initPage.pageEnterAnim();
    }

    public void nextPage() {
        InitPage initPage = mPageList.get(mCurrentPage);
        initPage.pageOutAnim();
        initPage.onAnimEndListener(new AnimHelperInter() {
            @Override
            public void onAnimEndListener() {
                if (mPageList.size() > ( ++ mCurrentPage)) {
                    addViewAndEnter();
                    if (mCallback != null) {
                        mCallback.onNextPageChange(mCurrentPage);
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onLastPageFinish();
                    }
                }
            }
        });
    }

    public interface OnPageChangeCallback {
        void onNextPageChange(int pageIndex);
        void onLastPageFinish();
    }
}
