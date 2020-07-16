package com.cos.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private ProgressBar pgbDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 컴포넌트 초기화
        // 컴포넌트 다 띄워야하는데 다 적으면 지저분해져서 initComponent()만듬
        initComponent();


        // 다운로드(5초)
        imageDownload();

    }

    private void imageDownload(){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 다운로드 쓰레드 시작");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: 다운로드 쓰레드 종료");
                pgbDownload.setVisibility(View.INVISIBLE);
            }
        }).start();
    }

    private void initComponent(){
        // R이 이미 new 했기 때문에 컴포넌트 찾기만 함.
        pgbDownload = findViewById(R.id.pgb_download);
    }
}