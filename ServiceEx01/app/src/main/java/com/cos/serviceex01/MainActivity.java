package com.cos.serviceex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private EditText etText;
    private Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listener();
    }

    public void init() {
        etText = findViewById(R.id.et_text);
        btnStart = findViewById(R.id.btn_start);
    }

    public void listener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etText.getText().toString();

                // 인텐트 객체 만들고 부가 데이터 넣기
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                // 서비스쪽으로 전달한 인텐트 객체의 데이터가 어떤 목적으로 사용되는지 구별하는 용도
                intent.putExtra("command","show");
                // 입력상자에서 가져온 문자열 전달 용
                intent.putExtra("name",name);

                // 객체 담아서 MyService 클래스의 onStartCommand() 메서드로 전달
                startService(intent);
            }
        });

        // 액티비티가 새로 만들어질 때 전달된 인텐트 처리
        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        // 액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리하기기
       processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "command : " + command + ", name : " + name +", ", Toast.LENGTH_LONG).show();
        }
    }
}
