package com.sunm.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sunm.guide.init.InitPageControl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, InitPageControl.OnPageChangeCallback {

    private FrameLayout mPageContainer;
    private Button mStartBtn;
    private TextView mSkipText;

    private InitPageControl mPageControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mPageContainer = (FrameLayout) findViewById(R.id.page_parent);
        mStartBtn = (Button) findViewById(R.id.start_btn);
        mSkipText = (TextView) findViewById(R.id.skip_btn);

        mPageControl = new InitPageControl(this, mPageContainer);
        mPageControl.setPageChangeCallback(this);
        mStartBtn.setOnClickListener(this);
        mSkipText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                mPageControl.nextPage();
                break;
            case R.id.skip_btn:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNextPageChange(int pageIndex) {

    }

    @Override
    public void onLastPageFinish() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
