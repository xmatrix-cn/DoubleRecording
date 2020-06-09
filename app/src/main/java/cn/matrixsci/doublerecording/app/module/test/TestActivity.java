package cn.matrixsci.doublerecording.app.module.test;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void findView() {

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

    @OnClick(R.id.test1)
    public void test1(){
        Intent intent = new Intent(this, TestActivity1.class);
        startActivity(intent);
    }
    @OnClick(R.id.test2)
    public void test2(){
        Intent intent = new Intent(this, TestActivity2.class);
        startActivity(intent);
    }
    @OnClick(R.id.test3)
    public void test3(){
        Intent intent = new Intent(this, TestActivity3.class);
        startActivity(intent);
    }
//    @OnClick(R.id.test4)
//    public void test4(){
//        Intent intent = new Intent(this, TestActivity4.class);
//        startActivity(intent);
//    }
}
