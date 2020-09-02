package com.cos.musicplayerapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private final IBinder binder = new LocalBinder();
    private MediaPlayer mp;

    // 내부클래스에 익스텐즈하여 서비스에 레퍼런스 자체를 넘김(AIDL 사용 X)
    class LocalBinder extends Binder {
        MusicService getService() {
            return MusicService.this; // AIDL 과 비슷
        }
    }

    // mp 객체를 넘기는 함수
    public MediaPlayer getMp() {
        return mp;
    }

    // binderService(), startService() => 서비스 실행될 때 mp 객체 생성
    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sample1);
//        mp.start();
//        mp.getDuration(); // 음악의 총 길이 알 수 있음
//        mp.getCurrentPosition(); // 음악이 어디까지 흘렀는지 알 수 있음
//        mp.seekTo(1); // 음악의 시간대를 이동시킬 수 있음. 1 : 1초대로 이동.(원하는 위치로 이동)
    }

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }
}
