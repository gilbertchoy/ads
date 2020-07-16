package com.adviewer.gc.ads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    Button applovinBtn;
    Button admobBtn;
    //Button mopubBtn;
    Button vungleBtn;
    TextView privacyTV;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //getSupportActionBar().hide();
        applovinBtn = findViewById(R.id.applovin);
        admobBtn = findViewById(R.id.admob);
        //mopubBtn = findViewById(R.id.mopub);
        vungleBtn = findViewById(R.id.vungle);
        privacyTV = findViewById(R.id.textprivacy);

        applovinBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(context, ApplovinActivity.class);
                FirstActivity.this.startActivity(activityChangeIntent);
            }
        });

        admobBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(context, AdmobActivity.class);
                FirstActivity.this.startActivity(activityChangeIntent);
            }
        });

        vungleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(context, VungleActivity.class);
                FirstActivity.this.startActivity(activityChangeIntent);
            }
        });

        privacyTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Uri uriUrl = Uri.parse("https://scratcherserver.herokuapp.com/privacypolicyadwatcher/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }


}
