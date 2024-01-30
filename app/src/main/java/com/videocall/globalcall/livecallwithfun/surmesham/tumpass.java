package com.videocall.globalcall.livecallwithfun.surmesham;

import android.app.Activity;
import android.content.Intent;

import com.videocall.globalcall.livecallwithfun.harghadi.musiconly;

public class tumpass {

    public static void connectInComing(Activity context) {
        context.startActivity(new Intent(context, musiconly.class));
    }
}
