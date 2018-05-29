package com.adviewer.gc.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
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

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-6760835969070814~5912740615");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6760835969070814/4954882160");

        //ca-app-pub-6760835969070814/4954882160

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Load Ad Button Pressed");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Play Ad Button Pressed");
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    log("Ad not loaded yet");
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                log("onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                log("onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                log("onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                log("onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                log("onAdClosed");
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
