package cn.matrixsci.doublerecording.app.module.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.core.asr.AsrProxy;
import cn.matrixsci.doublerecording.utils.CommonUtils;

public class TestActivity3 extends BaseActivity {

    Handler mainHandler = new Handler() {
        /*
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==6){
                Log.i("xxxxxx",msg.obj+"");
                CommonUtils.showShortToast(String.valueOf(msg.obj));
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test3);
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

    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.start_speak)
    public void startSpeak(){
        AsrProxy ap = AsrProxy.getInstance();
        ap.setMainHandler(mainHandler);
        ap.start();
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                /* 下面是蓝牙用的，可以不申请
                Manifest.permission.BROADCAST_STICKY,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
                */
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
