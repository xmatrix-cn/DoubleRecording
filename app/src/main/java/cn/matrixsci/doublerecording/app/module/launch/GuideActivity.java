package cn.matrixsci.doublerecording.app.module.launch;

import android.os.Bundle;

import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.EventTag;
import cn.matrixsci.doublerecording.common.MFGT;
import cn.matrixsci.doublerecording.event.EventListener;
import cn.matrixsci.doublerecording.event.EventManager;

public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
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
        EventManager.ins().registListener(EventTag.ACCOUNT_LOGIN, listener);
    }

    //返回按钮点击事件
    @OnClick(R.id.img_login)
    public void img_login_OnClick() {
        MFGT.gotoLogin(this);
    }

    //返回按钮点击事件
    @OnClick(R.id.img_register)
    public void img_register_OnClick() {
        MFGT.gotoRegister(this);
    }

    EventListener listener = new EventListener() {
        @Override
        public void handleMessage(int what, int arg1, int arg2, Object dataobj) {
            switch (what) {
                case EventTag.ACCOUNT_LOGIN:
                    finish();
                    break;
            }
        }
    };

}
