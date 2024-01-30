package com.ads.sdk.new_adsClass;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.ads.sdk.new_configs.Data_Preference;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class AppOpenLoader {

    private AppOpenAd appOpenAd = null;
    private boolean isLoadingAd = false;
    public static boolean isShowingAd = false;

    private long loadTime = 0;

    public AppOpenLoader() {
    }

    public void loadSplashAd(Activity activity, OnShowAdCompleteListener listener) {
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(activity, new Data_Preference(activity).getAdmobAppOpenID(), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        listener.onShowAdComplete();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        listener.onShowAdComplete();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                    }
                });
                ad.show(activity);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                listener.onShowAdComplete();
            }
        });
    }

    public void loadAd(Activity context) {
        if (isLoadingAd || isAdAvailable()) {
            return;
        }

        isLoadingAd = true;
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(context, new Data_Preference(context).getAdmobAppOpenID(), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                new AppOpenAd.AppOpenAdLoadCallback() {

                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        isLoadingAd = false;
                        appOpenAd = ad;
                        loadTime = (new Date()).getTime();
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        isLoadingAd = false;
                        appOpenAd = null;
                    }
                });
    }

    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    private boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    public void showAdIfAvailable(@NonNull Activity activity, @NonNull Class<?> splashClass) {
        if (!isAdAvailable()) {
            loadAd(activity);
            return;
        }

        if (activity.getClass().equals(splashClass)) {
            return;
        }

        if (isShowingAd || InterAdLoader.isShowingAd || InterAdLoader.isAppOpenShowingAd) {
            return;
        }

        appOpenAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        isShowingAd = false;
                        appOpenAd = null;
                        loadAd(activity);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        isShowingAd = false;
                        appOpenAd = null;
                        loadAd(activity);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                    }
                });
        isShowingAd = true;
        appOpenAd.show(activity);
    }

}
