package com.cos.activityex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";
    private Button btnFinishSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnFinishSub = findViewById(R.id.btn_finish_sub);
        btnFinishSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // this 생략되어 있음. SubActivity Task 날림.
            }
        });

//      new하면 새로 생성, get 하여 메인에 있는 intent에 접근 함
        Intent intent = getIntent();
//      키값으로 데이터 받음.
        String name = intent.getStringExtra("name");
//      Serializabl의 자식 타입으로 다운캐스팅
        User user = (User) intent.getSerializableExtra("user");
        Log.d(TAG, "onCreate: name "+name);
        Log.d(TAG, "onCreate: name "+user.getUsername());
        Log.d(TAG, "onCreate: name "+user.getPassword());
    }
}