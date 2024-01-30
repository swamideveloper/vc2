package com.videocall.globalcall.livecallwithfun.tumsehi;

import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.incoming_counter;
import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.isVectorShow;
import static com.videocall.globalcall.livecallwithfun.shyame.aakhometeri.native_ShowAds;
import static com.videocall.globalcall.livecallwithfun.surmesham.tumpass.connectInComing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ads.sdk.IntertitialAdsEvent;
import com.ads.sdk.new_configs.Data_Preference;
import com.videocall.globalcall.livecallwithfun.R;

public class kyukyu extends AppCompatActivity {

    private AppCompatButton age_next;
    private RadioButton r1, r2, r3;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

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
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }


        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        radioGroup = findViewById(R.id.radioGroup);
        age_next = findViewById(R.id.age_next);

        age_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new Data_Preference(kyukyu.this).getIncoming_Counter().equalsIgnoreCase(String.valueOf(incoming_counter))) {
                    connectInComing(kyukyu.this);
                } else {
                    startActivity(new Intent(kyukyu.this, teresamne.class));
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
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }
    }
}