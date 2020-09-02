package com.cos.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etText;
    private Button btnStart, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initListener();
    }

    private void initObject(){
        etText = findViewById(R.id.et_title);
        btnStart = findViewById(R.id.btn_start);
        btnFinish = findViewById(R.id.btn_finish);
    }

    private void initListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musicName = etText.getText().toString();
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("musicName", musicName);
                startService(intent);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });
    }
}