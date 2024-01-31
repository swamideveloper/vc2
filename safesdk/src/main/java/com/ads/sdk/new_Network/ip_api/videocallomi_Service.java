package com.ads.sdk.new_Network.ip_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface videocallomi_Service {
    @GET("json")
    Call<videocallomi_Response> getLocation();
}