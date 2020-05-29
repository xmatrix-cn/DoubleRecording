package cn.matrixsci.doublerecording.app.module.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseFragment;
import cn.matrixsci.doublerecording.app.module.account.LoginActivity;
import cn.matrixsci.doublerecording.common.MFGT;

//待面签保单
public class TodoPolicyFragment extends BaseFragment {

    private AlertDialog.Builder builder;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_todo_policy;
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

    //面签呼叫
    @OnClick(R.id.btn_call_policy)
    public void login() {
        builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.ic_launcher).setTitle("选择面签")
                .setMessage("").setPositiveButton("人工远程", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.ic_launcher).setTitle("提示")
                                .setMessage("申请已受理，请耐心等待").setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).create().show();
                    }
                }).setNeutralButton("线下面签", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.ic_launcher).setTitle("提示")
                                .setMessage("是否访问手机摄像头")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        MFGT.gotoRecordActivity1(getActivity());
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) { }
                                })
                                .create().show();
                    }
                });
        builder.create().show();
    }

}
