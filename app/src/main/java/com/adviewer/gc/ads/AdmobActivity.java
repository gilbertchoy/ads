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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Button loadAdBtn;
    private Button playAdBtn;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admob);
        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-6760835969070814~5912740615");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6760835969070814/4954882160");

        //ca-app-pub-6760835969070814/4954882160

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
            }
        });

    }

}
