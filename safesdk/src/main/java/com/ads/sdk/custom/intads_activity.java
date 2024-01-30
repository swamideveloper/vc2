package com.ads.sdk.custom;

import static com.ads.sdk.new_configs.Data_Config.getPlayStoreUrl;
import static com.ads.sdk.new_configs.Data_Config.openChromeCustomTabUrl;
import static com.ads.sdk.splashconnect_act.customAdData;
import static com.ads.sdk.splashconnect_act.getMyCustomAd;
import static com.ads.sdk.splashconnect_act.getRandomNum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.sdk.R;
import com.ads.sdk.new_configs.Data_Preference;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class intads_activity extends AppCompatActivity {

    public static OnShowAdCompleteListener listner;
    addatacustom customAdModel;
    private String action_str;
    private ImageView CloseBtn;
    private ImageView iv_inter;
    private boolean clicked = false;

    public static void newIntent(Activity activity, OnShowAdCompleteListener listner_for_app) {
        if (new Data_Preference(activity).getCustomAdshowStatus().equals("0") || new Data_Preference(activity).getCustomInter().equals("0")) {
            return;
        }
        listner = listner_for_app;
        activity.startActivity(new Intent(activity, intads_activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_int);
        if (new Data_Preference(this).getCustomAdshowStatus().equals("0") || new Data_Preference(this).getCustomInter().equals("0")) {
            return;
        }
        customAdModel = getMyCustomAd();
        int pos = getRandomNum();
        if (customAdModel != null) {
            try {
                iv_inter = (ImageView) findViewById(R.id.iv_inter);
                CloseBtn = (ImageView) findViewById(R.id.iv_close);
                Glide.with(this).load(customAdData.get(pos).getInterstitialImage()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.img_interstitial).into(iv_inter);
                findViewById(R.id.iv_inter).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        clicked = true;
                        action_str = customAdData.get(pos).getUrl();
                        if (action_str.startsWith("https")) {
                            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getPlayStoreUrl(action_str))));
                        } else {
                            openChromeCustomTabUrl(intads_activity.this, action_str);
                        }
                    }
                });
                CloseBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        closeAd(intads_activity.this, pos);
                    }
                });
            } catch (Exception e) {
                closeAd(intads_activity.this, pos);
            }
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (clicked) {
            clicked = false;
            if (listner != null) {
                listner.onShowAdComplete();
                listner = null;
            }
            finish();
        }
    }

    private void closeAd(Activity activity, int pos) {
        if (new Data_Preference(activity).getCustomCloseAd().equals("1")) {
            clicked = true;
            action_str = customAdData.get(pos).getUrl();
            if (action_str.startsWith("https")) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getPlayStoreUrl(action_str))));
            } else {
                openChromeCustomTabUrl(intads_activity.this, action_str);
            }
        } else {
            if (listner != null) {
                listner.onShowAdComplete();
                listner = null;
            }
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (listner != null) {
            listner.onShowAdComplete();
            listner = null;
        }
        finish();
    }
}