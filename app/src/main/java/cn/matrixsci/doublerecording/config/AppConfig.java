package cn.matrixsci.doublerecording.config;

public class AppConfig {
    public static boolean isPublish = false;
    public static final String TAG = "DoubleRecording";
    private static AppConfig self = null;
    public static boolean mIsInit = false;

    public static synchronized AppConfig ins() {
        if (self == null) {
            self = new AppConfig();
        }
        return self;
    }

    public void init() {
        synchronized (this) {
            if (!mIsInit) {
                // 在这里做app依赖库的初始化
                // eg xxxx.getInstance().init(AppContext.getInstance());
            }
            mIsInit = true;
        }
    }
}
