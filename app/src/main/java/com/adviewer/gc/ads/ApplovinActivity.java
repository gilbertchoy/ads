package com.adviewer.gc.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class ApplovinActivity extends AppCompatActivity {

    private AppLovinAd loadedAd;
    private Button loadAdBtn;
    private Button playAdBtn;
    private Context context = this;
    private AppLovinInterstitialAdDialog interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applovin);

        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);

        AppLovinSdk.initializeSdk(this);

        interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );


        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppLovinSdk.getInstance( context ).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
                {
                    @Override
                    public void adReceived(AppLovinAd ad)
                    {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode)
                    {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                } );
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( loadedAd != null )
                {
                    interstitialAd.showAndRender( loadedAd );
                }
            }
        });


        interstitialAd.setAdDisplayListener( new AppLovinAdDisplayListener()
        {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd)
            {
                Log.d("applovin", "Interstitial Displayed" );
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd)
            {
                Log.d( "applovin","Interstitial Hidden" );
            }
        } );

        interstitialAd.setAdClickListener( new AppLovinAdClickListener()
        {
            @Override
            public void adClicked(AppLovinAd appLovinAd)
            {
                Log.d( "applovin","Interstitial Clicked" );
            }
        } );

        // This will only ever be used if you have video ads enabled.
        interstitialAd.setAdVideoPlaybackListener( new AppLovinAdVideoPlaybackListener()
        {
            @Override
            public void videoPlaybackBegan(AppLovinAd appLovinAd)
            {
                Log.d( "applovin","Video Started" );
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean wasFullyViewed)
            {
                Log.d( "applovin","applovin Video Ended" );
            }
        } );
    }



}
