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

import com.google.android.gms.ads.AdRequest;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class MopubActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    private Button loadAdBtn;
    private Button playAdBtn;
    private MoPubInterstitial mInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub);
        getSupportActionBar().hide();
        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);

        mInterstitial = new MoPubInterstitial(this, "c8c806410feb48a7b1795e43a10f949c");
        mInterstitial.setInterstitialAdListener(this);

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mInterstitial.load();
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mInterstitial.isReady()) {
                    mInterstitial.show();
                } else {
                    // Caching is likely already in progress if `isReady()` is false.
                    // Avoid calling `load()` here and instead rely on the callbacks as suggested below.
                }
            }
        });


    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {

    }

    @Override
    protected void onDestroy() {
        mInterstitial.destroy();
        super.onDestroy();
    }
}
