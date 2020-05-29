package cn.matrixsci.doublerecording.app.module.record;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.MFGT;

public class RecordActivity6 extends BaseActivity {

    @Bind(R.id.txt_title)
    TextView txt_title;
    @Bind(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record6);
    }

    @Override
    protected void findView() {
        txt_title.setText(getString(R.string.record_title_sign));
        img_back.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.img_back)
    public void close() {
        MFGT.finish(this);
    }

    @OnClick(R.id.btn_next)
    public void next(){
        new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("提示")
                .setMessage("是否访问手机摄像头")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MFGT.gotoRecordActivityFinish(RecordActivity6.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                })
                .create().show();
    }
}
