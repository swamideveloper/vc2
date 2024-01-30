package com.ads.sdk.new_adsClass;


import static com.google.android.gms.ads.nativead.NativeAdOptions.ADCHOICES_TOP_LEFT;
import static com.google.android.gms.ads.nativead.NativeAdOptions.ADCHOICES_TOP_RIGHT;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.ads.sdk.R;
import com.ads.sdk.custom.nativecustom;
import com.ads.sdk.new_configs.Data_Config;
import com.ads.sdk.new_configs.Data_Preference;
import com.facebook.ads.Ad;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

public class AD_native {

    static Activity activity;
    static String nativeloading_status = "show";
    static AD_native mInstance;
    NativeAd native_PreloadAd = null;
    int countclick = -1;
    View adViewBanner;
    AdView adViewPreloadMediumBanner = null;
    View adViewBanner1;
    AdView adViewPreloadMediumBanner1 = null;
    View adViewBanner2;
    AdView adViewPreloadMediumBanner2 = null;

    public AD_native(Activity activity) {
        AD_native.activity = activity;
    }

    public static AD_native getInstance(Activity activity) {
        AD_native.activity = activity;
        if (mInstance == null) {
            mInstance = new AD_native(activity);
        }
        return mInstance;
    }

    public void nativeadcount() {
        countclick++;
    }

