package cn.matrixsci.doublerecording.app.module.record;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.MFGT;

public class RecordActivity5 extends BaseActivity {

    @Bind(R.id.txt_title)
    TextView txt_title;
    @Bind(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record5);
    }

    @Override
    protected void findView() {
        txt_title.setText(getString(R.string.record_title_offline));
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
        MFGT.gotoRecordActivity6(this);
    }
}
