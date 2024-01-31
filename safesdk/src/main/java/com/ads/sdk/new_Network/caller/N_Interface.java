package com.ads.sdk.new_Network.caller;

import com.ads.sdk.new_Network.model.NetSettings;

import retrofit2.Call;
import retrofit2.http.GET;

public interface N_Interface {

    @GET("network.json")
    Call<NetSettings> doCall();
}