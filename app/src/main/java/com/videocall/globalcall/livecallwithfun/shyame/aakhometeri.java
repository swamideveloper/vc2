package com.videocall.globalcall.livecallwithfun.shyame;

import static com.ads.sdk.new_configs.Data_Preference.app_count_click;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ads.sdk.UpdateSdk;
import com.ads.sdk.new_adsClass.AD_native;
import com.videocall.globalcall.livecallwithfun.R;
import com.videocall.globalcall.livecallwithfun.surmesham.hokebhi;

import java.util.Date;

public class aakhometeri extends Application {
    private static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    private static Context context = null;
    private static aakhometeri instance_app_data = null;

    public static final String CHANEL_ID = "Video Call";


    private aakhometeri mInstance;

    private void createNotificationChangel() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANEL_ID, getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT));
        }
    }

    public static boolean isNetworkConnected(Activity activity) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void native_ShowAds(Activity activity, ViewGroup viewGroup, int Type) {
        if (Type == 1) {
            AD_native.getInstance(activity).show_native_AD(activity, viewGroup, 1, app_count_click);
        } else {
            AD_native.getInstance(activity).show_native_AD(activity, viewGroup, 2, app_count_click);
        }
    }


    public static Context getContext() {
        return context;
    }

    public static aakhometeri getInstance() {
        return instance_app_data;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setFirstLaunch(this);
        context = getApplicationContext();
        instance_app_data = this;

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mInstance = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (!getPackageName().equals(Application.getProcessName())) {
                WebView.setDataDirectorySuffix(Application.getProcessName());
            }
        }

        createNotificationChangel();
        UpdateSdk.getManager().initialize(this, newkese.class);

        //onetime
        hokebhi.initializingSharedPreference(context);
    }


    private static void setFirstLaunch(Context context2) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context2.getApplicationContext());
        if (!defaultSharedPreferences.contains(FIRST_LAUNCH)) {
            defaultSharedPreferences.edit().putLong(FIRST_LAUNCH, new Date().getTime()).apply();
        }
    }


}