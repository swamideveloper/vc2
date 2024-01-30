package com.videocall.globalcall.livecallwithfun.surmesham;

import android.content.Context;
import android.content.SharedPreferences;

public class hokebhi {
    public static String KEY_INCOMING = "key_free_incoming";
    public static String KEY_FREE_TRAIL = "key_free_trail";
    public static String KEY_VIP_TYPE = "key_vip_type";
    public static String KEY_TERMS = "key_terms";
    static SharedPreferences.Editor myEdit;
    static SharedPreferences sharedPreferences;

    //after inApp


    public static void initializingSharedPreference(Context context) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("MySharedPref123", 0);
        sharedPreferences = sharedPreferences2;
        myEdit = sharedPreferences2.edit();
    }

    public static String getVipType() {
        return sharedPreferences.getString(KEY_VIP_TYPE, "null");
    }

    public static void setVipType(String str) {
        sharedPreferences.edit().putString(KEY_VIP_TYPE, str).apply();
    }


    public static boolean isFreeIncomingActive() {
        return sharedPreferences.getBoolean(KEY_INCOMING, true);
    }

    public static void setFreeIncoming(boolean str) {
        sharedPreferences.edit().putBoolean(KEY_INCOMING, str).apply();
    }

    public static boolean isFreeTrailActive() {
        return sharedPreferences.getBoolean(KEY_FREE_TRAIL, true);
    }

    public static void setFreeTrail(boolean str) {
        sharedPreferences.edit().putBoolean(KEY_FREE_TRAIL, str).apply();
    }

    public static boolean isTerms() {
        return sharedPreferences.getBoolean(KEY_TERMS, true);
    }

    public static void stopTerms() {
        sharedPreferences.edit().putBoolean(KEY_TERMS, false).apply();
    }
}
