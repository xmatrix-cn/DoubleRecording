package cn.matrixsci.doublerecording.app.module.main;

import android.content.Intent;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseFragment;
import cn.matrixsci.doublerecording.app.module.test.TestActivity;

//首页
public class HomeFragment extends BaseFragment {

    @OnClick(R.id.testBtn)
    public void test(){
        Intent intent = new Intent(getContext(), TestActivity.class);
        startActivity(intent);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_home;
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
