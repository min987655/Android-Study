package com.cos.counterapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CounterService extends Service {

    private static final String TAG = "CounterService";
    private int count;
    private int startCount;
    private boolean isStop = false;

    ICounterService.Stub binder = new ICounterService.Stub() {
        @Override
        public int getCount() throws RemoteException {
            return count;
        }
    };

    public CounterService() {
        Log.d(TAG, "CounterService: 생성자 실행 됨");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: 서비스 시작");

        Thread counterThread = new Thread(new Counter());
        counterThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        startCount = intent.getIntExtra("startCount",startCount);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: count : " + count);
        isStop = true;
        return super.onUnbind(intent);
    }

    class Counter implements Runnable {
        @Override
        public void run() {

            for (count=startCount; count<20; count++){

                if (isStop){
                    break;
                }

                try {
                    Thread.sleep(1000);
                    Log.d(TAG, "run: count : " + count);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }
}
