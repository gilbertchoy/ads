package com.adviewer.gc.ads;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button applovinBtn;
    Button admobBtn;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        applovinBtn = findViewById(R.id.applovin);
        admobBtn = findViewById(R.id.admob);

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
    }


}
