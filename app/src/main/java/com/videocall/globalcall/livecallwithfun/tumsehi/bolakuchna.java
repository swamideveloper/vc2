package com.videocall.globalcall.livecallwithfun.tumsehi;

import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.incoming_counter;
import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.isVectorShow;
import static com.videocall.globalcall.livecallwithfun.shyame.aakhometeri.native_ShowAds;
import static com.videocall.globalcall.livecallwithfun.surmesham.tumpass.connectInComing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.sdk.IntertitialAdsEvent;
import com.ads.sdk.new_configs.Data_Preference;
import com.videocall.globalcall.livecallwithfun.R;
import com.videocall.globalcall.livecallwithfun.harghadi.teraname;

public class bolakuchna extends AppCompatActivity {

    LinearLayout guide_1, guide_2, guide_3, guide_4, guide_5, guide_6, guide_7, guide_8, guide_9, guide_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (!isVectorShow) {
            ((ImageView) findViewById(R.id.vector_pro)).setVisibility(View.GONE);
        }

        incoming_counter++;

        Data_Preference.show_anim_header(this, (RelativeLayout) findViewById(R.id.rl_anim_header));
        IntertitialAdsEvent.ShowInterstitialAdsOnCreate(this);
        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("0")) {
            native_ShowAds(this, findViewById(R.id.native_container), 2);
        }

        init();

        guide_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bolakuchna.this, teraname.class));
            }
        });

        guide_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(1);
            }
        });
        guide_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(2);
            }
        });
        guide_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(3);
            }
        });
        guide_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(4);
            }
        });
        guide_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(5);
            }
        });
        guide_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(6);
            }
        });
        guide_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(7);
            }
        });
        guide_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(8);
            }
        });
    }

    public void init() {
        guide_1 = findViewById(R.id.guide_1);
        guide_2 = findViewById(R.id.guide_2);
        guide_3 = findViewById(R.id.guide_3);
        guide_4 = findViewById(R.id.guide_4);
        guide_5 = findViewById(R.id.guide_5);
        guide_6 = findViewById(R.id.guide_6);
        guide_7 = findViewById(R.id.guide_7);
        guide_8 = findViewById(R.id.guide_8);
        guide_9 = findViewById(R.id.guide_1);
        guide_0 = findViewById(R.id.guide_0);
    }

    public void next(int count) {
        if (new Data_Preference(bolakuchna.this).getIncoming_Counter().equalsIgnoreCase(String.valueOf(incoming_counter))) {
            connectInComing(bolakuchna.this);
        } else {
            Intent intent = new Intent(bolakuchna.this, hamtogile.class);
            intent.putExtra("counter", count);
            startActivity(intent);
        }
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