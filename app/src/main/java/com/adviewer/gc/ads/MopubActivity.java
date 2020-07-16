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

import com.google.android.gms.ads.AdRequest;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class MopubActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    private Button loadAdBtn;
    private Button playAdBtn;
    private TextView logTextView;
    private ScrollView scrollView;
    private MoPubInterstitial mInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub);
        //getSupportActionBar().hide();
        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);
        logTextView = findViewById(R.id.log);
        scrollView = findViewById(R.id.scrollview);

        mInterstitial = new MoPubInterstitial(this, "c8c806410feb48a7b1795e43a10f949c");
        mInterstitial.setInterstitialAdListener(this);

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("LOAD AD button pressed");
                mInterstitial.load();
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("PLAY AD button pressed");
                if (mInterstitial.isReady()) {
                    mInterstitial.show();
                } else {
                    log("Failed to play Ad");
                }
            }
        });


    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        log("onInterstitialLoaded");
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        log("onInterstitialFailed");
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        log("onInterstitialShown");
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        log("onInterstitialClicked");
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        log("onInterstitialDismissed");
    }

    @Override
    protected void onDestroy() {
        mInterstitial.destroy();
        super.onDestroy();
    }

    private void log(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run(){
                if (logTextView.length() > 0)
                    logTextView.append("\n");
                logTextView.append(text);
            }
        });

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        }, 500);
    }
}
