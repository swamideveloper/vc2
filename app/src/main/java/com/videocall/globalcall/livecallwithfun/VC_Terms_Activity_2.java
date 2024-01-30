package com.videocall.globalcall.livecallwithfun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.sdk.IntertitialAdsEvent;
import com.videocall.globalcall.livecallwithfun.tumsehi.haifasle;

public class VC_Terms_Activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy2);

        IntertitialAdsEvent.ShowInterstitialAdsOnCreate(this);

        findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VC_Terms_Activity_2.this, haifasle.class).putExtra("issplash", 2));
                finish();
            }
        });

        findViewById(R.id.tv_decline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
    }
}