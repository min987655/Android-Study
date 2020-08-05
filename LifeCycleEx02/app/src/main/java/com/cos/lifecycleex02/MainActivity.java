package com.cos.lifecycleex02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    // 내 액티비티 컨텍스트를 전역으로 띄워 놓음(안띄워두면 스코프때문에 찾기 힘듬)
    private Context mContext = MainActivity.this;
    private Button btnNum, btnEmail;
    private TextView tvNum, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNum = findViewById(R.id.btn_num);
        btnEmail = findViewById(R.id.btn_email);
        tvNum = findViewById(R.id.tv_num);
        tvEmail = findViewById(R.id.tv_email);

        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SubActivity.class);
                //startActivity(intent); 그냥 이동
                startActivityForResult(intent, 1); // 1은 MainActivity로 이동하는 코드 : Enum으로 남겨 놓으면 어디서 요청했는지 알 수 있음.
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SubActivity2.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    // 콜백 함수, 새로 띄운 화면이 꺼질 때 바로 콜백 됨.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: 콜백 받음");

        if(requestCode == 1) { // 내가 요청했는지 확인
            if(resultCode == 10){ // 인증번호 응답 받음
                tvNum.setText(data.getStringExtra("number"));
            } else if(resultCode == 20){
                tvEmail.setText(data.getStringExtra("email"));
            }
        }
    }
}