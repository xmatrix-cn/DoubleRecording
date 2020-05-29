package cn.matrixsci.doublerecording.common;

import android.app.Activity;
import android.content.Intent;
import android.provider.SyncStateContract;

import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.module.account.LoginActivity;
import cn.matrixsci.doublerecording.app.module.account.RegiseterActivity;
import cn.matrixsci.doublerecording.app.module.launch.GuideActivity;
import cn.matrixsci.doublerecording.app.module.main.MainActivity;
import cn.matrixsci.doublerecording.app.module.record.RecordActivity1;
import cn.matrixsci.doublerecording.app.module.record.RecordActivity2;
import cn.matrixsci.doublerecording.app.module.record.RecordActivity3;
import cn.matrixsci.doublerecording.app.module.record.RecordActivity4;
import cn.matrixsci.doublerecording.app.module.record.RecordActivity5;
import cn.matrixsci.doublerecording.app.module.record.RecordActivity6;
import cn.matrixsci.doublerecording.app.module.record.RecordActivityFinish;
import cn.matrixsci.doublerecording.utils.ExitAppUtils;

public class MFGT {
    /**
     * 右侧动画关闭 Activity
     *
     * @param activity
     */
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }

    /**
     * 底部动画关闭 Activity
     *
     * @param activity
     */
    public static void finishFormBottom(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_bottom_in,
                R.anim.push_bottom_out);
    }

    /**
     * 跳转主界面
     */
    public static void gotoMainActivity(Activity context) {
        startActivity(context, MainActivity.class);
    }

    /**
     * 跳转登陆界面
     */
    public static void gotoLogin(Activity context) {
        startActivity(context, LoginActivity.class);
    }

    /**
     * 跳转注册界面
     */
    public static void gotoRegister(Activity context) {
        startActivity(context, RegiseterActivity.class);
    }

    public static void gotoRecordActivity1(Activity context) {
        startActivity(context, RecordActivity1.class);
    }

    public static void gotoRecordActivity2(Activity context) {
        startActivity(context, RecordActivity2.class);
    }

    public static void gotoRecordActivity3(Activity context) {
        startActivity(context, RecordActivity3.class);
    }

    public static void gotoRecordActivity4(Activity context) {
        startActivity(context, RecordActivity4.class);
    }

    public static void gotoRecordActivity5(Activity context) {
        startActivity(context, RecordActivity5.class);
    }

    public static void gotoRecordActivity6(Activity context) {
        startActivity(context, RecordActivity6.class);
    }

    public static void gotoRecordActivityFinish(Activity context) {
        startActivity(context, RecordActivityFinish.class);
    }

    public static void gotoGuide(final Activity context) {
        //异步
        context.runOnUiThread(new Runnable() {
            public void run() {
                ExitAppUtils.getInstance().exit();
                Intent it = new Intent(context, GuideActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            }
        });
    }

    public static void startActivity(Activity context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

}
