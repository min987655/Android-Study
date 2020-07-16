package com.cos.bottumnavigationex01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Log.d(TAG, "onNavigationItemSelected: " + item.getItemId());
                Log.d(TAG, "onNavigationItemSelected: R값 : " + R.id.nav_search);
                switch (item.getItemId()) {
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag1()).commit();
                        break;
                    case R.id.nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag2()).commit();
                        break;
                    case R.id.nav_navigation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag3()).commit();
                        break;
                }
                return true;
            }
        });
    }
}