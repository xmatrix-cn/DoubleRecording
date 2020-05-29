package cn.matrixsci.doublerecording.utils;

import android.widget.Toast;

import cn.matrixsci.doublerecording.AppContext;

public class CommonUtils {
    public static void showLongToast( String pMsg) {
        Toast.makeText(AppContext.getInstance(), pMsg, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(String pMsg) {
        Toast.makeText(AppContext.getInstance(), pMsg, Toast.LENGTH_SHORT).show();
    }
}
