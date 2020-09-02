package com.cos.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    
    private ImageView btnPlayStop;
    private SeekBar seekBar;
    private TextView tvTime;

    private MusicService musicService;
    private MediaPlayer mp;

    private int isPlaying = -1; // 1은 음악재생, -1은 음악 멈춤

    private Handler handler = new Handler();
    private Thread uiHandleThread;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            musicService = binder.getService();
            mp = musicService.getMp();
            seekbarInit();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp.stop();
            mp.release(); // 완전히 끔
        }
    };

    private void seekbarInit() {
        seekBar.setMax(mp.getDuration()); // 시크바의 최대 길이 설정
        seekBar.setProgress(0); // 시크바의 위치
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initBinding();
        initListener();
    }

    private void initObject(){

        btnPlayStop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);
    }

    private void initBinding() {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private void initListener(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) { // fromUser : 유저가 클릭하는것에 따라 true, flase
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPlaying = isPlaying * -1;
                if(isPlaying == 1){
                    musicStart();
                }else{
                    musicPause();
                }

                // UI 지속 변경을 위한 Thread 필요.
                // seekBar 그림 그리기
                uiHandleThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isPlaying == 1){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "run: " +mp.getCurrentPosition() );
                                    seekBar.setProgress(mp.getCurrentPosition());
                                    if(mp.getCurrentPosition() >= mp.getDuration()){
                                        musicStop();
                                    }
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    setMusicTime();
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }// end of while
                        try {
                            Thread.sleep(1); // 규칙 ! 스레드 종료를 위한 잠깐의 시간 주기
                            uiHandleThread.interrupt(); // 쓰레드 강제 종료, 쓰레드를 종료하려면 0.001초라도 멈춰야 종료 가능함.
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                uiHandleThread.start();
            }
        });
    }

    private void setMusicTime(){
        int m = mp.getCurrentPosition() / 60000;
        int s = (mp.getCurrentPosition() % 60000) / 1000;
        String strTime = String.format("%02d:%02d", m, s);
        tvTime.setText(strTime);
    }

    private void musicStart(){
        mp.start();
        btnPlayStop.setImageResource(R.drawable.ic_pause);
    }


    private void musicPause(){
        mp.pause();
        btnPlayStop.setImageResource(R.drawable.ic_play);
    }

    // 음악 재생이 끝났을 때 호출하여 사용. // 상태 초기화
    private void musicStop() {
        mp.seekTo(0);
        seekBar.setProgress(0);
        btnPlayStop.setImageResource(R.drawable.ic_play);
        isPlaying = -1;
    }

}