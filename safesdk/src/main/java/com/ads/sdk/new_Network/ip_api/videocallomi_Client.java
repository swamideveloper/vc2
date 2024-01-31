package com.ads.sdk.new_Network.ip_api;

import android.content.Context;
import android.util.Log;

import com.ads.sdk.new_Network.caller.N_Enc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class videocallomi_Client {

//    public static final String BASE_URL = "http://ip-api.com/";

    public static videocallomi_Service getGeoApiService(Context context, String pk) {
        return new Retrofit.Builder()
                .baseUrl(IP_Enc.baseURL(context, pk))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(videocallomi_Service.class);
    }

}
