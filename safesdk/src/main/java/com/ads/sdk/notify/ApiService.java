package com.ads.sdk.notify;

import com.ads.sdk.notify.model.ModelNotify;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("notification.json")
    Call<ModelNotify> getNotifyData();
}