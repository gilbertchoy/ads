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
import android.widget.ScrollView;
import android.widget.TextView;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

import org.w3c.dom.Text;


//adReceived listener causes textview to not auto resize

public class ApplovinActivity extends AppCompatActivity {

    private AppLovinAd loadedAd;
    private Button loadAdBtn;
    private Button playAdBtn;
    private Context context;
    private ScrollView scrollView;
    private AppLovinInterstitialAdDialog interstitialAd;
    private TextView logTextView;
    private TextView test;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_applovin);
        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);
        logTextView = findViewById(R.id.logView);

        context = this;

        AppLovinSdk.initializeSdk(context);

        interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( context ), context );

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logTextView.append("LOAD AD button pressed \n");
                AppLovinSdk.getInstance( context ).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
                {
                    @Override
                    public void adReceived(AppLovinAd ad)
                    {
                        logTextView.append("adreceived \n");
                        Log.d("applovin","berttest adReceived");
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode)
                    {
                        logTextView.append("failedToReceiveAd \n");
                    }
                } );
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logTextView.append("PLAY AD button pressed \n");
                if ( loadedAd != null )
                {
                    interstitialAd.showAndRender( loadedAd );
                }
                else{
                    logTextView.append("failed playing ad \n");
                }
            }
        });


        interstitialAd.setAdDisplayListener( new AppLovinAdDisplayListener()
        {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd)
            {
                logTextView.append("adDisplayed \n");
                Log.d("applovin", "berttest Interstitial Displayed" );
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd)
            {
                logTextView.append("adHidden \n");
                Log.d( "applovin","berttest Interstitial Hidden" );
            }
        } );

        interstitialAd.setAdClickListener( new AppLovinAdClickListener()
        {
            @Override
            public void adClicked(AppLovinAd appLovinAd)
            {
                logTextView.append("adClicked \n");
                Log.d( "applovin","berttest Interstitial Clicked" );
            }
        } );

        // This will only ever be used if you have video ads enabled.
        interstitialAd.setAdVideoPlaybackListener( new AppLovinAdVideoPlaybackListener()
        {
            @Override
            public void videoPlaybackBegan(AppLovinAd appLovinAd)
            {
                logTextView.append("videoPlaybackBegan \n");
                Log.d( "applovin","berttest Video Started" );
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean wasFullyViewed)
            {
                logTextView.append("videoPlaybackEnded \n");
                Log.d( "applovin","berttest applovin Video Ended" );
            }
        } );
    }
}
