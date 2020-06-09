package cn.matrixsci.doublerecording.app.module.account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.MFGT;
import cn.matrixsci.doublerecording.utils.LogUtils;

public class RegiseterActivity extends BaseActivity {
    @Bind(R.id.txt_title)
    TextView txt_title;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.et_usertel)
    EditText userNameEditText;
    @Bind(R.id.et_password)
    EditText passwordEditText;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void findView() {
        txt_title.setText(getString(R.string.login_btn_register));
        img_back.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
    }

    @OnClick(R.id.btn_register)
    public void btn_register() {
        register();
    }

    //返回按钮点击事件
    @OnClick(R.id.img_back)
    public void close() {
        MFGT.finish(this);
    }

    public void register() {
        final String username = userNameEditText.getText().toString().trim();
        final String pwd = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            userNameEditText.requestFocus();
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage(getResources().getString(R.string.Is_the_registered));
            pd.show();

            new Thread(new Runnable() {
                //模拟登陆耗时1秒
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!RegiseterActivity.this.isFinishing())
                                pd.dismiss();
                            // save current user
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully),Toast.LENGTH_SHORT ).show();
                            finish();
                        }
                    });
                }
            }).start();
        }
    }

    LoginCallback callback = new LoginCallback() {
        @Override
        public void onSuccess(String data) {
            LogUtils.e(data);
            MFGT.gotoMainActivity(RegiseterActivity.this);
            finish();
        }

        @Override
        public void onFailure(String error) {
            LogUtils.e(error);
        }
    };

}
