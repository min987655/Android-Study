package com.cos.serviceex01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        // 인텐트 객체가 null이 아니면 processCommand() 메서드 호출
        if (intent == null) {
            return Service.START_STICKY; // 서비스 비정상 종료. 시스템 자동 재시작
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        // 인텐트에서 부가데이터 가져오기
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "command: " + command + ", name : " + name);

        for (int i = 0; i <5; i++){
            try {
                Thread.sleep(1000);
            } catch (Exception e) {};
            Log.d(TAG, "Waiting: " + i + "seconds.");
        }

        // 인텐트에 플래그 추가
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | //새로운 Task 생성(화면이 없는 서비스에서 화면이 있는 액티비티를 띄우기 위해)
                            Intent.FLAG_ACTIVITY_SINGLE_TOP | // MainActivity 객체가 이미 메모리에 만들어져 있을 때 재사용
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);

        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + "from service.");
        startActivity(showIntent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
