package com.videocall.globalcall.livecallwithfun.shyame;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.ads.sdk.new_configs.Data_Preference;
import com.ads.sdk.splashconnect_act;
import com.videocall.globalcall.livecallwithfun.BuildConfig;
import com.videocall.globalcall.livecallwithfun.R;
import com.videocall.globalcall.livecallwithfun.VC_Terms_Activity;
import com.videocall.globalcall.livecallwithfun.surmesham.hokebhi;
import com.videocall.globalcall.livecallwithfun.tumsehi.haifasle;


public class newkese extends splashconnect_act {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
                return;
            } else {
                loadSplash(BuildConfig.DEBUG, BuildConfig.VERSION_CODE);
            }
        } else {
            loadSplash(BuildConfig.DEBUG, BuildConfig.VERSION_CODE);
        }
    }


    @Override
    public void onComplete() {

        manageVip();

        if (new Data_Preference(this).getKeyTermscreen().equals("1")) {
            if (new hokebhi().isTerms()) {
                startActivity(new Intent(newkese.this, VC_Terms_Activity.class).putExtra("issplash", 1));
            } else {
                startActivity(new Intent(newkese.this, haifasle.class).putExtra("issplash", 1));
            }
        } else {
            startActivity(new Intent(newkese.this, haifasle.class).putExtra("issplash", 1));
        }
        finish();

    }

    private void manageVip() {

        if (new hokebhi().getVipType().equals("in_app_gold")) {
            new Data_Preference(this).redeemGoldPlan();
//            new Data_Preference(this).setAdsFlag(false);
//            new Data_Preference(this).set_IncomingWelcome_Fix();
        } else if (new hokebhi().getVipType().equals("in_app_silver")) {
            new Data_Preference(this).redeemSilverPlan();
        } else if (new hokebhi().getVipType().equals("in_app_bronze")) {
            new Data_Preference(this).redeemBronzePlan();
            SharedPreferences sh = getSharedPreferences("RandomSF", MODE_APPEND);
            int vc_count = sh.getInt("KEY_RAND", 0);
            if (vc_count == 7) {
                new hokebhi().setVipType("null");
                Toast.makeText(this, "Bronze Premium Expire! You reached your call limit.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            switch (requestCode) {
                case 101: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        loadSplash(BuildConfig.DEBUG, BuildConfig.VERSION_CODE);
                    } else {
                        loadSplash(BuildConfig.DEBUG, BuildConfig.VERSION_CODE);
                    }
                    return;
                }
            }
        }
    }


}
