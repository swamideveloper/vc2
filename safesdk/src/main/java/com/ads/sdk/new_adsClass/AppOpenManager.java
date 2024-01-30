package com.ads.sdk.new_adsClass;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.ads.sdk.new_configs.Data_Config;
import com.ads.sdk.new_configs.Data_Preference;


public class AppOpenManager implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
    private Activity currentActivity;
    private Class<?> splashClass;
    private static final String TAG = "AppOpenManager";

    AppOpenLoader appOpenLoader;

    public AppOpenManager(Application application, Class<?> aClass) {
        splashClass = aClass;
        application.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenLoader = new AppOpenLoader();
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);

        if (Data_Config.isNetworkAvailable(currentActivity) && new Data_Preference(currentActivity).getAdsFlag() && !new Data_Preference(currentActivity).getAdmobAppOpenID().isEmpty() &&!currentActivity.getClass().getSimpleName().equals("Activity_ScanQRCode")) {
            appOpenLoader.showAdIfAvailable(currentActivity, splashClass);
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onDestroy(owner);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

}
