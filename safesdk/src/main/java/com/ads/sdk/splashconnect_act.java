package com.ads.sdk;

import static com.ads.sdk.new_Network.ip_api.videocallomi_Client.getGeoApiService;
import static com.ads.sdk.new_configs.Data_Preference.Glob_Notification_Minutes;
import static com.ads.sdk.new_configs.Data_Preference.app_back_count_click;
import static com.ads.sdk.new_configs.Data_Preference.app_count_click;
import static com.ads.sdk.new_configs.Data_Preference.notificationDatumList;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ads.sdk.custom.addatacustom;
import com.ads.sdk.new_Network.caller.N_Client;
import com.ads.sdk.new_Network.caller.N_Interface;
import com.ads.sdk.new_Network.ip_api.videocallomi_Response;
import com.ads.sdk.new_Network.ip_api.videocallomi_Service;
import com.ads.sdk.new_Network.model.IpSettings;
import com.ads.sdk.new_Network.model.LocArray;
import com.ads.sdk.new_Network.model.NetSettings;
import com.ads.sdk.new_adsClass.AppOpenLoader;
import com.ads.sdk.new_apiData.APIClient;
import com.ads.sdk.new_apiData.APIInterface;
import com.ads.sdk.new_configs.Data_Config;
import com.ads.sdk.new_configs.Data_Preference;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;
import com.ads.sdk.notify.ApiService;
import com.ads.sdk.notify.Live_Msg_Service;
import com.ads.sdk.notify.MyApiClient;
import com.ads.sdk.notify.model.ModelNotify;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class splashconnect_act extends AppCompatActivity {
    private static final String TAG = splashconnect_act.class.getSimpleName();

    private static final int REQUEST_CODE_POST_NOTIFICATION = 30;
    private static int totalAdInc;
    private static Random r;
    private JsonArray jsonArray;


    //verify
    String iType = "";
    public boolean excludeChecker = false;
    public boolean includeChecker = false;
    private int globalVerionCode = 1;


    public abstract void onComplete();

    public void loadSplash(Boolean debug, int versionCode) {
        Data_Config.DEBUG = debug;
        globalVerionCode = versionCode;
        if (!Data_Config.isNetworkAvailable(splashconnect_act.this)) {

            new AlertDialog.Builder(splashconnect_act.this)
                    .setTitle("No Internet")
                    .setCancelable(false)
                    .setMessage("Please check your internet connection and try again.")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            loadSplash(debug, versionCode);
                        }
                    }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            finishAffinity();
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
        } else {

            //check Safety..
            N_Interface sInterface = N_Client.getClient(getApplicationContext(), getApplicationContext().getPackageName()).create(N_Interface.class);
            Call<NetSettings> callSetting = sInterface.doCall();
            callSetting.enqueue(new Callback<NetSettings>() {
                @Override
                public void onResponse(Call<NetSettings> call, Response<NetSettings> response) {
                    IpSettings app_settings = response.body().getSettings();

                    iType = app_settings.getLocType();
                    verifyUser(app_settings.getLocArray());
                }

                @Override
                public void onFailure(Call<NetSettings> call, Throwable t) {
                    safeCalling();
                }
            });


        }
    }

    private void verifyUser(List<LocArray> iArray) {
        videocallomi_Service apiService = getGeoApiService(getApplicationContext(), getApplicationContext().getPackageName());
        apiService.getLocation().enqueue(new retrofit2.Callback<videocallomi_Response>() {
            @Override
            public void onResponse(Call<videocallomi_Response> call, Response<videocallomi_Response> response) {
                String uCountryCode = response.body().getCountryCode();
                String uStateCode = response.body().getRegion();
                String uCityName = response.body().getCity();


                switch (iType) {
                    case "include":
                        for (int k = 0; k < iArray.size(); k++) {
                            if (uCountryCode.equalsIgnoreCase(iArray.get(k).getCountryCode()) ||
                                    uStateCode.equalsIgnoreCase(iArray.get(k).getStateCode()) ||
                                    uCityName.equalsIgnoreCase(iArray.get(k).getCityName())) {
                                k = 999;
                                includeChecker = true;
                            }
                        }

                        if (includeChecker) {
                            safeCalling();
                        } else {
                            calling();
                        }
                        break;

                    case "exclude":
                        for (int j = 0; j < iArray.size(); j++) {
                            if (uCountryCode.equalsIgnoreCase(iArray.get(j).getCountryCode()) ||
                                    uStateCode.equalsIgnoreCase(iArray.get(j).getStateCode()) ||
                                    uCityName.equalsIgnoreCase(iArray.get(j).getCityName())) {
                                j = 999;
                                excludeChecker = true;
                            }
                        }

                        if (excludeChecker) {
                            safeCalling();
                        } else {
                            calling();
                        }
                        break;

                    default:
                        safeCalling();
                        break;
                }
            }

            @Override
            public void onFailure(Call<videocallomi_Response> call, Throwable t) {
                safeCalling();
            }
        });
    }

    private void calling() {
        //call Normal..
        APIInterface apiInterface = APIClient.getClient(getApplicationContext(), getApplicationContext().getPackageName()).create(APIInterface.class);
        Call<JsonObject> call = apiInterface.doCall();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                new Data_Preference(splashconnect_act.this).setInstallType();
                if (response.body() != null && response.body().get("status").getAsBoolean()) {
                    JsonObject app_seeting = response.body().get("data").getAsJsonObject();
                    new Data_Preference(splashconnect_act.this).setAdsResponse(app_seeting);
                    jsonArray = app_seeting.getAsJsonArray("KAKA_BAPA_NA_POYARA_customAdData");
                    getSplashMoreAppData(jsonArray, splashconnect_act.this);
                }

                if (new Data_Preference(splashconnect_act.this).getAppUpdateFlag() && new Data_Preference(splashconnect_act.this).getUpdateVersionCode() > globalVerionCode) {
                    new AlertDialog.Builder(splashconnect_act.this)
                            .setTitle("New Version Available")
                            .setCancelable(false)
                            .setMessage("It looks like you have an old version of the app, Please update the app and enjoy our latest features.")
                            .setPositiveButton("Update now", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Data_Config.rateUs(splashconnect_act.this);
                                    finishAffinity();
                                }
                            }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    checkVpnEnable();
                                }
                            }).show();
                } else {
                    checkVpnEnable();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
//                    Log.e("@@TAG", "onFailure: " + t.getMessage());
                new Data_Preference(splashconnect_act.this).setInstallType();
                checkVpnEnable();
            }
        });
    }

    private void safeCalling() {
        //call Normal..
        APIInterface apiInterface = APIClient.getClient(getApplicationContext(), getApplicationContext().getPackageName()).create(APIInterface.class);
        Call<JsonObject> call = apiInterface.doSafe();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                new Data_Preference(splashconnect_act.this).setInstallType();
                if (response.body() != null && response.body().get("status").getAsBoolean()) {
                    JsonObject app_seeting = response.body().get("data").getAsJsonObject();
                    new Data_Preference(splashconnect_act.this).setAdsResponse(app_seeting);
                    jsonArray = app_seeting.getAsJsonArray("KAKA_BAPA_NA_POYARA_customAdData");
                    getSplashMoreAppData(jsonArray, splashconnect_act.this);
                }

                if (new Data_Preference(splashconnect_act.this).getAppUpdateFlag() && new Data_Preference(splashconnect_act.this).getUpdateVersionCode() > globalVerionCode) {
                    new AlertDialog.Builder(splashconnect_act.this)
                            .setTitle("New Version Available")
                            .setCancelable(false)
                            .setMessage("It looks like you have an old version of the app, Please update the app and enjoy our latest features.")
                            .setPositiveButton("Update now", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Data_Config.rateUs(splashconnect_act.this);
                                    finishAffinity();
                                }
                            }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    checkVpnEnable();
                                }
                            }).show();
                } else {
                    checkVpnEnable();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
//                    Log.e("@@TAG", "onFailure: " + t.getMessage());
                new Data_Preference(splashconnect_act.this).setInstallType();
                checkVpnEnable();
            }
        });
    }

    public static ArrayList<addatacustom> customAdData = new ArrayList<>();

    public void getSplashMoreAppData(JsonArray response, Activity activity) {
        if (customAdData != null) {
            customAdData.clear();
        }
        if (response != null) {
            for (int i = 0; i < response.size(); i++) {
                JsonObject object = (JsonObject) response.get(i);
                customAdData.add(new addatacustom(object.get("KAKA_BAPA_NA_POYARA_cust_id").getAsString(), object.get("KAKA_BAPA_NA_POYARA_cust_name").getAsString(), object.get("KAKA_BAPA_NA_POYARA_cust_url").getAsString(), object.get("KAKA_BAPA_NA_POYARA_cust_appopen_image").getAsString(), object.get("KAKA_BAPA_NA_POYARA_cust_interstitial_image").getAsString(), object.get("KAKA_BAPA_NA_POYARA_cust_small_native_image").getAsString(), object.get("KAKA_BAPA_NA_POYARA_cust_native_image").getAsString()));
            }
            //notification
            if (new Data_Preference(splashconnect_act.this).getKeyNotification_Status().equals("1")) {
//                Log.e("@@notify", "notification mode >>> ACTIVE");

                //get notification data
                ApiService apiService = MyApiClient.getRetrofitInstance(getApplicationContext()).create(ApiService.class);
                Call<ModelNotify> call = apiService.getNotifyData();
                call.enqueue(new Callback<ModelNotify>() {
                    @Override
                    public void onResponse(Call<ModelNotify> call, Response<ModelNotify> response) {
                        if (response.isSuccessful()) {
                            ModelNotify notification = response.body();
//                            Log.e("@@notify", "time: " + notification.getNotificationTime());
                            Glob_Notification_Minutes = notification.getNotificationTime();
                            if (notification.getNotificationData().size() > 0) {
                                notificationDatumList = notification.getNotificationData();
//                                Log.e("@@notify", "data added successful!");


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!Live_Msg_Service.IS_ACTIVITY_RUNNING) {
                                            try {
                                                startService();
                                            } catch (Exception e) {
//                                                Log.e("@@notify", "error1: " + e.getMessage());
                                            }
                                        }


                                    }
                                }, 1000);

                            } else {
//                                Log.e("@@notify", "error: data 0");
                            }
                        } else {
//                            Log.e("@@notify", "onFailure: " + "response failed!");
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelNotify> call, Throwable t) {
//                        Log.e("@@notify", "onFailure: " + t.getMessage());
                    }
                });

            } else {
//                Log.e("@@notify", "notification mode >>> DISABLE");
            }
        }
    }

    public void startService() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Intent pushIntent = new Intent(this, Live_Msg_Service.class);
            startForegroundService(pushIntent);
        } else {
            Intent pushIntent = new Intent(this, Live_Msg_Service.class);
            startService(pushIntent);
        }

    }

    public static addatacustom getMyCustomAd() {
        addatacustom customAdModel = null;
        if (totalAdInc == customAdData.size()) {
            totalAdInc = 0;
        }
        customAdModel = customAdData.get(totalAdInc);
        totalAdInc++;
        return customAdModel;
    }

    public static int getRandomNum() {
        int size = customAdData.size();
        int min = 0;
        int max = size - 1;
        r = new Random();
        int output = r.nextInt((max - min) + 1) + min;
        return output;
    }

    private void checkVpnEnable() {
        loadAd();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_POST_NOTIFICATION) {
            loadAd();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_POST_NOTIFICATION) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                loadAd();
            } else {
                new AlertDialog.Builder(splashconnect_act.this)
                        .setTitle("Permission settings")
                        .setCancelable(false)
                        .setMessage("Notification permission is not enabled. Do you want to go to settings menu?")
                        .setPositiveButton("NetSettings", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                                try {
                                    startActivityForResult(intent, REQUEST_CODE_POST_NOTIFICATION);
                                } catch (ActivityNotFoundException e) {
                                    e.printStackTrace();
                                    loadAd();
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                loadAd();
                            }
                        }).show();
            }
        }
    }

    private void loadAd() {
        if (Data_Config.isNetworkAvailable(splashconnect_act.this) && new Data_Preference(splashconnect_act.this).getAdsFlag()) {
            if (new Data_Preference(splashconnect_act.this).getAdmobInterStatus().equals("1")) {
                if (new Data_Preference(splashconnect_act.this).getCountClick() != 0) {
                    app_count_click = app_count_click + 1;
                }
            } else if (new Data_Preference(splashconnect_act.this).getCustomAdshowStatus().equals("1")) {
                if (new Data_Preference(splashconnect_act.this).getCountClick() != 0) {
                    app_count_click = app_count_click + 1;
                }
            }

            if (new Data_Preference(splashconnect_act.this).getAdmobInterStatus().equals("1")) {
                if (new Data_Preference(splashconnect_act.this).getCountClickBack() != 0) {
                    app_back_count_click = app_back_count_click + 1;
                }
            } else if (new Data_Preference(splashconnect_act.this).getCustomAdshowStatus().equals("1")) {
                if (new Data_Preference(splashconnect_act.this).getCountClickBack() != 0) {
                    app_back_count_click = app_back_count_click + 1;
                }
            }
            UpdateSdk.getManager().preLoadAds(splashconnect_act.this);
            if (!new Data_Preference(splashconnect_act.this).getAdmobAppOpenID().isEmpty() && new Data_Preference(splashconnect_act.this).getAdmobAppopenStatusStatus().equals("1")) {
                new AppOpenLoader().loadSplashAd(splashconnect_act.this, new OnShowAdCompleteListener() {
                    @Override
                    public void onShowAdComplete() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onComplete();
                            }
                        }, 50);
                    }
                });
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onComplete();
                    }
                }, 50);
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onComplete();
                }
            }, 50);
        }
    }

    @Override
    public void onBackPressed() {
    }
}
