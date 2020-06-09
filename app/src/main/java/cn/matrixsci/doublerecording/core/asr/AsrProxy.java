package cn.matrixsci.doublerecording.core.asr;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cn.matrixsci.doublerecording.AppContext;
import cn.matrixsci.doublerecording.core.asr.recog.MyRecognizer;
import cn.matrixsci.doublerecording.core.asr.recog.listener.MessageStatusRecogListener;
import cn.matrixsci.doublerecording.core.asr.util.AutoCheck;

public class AsrProxy {
    
    /**
     * 识别控制器，使用MyRecognizer控制识别的流程
     */
    private MyRecognizer myRecognizer;

    private MessageStatusRecogListener listener;

    private boolean enableOffline = true;

    private static volatile AsrProxy instance;

    public static AsrProxy getInstance(){
        if(instance==null){
            AsrProxy ttsProxy = new AsrProxy();
            ttsProxy.init();
            instance = ttsProxy;
        }
        return instance;
    }

    private void init() {
        listener = new MessageStatusRecogListener(null);
        Context context =  AppContext.getInstance();
        myRecognizer = new MyRecognizer(context, listener);
        if (enableOffline) {
            // 基于DEMO集成1.4 加载离线资源步骤(离线时使用)。offlineParams是固定值，复制到您的代码里即可
            Map<String, Object> offlineParams = new HashMap<>();
            offlineParams.put("grammar","assets:///baidu_speech_grammar.bsg");
            offlineParams.put("decoder",2);

            Log.e("xxxxxxxxxxx",offlineParams.toString());
            Log.e("xxxxxxxxxxx",offlineParams.get("grammar").getClass().getSimpleName());
            myRecognizer.loadOfflineEngine(offlineParams);
        }
    }

    public void start() {
        // DEMO集成步骤2.1 拼接识别参数： 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
//        decoder=2, accept-audio-volume=false
        final Map<String, Object> params = new HashMap<>();
        params.put("decoder",2);
        params.put("accept-audio-volume",false);
        // params 也可以根据文档此处手动修改，参数会以json的格式在界面和logcat日志中打印
        Log.i("AsrProxy", "设置的start输入参数：" + params);
        // 复制此段可以自动检测常规错误
        (new AutoCheck(AppContext.getInstance(), new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainErrorMessage(); // autoCheck.obtainAllMessage();
//                        txtLog.append(message + "\n");
                        Log.i("AsrProxy",message);
                        ; // 可以用下面一行替代，在logcat中查看代码
                        // Log.w("AutoCheckMessage", message);
                    }
                }
            }
        }, enableOffline)).checkAsr(params);

        // 这里打印出params， 填写至您自己的app中，直接调用下面这行代码即可。
        // DEMO集成步骤2.2 开始识别
        myRecognizer.start(params);
    }

    public void setMainHandler(Handler handler){
        listener.setMainHandler(handler);
    }
    
}
