package com.cos.lifecycleex01;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private String downloadData = "";
    private String  currentState = "";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

        currentState = "에너지 50";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        Log.d(TAG, "onResume: 현재 에너지는 : " +currentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        //getSharedPreferences 접근하면서 키값을 만듬, mode : 0
        SharedPreferences pref = getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("currentState", currentState);
        editor.commit(); // 하드디스크에 영구히 기록
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor = pref.edit();
        currentState = pref.getString("currentState", ""); // 키의 값이 없을 때 디폴트 밸뉴 적어줘야 함.
        Log.d(TAG, "onCreate: 현재 에너지 : "+currentState);
        
        // 다운로드 시작(IO / UI 쓰레드가 다운로드하게 하면 안됨 / 새로운 쓰레드 생성)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=1; i<4; i++){
                        Thread.sleep(1000);
                        Log.d(TAG, "run: "+i+"초");
                    }
                    downloadData = "다운받은 데이터";
                    Log.d(TAG, "run: 다운로드 종료");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.d(TAG, "onCreate: ");
    }
}