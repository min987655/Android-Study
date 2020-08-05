package com.cos.mvvmex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ROOM 연결
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), // 모든 액티비티에서 사용해야하기 때문에 getApplicationContext() 사용
                AppDatabase.class, "db-cos")
                .fallbackToDestructiveMigration() // 스키마의 버전 변경 가능. 처음 배포할 때는 지우고, 앱 변경 - 후속 배포시 걸어 놓음
                .allowMainThreadQueries() // 메인쓰레드에서 DB에 IO를 가능하게 해줌 : 테스트용
                .build();

        userRepository = db.userRepository();

        User user = new User("MIN", "K");
        userRepository.insert(user);
        Log.d(TAG, "onCreate: 데이터가 잘 저장되었습니다.");

        List<User> users = userRepository.findAll();
        Log.d(TAG, "onCreate: findAll() : " + users);

        User userEntity = userRepository.findById(1);
        Log.d(TAG, "onCreate: findById(1) : " + userEntity);
    }
}