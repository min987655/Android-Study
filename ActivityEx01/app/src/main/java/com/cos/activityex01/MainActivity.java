package com.cos.activityex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMoveSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveSub = findViewById(R.id.btn_move_sub);
        btnMoveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              btn_move_sub 버튼 click 시 화면 전환
//              (현재 내가 있는 페이지.this, 내가 이동할 페이지.class)
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
//              putExtra 키=밸뉴(해쉬맵) 담음, 오브젝트는 못 담음
                intent.putExtra("name", "코스");
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", new User(1, "ssar", "1234"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}