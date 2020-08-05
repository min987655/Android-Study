package com.cos.lifecycleex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private Button btnNum;
    private EditText etNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnNum = findViewById(R.id.btn_num);
        etNum = findViewById(R.id.et_num);
        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("number", etNum.getText().toString()); // etNum의 값을 담음
                setResult(10, intent); // 스테이터스코드와 데이터 넘김
                finish();
            }
        });
    }
}