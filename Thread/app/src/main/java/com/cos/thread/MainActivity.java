package com.cos.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private Button btnExecute, btnStop;
    private ProgressBar progressBar;
    private int value = 0; // 진행바의 퍼센트
    private  BackgroundTask task; // 해당 레퍼런스에 접근해야 while 멈출 수 있음(전역으로 뺌)
    private static final String TAG = "Main_Activity";
    private boolean threadStatus = true;

    // < 실행 시 매개변수로 넘길 수 있는 값, doInBackground 응답해줄 value값, onPostExecute post 후 받는 파라메터>
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {

        // 타겟 호출 직전
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            super.onPreExecute();
            progressBar.setProgress(value); // 초기 값 셋팅
            threadStatus = true;
        }

        // 타겟 run() 메서드
        // 값 바로바로 던짐
        @Override
        protected Integer doInBackground(Integer... integers) { // 스레드 실행시 인수 받기
            Log.d(TAG, "doInBackground: ");
            while (value <= 100 && threadStatus) {
                value = value + 5;
                publishProgress(value);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 정상적으로 수행됐으면 1, 안됐으면 -1 (내가 정함)
            return 1;
        }

        // UI스레드에 그림을 그려주는 메서드
        // Integer... = Integer[] : 배열
        @Override
        protected void onProgressUpdate(Integer... values) { //publishProgress 인수 받기
            Log.d(TAG, "onProgressUpdate: ");
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            
        }

        // 타겟 호출 직후
        @Override
        protected void onPostExecute(Integer integer) { // doInBackground 리턴값 받기
            Log.d(TAG, "onPostExecute: ");
            super.onPostExecute(integer);
            Toast.makeText(MainActivity.this, "다운로드 완료", Toast.LENGTH_SHORT).show();
        }


    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 내가 생성한 함수 표시를 위해 m 붙임
        mInit();
        mListener();
    }

    private void mListener() {
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new BackgroundTask(); // 실행 버튼 누를 때 마다 쓰레드 생성
                task.execute();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadStatus = false;
                task.cancel(true); // 스레드를 완전 종료시켜버림.
            }
        });
    }

    private void mInit() {
        btnExecute = findViewById(R.id.btn_execute);
        btnStop = findViewById(R.id.btn_stop);
        progressBar = findViewById(R.id.progressBar);
    }
}