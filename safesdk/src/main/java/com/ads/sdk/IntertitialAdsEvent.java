package com.ads.sdk;

import static com.ads.sdk.new_configs.Data_Preference.app_back_count_click;
import static com.ads.sdk.new_configs.Data_Preference.app_count_click;

import android.app.Activity;

import com.ads.sdk.new_adsClass.InterAdLoader;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;

public class IntertitialAdsEvent {

    public static void ShowInterstitialAdsOnCreate(Activity activity) {
        InterAdLoader.getManager(activity).showInterAd(activity, new OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                InterAdLoader.getManager(activity).showFacebookAds(activity);
            }
        }, app_count_click);


    }

    public static void ShowInterstitialAdsOnCreate_1stTime(Activity activity) {
        InterAdLoader.getManager(activity).showInterAd(activity, new OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
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