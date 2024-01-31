package com.ads.sdk.new_configs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;

import com.ads.sdk.R;
import com.ads.sdk.notify.model.NotificationDatum;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Data_Preference {

    //multiple fb
    public static int fb_inter_index = 1;
    public static int fb_native_index = 1;
    public static int fb_banner_index = 1;


    private static final String TAG = Data_Preference.class.getSimpleName();

    SharedPreferences preferences;


    public Data_Preference(Context activity) {
        preferences = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    private static final String PREFERENCE_NAME = "ads_sdk";
    private static final String KEY_INSTALL_TYPE = "install_type";
    private static final String KEY_PRIVACY_LINK = "KAKA_BAPA_NA_POYARA_privacyLink"; //
    private static final String KEY_ADS_FLAG = "KAKA_BAPA_NA_POYARA_adShow_Sts";  //
    private static final String KEY_UPDATE_APP = "KAKA_BAPA_NA_POYARA_updateDialog";  //
    private static final String KEY_UPDATE_VERSION_CODE = "KAKA_BAPA_NA_POYARA_verCode";

    private static final String KEY_FIX_COUNTRY = "KAKA_BAPA_NA_POYARA_launchCountryName"; //
    private static final String KEY_SWIPE_COUNTRY = "KAKA_BAPA_NA_POYARA_swipCountryName";//
    private static final String KEY_SWIPE_MAIN = "KAKA_BAPA_NA_POYARA_swipMain";//

    public static String KEY_FORWARD_INTER_ALTERNATIVE = "KAKA_BAPA_NA_POYARA_forwardInterstitialF"; //
    private static final String KEY_BACKWARD_INTER_ALTERNATIVE = "KAKA_BAPA_NA_POYARA_backwardInterstitialB";//

    //added ADMOB
    private static final String KEY_ADMOB_INTER_STATUS = "KAKA_BAPA_NA_POYARA_inter_Sts";
    private static final String KEY_ADMOB_BACK_STATUS = "KAKA_BAPA_NA_POYARA_back_Sts";
    private static final String KEY_ADMOB_NATIVE_STATUS = "KAKA_BAPA_NA_POYARA_native_Sts";
    private static final String KEY_ADMOB_BANNER_STATUS = "KAKA_BAPA_NA_POYARA_banner_Sts";
    private static final String KEY_ADMOB_APPOPEN_STATUS = "KAKA_BAPA_NA_POYARA_appOpen_Sts";
    private static final String KEY_ADMOB_NATIVE = "KAKA_BAPA_NA_POYARA_nativeBigAd";
    private static final String KEY_ADMOB_BANNER = "KAKA_BAPA_NA_POYARA_nativeBannerAd";
    private static final String KEY_INTERSTITIAL_FREQUENCY = "KAKA_BAPA_NA_POYARA_forwardClick"; //
    private static final String KEY_INTERSTITIAL_BACK_FREQUENCY = "KAKA_BAPA_NA_POYARA_backClick"; //
    private static final String KEY_COMBO_FREQUENCY = "KAKA_BAPA_NA_POYARA_comboClick"; //
    private static final String KEY_AD_DIALOG = "KAKA_BAPA_NA_POYARA_admob_ad_dailog";
    private static final String KEY_APP_OPEN_AD_SEC = "KAKA_BAPA_NA_POYARA_app_open_ad_sec";

    //added FB
    private static final String KEY_FB_ADSHOW_STATUS = "KAKA_BAPA_NA_POYARA_fb_adshowStatus";//
    private static final String KEY_FB_BANNER_AD_ID_1 = "KAKA_BAPA_NA_POYARA_fb_bannerId_1";
    private static final String KEY_FB_BANNER_AD_ID_2 = "KAKA_BAPA_NA_POYARA_fb_bannerId_2";
    private static final String KEY_FB_BANNER_AD_ID_3 = "KAKA_BAPA_NA_POYARA_fb_bannerId_3";
    private static final String KEY_FB_BANNER_AD_ID_4 = "KAKA_BAPA_NA_POYARA_fb_bannerId_4";
    private static final String KEY_FB_BANNER_AD_ID_5 = "KAKA_BAPA_NA_POYARA_fb_bannerId_5";
    private static final String KEY_FB_BANNER_AD_ID_6 = "KAKA_BAPA_NA_POYARA_fb_bannerId_6";
    private static final String KEY_FB_INTERSTITIAL_AD_ID_1 = "KAKA_BAPA_NA_POYARA_fb_interstitialId_1";
    private static final String KEY_FB_INTERSTITIAL_AD_ID_2 = "KAKA_BAPA_NA_POYARA_fb_interstitialId_2";
    private static final String KEY_FB_INTERSTITIAL_AD_ID_3 = "KAKA_BAPA_NA_POYARA_fb_interstitialId_3";
    private static final String KEY_FB_INTERSTITIAL_AD_ID_4 = "KAKA_BAPA_NA_POYARA_fb_interstitialId_4";
    private static final String KEY_FB_INTERSTITIAL_AD_ID_5 = "KAKA_BAPA_NA_POYARA_fb_interstitialId_5";
    private static final String KEY_FB_INTERSTITIAL_AD_ID_6 = "KAKA_BAPA_NA_POYARA_fb_interstitialId_6";
    private static final String KEY_FB_NATIVE_AD_ID_1 = "KAKA_BAPA_NA_POYARA_fb_nativeId_1";
    private static final String KEY_FB_NATIVE_AD_ID_2 = "KAKA_BAPA_NA_POYARA_fb_nativeId_2";
    private static final String KEY_FB_NATIVE_AD_ID_3 = "KAKA_BAPA_NA_POYARA_fb_nativeId_3";
    private static final String KEY_FB_NATIVE_AD_ID_4 = "KAKA_BAPA_NA_POYARA_fb_nativeId_4";
    private static final String KEY_FB_NATIVE_AD_ID_5 = "KAKA_BAPA_NA_POYARA_fb_nativeId_5";
    private static final String KEY_FB_NATIVE_AD_ID_6 = "KAKA_BAPA_NA_POYARA_fb_nativeId_6";

    /*For Ad Color*/
    private static String KEY_NATIVE_BUTTON_COLOR = "KAKA_BAPA_NA_POYARA_ad_nativebutton";
    private static String KEY_NATIVE_TAG_COLOR = "KAKA_BAPA_NA_POYARA_ad_nativeTAG";
    private static String KEY_NATIVE_BG_COLOR = "KAKA_BAPA_NA_POYARA_ad_nativebg";
    private static String KEY_NATIVE_TITLE_COLOR = "KAKA_BAPA_NA_POYARA_ad_nativetitle";
    private static String KEY_NATIVE_DESC_COLOR = "KAKA_BAPA_NA_POYARA_ad_nativedescription";
    private static String KEY_NATIVE_SPACEBOX = "KAKA_BAPA_NA_POYARA_native_spacebox";
    private static String KEY_NATIVE_SPACEBOX_COLOR = "KAKA_BAPA_NA_POYARA_native_spacebox_color";

    /*For ads Ids*/
    private static final String KEY_ADMOB_APPOPEN_AD_ID = "KAKA_BAPA_NA_POYARA_appOpenAdId";//
    private static final String KEY_ADMOB_BANNER_AD_ID = "KAKA_BAPA_NA_POYARA_bannerAdId";//
    private static final String KEY_ADMOB_INTERSTITIAL_AD_ID = "KAKA_BAPA_NA_POYARA_interstitialAdId"; //
    private static final String KEY_ADMOB_NATIVE_AD_ID = "KAKA_BAPA_NA_POYARA_nativeAdId"; //
    private static final String KEY_ONRESUMENATIVESHOW = "KAKA_BAPA_NA_POYARA_onResumeNativeShow"; //

    //custom
    private static String KEY_CUSTOM_ADSHOW_STATUS = "KAKA_BAPA_NA_POYARA_customAd_Sts";
    private static String KEY_CUSTOM_APPOPEN_AD = "KAKA_BAPA_NA_POYARA_customAppOpen_Sts";
    private static String KEY_CUSTOM_INTER_AD = "KAKA_BAPA_NA_POYARA_customInterstitial_Sts";
    private static String KEY_CUSTOM_BACK_AD = "KAKA_BAPA_NA_POYARA_customBack_Sts";
    private static String KEY_CUSTOM_NATIVE_AD = "KAKA_BAPA_NA_POYARA_customNativeBanner_Sts";
    private static String KEY_CUSTOM_CLOSE_AD = "KAKA_BAPA_NA_POYARA_Custom_close_ad";

    //other
    private static String KEY_STATUSSAVER = "KAKA_BAPA_NA_POYARA_statusSaver";
    private static String KEY_TERMSCREEN = "KAKA_BAPA_NA_POYARA_termScreen";
    private static String KEY_STARTSCREEN = "KAKA_BAPA_NA_POYARA_startScreen";
    private static String KEY_EXITSCREEN = "KAKA_BAPA_NA_POYARA_exitScreen";
    private static String KEY_PERMISSIONSCREEN = "KAKA_BAPA_NA_POYARA_permissionScreen";
    private static String KEY_VIDEOPLAYER = "KAKA_BAPA_NA_POYARA_videoPlayer";

    //notification
    private static String KEY_NOTIFICATION_STATUS = "KAKA_BAPA_NA_POYARA_notification_status";
    private static String KEY_NOTIFICATION_LINK = "KAKA_BAPA_NA_POYARA_notification_link";
    public static List<NotificationDatum> notificationDatumList = new ArrayList<>();
    public static String Glob_Notification_Minutes = "10";

    //qureka
    private static String KEY_QUREKABUTTONVIEW = "KAKA_BAPA_NA_POYARA_qurekabuttonview";
    private static String KEY_PREDCHAMPBUTTONVIEW = "KAKA_BAPA_NA_POYARA_predchampbuttonview";
    private static String KEY_MGLBUTTONVIEW = "KAKA_BAPA_NA_POYARA_mglbuttonview";
    private static String KEY_HEADERVIEW = "KAKA_BAPA_NA_POYARA_headerview";
    private static String KEY_QUREKALINK = "KAKA_BAPA_NA_POYARA_qurekaLink";
    private static String KEY_PREDCHAMPLINK = "KAKA_BAPA_NA_POYARA_predchampLink";
    private static String KEY_MGLLINK = "KAKA_BAPA_NA_POYARA_mglLink";
    private static String KEY_QUREKAIMAGELINK = "KAKA_BAPA_NA_POYARA_qurekaImageLink";
    private static String KEY_PREDCHAMPIMAGELINK = "KAKA_BAPA_NA_POYARA_predchampImageLink";
    private static String KEY_MGLIMAGELINK = "KAKA_BAPA_NA_POYARA_mglImageLink";
    private static String KEY_HEADERLINK = "KAKA_BAPA_NA_POYARA_HeaderLink";
    private static String KEY_HEADERIMAGELINK = "KAKA_BAPA_NA_POYARA_qurekaHeaderImagelink";

    private static String KEY_CUSTOMAPPLINK = "KAKA_BAPA_NA_POYARA_custom_open_link_on_termscreen";
    private static String KEY_CUSTOMAPPBUTTON = "KAKA_BAPA_NA_POYARA_custom_open_link";


    private static String KEY_EXITNATIVE = "KAKA_BAPA_NA_POYARA_exitNative_sts";

    private static String KEY_NEW_COUNTRY = "KAKA_BAPA_NA_POYARA_countryScreen";
    private static String KEY_NEW_LANGUAGE = "KAKA_BAPA_NA_POYARA_app_language_Screen";

    private static String KEY_VIEWPAGER_INTER = "ViewPager_Inter_Counter";


    //video call
    private static String KEY_VC_INCOMING_WELCOME = "videoCall_Incoming_Welcome";
    private static String KEY_VC_INCOMING_COUNTER = "videoCall_Incoming_Counter";
    private static String KEY_VC_INCOMING_LONGAPP = "videoCall_IsLongApp";
    private static String KEY_VC_ON_OFF = "videoCall_OnOff";

    //in-app
    private static String KEY_IN_APP_MODE = "InApp_Mode";
    private static String KEY_IN_APP_PRICE_GOLD = "InApp_Price_Gold";
    private static String KEY_IN_APP_PRICE_SILVER = "InApp_Price_Silver";
    private static String KEY_IN_APP_PRICE_BRONZE = "InApp_Price_Bronze";
    private static String KEY_IN_APP_UPI_ID = "InApp_Upi_Id";
    private static String KEY_IN_APP_UPI_NAME = "InApp_Upi_Name";
    private static String KEY_IN_APP_UPI_DESC = "InApp_Upi_Desc";

    //hd videos
    private static String KEY_HOT_VIDEO_1 = "hotvideoLink1";
    private static String KEY_HOT_VIDEO_2 = "hotvideoLink2";


    public void setAdsResponse(JsonObject object) {
        if (object != null) {


            checkNotNull(object, KEY_PRIVACY_LINK, "KAKA_BAPA_NA_POYARA_privacyLink"); //
            checkNotNull(object, KEY_UPDATE_APP, "KAKA_BAPA_NA_POYARA_updateDialog"); //
            checkNotNull(object, KEY_UPDATE_VERSION_CODE, "KAKA_BAPA_NA_POYARA_verCode"); //

            checkNotNull(object, KEY_ADS_FLAG, "KAKA_BAPA_NA_POYARA_adShow_Sts"); //
            checkNotNull(object, KEY_INTERSTITIAL_FREQUENCY, "KAKA_BAPA_NA_POYARA_forwardClick"); //
            checkNotNull(object, KEY_INTERSTITIAL_BACK_FREQUENCY, "KAKA_BAPA_NA_POYARA_backClick"); //
            checkNotNull(object, KEY_COMBO_FREQUENCY, "KAKA_BAPA_NA_POYARA_comboClick"); //

            checkNotNull(object, KEY_FORWARD_INTER_ALTERNATIVE, "KAKA_BAPA_NA_POYARA_forwardInterstitialF");//
            checkNotNull(object, KEY_BACKWARD_INTER_ALTERNATIVE, "KAKA_BAPA_NA_POYARA_backwardInterstitialB");//
            //admob
            checkNotNull(object, KEY_ADMOB_INTER_STATUS, "KAKA_BAPA_NA_POYARA_inter_Sts");//
            checkNotNull(object, KEY_ADMOB_BACK_STATUS, "KAKA_BAPA_NA_POYARA_back_Sts");//
            checkNotNull(object, KEY_ADMOB_NATIVE_STATUS, "KAKA_BAPA_NA_POYARA_native_Sts");
            checkNotNull(object, KEY_ADMOB_APPOPEN_STATUS, "KAKA_BAPA_NA_POYARA_appOpen_Sts");
            checkNotNull(object, KEY_ADMOB_BANNER_STATUS, "KAKA_BAPA_NA_POYARA_banner_Sts");
            checkNotNull(object, KEY_ADMOB_NATIVE, "KAKA_BAPA_NA_POYARA_nativeBigAd");
            checkNotNull(object, KEY_ADMOB_BANNER, "KAKA_BAPA_NA_POYARA_nativeBannerAd");
            //fB
            checkNotNull(object, KEY_FB_ADSHOW_STATUS, "KAKA_BAPA_NA_POYARA_fb_adshowStatus");
            checkNotNull(object, KEY_FB_BANNER_AD_ID_1, "KAKA_BAPA_NA_POYARA_fb_bannerId_1");
            checkNotNull(object, KEY_FB_BANNER_AD_ID_2, "KAKA_BAPA_NA_POYARA_fb_bannerId_2");
            checkNotNull(object, KEY_FB_BANNER_AD_ID_3, "KAKA_BAPA_NA_POYARA_fb_bannerId_3");
            checkNotNull(object, KEY_FB_BANNER_AD_ID_4, "KAKA_BAPA_NA_POYARA_fb_bannerId_4");
            checkNotNull(object, KEY_FB_BANNER_AD_ID_5, "KAKA_BAPA_NA_POYARA_fb_bannerId_5");
            checkNotNull(object, KEY_FB_BANNER_AD_ID_6, "KAKA_BAPA_NA_POYARA_fb_bannerId_6");
            checkNotNull(object, KEY_FB_INTERSTITIAL_AD_ID_1, "KAKA_BAPA_NA_POYARA_fb_interstitialId_1");
            checkNotNull(object, KEY_FB_INTERSTITIAL_AD_ID_2, "KAKA_BAPA_NA_POYARA_fb_interstitialId_2");
            checkNotNull(object, KEY_FB_INTERSTITIAL_AD_ID_3, "KAKA_BAPA_NA_POYARA_fb_interstitialId_3");
            checkNotNull(object, KEY_FB_INTERSTITIAL_AD_ID_4, "KAKA_BAPA_NA_POYARA_fb_interstitialId_4");
            checkNotNull(object, KEY_FB_INTERSTITIAL_AD_ID_5, "KAKA_BAPA_NA_POYARA_fb_interstitialId_5");
            checkNotNull(object, KEY_FB_INTERSTITIAL_AD_ID_6, "KAKA_BAPA_NA_POYARA_fb_interstitialId_6");
            checkNotNull(object, KEY_FB_NATIVE_AD_ID_1, "KAKA_BAPA_NA_POYARA_fb_nativeId_1");
            checkNotNull(object, KEY_FB_NATIVE_AD_ID_2, "KAKA_BAPA_NA_POYARA_fb_nativeId_2");
            checkNotNull(object, KEY_FB_NATIVE_AD_ID_3, "KAKA_BAPA_NA_POYARA_fb_nativeId_3");
            checkNotNull(object, KEY_FB_NATIVE_AD_ID_4, "KAKA_BAPA_NA_POYARA_fb_nativeId_4");
            checkNotNull(object, KEY_FB_NATIVE_AD_ID_5, "KAKA_BAPA_NA_POYARA_fb_nativeId_5");
            checkNotNull(object, KEY_FB_NATIVE_AD_ID_6, "KAKA_BAPA_NA_POYARA_fb_nativeId_6");

            checkNotNull(object, KEY_CUSTOM_ADSHOW_STATUS, "KAKA_BAPA_NA_POYARA_customAd_Sts"); //
            checkNotNull(object, KEY_CUSTOM_APPOPEN_AD, "KAKA_BAPA_NA_POYARA_customAppOpen_Sts"); //
            checkNotNull(object, KEY_CUSTOM_INTER_AD, "KAKA_BAPA_NA_POYARA_customInterstitial_Sts"); //
            checkNotNull(object, KEY_CUSTOM_BACK_AD, "KAKA_BAPA_NA_POYARA_customBack_Sts"); //
            checkNotNull(object, KEY_CUSTOM_NATIVE_AD, "KAKA_BAPA_NA_POYARA_customNativeBanner_Sts"); //
            checkNotNull(object, KEY_CUSTOM_CLOSE_AD, "KAKA_BAPA_NA_POYARA_Custom_close_ad"); //

            checkNotNull(object, KEY_FIX_COUNTRY, "KAKA_BAPA_NA_POYARA_launchCountryName");//
            checkNotNull(object, KEY_SWIPE_COUNTRY, "KAKA_BAPA_NA_POYARA_swipCountryName");//
            checkNotNull(object, KEY_SWIPE_MAIN, "KAKA_BAPA_NA_POYARA_swipMain"); //

            checkNotNull(object, KEY_ADMOB_APPOPEN_AD_ID, "KAKA_BAPA_NA_POYARA_appOpenAdId"); //
            checkNotNull(object, KEY_ADMOB_BANNER_AD_ID, "KAKA_BAPA_NA_POYARA_bannerAdId"); //
            checkNotNull(object, KEY_ADMOB_INTERSTITIAL_AD_ID, "KAKA_BAPA_NA_POYARA_interstitialAdId"); //
            checkNotNull(object, KEY_ADMOB_NATIVE_AD_ID, "KAKA_BAPA_NA_POYARA_nativeAdId"); //
            checkNotNull(object, KEY_NATIVE_SPACEBOX, "KAKA_BAPA_NA_POYARA_native_spacebox");
            checkNotNull(object, KEY_ONRESUMENATIVESHOW, "KAKA_BAPA_NA_POYARA_onResumeNativeShow");


            checkNotNull(object, KEY_NATIVE_BUTTON_COLOR, "KAKA_BAPA_NA_POYARA_ad_nativebutton");
            checkNotNull(object, KEY_NATIVE_TAG_COLOR, "KAKA_BAPA_NA_POYARA_ad_nativeTAG");
            checkNotNull(object, KEY_NATIVE_BG_COLOR, "KAKA_BAPA_NA_POYARA_ad_nativebg");
            checkNotNull(object, KEY_NATIVE_TITLE_COLOR, "KAKA_BAPA_NA_POYARA_ad_nativetitle");
            checkNotNull(object, KEY_NATIVE_DESC_COLOR, "KAKA_BAPA_NA_POYARA_ad_nativedescription");
            checkNotNull(object, KEY_NATIVE_SPACEBOX_COLOR, "KAKA_BAPA_NA_POYARA_native_spacebox_color");

            checkNotNull(object, KEY_STATUSSAVER, "KAKA_BAPA_NA_POYARA_statusSaver");
            checkNotNull(object, KEY_TERMSCREEN, "KAKA_BAPA_NA_POYARA_termScreen");
            checkNotNull(object, KEY_STARTSCREEN, "KAKA_BAPA_NA_POYARA_startScreen");
            checkNotNull(object, KEY_EXITSCREEN, "KAKA_BAPA_NA_POYARA_exitScreen");
            checkNotNull(object, KEY_PERMISSIONSCREEN, "KAKA_BAPA_NA_POYARA_permissionScreen");
            checkNotNull(object, KEY_VIDEOPLAYER, "KAKA_BAPA_NA_POYARA_videoPlayer");

            checkNotNull(object, KEY_QUREKABUTTONVIEW, "KAKA_BAPA_NA_POYARA_qurekabuttonview");
            checkNotNull(object, KEY_PREDCHAMPBUTTONVIEW, "KAKA_BAPA_NA_POYARA_predchampbuttonview");
            checkNotNull(object, KEY_MGLBUTTONVIEW, "KAKA_BAPA_NA_POYARA_mglbuttonview");
            checkNotNull(object, KEY_HEADERVIEW, "KAKA_BAPA_NA_POYARA_headerview");
            checkNotNull(object, KEY_QUREKALINK, "KAKA_BAPA_NA_POYARA_qurekaLink");
            checkNotNull(object, KEY_PREDCHAMPLINK, "KAKA_BAPA_NA_POYARA_predchampLink");
            checkNotNull(object, KEY_MGLLINK, "KAKA_BAPA_NA_POYARA_mglLink");
            checkNotNull(object, KEY_QUREKAIMAGELINK, "KAKA_BAPA_NA_POYARA_qurekaImageLink");
            checkNotNull(object, KEY_PREDCHAMPIMAGELINK, "KAKA_BAPA_NA_POYARA_predchampImageLink");
            checkNotNull(object, KEY_MGLIMAGELINK, "KAKA_BAPA_NA_POYARA_mglImageLink");
            checkNotNull(object, KEY_HEADERLINK, "KAKA_BAPA_NA_POYARA_HeaderLink");
            checkNotNull(object, KEY_HEADERIMAGELINK, "KAKA_BAPA_NA_POYARA_qurekaHeaderImagelink");

            checkNotNull(object, KEY_EXITNATIVE, "KAKA_BAPA_NA_POYARA_exitNative_sts");

            checkNotNull(object, KEY_NEW_COUNTRY, "KAKA_BAPA_NA_POYARA_countryScreen");
            checkNotNull(object, KEY_NEW_LANGUAGE, "KAKA_BAPA_NA_POYARA_app_language_Screen");
            checkNotNull(object, KEY_VIEWPAGER_INTER, "ViewPager_Inter_Counter");


            checkNotNull(object, KEY_AD_DIALOG, "KAKA_BAPA_NA_POYARA_admob_ad_dailog");
            checkNotNull(object, KEY_APP_OPEN_AD_SEC, "KAKA_BAPA_NA_POYARA_app_open_ad_sec");

            //notification
            checkNotNull(object, KEY_NOTIFICATION_STATUS, "KAKA_BAPA_NA_POYARA_notification_status");
            checkNotNull(object, KEY_NOTIFICATION_LINK, "KAKA_BAPA_NA_POYARA_notification_link");

            checkNotNull(object, KEY_CUSTOMAPPLINK, "KAKA_BAPA_NA_POYARA_custom_open_link_on_termscreen");
            checkNotNull(object, KEY_CUSTOMAPPBUTTON, "KAKA_BAPA_NA_POYARA_custom_open_link");

            //vc
            checkNotNull(object, KEY_VC_INCOMING_WELCOME, "videoCall_Incoming_Welcome");
            checkNotNull(object, KEY_VC_INCOMING_COUNTER, "videoCall_Incoming_Counter");
            checkNotNull(object, KEY_VC_INCOMING_LONGAPP, "videoCall_IsLongApp");
            checkNotNull(object, KEY_VC_ON_OFF, "videoCall_OnOff");

            //in_app
            checkNotNull(object, KEY_IN_APP_MODE, "InApp_Mode");
            checkNotNull(object, KEY_IN_APP_PRICE_GOLD, "InApp_Price_Gold");
            checkNotNull(object, KEY_IN_APP_PRICE_SILVER, "InApp_Price_Silver");
            checkNotNull(object, KEY_IN_APP_PRICE_BRONZE, "InApp_Price_Bronze");

            checkNotNull(object, KEY_IN_APP_UPI_ID, "InApp_Upi_Id");
            checkNotNull(object, KEY_IN_APP_UPI_NAME, "InApp_Upi_Name");
            checkNotNull(object, KEY_IN_APP_UPI_DESC, "InApp_Upi_Desc");

            //hd videos
            checkNotNull(object, KEY_HOT_VIDEO_1, "hotvideoLink1");
            checkNotNull(object, KEY_HOT_VIDEO_2, "hotvideoLink2");

            getCountClick();
            getCountClickBack();
            manageCombo();
        }
    }

    private void manageCombo() {
        if (getFbAdshowStatus().equals("1") && getAdmobInterStatus().equals("1")) {
            isComboActive = true;
        } else {
            isComboActive = false;
        }
    }

    private void checkNotNull(JsonObject object, String key, String jsonKey) {
        try {
            preferences.edit().putString(key, object.get(jsonKey).getAsString()).apply();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //notification
    public String getKeyNotification_Status() {
        return preferences.getString(KEY_NOTIFICATION_STATUS, "0");
    }

    public String getKeyNotification_Link() {
        return preferences.getString(KEY_NOTIFICATION_LINK, "null");
    }

    public String getKeyOnresumenativeshow() {
        return preferences.getString(KEY_ONRESUMENATIVESHOW, "0");
    }

    public String getKeyCustomapplink() {
        return preferences.getString(KEY_CUSTOMAPPLINK, "0");
    }

    public String getKeyCustomappbutton() {
        return preferences.getString(KEY_CUSTOMAPPBUTTON, "0");
    }

    public String getKeyExitscreen() {
        return preferences.getString(KEY_EXITSCREEN, "0");
    }

    public String getKeyQurekabuttonview() {
        return preferences.getString(KEY_QUREKABUTTONVIEW, "0");
    }

    public String getKeyPredchampbuttonview() {
        return preferences.getString(KEY_PREDCHAMPBUTTONVIEW, "0");
    }

    public String getKeyMglbuttonview() {
        return preferences.getString(KEY_MGLBUTTONVIEW, "0");
    }

    public String getKeyHeaderview() {
        return preferences.getString(KEY_HEADERVIEW, "0");
    }

    public String getKeyQurekalink() {
        return preferences.getString(KEY_QUREKALINK, "0");
    }

    public String getKeyPredchamplink() {
        return preferences.getString(KEY_PREDCHAMPLINK, "0");
    }

    public String getKeyMgllink() {
        return preferences.getString(KEY_MGLLINK, "0");
    }

    public String getKeyQurekaimagelink() {
        return preferences.getString(KEY_QUREKAIMAGELINK, "0");
    }

    public String getKeyPredchampimagelink() {
        return preferences.getString(KEY_PREDCHAMPIMAGELINK, "0");
    }

    public String getKeyMglimagelink() {
        return preferences.getString(KEY_MGLIMAGELINK, "0");
    }

    public String getKeyHeaderlink() {
        return preferences.getString(KEY_HEADERLINK, "0");
    }

    public String getKeyHeaderimagelink() {
        return preferences.getString(KEY_HEADERIMAGELINK, "0");
    }

    public String getKeyTermscreen() {
        return preferences.getString(KEY_TERMSCREEN, "0");
    }

    public String getKeyStartscreen() {
        return preferences.getString(KEY_STARTSCREEN, "0");
    }

    public String getKeyPermissionscreen() {
        return preferences.getString(KEY_PERMISSIONSCREEN, "0");
    }

    public String getKeyVideoPlayer() {
        return preferences.getString(KEY_VIDEOPLAYER, "0");
    }

    public String getIncoming_Welcome() {
        return preferences.getString(KEY_VC_INCOMING_WELCOME, "0");
    }

    public String getKeyStatussaver() {
        return preferences.getString(KEY_STATUSSAVER, "0");
    }

    public String getForwardInter() {
        return preferences.getString(KEY_FORWARD_INTER_ALTERNATIVE, "0");
    }

    public String getAdmobBackInter() {
        return preferences.getString(KEY_BACKWARD_INTER_ALTERNATIVE, "0");
    }

    public String getAdmobNative() {
        return preferences.getString(KEY_ADMOB_NATIVE, "0");
    }

    public String getAdmobBanner() {
        return preferences.getString(KEY_ADMOB_BANNER, "0");
    }

    public String getAdmobNativeStatus() {
        return preferences.getString(KEY_ADMOB_NATIVE_STATUS, "0");
    }

    public String getAdmobInterStatus() {
        return preferences.getString(KEY_ADMOB_INTER_STATUS, "0");
    }

    public String getAdmobBackStatus() {
        return preferences.getString(KEY_ADMOB_BACK_STATUS, "0");
    }

    public String getAdmobBannerStatus() {
        return preferences.getString(KEY_ADMOB_BANNER_STATUS, "0");
    }

    public String getAdmobAppopenStatusStatus() {
        return preferences.getString(KEY_ADMOB_APPOPEN_STATUS, "0");
    }


    public String getAdDialog() {
        return preferences.getString(KEY_AD_DIALOG, "0");
    }

    public String getAppOpenADSec() {
        return preferences.getString(KEY_APP_OPEN_AD_SEC, "");
    }

    //fb
    public String getFbAdshowStatus() {
        return preferences.getString(KEY_FB_ADSHOW_STATUS, "0");
    }

    public String getFbBannerID() {


        if (fb_banner_index == 1) {
            fb_banner_index = fb_banner_index + 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_1, "0");
        } else if (fb_banner_index == 2) {
            fb_banner_index = fb_banner_index + 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_2, "0");
        } else if (fb_banner_index == 3) {
            fb_banner_index = fb_banner_index + 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_3, "0");
        } else if (fb_banner_index == 4) {
            fb_banner_index = fb_banner_index + 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_4, "0");
        } else if (fb_banner_index == 5) {
            fb_banner_index = fb_banner_index + 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_5, "0");
        } else if (fb_banner_index == 6) {
            fb_banner_index = 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_6, "0");
        } else {
            fb_banner_index = 1;
            return preferences.getString(KEY_FB_BANNER_AD_ID_1, "0");
        }
    }

    public String getFbNativeID() {

        if (fb_native_index == 1) {
            fb_native_index = fb_native_index + 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_1, "0");
        } else if (fb_native_index == 2) {
            fb_native_index = fb_native_index + 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_2, "0");
        } else if (fb_native_index == 3) {
            fb_native_index = fb_native_index + 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_3, "0");
        } else if (fb_native_index == 4) {
            fb_native_index = fb_native_index + 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_4, "0");
        } else if (fb_native_index == 5) {
            fb_native_index = fb_native_index + 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_5, "0");
        } else if (fb_native_index == 6) {
            fb_native_index = 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_6, "0");
        } else {
            fb_native_index = 1;
            return preferences.getString(KEY_FB_NATIVE_AD_ID_1, "0");
        }
    }

    public String getFbInterID() {

        if (fb_inter_index == 1) {
            fb_inter_index = fb_inter_index + 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_1, "0");
        } else if (fb_inter_index == 2) {
            fb_inter_index = fb_inter_index + 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_2, "0");
        } else if (fb_inter_index == 3) {
            fb_inter_index = fb_inter_index + 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_3, "0");
        } else if (fb_inter_index == 4) {
            fb_inter_index = fb_inter_index + 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_4, "0");
        } else if (fb_inter_index == 5) {
            fb_inter_index = fb_inter_index + 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_5, "0");
        } else if (fb_inter_index == 6) {
            fb_inter_index = 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_6, "0");
        } else {
            fb_inter_index = 1;
            return preferences.getString(KEY_FB_INTERSTITIAL_AD_ID_1, "0");
        }
    }

    //custom
    public String getCustomAdshowStatus() {
        return preferences.getString(KEY_CUSTOM_ADSHOW_STATUS, "0");
    }

    public String getCustomNative() {
        return preferences.getString(KEY_CUSTOM_NATIVE_AD, "0");
    }

    public String getCustomInter() {
        return preferences.getString(KEY_CUSTOM_INTER_AD, "0");
    }

    public String getCustomBack() {
        return preferences.getString(KEY_CUSTOM_BACK_AD, "0");
    }

    public String getCustomCloseAd() {
        return preferences.getString(KEY_CUSTOM_CLOSE_AD, "0");
    }

    /*For Other*/
    public String getPrivacyLink() {
        return preferences.getString(KEY_PRIVACY_LINK, "https/www.google.com/");
    }

    public Boolean getAppUpdateFlag() {
        return preferences.getString(KEY_UPDATE_APP, "0").equals("1");
    }

    public Integer getUpdateVersionCode() {
        return Integer.parseInt(preferences.getString(KEY_UPDATE_VERSION_CODE, "1"));
    }

    public String getInstallType() {
        return preferences.getString(KEY_INSTALL_TYPE, "1");
    }

    public void setInstallType() {
        if (getInstallType().equals("1"))
            preferences.edit().putString(KEY_INSTALL_TYPE, "2").apply();
    }

    /*For ads*/
    public Boolean getAdsFlag() {
        return preferences.getString(KEY_ADS_FLAG, "0").equals("1");
    }

    public void setAdsFlag(boolean b) {
        if (b) {
            preferences.edit().putString(KEY_ADS_FLAG, "1").apply();
        } else {
            preferences.edit().putString(KEY_ADS_FLAG, "0").apply();
        }

    }

    public static int app_count_click = -1;
    public static int app_back_count_click = -1;
    public static int app_combo_counter = 0;
    public static boolean isComboActive = false;

    public String getComboCount() {
        return preferences.getString(KEY_COMBO_FREQUENCY, "0");
    }

    public Integer getCountClick() {
        int value = 0;
        if (!preferences.getString(KEY_INTERSTITIAL_FREQUENCY, "0").isEmpty()) {
            value = Integer.parseInt(preferences.getString(KEY_INTERSTITIAL_FREQUENCY, "0"));
        }
        app_count_click = value;
        return value;
    }

    public Integer getCountClickBack() {
        int value = 0;
        if (!preferences.getString(KEY_INTERSTITIAL_BACK_FREQUENCY, "0").isEmpty()) {
            value = Integer.parseInt(preferences.getString(KEY_INTERSTITIAL_BACK_FREQUENCY, "0"));
        }
        app_back_count_click = value;
        return value;
    }

    public String getAdmobAppOpenID() {
        return preferences.getString(KEY_ADMOB_APPOPEN_AD_ID, "0");
    }

    public String getAdmobBannerID() {
        return preferences.getString(KEY_ADMOB_BANNER_AD_ID, "");
    }

    public String getAdmobInterstitialID() {
        return preferences.getString(KEY_ADMOB_INTERSTITIAL_AD_ID, "");
    }

    public String getAdmobNativeID() {
        return preferences.getString(KEY_ADMOB_NATIVE_AD_ID, "");
    }

    public String getNativeBtnColor() {
        return preferences.getString(KEY_NATIVE_BUTTON_COLOR, "#ff1744");
    }

    public String getKeyNativeTagColor() {
        return preferences.getString(KEY_NATIVE_TAG_COLOR, "#ff1744");
    }

    public String getNativeBgColor() {
        return preferences.getString(KEY_NATIVE_BG_COLOR, "#FAEBD7");
    }

    public String getNativeTitleColor() {
        return preferences.getString(KEY_NATIVE_TITLE_COLOR, "#000000");
    }

    public String getNativeDecColor() {
        return preferences.getString(KEY_NATIVE_DESC_COLOR, "#000000");
    }

    public String getNativeSpaceboxColor() {
        return preferences.getString(KEY_NATIVE_SPACEBOX_COLOR, "#FFFFFF");
    }

    public String getNativeSpacebox() {
        return preferences.getString(KEY_NATIVE_SPACEBOX, "0");
    }

    public Boolean getFixCountry() {
        return preferences.getString(KEY_FIX_COUNTRY, "0").equals("1");
    }

    public String getSwipeCountry() {
        return preferences.getString(KEY_SWIPE_COUNTRY, "0");
    }

    public String getSwipeMain() {
        return preferences.getString(KEY_SWIPE_MAIN, "0");
    }

    public String getCountryStatus() {
        return preferences.getString(KEY_NEW_COUNTRY, "0");
    }

    public String getKeyExitnative_Status() {
        return preferences.getString(KEY_EXITNATIVE, "0");
    }

    public String getLanguageStatus() {
        return preferences.getString(KEY_NEW_LANGUAGE, "0");
    }


    public String getKeyViewpagerInter() {
        return preferences.getString(KEY_VIEWPAGER_INTER, "3");
    }


    public static void show_anims_3btn(final Activity activity, RelativeLayout rl_qureka_container, RelativeLayout rl_pred_container, RelativeLayout rl_mgl_container) {
        if (new Data_Preference(activity).getKeyQurekabuttonview().equals("1")) {
            rl_qureka_container.setVisibility(View.VISIBLE);
            View view = activity.getLayoutInflater().inflate(R.layout.sdk_qureka_anim_header, null);
            rl_qureka_container.addView(view);
            ImageView iv_anim = (ImageView) view.findViewById(R.id.iv_anim);
            Glide.with(activity).load(new Data_Preference(activity).getKeyQurekaimagelink()).centerInside().into(iv_anim);
            iv_anim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAppLive(activity, new Data_Preference(activity).getKeyQurekalink());

                }
            });
        } else {
            rl_qureka_container.setVisibility(View.GONE);
        }
        if (new Data_Preference(activity).getKeyPredchampbuttonview().equals("1")) {
            rl_pred_container.setVisibility(View.VISIBLE);
            View view = activity.getLayoutInflater().inflate(R.layout.sdk_qureka_anim_header, null);
            rl_pred_container.addView(view);
            ImageView iv_anim = (ImageView) view.findViewById(R.id.iv_anim);
            Glide.with(activity).load(new Data_Preference(activity).getKeyPredchampimagelink()).centerInside().into(iv_anim);
            iv_anim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAppLive(activity, new Data_Preference(activity).getKeyPredchamplink());
                }
            });
        } else {
            rl_pred_container.setVisibility(View.GONE);
        }
        if (new Data_Preference(activity).getKeyMglbuttonview().equals("1")) {
            rl_mgl_container.setVisibility(View.VISIBLE);
            View view = activity.getLayoutInflater().inflate(R.layout.sdk_qureka_anim_header, null);
            rl_mgl_container.addView(view);
            ImageView iv_anim = (ImageView) view.findViewById(R.id.iv_anim);
            Glide.with(activity).load(new Data_Preference(activity).getKeyMglimagelink()).centerInside().into(iv_anim);
            iv_anim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAppLive(activity, new Data_Preference(activity).getKeyMgllink());
                }
            });
        } else {
            rl_mgl_container.setVisibility(View.GONE);
        }
    }

    public static void show_Gift(final Activity activity, RelativeLayout rl_anim_container) {
        if (new Data_Preference(activity).getKeyHeaderview().equals("1")) {
            rl_anim_container.setVisibility(View.VISIBLE);
            rl_anim_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAppLive(activity, new Data_Preference(activity).getKeyHeaderlink());
                }
            });
        } else {
            rl_anim_container.setVisibility(View.GONE);
        }
    }

    public static void play_quiz(final Activity activity) {
        isAppLive(activity, new Data_Preference(activity).getKeyHeaderlink());
    }

    public static void show_anim_header(final Activity activity, RelativeLayout rl_anim_container) {
        if (new Data_Preference(activity).getKeyHeaderview().equals("1")) {
            rl_anim_container.setVisibility(View.VISIBLE);
            View view = activity.getLayoutInflater().inflate(R.layout.sdk_gif_anim_header, null);
            rl_anim_container.addView(view);
            ImageView iv_anim = (ImageView) view.findViewById(R.id.iv_anim);
            Glide.with(activity).load(new Data_Preference(activity).getKeyHeaderimagelink()).centerInside().into(iv_anim);
            iv_anim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAppLive(activity, new Data_Preference(activity).getKeyHeaderlink());
                }
            });
        } else {
            rl_anim_container.setVisibility(View.GONE);
        }
    }

    public static void openChromeCustomTabUrl(final Context context, String webUrl) {
        try {
            if (isAppInstalled1(context, "com.android.chrome")) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int coolorInt = Color.parseColor("#0162b1");
                builder.setToolbarColor(coolorInt);
                builder.setStartAnimations(context, R.anim.sdk_slide_in_right, R.anim.sdk_slide_out_left);
                builder.setExitAnimations(context, R.anim.sdk_slide_in_left, R.anim.sdk_slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(webUrl));
            } else {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int coolorInt = Color.parseColor("#0162b1");
                builder.setToolbarColor(coolorInt);
                builder.setStartAnimations(context, R.anim.sdk_slide_in_right, R.anim.sdk_slide_out_left);
                builder.setExitAnimations(context, R.anim.sdk_slide_in_left, R.anim.sdk_slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(webUrl));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isAppInstalled1(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isAppLive(Context context, String keyHeaderlink) {
        if (keyHeaderlink.contains("play.google")) {
            new AsyncTask<Void, Boolean, Boolean>() {
                private String[] str;

                @Override
                protected Boolean doInBackground(Void... voids) {
                    boolean b = isAppLiveOnPlayStore(keyHeaderlink);
                    return b;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        str = keyHeaderlink.split("=");
                        Uri uri = Uri.parse("market://details?id=" + str[1]);
                        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        try {
                            myAppLinkToMarket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(myAppLinkToMarket);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(context, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        openChromeCustomTabUrl(context, keyHeaderlink);
                    }

                }
            }.execute();
        } else {
            openChromeCustomTabUrl(context, keyHeaderlink);
        }
        return false;
    }

    public static boolean isAppLiveOnPlayStore(String appid) {
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(appid))
                    .openConnection();
            conn.setUseCaches(false);
            conn.connect();
            int status = conn.getResponseCode();
            conn.disconnect();
            return status == 200;
        } catch (Exception e) {
//            Log.e("isAppLiveOnPlayStore", e.toString());
        }
        return false;
    }


    //video call
    public void set_IncomingWelcome_Fix() {
        preferences.edit().putString(KEY_VC_INCOMING_WELCOME, "1").apply();
    }

    public String getIncoming_Counter() {
        return preferences.getString(KEY_VC_INCOMING_COUNTER, "0");
    }

    public String getIsLongApp() {
        return preferences.getString(KEY_VC_INCOMING_LONGAPP, "0");
    }

    public String getVideo_call() {
        return preferences.getString(KEY_VC_ON_OFF, "0");
    }


    //in-app
    public String getKeyInAppMode() {
        return preferences.getString(KEY_IN_APP_MODE, "0");
    }

    public String getKeyInAppPriceGold() {
        return preferences.getString(KEY_IN_APP_PRICE_GOLD, "0");
    }

    public String getKeyInAppPriceSilver() {
        return preferences.getString(KEY_IN_APP_PRICE_SILVER, "0");
    }

    public String getKeyInAppPriceBronze() {
        return preferences.getString(KEY_IN_APP_PRICE_BRONZE, "0");
    }

    public String getKeyInAppUpiId() {
        return preferences.getString(KEY_IN_APP_UPI_ID, "0");
    }

    public String getKeyInAppUpiName() {
        return preferences.getString(KEY_IN_APP_UPI_NAME, "0");
    }

    public String getKeyInAppUpiDesc() {
        return preferences.getString(KEY_IN_APP_UPI_DESC, "0");
    }


    //hot videos
    public String getKeyHotVideo1() {
        return preferences.getString(KEY_HOT_VIDEO_1, "0");
    }

    public String getKeyHotVideo2() {
        return preferences.getString(KEY_HOT_VIDEO_2, "0");
    }


    public void redeemGoldPlan() {
        preferences.edit().putString(KEY_VC_INCOMING_WELCOME, "1").apply();
        preferences.edit().putString(KEY_VC_INCOMING_LONGAPP, "0").apply();

        preferences.edit().putString(KEY_ADMOB_APPOPEN_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_INTER_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_BACK_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_NATIVE_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_BANNER_STATUS, "0").apply();
        preferences.edit().putString(KEY_FB_ADSHOW_STATUS, "0").apply();
        preferences.edit().putString(KEY_CUSTOM_ADSHOW_STATUS, "0").apply();
    }

    public void redeemSilverPlan() {
        preferences.edit().putString(KEY_ADMOB_APPOPEN_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_INTER_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_BACK_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_NATIVE_STATUS, "0").apply();
        preferences.edit().putString(KEY_ADMOB_BANNER_STATUS, "0").apply();
        preferences.edit().putString(KEY_FB_ADSHOW_STATUS, "0").apply();
        preferences.edit().putString(KEY_CUSTOM_ADSHOW_STATUS, "0").apply();
    }

    public void redeemBronzePlan() {

    }
}
