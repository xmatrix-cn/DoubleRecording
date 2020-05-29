package cn.matrixsci.doublerecording.app.module.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.app.presenter.LoginCallback;
import cn.matrixsci.doublerecording.app.presenter.LoginPresenter;
import cn.matrixsci.doublerecording.common.MFGT;
import cn.matrixsci.doublerecording.utils.LogUtils;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.txt_title)
    TextView txt_title;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.et_usertel)
    EditText et_usertel;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.btn_login)
    Button btn_login;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void findView() {
    }

    @Override
    protected void initView() {
        txt_title.setText(getString(R.string.login_btn_action));
        img_back.setVisibility(View.VISIBLE);
        btn_login.setEnabled(true);
    }

    @Override
    protected void initData() {
        loginPresenter = new LoginPresenter();
    }

    @Override
    protected void setListener() {

    }

    //返回按钮点击事件
    @OnClick(R.id.img_back)
    public void close() {
        MFGT.finish(this);
    }

    //登录按钮点击事件
    @OnClick(R.id.btn_login)
    public void login() {
        String userName=et_usertel.getText().toString().trim();
        String userPwd=et_password.getText().toString().trim();
        checkForm(userName, userPwd);
    }

    private void checkForm(String userName, String userPwd) {
//        if(!RegUtils.isMobileNumber(userName)){
//            CommonUtils.showLongToast(getString(R.string.login_tip_tel_error));
//            return;
//        }

        loginPresenter.login(userName, userPwd, callback);
    }

    LoginCallback callback = new LoginCallback() {
        @Override
        public void onSuccess(String data) {
            LogUtils.e(data);
            Toast.makeText(getApplicationContext(), data,Toast.LENGTH_SHORT).show();
            MFGT.gotoMainActivity(LoginActivity.this);
            finish();
        }

        @Override
        public void onFailure(String error) {
            //add
            MFGT.gotoMainActivity(LoginActivity.this);
//            CommonUtils.showLongToast(getString(R.string.login_tip_loginerror)+error);
            LogUtils.e(error);
        }
    };

}
