package com.cos.toastsnackbarex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cos.toastsnackbarex01.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this;
    private ConstraintLayout mainView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        mainView = findViewById(R.id.main_view);
        Test.callToast(mContext);

        Log.d(TAG, "onCreate:"+mainView.getContext());
        Log.d(TAG, "onCreate:"+tv.getContext());
        Snackbar.make(tv, "스낵바", Snackbar.LENGTH_LONG).show();
    }
}