    //With Preload Big & Small native
    public void show_native_AD(final Activity activity, final ViewGroup nativeAdContainer, final int type, int howmanyclick) {
        if (Data_Config.isNetworkAvailable(activity)) {
            if (new Data_Preference(activity).getAdmobNativeStatus().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                    nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                } else {
                    if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                        nativeAdContainer.setVisibility(View.GONE);
                    }
                }
                return;
            }
            countclick++;
            if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && new Data_Preference(activity).getFbAdshowStatus().equals("1")) {
                if (howmanyclick != 0) {
                    if (countclick % howmanyclick != 0) {
                        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
                            showFacebookNative(activity, nativeAdContainer, type);
                        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
                            showNativeFacebookBanner(activity, nativeAdContainer, type);
                        }
                    } else {
                        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                            showMediumNative(activity, nativeAdContainer, type);
                        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                            showSmallNative(activity, nativeAdContainer, type);
                        }
                    }
                } else {
                    if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                        showMediumNative(activity, nativeAdContainer, type);
                    } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                        showSmallNative(activity, nativeAdContainer, type);
                    }
                }
            } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
                if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                    showMediumNative(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                    showSmallNative(activity, nativeAdContainer, type);
                }
            } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("1")) {
                if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
                    showFacebookNative(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
                    showNativeFacebookBanner(activity, nativeAdContainer, type);
                }
            }
        } else {
            return;
        }
    }

    public void show_native_AD2(final Activity activity, final ViewGroup nativeAdContainer, final int type, int howmanyclick) {
        if (Data_Config.isNetworkAvailable(activity)) {
            if (new Data_Preference(activity).getAdmobNativeStatus().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                    nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                } else {
                    if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                        nativeAdContainer.setVisibility(View.GONE);
                    }
                }
                return;
            }
            countclick++;
            if (howmanyclick != 0) {
                if (countclick % howmanyclick != 0) {
                    if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
                        showFacebookNative2(activity, nativeAdContainer, type);
                    } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
                        showNativeFacebookBanner2(activity, nativeAdContainer, type);
                    }
                } else {
                    if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                        showMediumNative2(activity, nativeAdContainer, type);
                    } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                        showSmallNative2(activity, nativeAdContainer, type);
                    }
                }
            } else {
                if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                    showMediumNative2(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                    showSmallNative2(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
                    showFacebookNative2(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
                    showNativeFacebookBanner2(activity, nativeAdContainer, type);
                }
            }
        } else {
            return;
        }
    }

    public void show_native_AD3(final Activity activity, final ViewGroup nativeAdContainer, final int type, int howmanyclick) {
        if (Data_Config.isNetworkAvailable(activity)) {
            if (new Data_Preference(activity).getAdmobNativeStatus().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                    nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                } else {
                    if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                        nativeAdContainer.setVisibility(View.GONE);
                    }
                }
                return;
            }
            countclick++;
            if (howmanyclick != 0) {
                if (countclick % howmanyclick != 0) {
                    if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
                        showFacebookNative3(activity, nativeAdContainer, type);
                    } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
                        showNativeFacebookBanner3(activity, nativeAdContainer, type);
                    }
                } else {
                    if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                        showMediumNative3(activity, nativeAdContainer, type);
                    } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                        showSmallNative3(activity, nativeAdContainer, type);
                    }
                }
            } else {
                if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
                    showMediumNative3(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
                    showSmallNative3(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
                    showFacebookNative3(activity, nativeAdContainer, type);
                } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
                    showNativeFacebookBanner3(activity, nativeAdContainer, type);
                }
            }
        } else {
            return;
        }
    }

    public void showMediumNative(final Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getAdmobNative().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
            } else {
                if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
            if (new Data_Preference(activity).getAdmobNative().equals("1")) {
                final View adView = getView_AD(activity, nativeAdContainer, type);
                SpaceBox_AD(activity, adView, nativeAdContainer);
                setView_AD_Height(activity, nativeAdContainer, type);
                if (native_PreloadAd != null) {
                    populate_nativeAD_View(activity, nativeAdContainer, native_PreloadAd, adView, type);
                    native_PreloadAd = null;
                    nativeloading_status = "show";
                    preLoadNativeAds(activity, type);
                    return;
                }
                AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        boolean isDestroyed = false;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            isDestroyed = activity.isDestroyed();
                        }
                        if (isDestroyed || activity.isFinishing() || activity.isChangingConfigurations()) {
                            nativeAd.destroy();
                            return;
                        }
                        if (native_PreloadAd != null) {
                            native_PreloadAd.destroy();
                        }
                        nativeloading_status = "loaded";
                        native_PreloadAd = nativeAd;
                        populate_nativeAD_View(activity, nativeAdContainer, native_PreloadAd, adView, type);
                        native_PreloadAd = null;
                        nativeloading_status = "show";
                        preLoadNativeAds(activity, type);
                    }
                });
                Load_AD(builder, nativeAdContainer, type);
            } else {
                adViewBanner = activity.getLayoutInflater().inflate(R.layout.unitad_admob_medium_bannerad, null);
                adViewBanner.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (activity.getResources().getDisplayMetrics().heightPixels * 37) / 100));
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(adViewBanner);
                adViewPreloadMediumBanner = new AdView(activity);
                AdRequest adRequest = new AdRequest.Builder().build();

                adViewPreloadMediumBanner.setAdUnitId(new Data_Preference(activity).getAdmobBannerID());
                adViewPreloadMediumBanner.setAdSize(AdSize.MEDIUM_RECTANGLE);
                adViewPreloadMediumBanner.loadAd(adRequest);
                adViewPreloadMediumBanner.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdClosed() {
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        adViewPreloadMediumBanner = null;
                        nativeAdContainer.removeAllViews();
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                            nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                        } else {
                            if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                                nativeAdContainer.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onAdImpression() {
                    }

                    @Override
                    public void onAdLoaded() {
                        final FrameLayout middum_banner_container = adViewBanner.findViewById(R.id.middum_banner_container);
                        if (((LinearLayout) adViewBanner.findViewById(R.id.ll_space_banner) != null))
                            ((LinearLayout) adViewBanner.findViewById(R.id.ll_space_banner)).setVisibility(View.GONE);
                        middum_banner_container.removeAllViews();
                        middum_banner_container.addView(adViewPreloadMediumBanner);
//                        adViewPreloadMediumBanner = null;
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                });
            }
        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
            showFacebookNative(activity, nativeAdContainer, type);
        }
    }

    public void showMediumNative2(final Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getAdmobNative().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
            } else {
                if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
            if (new Data_Preference(activity).getAdmobNative().equals("1")) {
                final View adView = getView_AD(activity, nativeAdContainer, type);
                SpaceBox_AD(activity, adView, nativeAdContainer);
                setView_AD_Height(activity, nativeAdContainer, type);
                AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        nativeAdContainer.removeAllViews();
                        populate_nativeAD_View(activity, nativeAdContainer, nativeAd, adView, type);
                    }
                });
                Load_AD2(builder, nativeAdContainer, type);
            } else {
                adViewBanner1 = activity.getLayoutInflater().inflate(R.layout.unitad_admob_medium_bannerad, null);
                adViewBanner1.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (activity.getResources().getDisplayMetrics().heightPixels * 37) / 100));
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(adViewBanner1);
                adViewPreloadMediumBanner1 = new AdView(activity);
                AdRequest adRequest = new AdRequest.Builder().build();
                adViewPreloadMediumBanner1.setAdUnitId(new Data_Preference(activity).getAdmobBannerID());
                adViewPreloadMediumBanner1.setAdSize(AdSize.MEDIUM_RECTANGLE);
                adViewPreloadMediumBanner1.loadAd(adRequest);
                adViewPreloadMediumBanner1.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdClosed() {
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        adViewPreloadMediumBanner1 = null;
                        nativeAdContainer.removeAllViews();
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                            nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                        } else {
                            if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                                nativeAdContainer.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onAdImpression() {
                    }

                    @Override
                    public void onAdLoaded() {
                        final FrameLayout middum_banner_container = adViewBanner1.findViewById(R.id.middum_banner_container);
                        if (((LinearLayout) adViewBanner1.findViewById(R.id.ll_space_banner) != null))
                            ((LinearLayout) adViewBanner1.findViewById(R.id.ll_space_banner)).setVisibility(View.GONE);
                        middum_banner_container.removeAllViews();
                        middum_banner_container.addView(adViewPreloadMediumBanner1);
//                        adViewPreloadMediumBanner1 = null;
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                });
            }
        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
            showFacebookNative2(activity, nativeAdContainer, type);
        }
    }

    public void showMediumNative3(final Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getAdmobNative().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
            } else {
                if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
            if (new Data_Preference(activity).getAdmobNative().equals("1")) {
                final View adView = getView_AD(activity, nativeAdContainer, type);
                SpaceBox_AD(activity, adView, nativeAdContainer);
                setView_AD_Height(activity, nativeAdContainer, type);

                AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        nativeAdContainer.removeAllViews();
                        populate_nativeAD_View(activity, nativeAdContainer, nativeAd, adView, type);
                    }
                });
                Load_AD3(builder, nativeAdContainer, type);
            } else {
                adViewBanner2 = activity.getLayoutInflater().inflate(R.layout.unitad_admob_medium_bannerad, null);
                adViewBanner2.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (activity.getResources().getDisplayMetrics().heightPixels * 37) / 100));
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(adViewBanner2);
                adViewPreloadMediumBanner2 = new AdView(activity);
                AdRequest adRequest = new AdRequest.Builder().build();

                adViewPreloadMediumBanner2.setAdUnitId(new Data_Preference(activity).getAdmobBannerID());
                adViewPreloadMediumBanner2.setAdSize(AdSize.MEDIUM_RECTANGLE);
                adViewPreloadMediumBanner2.loadAd(adRequest);
                adViewPreloadMediumBanner2.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdClosed() {
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        adViewPreloadMediumBanner = null;
                        nativeAdContainer.removeAllViews();
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                            nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                        } else {
                            if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                                nativeAdContainer.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onAdImpression() {
                    }

                    @Override
                    public void onAdLoaded() {
                        final FrameLayout middum_banner_container = adViewBanner2.findViewById(R.id.middum_banner_container);
                        if (((LinearLayout) adViewBanner2.findViewById(R.id.ll_space_banner) != null))
                            ((LinearLayout) adViewBanner2.findViewById(R.id.ll_space_banner)).setVisibility(View.GONE);
                        middum_banner_container.removeAllViews();
                        middum_banner_container.addView(adViewPreloadMediumBanner2);
//                        adViewPreloadMediumBanner2 = null;
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                });
            }
        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
            showFacebookNative3(activity, nativeAdContainer, type);
        }
    }

    public void showSmallNative(final Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getAdmobNative().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
            } else {
                if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
            if (new Data_Preference(activity).getAdmobBanner().equals("1")) {
                View adView = getView_AD(activity, nativeAdContainer, type);
                SpaceBox_AD(activity, adView, nativeAdContainer);
                setView_AD_Height(activity, nativeAdContainer, type);
                if (native_PreloadAd != null) {
                    populate_nativeAD_View(activity, nativeAdContainer, native_PreloadAd, adView, type);
                    native_PreloadAd = null;
                    nativeloading_status = "show";
//                    Log.e("TAG", "Preloaded showSmallNative: ");
                    preLoadNativeAds(activity, type);
                    return;
                }
                AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        boolean isDestroyed = false;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            isDestroyed = activity.isDestroyed();
                        }
                        if (isDestroyed || activity.isFinishing() || activity.isChangingConfigurations()) {
                            nativeAd.destroy();
                            return;
                        }
                        if (native_PreloadAd != null) {
                            native_PreloadAd.destroy();
                        }
                        nativeloading_status = "loaded";
                        native_PreloadAd = nativeAd;
                        populate_nativeAD_View(activity, nativeAdContainer, native_PreloadAd, adView, type);
                        nativeloading_status = "show";
                        native_PreloadAd = null;
//                        Log.e("TAG", "show showSmallNative: ");
                        preLoadNativeAds(activity, type);
                    }
                });
                Load_AD(builder, nativeAdContainer, type);
            } else {
                adViewBanner = activity.getLayoutInflater().inflate(R.layout.unitad_admob_smart_bannerad, null);
                adViewBanner.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) ((activity.getResources().getDisplayMetrics().heightPixels * 17.7) / 100)));
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(adViewBanner);

                adViewPreloadMediumBanner = new AdView(activity);
                AdRequest adRequest = new AdRequest.Builder().build();
                adViewPreloadMediumBanner.setAdUnitId(new Data_Preference(activity).getAdmobBannerID());
                adViewPreloadMediumBanner.setAdSize(AdSize.LARGE_BANNER);
                adViewPreloadMediumBanner.loadAd(adRequest);
                adViewPreloadMediumBanner.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                        // Code to be executed when the user clicks on an ad.
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the mainapp after tapping on an ad.
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Code to be executed when an ad request fails.
                        adViewPreloadMediumBanner = null;
                        nativeAdContainer.removeAllViews();
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                            nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                        } else {
                            if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                                nativeAdContainer.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onAdImpression() {
                        // Code to be executed when an impression is recorded
                        // for an ad.
                    }

                    @Override
                    public void onAdLoaded() {
                        FrameLayout middum_banner_container = adViewBanner.findViewById(R.id.smart_banner_container);
                        if (((LinearLayout) adViewBanner.findViewById(R.id.ll_space_banner) != null))
                            ((LinearLayout) adViewBanner.findViewById(R.id.ll_space_banner)).setVisibility(View.GONE);
                        middum_banner_container.removeAllViews();
                        middum_banner_container.addView(adViewPreloadMediumBanner);
//                        adViewPreloadMediumBanner = null;
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                });
            }
        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
            showNativeFacebookBanner(activity, nativeAdContainer, type);
        }
    }

    public void showSmallNative2(final Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getAdmobNative().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
            } else {
                if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
            if (new Data_Preference(activity).getAdmobBanner().equals("1")) {
                View adView = getView_AD(activity, nativeAdContainer, type);
                SpaceBox_AD(activity, adView, nativeAdContainer);
                setView_AD_Height(activity, nativeAdContainer, type);
                AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        nativeAdContainer.removeAllViews();
                        populate_nativeAD_View(activity, nativeAdContainer, nativeAd, adView, type);
                    }
                });
                Load_AD2(builder, nativeAdContainer, type);
            } else {
                adViewBanner1 = activity.getLayoutInflater().inflate(R.layout.unitad_admob_smart_bannerad, null);
                adViewBanner1.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) ((activity.getResources().getDisplayMetrics().heightPixels * 17.7) / 100)));
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(adViewBanner1);
                adViewPreloadMediumBanner1 = new AdView(activity);
                AdRequest adRequest = new AdRequest.Builder().build();

                adViewPreloadMediumBanner1.setAdUnitId(new Data_Preference(activity).getAdmobBannerID());
                adViewPreloadMediumBanner1.setAdSize(AdSize.LARGE_BANNER);
                adViewPreloadMediumBanner1.loadAd(adRequest);
                adViewPreloadMediumBanner1.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdClosed() {
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        adViewPreloadMediumBanner1 = null;
                        nativeAdContainer.removeAllViews();
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                            nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                        } else {
                            if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                                nativeAdContainer.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onAdImpression() {
                        // Code to be executed when an impression is recorded
                        // for an ad.
                    }

                    @Override
                    public void onAdLoaded() {
                        final FrameLayout middum_banner_container = adViewBanner1.findViewById(R.id.smart_banner_container);
                        if (((LinearLayout) adViewBanner1.findViewById(R.id.ll_space_banner) != null))
                            ((LinearLayout) adViewBanner1.findViewById(R.id.ll_space_banner)).setVisibility(View.GONE);
                        middum_banner_container.removeAllViews();
                        middum_banner_container.addView(adViewPreloadMediumBanner1);
//                        adViewPreloadMediumBanner1 = null;
                    }

                    @Override
                    public void onAdOpened() {
                    }
                });
            }
        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
            showNativeFacebookBanner2(activity, nativeAdContainer, type);
        }
    }

    public void showSmallNative3(final Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getAdmobNative().equals("0") && new Data_Preference(activity).getFbAdshowStatus().equals("0")) {
            if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
            } else {
                if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
            if (new Data_Preference(activity).getAdmobBanner().equals("1")) {
                View adView = getView_AD(activity, nativeAdContainer, type);
                SpaceBox_AD(activity, adView, nativeAdContainer);
                setView_AD_Height(activity, nativeAdContainer, type);
                AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        nativeAdContainer.removeAllViews();
                        populate_nativeAD_View(activity, nativeAdContainer, nativeAd, adView, type);
                    }
                });
                Load_AD3(builder, nativeAdContainer, type);
            } else {
                adViewBanner2 = activity.getLayoutInflater().inflate(R.layout.unitad_admob_smart_bannerad, null);
                adViewBanner2.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) ((activity.getResources().getDisplayMetrics().heightPixels * 17.7) / 100)));
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(adViewBanner2);
                adViewPreloadMediumBanner2 = new AdView(activity);
                AdRequest adRequest = new AdRequest.Builder().build();

                adViewPreloadMediumBanner2.setAdUnitId(new Data_Preference(activity).getAdmobBannerID());
                adViewPreloadMediumBanner2.setAdSize(AdSize.LARGE_BANNER);
                adViewPreloadMediumBanner2.loadAd(adRequest);
                adViewPreloadMediumBanner2.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdClosed() {
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        adViewPreloadMediumBanner2 = null;
                        nativeAdContainer.removeAllViews();
                        if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                            nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                        } else {
                            if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                                nativeAdContainer.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onAdImpression() {
                        // Code to be executed when an impression is recorded
                        // for an ad.
                    }

                    @Override
                    public void onAdLoaded() {
                        FrameLayout middum_banner_container = adViewBanner2.findViewById(R.id.smart_banner_container);
                        if (((LinearLayout) adViewBanner2.findViewById(R.id.ll_space_banner) != null))
                            ((LinearLayout) adViewBanner2.findViewById(R.id.ll_space_banner)).setVisibility(View.GONE);
                        middum_banner_container.removeAllViews();
                        middum_banner_container.addView(adViewPreloadMediumBanner2);
//                        adViewPreloadMediumBanner2 = null;
                    }

                    @Override
                    public void onAdOpened() {
                    }
                });
            }
        } else if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
            showNativeFacebookBanner3(activity, nativeAdContainer, type);
        }
    }

    private void populate_nativeAD_View(Activity activity, ViewGroup nativeAdContainer, NativeAd nativeAd, View adView, int type) {
        ((TextView) adView.findViewById(R.id.ad_call_to_action)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(new Data_Preference(activity).getNativeBtnColor())));
        ((TextView) adView.findViewById(R.id.adText)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(new Data_Preference(activity).getKeyNativeTagColor())));
        ((TextView) adView.findViewById(R.id.ad_headline)).setTextColor(Color.parseColor(new Data_Preference(activity).getNativeTitleColor()));
        ((TextView) adView.findViewById(R.id.ad_body)).setTextColor(Color.parseColor(new Data_Preference(activity).getNativeDecColor()));
        if (((LinearLayout) adView.findViewById(R.id.ll_space) != null))
            ((CardView) adView.findViewById(R.id.ll_space_card)).setCardBackgroundColor(Color.parseColor(new Data_Preference(activity).getNativeSpaceboxColor()));
        ((CardView) adView.findViewById(R.id.adview_card)).setCardBackgroundColor(Color.parseColor(new Data_Preference(activity).getNativeBgColor()));

        final NativeAdView adView1 = adView.findViewById(R.id.adview);
        adView1.setVisibility(View.VISIBLE);

        // Set other ad assets.
        adView1.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView1.setBodyView(adView.findViewById(R.id.ad_body));
        adView1.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView1.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView1.setPriceView(adView.findViewById(R.id.ad_price));
        adView1.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView1.setStoreView(adView.findViewById(R.id.ad_store));
        adView1.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // Set the media view.
        if (type == 1) {
            adView1.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
            adView1.getMediaView().setMediaContent(nativeAd.getMediaContent());
            // Get the video controller for the ad. One will always be provided, even if the ad doesn't
            // have a video asset.
            VideoController vc = nativeAd.getMediaContent().getVideoController();
            // Updates the UI to say whether or not this ad has a video asset.
            if (vc.hasVideoContent()) {
                // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
                // VideoController will call methods on this object when events occur in the video
                // lifecycle.
                vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    @Override
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        }
        // The headline and mediaContent are guaranteed to be in every NativeAd.
        ((TextView) adView1.getHeadlineView()).setText(nativeAd.getHeadline());
        // These assets aren't guaranteed to be in every NativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView1.getBodyView().setVisibility(View.GONE);
        } else {
            adView1.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView1.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            adView1.getCallToActionView().setVisibility(View.GONE);
        } else {
            adView1.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView1.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getIcon() == null) {
            adView1.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView1.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            if (type != 2) {
                adView1.getIconView().setVisibility(View.VISIBLE);
            }
        }
        if (nativeAd.getPrice() == null) {
            adView1.getPriceView().setVisibility(View.GONE);
        } else {
            adView1.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView1.getPriceView()).setText(nativeAd.getPrice());
        }
        if (nativeAd.getStore() == null) {
            adView1.getStoreView().setVisibility(View.GONE);
        } else {
            adView1.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView1.getStoreView()).setText(nativeAd.getStore());
        }
        if (type == 2 || type == 3) {
            if (type == 2) {
                if (nativeAd.getStarRating() == null) {
                    adView1.getStarRatingView().setVisibility(View.GONE);
                } else {
                    ((RatingBar) adView1.getStarRatingView())
                            .setRating(nativeAd.getStarRating().floatValue());
                    adView1.getStarRatingView().setVisibility(View.VISIBLE);
                }
            } else {
                adView1.getStarRatingView().setVisibility(View.GONE);
            }
        } else {
            if (nativeAd.getStarRating() == null) {
                adView1.getStarRatingView().setVisibility(View.GONE);
            } else {
                ((RatingBar) adView1.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
                adView1.getStarRatingView().setVisibility(View.VISIBLE);
            }
        }
        if (nativeAd.getAdvertiser() == null) {
            adView1.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView1.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView1.getAdvertiserView().setVisibility(View.GONE);
        }
        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView1.setNativeAd(nativeAd);
        nativeAdContainer.removeAllViews();
        nativeAdContainer.addView(adView);
    }

    private void SpaceBox_AD(Activity activity, View adView, ViewGroup nativeAdContainer) {
        if (new Data_Preference(activity).getNativeSpacebox().equals("1") && new Data_Preference(activity).getAdsFlag()) {
            if (((LinearLayout) adView.findViewById(R.id.ll_space) != null)) {
                ((LinearLayout) adView.findViewById(R.id.ll_space)).setVisibility(View.VISIBLE);
                ((CardView) adView.findViewById(R.id.ll_space_card)).setCardBackgroundColor(Color.parseColor(new Data_Preference(activity).getNativeSpaceboxColor()));
            }
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(adView);
        } else if (new Data_Preference(activity).getAdsFlag() &&
                new Data_Preference(activity).getCustomAdshowStatus().equals("1") &&
                new Data_Preference(activity).getCustomNative().equals("1")) {
            nativeAdContainer.setVisibility(View.VISIBLE);
        } else {
            if (!new Data_Preference(activity).getAdsFlag())
                nativeAdContainer.setVisibility(View.GONE);
            if (((LinearLayout) adView.findViewById(R.id.ll_space) != null))
                ((LinearLayout) adView.findViewById(R.id.ll_space)).setVisibility(View.GONE);
        }
    }

    private View getView_AD(Activity activity, ViewGroup nativeAdContainer, int type) {
        View adView;
        switch (type) {
            case 1:
                adView = activity.getLayoutInflater().inflate(R.layout.ad_unit_admob_med, null);
                break;
            case 2:
                adView = activity.getLayoutInflater().inflate(R.layout.ad_unit_admob_small, null);
                break;
            default:
                adView = activity.getLayoutInflater().inflate(R.layout.ad_unit_admob_med, null);
                setView_AD_Height(activity, nativeAdContainer, type);
                break;
        }
        return adView;
    }

    private void setView_AD_Height(Activity activity, ViewGroup view, int type) {
        switch (type) {
            case 1:
                view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (activity.getResources().getDisplayMetrics().heightPixels * 35) / 100));
                break;
        }
    }

    //For Preload Ad Method Big & Small native
    public void preLoadNativeAds(Activity activity, int type) {
        if (!nativeloading_status.equals("show") && !nativeloading_status.equals("fail")) {
            return;
        }
        AdLoader.Builder builder = new AdLoader.Builder(activity, new Data_Preference(activity).getAdmobNativeID());
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {
                nativeloading_status = "loaded";
                native_PreloadAd = nativeAd;
            }
        });
        NativeAdOptions adOptions = new NativeAdOptions.Builder().setAdChoicesPlacement(ADCHOICES_TOP_LEFT).build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdImpression() {

            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void Load_AD(AdLoader.Builder builder, final ViewGroup nativeAdContainer, final int type) {
        VideoOptions videoOptions = new VideoOptions.Builder().build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).setAdChoicesPlacement(ADCHOICES_TOP_RIGHT).build();
        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdImpression() {
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                nativeloading_status = "fail";
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                    nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                } else {
                    if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                        nativeAdContainer.setVisibility(View.GONE);
                    }
                }
            }
        }).build();
        nativeloading_status = "loading";
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void Load_AD2(AdLoader.Builder builder, final ViewGroup nativeAdContainer, final int type) {
        VideoOptions videoOptions = new VideoOptions.Builder().build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).setAdChoicesPlacement(ADCHOICES_TOP_RIGHT).build();
        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdImpression() {
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                    nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                } else {
                    if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                        nativeAdContainer.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void Load_AD3(AdLoader.Builder builder, final ViewGroup nativeAdContainer, final int type) {
        VideoOptions videoOptions = new VideoOptions.Builder().build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).setAdChoicesPlacement(ADCHOICES_TOP_RIGHT).build();
        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdImpression() {
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                    nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                } else {
                    if (new Data_Preference(activity).getNativeSpacebox().equals("0")) {
                        nativeAdContainer.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void showFacebookNative(Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
            setView_AD_Height(activity, nativeAdContainer, type);
            final com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, new Data_Preference(activity).getFbNativeID());
            nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                        nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                    }
                    nativeAdContainer.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd == null || nativeAd != ad) {
                        return;
                    }
                    new Inflate_ADS(activity).inflate_NATIV_FB(nativeAd, nativeAdContainer);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
            showMediumNative(activity, nativeAdContainer, type);
        }
    }

    private void showFacebookNative2(Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
            setView_AD_Height(activity, nativeAdContainer, type);
            final com.facebook.ads.NativeAd nativeAd1 = new com.facebook.ads.NativeAd(activity, new Data_Preference(activity).getFbNativeID());
            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                        nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                    }
                    nativeAdContainer.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    new Inflate_ADS(activity).inflate_NATIV_FB(nativeAd1, nativeAdContainer);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
            showMediumNative2(activity, nativeAdContainer, type);
        }
    }

    private void showFacebookNative3(Activity activity, final ViewGroup nativeAdContainer, final int type) {
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 1) {
            setView_AD_Height(activity, nativeAdContainer, type);
            final com.facebook.ads.NativeAd nativeAd2 = new com.facebook.ads.NativeAd(activity, new Data_Preference(activity).getFbNativeID());
            nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                        nativecustom.getInstance(activity).showMyCustomNative(activity, nativeAdContainer, type);
                    }
                    nativeAdContainer.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd2 == null || nativeAd2 != ad) {
                        return;
                    }
                    new Inflate_ADS(activity).inflate_NATIV_FB(nativeAd2, nativeAdContainer);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 1) {
            showMediumNative3(activity, nativeAdContainer, type);
        }
    }

    private void showNativeFacebookBanner(Activity activity, final ViewGroup container, final int type) {
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
            final NativeBannerAd nativeAd1 = new NativeBannerAd(activity, new Data_Preference(activity).getFbBannerID());
            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                    container.removeAllViews();
                    container.setVisibility(View.VISIBLE);
                    new Inflate_ADS(activity).inflate_NB_FB(nativeAd1, container);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    container.removeAllViews();
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                        nativecustom.getInstance(activity).showMyCustomNative(activity, container, 0);
                    }
                    container.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    nativeAd1.downloadMedia();
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
            showSmallNative(activity, container, type);
        }
    }

    private void showNativeFacebookBanner2(Activity activity, final ViewGroup container, final int type) {
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
            final NativeBannerAd nativeAd1 = new NativeBannerAd(activity, new Data_Preference(activity).getFbBannerID());
            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                    container.removeAllViews();
                    container.setVisibility(View.VISIBLE);
                    new Inflate_ADS(activity).inflate_NB_FB(nativeAd1, container);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    container.removeAllViews();
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                        nativecustom.getInstance(activity).showMyCustomNative(activity, container, 0);
                    }
                    container.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    nativeAd1.downloadMedia();
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
            showSmallNative2(activity, container, type);
        }
    }

    private void showNativeFacebookBanner3(Activity activity, final ViewGroup container, final int type) {
        if (new Data_Preference(activity).getFbAdshowStatus().equals("1")  && type == 2) {
            final NativeBannerAd nativeAd2 = new NativeBannerAd(activity, new Data_Preference(activity).getFbBannerID());
            nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                    container.removeAllViews();
                    container.setVisibility(View.VISIBLE);
                    new Inflate_ADS(activity).inflate_NB_FB(nativeAd2, container);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    container.removeAllViews();
                    if (new Data_Preference(activity).getCustomAdshowStatus().equals("1") && new Data_Preference(activity).getCustomNative().equals("1")) {
                        nativecustom.getInstance(activity).showMyCustomNative(activity, container, 0);
                    }
                    container.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd2 == null || nativeAd2 != ad) {
                        return;
                    }
                    nativeAd2.downloadMedia();
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } else if (new Data_Preference(activity).getAdmobNativeStatus().equals("1") && !new Data_Preference(activity).getAdmobNativeID().isEmpty() && type == 2) {
            showSmallNative3(activity, container, type);
        }
    }
}
