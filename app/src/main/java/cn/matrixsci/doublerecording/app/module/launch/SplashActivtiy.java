package cn.matrixsci.doublerecording.app.module.launch;

import android.content.Intent;
import android.os.Bundle;

import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.MFGT;

public class SplashActivtiy extends BaseActivity {
    private static final int sleepTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void findView() {

    }
    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                //判断登录状态
                boolean isLoggedIn = false;
                if (isLoggedIn) {
                    long start = System.currentTimeMillis();
                    //同步拉取用户信息
                    long costTime = System.currentTimeMillis() - start;
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    MFGT.gotoMainActivity(SplashActivtiy.this);
                    finish();
                }else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivtiy.this, GuideActivity.class));
                    finish();
                }
            }
        }).start();

    }
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
