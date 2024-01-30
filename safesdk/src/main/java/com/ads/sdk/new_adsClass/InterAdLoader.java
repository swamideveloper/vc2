package com.ads.sdk.new_adsClass;

import static com.ads.sdk.new_configs.Data_Config.getPlayStoreUrl;
import static com.ads.sdk.new_configs.Data_Config.openChromeCustomTabUrl;
import static com.ads.sdk.new_configs.Data_Preference.app_count_click;
import static com.ads.sdk.splashconnect_act.customAdData;
import static com.ads.sdk.splashconnect_act.getRandomNum;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.ads.sdk.R;
import com.ads.sdk.custom.intads_activity;
import com.ads.sdk.new_configs.Data_Config;
import com.ads.sdk.new_configs.Data_Preference;
import com.ads.sdk.new_interfaces.OnShowAdCompleteListener;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class InterAdLoader {

    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialBackAd;
    public static boolean isAppOpenShowingAd = false;
    public static boolean isShowingAd = false;
    public static boolean isShowingBackAd = false;
    public static boolean isShowingFBAd = false;
    private int count_click = -1;
    private int count_click_back = -1;
    private Dialog dialog;
    com.facebook.ads.InterstitialAd fbinterstitialAd1;
    private static InterAdLoader instance;
    private boolean isLoadingAd = false;
    private boolean isLoadingbackAd = false;
    public Activity activity;
    private Dialog progressDialog;
    private OnShowAdCompleteListener listener;


    public static InterAdLoader getManager(Activity activity) {
        if (instance == null) {
            instance = new InterAdLoader(activity);
        }
        return instance;
    }

    public InterAdLoader(Activity activity1) {
        activity = activity1;
    }

    public void showInterAd(Activity activity, OnShowAdCompleteListener listener, final int how_many_clicks) {
        count_click++;
        if (Data_Config.isNetworkAvailable(activity)) {
            if (new Data_Preference(activity).getAdmobInterStatus().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1")) {
                    if (how_many_clicks != 0) {
                        if (count_click % how_many_clicks == 0) {
                            //intads_activity.newIntent(activity, listener);
                            directHit(activity);
                        } else {
                            if (listener != null) {
                                listener.onShowAdComplete();
                            }
                        }
                    } else {
                        if (new Data_Preference(activity).getCustomInter().equals("1")) {
                            //intads_activity.newIntent(activity, listener);
                            directHit(activity);
                        } else {
                            if (listener != null) {
                                listener.onShowAdComplete();
                            }
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onShowAdComplete();
                    }
                }
                return;
            }
            InterAdsShow(activity, listener, how_many_clicks);
        } else {
            if (listener != null) {
                listener.onShowAdComplete();
            }
        }
    }

    private void directHit(Activity activity) {
        int pos = getRandomNum();
        String action_str = customAdData.get(pos).getUrl();
        if (action_str.contains("play.google.com")) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getPlayStoreUrl(action_str))));
        } else {
            openChromeCustomTabUrl(activity, action_str);
        }
    }

    public void showFBAd(final Activity activity, final OnShowAdCompleteListener listener, int how_many_clicks) {
        dialog = new Dialog(activity, R.style.full_screen_dialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")) {
            if (how_many_clicks != 0) {
                if (count_click % how_many_clicks != 0) {
                    if (!activity.isFinishing()) {
                        dialog.show();
                    }

                    fbinterstitialAd1 = new com.facebook.ads.InterstitialAd(activity, new Data_Preference(activity).getFbInterID());
                    fbinterstitialAd1.loadAd((com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig) fbinterstitialAd1.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            isShowingFBAd = false;
                            dialog.dismiss();
                            listener.onShowAdComplete();
                        }

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {
                            isShowingFBAd = false;
                            dialog.dismiss();
                            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1")) {
                                //intads_activity.newIntent(activity, listener);
                                directHit(activity);
                            } else {
                                if (listener != null) {
                                    listener.onShowAdComplete();
                                }
                            }
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            dialog.dismiss();
                            isShowingFBAd = true;
                            fbinterstitialAd1.show();
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    }).build());
                } else {
                    if (listener != null) {
                        listener.onShowAdComplete();
                    }
                }
            } else {
                if (new Data_Preference(activity).getAdmobInterStatus().equals("1")) {
                    if (listener != null) {
                        listener.onShowAdComplete();
                    }
                } else {
                    if (!activity.isFinishing()) {
                        dialog.show();
                    }
                    fbinterstitialAd1 = new com.facebook.ads.InterstitialAd(activity, new Data_Preference(activity).getFbInterID());
                    fbinterstitialAd1.loadAd((com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig) fbinterstitialAd1.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            isShowingFBAd = false;
                            dialog.dismiss();
                            if (listener != null) {
                                listener.onShowAdComplete();
                            }
                        }

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {
                            isShowingFBAd = false;
                            dialog.dismiss();
                            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1")) {
                                //intads_activity.newIntent(activity, listener);
                                directHit(activity);
                            } else {
                                if (listener != null) {
                                    listener.onShowAdComplete();
                                }
                            }
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            dialog.dismiss();
                            isShowingFBAd = true;
                            fbinterstitialAd1.show();
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    }).build());
                }
            }
        } else {
            if (listener != null) {
                listener.onShowAdComplete();
            }
        }
    }


    public void InterAdsShow(Activity activity, OnShowAdCompleteListener listener, int how_many_clicks) {
        if (new Data_Preference(activity).getAdmobInterStatus().equals("1")) {
            if (how_many_clicks != 0) {
                if (count_click % how_many_clicks != 0) {
                    if (new Data_Preference(activity).getAdmobInterStatus().equals("1") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    } else {
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    }
                } else {
                    displayAdmobInter(activity, listener);
                }
            } else {
                displayAdmobInter(activity, listener);
            }
        } else {
            if (listener != null) {
                listener.onShowAdComplete();
            }
        }
    }

    public void loadAd(Activity activity, boolean preload) {
        if (isLoadingAd || isAdAvailable()) {
            return;
        }
        isLoadingAd = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, new Data_Preference(activity).getAdmobInterstitialID(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                isLoadingAd = false;
                interstitialAd = ad;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isLoadingAd = false;
                interstitialAd = null;
                if (!preload) {
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1") && new Data_Preference(activity).getAdmobInterStatus().equals("1")) {
                        //intads_activity.newIntent(activity, listener);
                        directHit(activity);
                    } else {
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    }
                }
            }
        });
    }

    public void loadBackAd(Activity activity) {
        if (isLoadingbackAd || isBackAdAvailable()) {
            return;
        }
        isLoadingbackAd = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, new Data_Preference(activity).getAdmobInterstitialID(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                isLoadingbackAd = false;
                interstitialBackAd = ad;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isLoadingbackAd = false;
                interstitialBackAd = null;
            }
        });
    }

    private boolean isAdAvailable() {
        return interstitialAd != null;
    }

    private boolean isBackAdAvailable() {
        return interstitialBackAd != null;
    }

    public void ShowInterBackAd(Activity activity, OnShowAdCompleteListener listener, int how_many_clicks) {
        count_click_back++;

        if (new Data_Preference(activity).getAdmobBackStatus().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {

            //case===========with custom
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomBack().equals("1")) {


                if (how_many_clicks != 0) {
                    if (count_click_back % how_many_clicks == 0) {
                        //intads_activity.newIntent(activity, listener);
                        directHit(activity);
                    } else {
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    }
                } else {
                    if (new Data_Preference(activity).getCustomBack().equals("1")) {
                        //intads_activity.newIntent(activity, listener);
                        directHit(activity);
                    } else {
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    }
                }
            } else {
                if (listener != null) {
                    listener.onShowAdComplete();
                }
            }
            return;
        }

        //case====
        if (new Data_Preference(activity).getAdmobBackStatus().equals("1")) {
            if (how_many_clicks != 0) {
                if (count_click_back % how_many_clicks == 0) {
                    if (!isBackAdAvailable()) {
                        loadBackAd(activity);
                        listener.onShowAdComplete();
                        return;
                    }
                    interstitialBackAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            isShowingBackAd = false;
                            interstitialBackAd = null;
                            loadBackAd(activity);
                            if (listener != null) {
                                listener.onShowAdComplete();
                            }
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            isShowingBackAd = false;
                            interstitialBackAd = null;
                            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1")) {
                                //intads_activity.newIntent(activity, listener);
                                directHit(activity);
                            } else {
                                if (listener != null) {
                                    listener.onShowAdComplete();
                                }
                            }
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                        }
                    });
                    isShowingBackAd = true;
                    interstitialBackAd.show(activity);


                } else {
                    if (listener != null) {
                        listener.onShowAdComplete();
                    }
                }
            } else {

                if (!isBackAdAvailable()) {
                    loadBackAd(activity);
                    listener.onShowAdComplete();
                    return;
                }
                interstitialBackAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        isShowingBackAd = false;
                        interstitialBackAd = null;
                        loadBackAd(activity);
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        isShowingBackAd = false;
                        interstitialBackAd = null;
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1")) {
                            //intads_activity.newIntent(activity, listener);
                            directHit(activity);
                        } else {
                            if (listener != null) {
                                listener.onShowAdComplete();
                            }
                        }
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }
                });
                isShowingBackAd = true;
                interstitialBackAd.show(activity);
            }
        } else {

            if (listener != null) {
                listener.onShowAdComplete();
            }
        }
    }

    public void displayAdmobInter(Activity activity, final OnShowAdCompleteListener listener) {
        if (!new Data_Preference(activity).getAdmobInterstitialID().isEmpty()) {
            if (!isAdAvailable()) {
                this.listener = listener;
                loadAd(activity, false);
//                listener.onShowAdComplete();
                return;
            }
            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    isShowingAd = false;
                    interstitialAd = null;
                    loadAd(activity, false);
                    if (listener != null) {
                        listener.onShowAdComplete();
                    }
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    isShowingAd = false;
                    interstitialAd = null;
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1") && new Data_Preference(activity).getAdmobInterStatus().equals("1")) {
                        //intads_activity.newIntent(activity, listener);
                        directHit(activity);
                    } else {
                        if (listener != null) {
                            listener.onShowAdComplete();
                        }
                    }
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }
            });
            isShowingAd = true;
            interstitialAd.show(activity);
        } else {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomInter().equals("1")) {
                //intads_activity.newIntent(activity, listener);
                directHit(activity);
            } else {
                if (listener != null) {
                    listener.onShowAdComplete();
                }
            }
        }
    }

    public void adcount() {
        count_click++;
    }

    public void showFacebookAds(Activity activity) {
        if (activity.getIntent().getIntExtra("issplash", 0) == 2) {
            InterAdLoader.getManager(activity).showFBAd(activity, new OnShowAdCompleteListener() {
                @Override
                public void onShowAdComplete() {
                }
            }, app_count_click);
        } else if (activity.getIntent().getIntExtra("issplash", 0) == 1) {
            InterAdLoader.getManager(activity).adcount();
        } else {
            InterAdLoader.getManager(activity).showFBAd(activity, new OnShowAdCompleteListener() {
                @Override
                public void onShowAdComplete() {
                }
            }, app_count_click);
        }
    }
}
