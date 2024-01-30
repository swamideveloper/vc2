package com.ads.sdk.custom;


import static com.ads.sdk.splashconnect_act.customAdData;
import static com.ads.sdk.splashconnect_act.getRandomNum;
import static com.ads.sdk.new_configs.Data_Config.getPlayStoreUrl;
import static com.ads.sdk.new_configs.Data_Config.openChromeCustomTabUrl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.ads.sdk.R;
import com.ads.sdk.splashconnect_act;
import com.ads.sdk.new_configs.Data_Preference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class nativecustom {

    public static Activity activity;
    private static nativecustom mInstance;
    ImageView iv_banner;

    public nativecustom(Activity activity) {
        nativecustom.activity = activity;
    }

    public static nativecustom getInstance(Activity activity) {
        nativecustom.activity = activity;
        if (mInstance == null) {
            mInstance = new nativecustom(activity);
        }
        return mInstance;
    }

    public void showMyCustomNative(final Activity activity, ViewGroup nativeAdContainer, int type) {
        if (new Data_Preference(activity).getCustomAdshowStatus().equals("0") || new Data_Preference(activity).getCustomNative().equals("0")) {
            return;
        }
        addatacustom appModal = splashconnect_act.getMyCustomAd();
        int pos = getRandomNum();
        if (appModal != null) {
            View inflate = getCustomView(activity, nativeAdContainer, type);
            iv_banner = (ImageView) inflate.findViewById(R.id.iv_banner);
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(500); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            try {
                Glide.with(activity).load(customAdData.get(pos).getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }
                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(iv_banner);
                if (type == 1) {
                    Glide.with(activity).load(customAdData.get(pos).getNativeImage()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.img_big_native).into(iv_banner);
                } else {
                    Glide.with(activity).load(customAdData.get(pos).getSmallNativeImage()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.img_small_native).into(iv_banner);
                }
            } catch (Exception e) {
            }
            iv_banner.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String action_str = customAdData.get(pos).getUrl();
                    if(action_str.startsWith("https")){
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getPlayStoreUrl(action_str))));
                    } else {
                        openChromeCustomTabUrl(activity,action_str);
                    }
                }
            });
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(inflate);
        } else {
            nativeAdContainer.setVisibility(View.GONE);
        }
    }

    private View getCustomView(Activity activity, ViewGroup nativead, int type) {
        View adView;
        switch (type) {
            case 1:
                adView = activity.getLayoutInflater().inflate(R.layout.cust_med_native, null);
                break;
            case 2:
                adView = activity.getLayoutInflater().inflate(R.layout.cust_small_native, null);
                break;
            default:
                adView = activity.getLayoutInflater().inflate(R.layout.cust_med_native, null);
                break;
        }
        return adView;
    }
}
