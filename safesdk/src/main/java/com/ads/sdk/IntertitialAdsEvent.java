package com.ads.sdk;

import static com.ads.sdk.new_configs.Data_Preference.app_back_count_click;
import static com.ads.sdk.new_configs.Data_Preference.app_combo_counter;
import static com.ads.sdk.new_configs.Data_Preference.app_count_click;
import static com.ads.sdk.new_configs.Data_Preference.isComboActive;

import android.app.Activity;

import com.ads.sdk.new_adsClass.InterAdLoader;
import com.ads.sdk.new_configs.Data_Preference;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;

public class IntertitialAdsEvent {

    public static void ShowInterstitialAdsOnCreate(Activity activity) {

        if (isComboActive) {
            if (app_combo_counter == Integer.parseInt(new Data_Preference(activity).getComboCount())) {
                callingInter(activity);
                app_combo_counter = 0;
            } else {
                app_combo_counter = app_combo_counter + 1;
            }
        } else {
            callingInter(activity);
        }


    }

    private static void callingInter(Activity activity) {
        InterAdLoader.getManager(activity).showInterAd(activity, new OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                InterAdLoader.getManager(activity).showFacebookAds(activity);
            }
        }, app_count_click);
    }



    public static void ShowInterstitialAdsOnBack(Activity activity) {
        InterAdLoader.getManager(activity).ShowInterBackAd(activity, new OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                activity.finish();
            }
        }, app_back_count_click);
    }
}