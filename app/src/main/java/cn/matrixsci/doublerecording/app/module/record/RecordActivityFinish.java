package cn.matrixsci.doublerecording.app.module.record;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.MFGT;

public class RecordActivityFinish extends BaseActivity {

    @Bind(R.id.txt_title)
    TextView txt_title;
    @Bind(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_finish);
    }

    @Override
    protected void findView() {
        txt_title.setText(getString(R.string.record_title_finish));
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

    @OnClick(R.id.btn_back_home)
    public void next(){
        MFGT.gotoMainActivity(this);
    }
}
