package com.ads.sdk.new_adsClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ads.sdk.R;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;

import java.util.ArrayList;
import java.util.List;

public class Inflate_ADS {
    Context activity;

    public Inflate_ADS(Context context) {
        this.activity = context;
    }

    public void inflate_NB_FB(NativeBannerAd nativeBannerAd, ViewGroup cardView) {
        nativeBannerAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = null;
        view = inflater.inflate(R.layout.ads_nb_fb, null);
        cardView.addView(view);

        NativeAdLayout nativeAdLayout = view.findViewById(R.id.nativview);
        LinearLayout adChoicesContainer = view.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        TextView nativeAdTitle = view.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = view.findViewById(R.id.native_ad_social_context);
        MediaView nativeAdIconView = view.findViewById(R.id.native_icon_view);
        TextView nativeAdCallToAction = view.findViewById(R.id.b_name);
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdBodyText());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIconView);
        clickableViews.add(nativeAdSocialContext);
        nativeBannerAd.registerViewForInteraction(view, nativeAdIconView, clickableViews);
    }

    public void inflate_NATIV_FB(NativeAd nativeAd, ViewGroup card) {

        card.setVisibility(View.VISIBLE);
        nativeAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View adView = inflater.inflate(R.layout.ads_nativ_fb, null);

        card.removeAllViews();
        card.addView(adView);

        NativeAdLayout nativeAdLayout = adView.findViewById(R.id.nativview);

        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdBody);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIcon);
        clickableViews.add(nativeAdSocialContext);

        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }

}
