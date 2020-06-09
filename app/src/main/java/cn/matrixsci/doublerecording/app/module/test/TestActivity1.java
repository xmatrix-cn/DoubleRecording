package cn.matrixsci.doublerecording.app.module.test;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;

public class TestActivity1 extends BaseActivity {

    /**
     * Called when the activity is first created.
     */
    private SeekBar skb_audio = null;
    private Button btn_start_audio = null;
    private Button btn_stop_audio = null;

    private SeekBar skb_video = null;
    private Button btn_start_video = null;
    private Button btn_stop_video = null;
    private Button btn_set_cover = null;
    private Button btn_clear_cover = null;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private MediaPlayer m = null;
    private Timer mTimer;
    private TimerTask mTimerTask;

    private boolean isChanging = false;//互斥变量，防止定时器与SeekBar拖动时进度冲突

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test1);
        super.onCreate(savedInstanceState);
        //----------Media控件设置---------//
        m = new MediaPlayer();
        //播放结束之后弹出提示
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                Toast.makeText(TestActivity1.this, "结束", Toast.LENGTH_LONG).show();
                m.release();
            }
        });

        btn_start_audio = (Button) this.findViewById(R.id.Button01);
        btn_stop_audio = (Button) this.findViewById(R.id.Button02);
        btn_start_audio.setOnClickListener(new ClickEvent());
        btn_stop_audio.setOnClickListener(new ClickEvent());
        skb_audio = (SeekBar) this.findViewById(R.id.SeekBar01);
        skb_audio.setOnSeekBarChangeListener(new SeekBarChangeEvent());

        btn_start_video = (Button) this.findViewById(R.id.Button03);
        btn_stop_video = (Button) this.findViewById(R.id.Button04);
        btn_set_cover = (Button) this.findViewById(R.id.Button05);
        btn_clear_cover = (Button) this.findViewById(R.id.Button06);
        btn_start_video.setOnClickListener(new ClickEvent());
        btn_stop_video.setOnClickListener(new ClickEvent());
        btn_set_cover.setOnClickListener(new ClickEvent());
        btn_clear_cover.setOnClickListener(new ClickEvent());
        skb_video = (SeekBar) this.findViewById(R.id.SeekBar02);
        skb_video.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        surfaceView = (SurfaceView) findViewById(R.id.SurfaceView01);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setFixedSize(100, 100);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
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

    @Override
    protected void onResume() {
        super.onResume();

        //----------定时器记录播放进度---------//
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (isChanging == true)
                    return;
//                Log.i("xxxxxx","xxxxx");
                if (m.isPlaying()) {
//                    Log.i("current:",m.getCurrentPosition()+"");
                    if (m.getVideoHeight() == 0)
                        skb_audio.setProgress(m.getCurrentPosition());
                    else
                        skb_video.setProgress(m.getCurrentPosition());
                }
            }
        };

        mTimer.schedule(mTimerTask, 0, 10);
    }

    /*
     * 按键事件处理
     */
    class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == btn_start_audio) {
                m.reset();//恢复到未初始化的状态
                m = MediaPlayer.create(TestActivity1.this, R.raw.testaudio1);//读取音频
                skb_audio.setMax(m.getDuration());//设置SeekBar的长度
                try {
                    m.prepare(); //准备
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                m.start(); //播放
            } else if (v == btn_stop_audio || v == btn_stop_video) {
                m.stop();
            } else if (v == btn_start_video) {
                m.reset();//恢复到未初始化的状态
                m = MediaPlayer.create(TestActivity1.this, R.raw.testvideo1);//读取视频
                skb_video.setMax(m.getDuration());//设置SeekBar的长度
                m.setAudioStreamType(AudioManager.STREAM_MUSIC);
                m.setDisplay(surfaceHolder);//设置屏幕

                try {
                    m.prepare();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.start();
            }else if(v==btn_set_cover){
                View cover = TestActivity1.this.findViewById(R.id.cover_view);
                cover.setVisibility(View.VISIBLE);
            }else if(v==btn_clear_cover){
                View cover = TestActivity1.this.findViewById(R.id.cover_view);
                cover.setVisibility(View.INVISIBLE);
            }
        }
    }

    /*
     * SeekBar进度改变事件
     */
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanging = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            m.seekTo(seekBar.getProgress());
            isChanging = false;
        }
    }
}
