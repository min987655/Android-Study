package com.cos.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TextView tvCount;
    private Button btnStart, btnStop;
    private ICounterService binder;
    private boolean running = true;
    private Handler handler = new Handler();
    private int startCount = 0;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = ICounterService.Stub.asInterface(iBinder); // bindService가 실행되는 순간부터 iBinder가 서비스의 binder가 됨
//            try {
//                Log.d(TAG, "binder 카운트: " + binder.getCount());
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            } // 연결 확인
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            binder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initListener();
    }

    private void initObject(){
        tvCount = findViewById(R.id.tv_count);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
    }

    //BIND_AUTO_CREATE : Component와 연결되어있는 동안 비정상적으로 종료시 자동으로 다시 시작.
    //BIND_DEBUG_UNBIND : 비정상적으로 연결이 끊어지면 로그를 남긴다(디버깅용)
    //BIND_NOT_FORGRAOUND : 백그라운드로만 동작한다. 만약 Activity에서 생성한 경우
    // Activity와 생명주기를 같이 한다.
    private void initListener(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CounterService.class);
                intent.putExtra("startCount", startCount);
                bindService(intent, connection, BIND_AUTO_CREATE); // conn : 서비스와 연결했다는 정보
                new Thread(new Runnable() { // 새로운 쓰레드는 UddI 쓰레드에 접근 못하기 때문에 핸들러 써야 함
                    @Override
                    public void run() {
                        running = true;
                        while (running){
                            if(binder != null){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            tvCount.setText(binder.getCount()+"");
                                            if (binder.getCount() == 20){
                                                running = false;
                                            }
                                        } catch (RemoteException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                try { // Runnable, run 두번 컨텍스트체인지 일어남, 얼마나 걸리는 지 알 수 없음 2번 이상 확인해야 함.
                                    Thread.sleep(500); // 0.5초가 가장 안전. 1초는 통신 오류등의 사유로 어그러질 수 있음.
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startCount = binder.getCount();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                unbindService(connection);
                running = false;
            }
        });
    }
}