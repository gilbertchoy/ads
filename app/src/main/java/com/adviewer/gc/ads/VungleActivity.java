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
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.VungleInitListener;
import com.vungle.publisher.VunglePub;

public class VungleActivity extends AppCompatActivity {
    private Button loadAdBtn;
    private Button playAdBtn;
    private TextView logTextView;
    private ScrollView scrollView;
    private Context context = this;
    private final VunglePub vunglePub = VunglePub.getInstance();
    private AdConfig globalAdConfig;
    private final String app_id = "5a91fbcce1b3413c03002403";
    private final String placementID1 = "DEFAULT-4820184";
    private final String placementID2 = "INT-2079169";
    private String LOG_TAG = "berttest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vungle);
        //getSupportActionBar().hide();
        loadAdBtn = findViewById(R.id.loadAd);
        playAdBtn = findViewById(R.id.playAd);
        logTextView = findViewById(R.id.log);
        scrollView = findViewById(R.id.scrollview);

        vunglePub.init(this, app_id, new String[] { placementID1, placementID2 }, new VungleInitListener() {

            @Override
            public void onSuccess() {
                globalAdConfig = vunglePub.getGlobalAdConfig();
                globalAdConfig.setSoundEnabled(true);
                log("Init onSuccess");
                Log.d(LOG_TAG,"berttest onSuccess");
            }
            @Override
            public void onFailure(Throwable e){
                log("Init onFailure:" + e.toString());
            }
        });

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Load Ad button pressed");
                vunglePub.loadAd(placementID1);
                vunglePub.clearAndSetEventListeners(vungleListener);
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Play Ad button pressed");
                if (vunglePub.isAdPlayable(placementID1)) {
                    vunglePub.playAd(placementID1, globalAdConfig);
                }
            }
        });
        vunglePub.clearAndSetEventListeners(vungleListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        vunglePub.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vunglePub.onResume();
    }

    private final VungleAdEventListener vungleListener = new VungleAdEventListener(){

        @Override
        public void onAdEnd(String placementReferenceId, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            // Called when user exits the ad and control is returned to your application
            // if wasSuccessfulView is true, the user watched the ad and should be rewarded
            // (if this was a rewarded ad).
            // if wasCallToActionClicked is true, the user clicked the call to action
            // button in the ad.
            Log.d(LOG_TAG,"berttest onAdEnd");
            log("onAdEnd");

        }

        @Override
        public void onAdStart(String placementReferenceId) {
            // Called before playing an ad
            Log.d(LOG_TAG,"berttest onAdStart");
            log("onAdStart");
        }

        @Override
        public void onUnableToPlayAd(String placementReferenceId, String reason) {
            // Called after playAd(placementId, adConfig) is unable to play the ad
            Log.d(LOG_TAG,"berttest onUnableToPlayAd");
            log("onUnableToPlayAd");
        }

        @Override
        public void onAdAvailabilityUpdate(String placementReferenceId, boolean isAdAvailable) {
            // Notifies ad availability for the indicated placement
            // There can be duplicate notifications
            Log.d(LOG_TAG,"berttest onAdAvailabilityUpdate");
            log("onAdAvailabilityUpdate  isAdAvailable:" + isAdAvailable);
        }
    };

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

    @Override
    public void onDestroy() {
        vunglePub.clearEventListeners();
        super.onDestroy();
    };
}
