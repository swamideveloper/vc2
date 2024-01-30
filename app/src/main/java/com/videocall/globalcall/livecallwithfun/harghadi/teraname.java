package com.videocall.globalcall.livecallwithfun.harghadi;

import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.isVectorShow;
import static com.videocall.globalcall.livecallwithfun.shyame.aakhometeri.isNetworkConnected;
import static com.videocall.globalcall.livecallwithfun.shyame.aakhometeri.native_ShowAds;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.sdk.IntertitialAdsEvent;
import com.ads.sdk.new_configs.Data_Preference;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.videocall.globalcall.livecallwithfun.R;
import com.videocall.globalcall.livecallwithfun.khwab.MainActivity;
import com.videocall.globalcall.livecallwithfun.surmesham.hokebhi;

public class teraname extends AppCompatActivity {

    private TextView note;
    private boolean backer = false;
    private LottieAnimationView lottie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);

        if (!isVectorShow) {
            ((ImageView) findViewById(R.id.vector_pro)).setVisibility(View.GONE);
        }

        note = findViewById(R.id.note);
        lottie = findViewById(R.id.lottie);

        Data_Preference.show_anim_header(this, (RelativeLayout) findViewById(R.id.rl_anim_header));
        IntertitialAdsEvent.ShowInterstitialAdsOnCreate(this);
        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("0")) {
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backer = true;
                if (new Data_Preference(teraname.this).getVideo_call().equalsIgnoreCase("1")) {
                    lottie.setAnimation("suc.json");
                    lottie.playAnimation();
                    lottie.loop(true);
                    note.setTextColor(getResources().getColor(R.color.green));
                    note.setText("Video Call Connected!");
                    TextView button = findViewById(R.id.my_button);
                    button.setVisibility(View.VISIBLE);
                    button.setText("JOIN");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (isNetworkConnected(teraname.this)) {
                                Show_Dialog();
                            }
                        }
                    });

                } else {
                    lottie.setAnimation("failed.json");
                    lottie.loop(true);
                    lottie.setRepeatMode(LottieDrawable.RESTART);
                    lottie.playAnimation();
                    note.setTextColor(Color.RED);
                    note.setText("People not found!");
                    TextView button = findViewById(R.id.my_button);
                    button.setVisibility(View.VISIBLE);
                    button.setText("TRY AGAIN");
                    button.setBackground(getResources().getDrawable(R.drawable.ad_exit_three));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isNetworkConnected(teraname.this))
                                startActivity(new Intent(teraname.this, teraname.class));
                            finish();
                        }
                    });
                }
            }
        }, 6000);
    }

    private void Show_Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(teraname.this);
        final View view = getLayoutInflater().inflate(R.layout.dialog_sexual, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txt_con = view.findViewById(R.id.txt_con);
        txt_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                if (new Data_Preference(teraname.this).getKeyInAppMode().equals("1")) {

                    if (new hokebhi().getVipType().equals("in_app_gold") || new hokebhi().getVipType().equals("in_app_silver") || new hokebhi().getVipType().equals("in_app_bronze")) {
                        startActivity(new Intent(teraname.this, kyuhona.class));
                        finish();
                    } else {
                        if (new hokebhi().isFreeTrailActive()) {
                            new hokebhi().setFreeTrail(false);
                            startActivity(new Intent(teraname.this, kyuhona.class));
                            finish();
                        } else {
                            startActivity(new Intent(teraname.this, MainActivity.class));
                        }
                    }

                } else {
                    startActivity(new Intent(teraname.this, kyuhona.class));
                    finish();
                }


            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("1")) {
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }
    }


    @Override
    public void onBackPressed() {
        if (backer) {
            try {
                IntertitialAdsEvent.ShowInterstitialAdsOnBack(this);
            } catch (Exception e) {
                finish();
                e.printStackTrace();
            }
        }
    }
}