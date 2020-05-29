package cn.matrixsci.doublerecording;

import android.app.Application;

import cn.matrixsci.doublerecording.config.AppConfig;

/**
 * Application
 */
public class AppContext extends Application {
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initAppConfig();
    }

    private void initAppConfig() {
        AppConfig.ins().init();
    }

    public static AppContext getInstance() {
        return instance;
    }

}
