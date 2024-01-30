package com.videocall.globalcall.livecallwithfun.tumsehi;

import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.incoming_counter;
import static com.videocall.globalcall.livecallwithfun.shyame.aakhometeri.native_ShowAds;
import static com.videocall.globalcall.livecallwithfun.surmesham.tumpass.connectInComing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.sdk.IntertitialAdsEvent;
import com.ads.sdk.new_configs.Data_Preference;
import com.videocall.globalcall.livecallwithfun.R;

public class teresamne extends AppCompatActivity {

    LinearLayout videocall, videocall_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_types);

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        incoming_counter++;

        Data_Preference.show_anim_header(this, (RelativeLayout) findViewById(R.id.rl_anim_header));
        IntertitialAdsEvent.ShowInterstitialAdsOnCreate(this);
        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("0")) {
            native_ShowAds(this, findViewById(R.id.native_container), 2);
        }


        videocall = findViewById(R.id.videocall);
        videocall_advice = findViewById(R.id.videocall_advice);

        videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new Data_Preference(teresamne.this).getIncoming_Counter().equalsIgnoreCase(String.valueOf(incoming_counter))) {
                    connectInComing(teresamne.this);
                } else {
                    startActivity(new Intent(teresamne.this, milokefasle.class));
                }
            }
        });


        videocall_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new Data_Preference(teresamne.this).getIncoming_Counter().equalsIgnoreCase(String.valueOf(incoming_counter))) {
                    connectInComing(teresamne.this);
                } else {
                    startActivity(new Intent(teresamne.this, bolakuchna.class));
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        IntertitialAdsEvent.ShowInterstitialAdsOnBack(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("1")) {
            native_ShowAds(this, findViewById(R.id.native_container), 2);
        }
    }
}