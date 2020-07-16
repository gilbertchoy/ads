package com.adviewer.gc.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class AdmobActivity extends AppCompatActivity {

    private Button loadAdBtn;
    private Button playAdBtn;
    private TextView logTextView;
    private ScrollView scrollView;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admob);
        //getSupportActionBar().hide();
        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);
        logTextView = findViewById(R.id.log1);
        scrollView = findViewById(R.id.scrollview1);


        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Load Ad Button Pressed");
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Play Ad Button Pressed");

            }
        });


    }

    private void log(CharSequence text) {

        if (logTextView.length() > 0)
            logTextView.append("\n");
        logTextView.append(text);

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        }, 500);
    }
}
