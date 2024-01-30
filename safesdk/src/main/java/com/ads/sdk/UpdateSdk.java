package com.ads.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ads.sdk.new_adsClass.AD_native;
import com.ads.sdk.new_adsClass.AppOpenManager;
import com.ads.sdk.new_adsClass.InterAdLoader;
import static com.ads.sdk.new_configs.Data_Preference.app_count_click;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;
import com.ads.sdk.new_configs.Data_Preference;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class UpdateSdk {

    private static UpdateSdk instance;

    public static UpdateSdk getManager() {
        if (instance == null) {
            instance = new UpdateSdk();
        }
        return instance;
    }

    public void initialize(Application mContext, Class<?> splashClass) {
        MobileAds.initialize(mContext.getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AudienceNetworkAds.initialize(mContext);
        new AppOpenManager(mContext, splashClass);
    }

    public static boolean isOnline(Activity activity) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void preLoadAds(Activity activity) {

        if (!new Data_Preference(activity).getAdmobInterstitialID().isEmpty() ) {

            if (!new Data_Preference(activity).getAdmobInterStatus().equals("0")) {
                InterAdLoader.getManager(activity).loadAd(activity,true);
            }

            if (!new Data_Preference(activity).getAdmobInterStatus().equals("0")) {
                InterAdLoader.getManager(activity).loadBackAd(activity);
            }
        }

        if (!new Data_Preference(activity).getAdmobNative().equals("0") &&
                !new Data_Preference(activity).getAdmobNativeID().isEmpty()) {
            AD_native.getInstance(activity).preLoadNativeAds(activity,0);
        }
    }
}
