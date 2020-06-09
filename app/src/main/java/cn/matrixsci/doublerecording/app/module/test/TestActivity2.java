package cn.matrixsci.doublerecording.app.module.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.core.tts.TtsProxy;
import cn.matrixsci.doublerecording.utils.CommonUtils;
import cn.matrixsci.doublerecording.utils.LogUtils;

public class TestActivity2 extends BaseActivity {

    @Bind(R.id.words)
    EditText editText;

    Handler mainHandler = new Handler() {
        /*
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            LogUtils.e("mainHandlerxxxxxxxx",msg.what+"");
        }

    };

    @OnClick(R.id.speak)
    public void speak(){
        String text = editText.getText().toString();
        TtsProxy.getInstance().speak(text);
        LogUtils.i("speek end");
        CommonUtils.showShortToast(text);
    }

    @Override
    protected void onDestroy() {
        TtsProxy.getInstance().release();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test2);
        super.onCreate(savedInstanceState);
        initPermission();
    }


    @Override
    protected void findView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        TtsProxy tp = TtsProxy.getInstance();
        tp.setMainHandler(mainHandler);
    }

    @Override
    protected void setListener() {

    }



    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

}
