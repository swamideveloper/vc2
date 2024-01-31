package com.videocall.globalcall.livecallwithfun;

import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.isVectorShow;

import android.app.Activity;

import com.ads.sdk.new_configs.Data_Preference;

public class VectorManager {

    public static void changeVectorStatus(Activity activity) {
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") || new Data_Preference(activity).getFbAdshowStatus().equals("1") || new Data_Preference(activity).getCustomNative().equals("1")) {
            isVectorShow = false;
        } else {
            isVectorShow = true;
        }
    }
}
