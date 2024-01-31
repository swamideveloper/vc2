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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ads.sdk.IntertitialAdsEvent;
import com.ads.sdk.new_configs.Data_Preference;
import com.videocall.globalcall.livecallwithfun.R;


public class najanajane extends AppCompatActivity {

    private AppCompatButton gender_next;
    private LinearLayout ll_male, ll_female;
    private ImageView chk_male, chk_female;
    private boolean checker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

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
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }


        gender_next = findViewById(R.id.gender_next);
        ll_male = findViewById(R.id.ll_male);
        ll_female = findViewById(R.id.ll_female);
        chk_male = findViewById(R.id.chk_male);
        chk_female = findViewById(R.id.chk_female);

        ll_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = true;
                chk_male.setImageResource(R.drawable.check);
                chk_female.setImageResource(R.drawable.uncheck);
            }
        });

        ll_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = true;
                chk_male.setImageResource(R.drawable.uncheck);
                chk_female.setImageResource(R.drawable.check);
            }
        });

        gender_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checker) {
                    if (new Data_Preference(najanajane.this).getIncoming_Counter().equalsIgnoreCase(String.valueOf(incoming_counter))) {
                        connectInComing(najanajane.this);
                    } else {
                        startActivity(new Intent(najanajane.this, kyukyu.class));
                    }
                } else {
                    Toast.makeText(najanajane.this, "select gender first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isVectorShow) {
            ((ImageView) findViewById(R.id.vector_pro)).setVisibility(View.GONE);
        }

        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("1")) {
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }
    }

    @Override
    public void onBackPressed() {
        IntertitialAdsEvent.ShowInterstitialAdsOnBack(this);
    }


}