package com.cos.androidappv02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cos.androidappv02.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private RecyclerView rvProfile;
    private RecyclerView rvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initProfileAdapter();
        initContentAdapter();
    }

    private void initProfileAdapter() {
        ProfileAdapter adapter = new ProfileAdapter();

        adapter.addItem(new Profile("Your story", R.drawable.propic1));
        adapter.addItem(new Profile("Your story", R.drawable.propic2));
        adapter.addItem(new Profile("Your story", R.drawable.propic3));
        adapter.addItem(new Profile("Your story", R.drawable.propic4));
        adapter.addItem(new Profile("Your story", R.drawable.propic5));
        adapter.addItem(new Profile("Your story", R.drawable.propic1));
        adapter.addItem(new Profile("Your story", R.drawable.propic2));
        adapter.addItem(new Profile("Your story", R.drawable.propic3));

        rvProfile.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rvProfile.scrollToPosition(adapter.getItemCount()-1);
        rvProfile.setAdapter(adapter);
    }

    private void initContentAdapter() {
        ContentAdapter adapter = new ContentAdapter();

        adapter.addItem(new Content(R.drawable.postpic1));
        adapter.addItem(new Content(R.drawable.postpic2));
        adapter.addItem(new Content(R.drawable.postpic3));
        adapter.addItem(new Content(R.drawable.postpic4));
        adapter.addItem(new Content(R.drawable.postpic5));

        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(adapter);
    }

    private void init(){
        rvProfile = findViewById(R.id.rv_profile);
        rvContent = findViewById(R.id.rv_content);

    }

}