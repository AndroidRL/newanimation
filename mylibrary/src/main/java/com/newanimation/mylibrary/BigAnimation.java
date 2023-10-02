package com.newanimation.mylibrary;

import static com.newanimation.mylibrary.MyProHelperClass.getRandomNumber;
import static com.newanimation.mylibrary.MyProHelperClass.native_ads;
import static com.newanimation.mylibrary.MyProHelperClass.round_ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BigAnimation {

    /*Google*/
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads1 = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads2 = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads3 = null;
    public static int AutoGoogleNativeID;

    /*Facebook*/
    public static com.facebook.ads.NativeAd facebook_native_ads = null;
    public static int AutoLoadFBNativeID;

    /*AppLoving*/
    public static MaxNativeAdLoader appLoving_native_ads_loader;
    public static MaxNativeAdView max_nativeAdView;
    public static MaxAd appLoving_native_ads = null;

    /*Helper*/
    public static Activity main_context;

    /*Mix*/
    public static int auto_notShow_ads_native = 0;
    public static int mix_ads_native = 0;
    public static int auto_native_show_id = 0;
    public static String Ad_Size = "medium";


    /**
     * NATIVE ADS CODE START
     */
    public static void TopAnimation(Activity context1, RelativeLayout main_native) {
        main_context = context1;
        Ad_Size = MyProHelperClass.getNativeViewSize();   /*Google*/  //Small //Medium  //Big  /*Facebook*/ //Medium //Big

        if (!MyProHelperClass.isOnline(context1)) {
            context1.startActivity(new Intent(context1, InternetErrorActivity.class));
            return;
        }

        /*Stop Ad*/
        if (MyProHelperClass.getCounter_Native() == 0) {
            return;
        }

        /*Skip Ads*/
        if (MyProHelperClass.getCounter_Native() != 5000) {
            auto_notShow_ads_native++;
            if (MyProHelperClass.getCounter_Native() + 1 == auto_notShow_ads_native) {
                auto_notShow_ads_native = 0;
                if (MyProHelperClass.getNative_preload().equals("0")) {
                    if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                        StopPreLoadNativeMixAds(main_native);
                    } else {
                        StopPreLoadNative(main_native);
                    }
                } else {
                    if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                        NativeMixAds(main_native);
                    } else {
                        RegularAds(main_native);
                    }
                }
                return;
            }
            return;
        }

        //on Demand
        if (MyProHelperClass.getNative_preload().equals("0")) {
            if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                StopPreLoadNativeMixAds(main_native);
            } else {
                StopPreLoadNative(main_native);
            }
            return;
        }

        /*Mix Ads*/
        if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
            NativeMixAds(main_native);
        } else {
            RegularAds(main_native);
        }

    }

    private static void StopPreLoadNative(RelativeLayout main_native) {
        if (MyProHelperClass.getGoogleEnable().equals("1")) {
            GoogleSmallNativeLoadDialog(main_native);
            StopPreGoogleNative(main_native);
        } else if (MyProHelperClass.getFacebookEnable().equals("1")) {
            FacebookNativeLoadDialog(main_native);
            StopPreFacebookNative(main_native);
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            GoogleSmallNativeLoadDialog(main_native);
            StopPreAppLovingNative(main_native);
        } else if (MyProHelperClass.getQurekaADS().equals("1")) {
            GoogleSmallNativeLoadDialog(main_native);
            QurekaNative(main_native);
        } else {
            main_native.removeAllViews();
        }
    }

    private static void RegularAds(RelativeLayout main_native) {
        if (MyProHelperClass.getGoogleEnable().equals("1")) {
            GoogleADSNativeShow("r", main_native);
        } else if (MyProHelperClass.getFacebookEnable().equals("1")) {
            FacebookNativeShow(main_native);
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            AppLovingNativeShow(main_native);
        } else if (MyProHelperClass.getQurekaADS().equals("1")) {
            QurekaNative(main_native);
        } else {
            main_native.removeAllViews();
        }
    }

    /**
     * Regular Ads Show
     */
    /*Google*/
    private static void GoogleADSNativeShow(String checker, RelativeLayout main_native) {
        if (MyProHelperClass.Google_native_number == 1) {
            RegularGoogleNativeShow(checker, main_native);
        } else if (MyProHelperClass.Google_native_number == 2) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                RegularGoogleNativeShow1(checker, main_native);
            } else {
                auto_native_show_id = 0;
                RegularGoogleNativeShow2(checker, main_native);
            }
        } else if (MyProHelperClass.Google_native_number == 3) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                RegularGoogleNativeShow1(checker, main_native);
            } else if (auto_native_show_id == 1) {
                auto_native_show_id = 2;
                RegularGoogleNativeShow2(checker, main_native);
            } else {
                auto_native_show_id = 0;
                RegularGoogleNativeShow3(checker, main_native);
            }
        }
    }

    private static void RegularGoogleNativeShow(String checker, RelativeLayout main_native) {
        if (google_native_ads != null) {

            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("medium")) {
                GoogleNativePopulaterMediumShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("big")) {
                GoogleNativePopulaterLargeShow(google_native_ads, main_native);
            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker, main_native);
        }
        AllAdsPreLoadsNative("g", main_native);
    }


    private static void RegularGoogleNativeShow1(String checker, RelativeLayout main_native) {
        if (google_native_ads1 != null) {
            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("medium")) {
                GoogleNativePopulaterMediumShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("big")) {
                GoogleNativePopulaterLargeShow(google_native_ads, main_native);
            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker, main_native);
        }
        AllAdsPreLoadsNative("g1", main_native);
    }

    private static void RegularGoogleNativeShow2(String checker, RelativeLayout main_native) {
        if (google_native_ads2 != null) {
            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("medium")) {
                GoogleNativePopulaterMediumShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("big")) {
                GoogleNativePopulaterLargeShow(google_native_ads, main_native);
            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker, main_native);
        }
        AllAdsPreLoadsNative("g2", main_native);
    }

    private static void RegularGoogleNativeShow3(String checker, RelativeLayout main_native) {
        if (google_native_ads3 != null) {

            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("medium")) {
                GoogleNativePopulaterMediumShow(google_native_ads, main_native);
            } else if (Ad_Size.equals("big")) {
                GoogleNativePopulaterLargeShow(google_native_ads, main_native);
            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker, main_native);
        }
        AllAdsPreLoadsNative("g3", main_native);
    }

    private static void Google_Fails_Facebook_Show(RelativeLayout main_native) {
        if (facebook_native_ads != null) {

            if (Ad_Size.equals("medium")) {
                FacebookNativePopulateMediumShow(main_native);
            } else if (Ad_Size.equals("big")) {
                FacebookNativePopulateShow(main_native);

            } else if (Ad_Size.equals("small")) {

                FacebookNativePopulateSmallShow(main_native);

            }

        } else {
            Google_Facebook_Fails_AppLoving_Show(main_native);
        }
        AllAdsPreLoadsNative("f", main_native);
    }


    private static void Google_Facebook_Fails_AppLoving_Show(RelativeLayout main_native) {
        if (appLoving_native_ads != null) {
            main_native.removeAllViews();
            main_native.addView(max_nativeAdView);
        } else {
            QurekaNative(main_native);
        }
        AllAdsPreLoadsNative("a", main_native);
    }

    private static void AllGoogle_Fails_Other_Ads_Show(String checker, RelativeLayout main_native) {
        if (checker.equals("r")) {
            Google_Fails_Facebook_Show(main_native);
        } else if (checker.equals("f")) {
            Google_Facebook_Fails_AppLoving_Show(main_native);
        } else if (checker.equals("a")) {
            AppLoving_Google_Fails_facebook_Show(main_native);
        }
    }

    /*Facebook*/
    private static void FacebookNativeShow(RelativeLayout main_native) {
        if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {

            if (Ad_Size.equals("medium")) {

                FacebookNativePopulateMediumShow(main_native);

            } else if (Ad_Size.equals("big")) {

                FacebookNativePopulateShow(main_native);

            } else if (Ad_Size.equals("small")) {

                FacebookNativePopulateSmallShow(main_native);

            }

        } else {
            GoogleADSNativeShow("f", main_native);
        }
        AllAdsPreLoadsNative("f", main_native);
    }

    private static void FacebookNativePopulateMediumShow(RelativeLayout main_native) {


        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        NativeAdLayout adView = (NativeAdLayout) inflater.inflate(R.layout.ad_fb_medium_native, main_native, false);

        facebook_native_ads.unregisterView();

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(main_context, facebook_native_ads, adView);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);


        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(facebook_native_ads.getAdvertiserName());
        nativeAdBody.setText(facebook_native_ads.getAdBodyText());
        nativeAdSocialContext.setText(facebook_native_ads.getAdSocialContext());
        nativeAdCallToAction.setVisibility(facebook_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(facebook_native_ads.getAdCallToAction());
        nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));

        sponsoredLabel.setText(facebook_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        facebook_native_ads.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

//        main_native.removeAllViews();
        main_native.addView(adView);

    }

    public static void FacebookNativePopulateShow(RelativeLayout main_native) {
        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        NativeAdLayout adView = (NativeAdLayout) inflater.inflate(R.layout.ad_fb_big_native, main_native, false);
        facebook_native_ads.unregisterView();

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(main_context, facebook_native_ads, adView);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);


        // Set the Text.
        nativeAdTitle.setText(facebook_native_ads.getAdvertiserName());
        nativeAdBody.setText(facebook_native_ads.getAdBodyText());
        nativeAdSocialContext.setText(facebook_native_ads.getAdSocialContext());
        nativeAdCallToAction.setVisibility(facebook_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(facebook_native_ads.getAdCallToAction());
        nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
        sponsoredLabel.setText(facebook_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        facebook_native_ads.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

//        main_native.removeAllViews();
        main_native.addView(adView);

    }

    /*AppLoving*/
    private static void AppLovingNativeShow(RelativeLayout main_native) {
        if (appLoving_native_ads != null) {
            main_native.removeAllViews();
            main_native.addView(max_nativeAdView);
        } else {
            GoogleADSNativeShow("a", main_native);
        }
        AllAdsPreLoadsNative("a", main_native);
    }

    private static void AppLoving_Google_Fails_facebook_Show(RelativeLayout main_native) {
        if (facebook_native_ads != null) {
            if (Ad_Size.equals("medium")) {

                FacebookNativePopulateMediumShow(main_native);

            } else if (Ad_Size.equals("big")) {

                FacebookNativePopulateShow(main_native);

            } else if (Ad_Size.equals("small")) {

                FacebookNativePopulateSmallShow(main_native);
            }

        } else {
            QurekaNative(main_native);
        }
        AllAdsPreLoadsNative("f", main_native);
    }

    /**
     * Mix Ads Show
     */
    /*Mix Ads Helper*/
    private static void NativeMixAds(RelativeLayout main_native) {
        if (MyProHelperClass.getmix_ad_native().length() == 0) {
            main_native.removeAllViews();
        } else {
            if (MyProHelperClass.getmix_ad_native().length() == 1) {
                Mix1AdsNative(MyProHelperClass.getmix_ad_native(), main_native);  // 1 ads
            } else if (MyProHelperClass.getmix_ad_native().length() == 2) {
                Mix2AdsNative(MyProHelperClass.getmix_ad_native(), main_native);  // 2 ads
            } else {
                MixUnlimitedAdsNative(MyProHelperClass.getmix_ad_native(), main_native); // Unlimited
            }
        }
    }

    private static void Mix1AdsNative(String s, RelativeLayout main_native) {
        MixAdsShowNative(String.valueOf(s.charAt(0)), main_native);
    }

    private static void Mix2AdsNative(String s, RelativeLayout main_native) {
        if (MyProHelperClass.getmix_ad_counter_native() != 5000) {
            mix_ads_native++;
            if (MyProHelperClass.getmix_ad_counter_native() + 1 == mix_ads_native) {
                MixAdsShowNative(String.valueOf(s.charAt(1)), main_native);
                mix_ads_native = 0;
            } else {
                MixAdsShowNative(String.valueOf(s.charAt(0)), main_native);
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                MixAdsShowNative(String.valueOf(s.charAt(0)), main_native);
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                MixAdsShowNative(String.valueOf(s.charAt(1)), main_native);
            }
        }
    }

    private static void MixUnlimitedAdsNative(String s, RelativeLayout main_native) {
        MixAdsShowNative(String.valueOf(s.charAt(mix_ads_native)), main_native);
        if (MyProHelperClass.getmix_ad_native().length() - 1 == mix_ads_native) {
            mix_ads_native = 0;
        } else {
            mix_ads_native++;
        }
    }

    private static void MixAdsShowNative(String value, RelativeLayout main_native) {
        if (value.equals("g")) {
            GoogleADSNativeShow("r", main_native);
        } else if (value.equals("f")) {
            FacebookNativeShow(main_native);
        } else if (value.equals("a")) {
            AppLovingNativeShow(main_native);
        } else if (value.equals("z")) {
            QurekaNative(main_native);
        } else {
            main_native.removeAllViews();
        }
    }


    /**
     * PreLoad
     */
    /*Google*/
    public static void GoogleNativePreload() {
        String google_load_id = null;
        if (AutoGoogleNativeID == 1) {
            google_load_id = MyProHelperClass.getGoogleNative();
        } else if (AutoGoogleNativeID == 2) {
            google_load_id = MyProHelperClass.getGoogleNative1();
        } else if (AutoGoogleNativeID == 3) {
            google_load_id = MyProHelperClass.getGoogleNative2();
        }
        AdLoader.Builder builder = new AdLoader.Builder(main_context, google_load_id);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads = nativeAd;
                AutoGoogleNativeID = 1;

            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (AutoGoogleNativeID == 1) {
                    AutoGoogleNativeID = 2;
                    GoogleNativePreload();
                } else if (AutoGoogleNativeID == 2) {
                    AutoGoogleNativeID = 3;
                    GoogleNativePreload();
                } else {
                    google_native_ads = null;
                }
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void GoogleNativePreload1() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads1 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads1 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleNativePreload2() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative1());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads2 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads2 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleNativePreload3() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative2());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads3 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads3 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    /*Facebook*/
    public static void FacebookNativePreLoad() {
        String facebook_load_id = null;
        if (AutoLoadFBNativeID == 1) {
            facebook_load_id = MyProHelperClass.getFacebookNative();
        } else if (AutoLoadFBNativeID == 2) {
            facebook_load_id = MyProHelperClass.getFacebookNative1();
        } else if (AutoLoadFBNativeID == 3) {
            facebook_load_id = MyProHelperClass.getFacebookNative2();
        }
        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, facebook_load_id);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                if (AutoLoadFBNativeID == 1) {
                    AutoLoadFBNativeID = 2;
                    FacebookNativePreLoad();
                } else if (AutoLoadFBNativeID == 2) {
                    AutoLoadFBNativeID = 3;
                    FacebookNativePreLoad();
                } else {
                    facebook_native_ads = null;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                AutoLoadFBNativeID = 1;

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    /*AppLoving*/
    public static void AppLovingNativePreLoad() {
        appLoving_native_ads_loader = new MaxNativeAdLoader(MyProHelperClass.getAppLovinNative(), main_context);
        appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                appLoving_native_ads = ad;
                max_nativeAdView = nativeAdView;
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));

            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                appLoving_native_ads = null;
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        appLoving_native_ads_loader.loadAd();
    }

    /*All Preload*/
    public static void AllAdsPreLoadsNative(String refresh, RelativeLayout main_native) {
        if (refresh.equals("g")) {
            google_native_ads = null;
        } else if (refresh.equals("g1")) {
            google_native_ads1 = null;
        } else if (refresh.equals("g2")) {
            google_native_ads2 = null;
        } else if (refresh.equals("g3")) {
            google_native_ads3 = null;
        } else if (refresh.equals("f")) {
            facebook_native_ads = null;
        } else if (refresh.equals("a")) {
            appLoving_native_ads = null;
        }

        if (MyProHelperClass.Google_native_number == 1) {

            if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
                if (google_native_ads == null) {
                    GoogleNativePreload();
                }
            }

        } else if (MyProHelperClass.Google_native_number == 2) {

            if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();
                }
            }
            if (MyProHelperClass.getGoogleNative1() != null && !MyProHelperClass.getGoogleNative1().isEmpty()) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();
                }
            }
        } else if (MyProHelperClass.Google_native_number == 3) {

            if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();

                }
            }
            if (MyProHelperClass.getGoogleNative1() != null && !MyProHelperClass.getGoogleNative1().isEmpty()) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();

                }
            }

            if (MyProHelperClass.getGoogleNative2() != null && !MyProHelperClass.getGoogleNative2().isEmpty()) {
                if (google_native_ads3 == null) {
                    GoogleNativePreload3();
                }
            }

        }

        if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {
            if (facebook_native_ads == null) {
                FacebookNativePreLoad();
            }
        }

        if (MyProHelperClass.getAppLovinNative() != null && !MyProHelperClass.getAppLovinNative().isEmpty()) {
            if (appLoving_native_ads == null) {
                AppLovingNativePreLoad();
            }
        }
    }

    /**
     * Stop pre load
     */

    private static void StopPreAppLovingNative(RelativeLayout main_native) {

        appLoving_native_ads_loader = new MaxNativeAdLoader(MyProHelperClass.getAppLovinNative(), main_context);
        appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                appLoving_native_ads = ad;
                max_nativeAdView = nativeAdView;
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                if (appLoving_native_ads != null) {
                    main_native.removeAllViews();
                    main_native.addView(max_nativeAdView);
                }
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                appLoving_native_ads = null;
                Stop_Preload_apploving_fails_other_show(main_native);
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        appLoving_native_ads_loader.loadAd();
    }

    private static void StopPreFacebookNative(RelativeLayout main_native) {
        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                facebook_native_ads = null;
                Stop_Preload_facebook_fails_other_show(main_native);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                    if (Ad_Size.equals("medium")) {

                        FacebookNativePopulateMediumShow(main_native);

                    } else if (Ad_Size.equals("big")) {

                        FacebookNativePopulateShow(main_native);

                    } else if (Ad_Size.equals("small")) {

                        FacebookNativePopulateSmallShow(main_native);
                    }

                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    private static void StopPreGoogleNative(RelativeLayout main_native) {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
        builder.forNativeAd(nativeAd -> {
            google_native_ads = nativeAd;
            if (google_native_ads != null) {

                if (Ad_Size.equals("small")) {
                    GoogleNativePopulaterSmallShow(google_native_ads, main_native);
                } else if (Ad_Size.equals("medium")) {
                    GoogleNativePopulaterMediumShow(google_native_ads, main_native);
                } else if (Ad_Size.equals("big")) {
                    GoogleNativePopulaterLargeShow(google_native_ads, main_native);
                }
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Stop_Preload_google_fails_other_show(main_native);

            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    private static void StopPreLoadNativeMixAds(RelativeLayout main_native) {
        if (MyProHelperClass.getmix_ad_native().length() == 0) {
            main_native.removeAllViews();
        } else {
            if (MyProHelperClass.getmix_ad_native().length() == 1) {
                StopPreLoadMix1AdsNative(MyProHelperClass.getmix_ad_native(), main_native);  // 1 ads
            } else if (MyProHelperClass.getmix_ad_native().length() == 2) {
                StopPreLoadMix2AdsNative(MyProHelperClass.getmix_ad_native(), main_native);  // 2 ads
            } else {
                StopPreLoadMixUnlimitedAdsNative(MyProHelperClass.getmix_ad_native(), main_native); // Unlimited
            }
        }
    }

    private static void StopPreLoadMix1AdsNative(String s, RelativeLayout main_native) {
        StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(0)), main_native);
    }


    private static void StopPreLoadMix2AdsNative(String s, RelativeLayout main_native) {
        if (MyProHelperClass.getmix_ad_counter_native() != 5000) {
            mix_ads_native++;
            if (MyProHelperClass.getmix_ad_counter_native() + 1 == mix_ads_native) {
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(1)), main_native);
                mix_ads_native = 0;
            } else {
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(0)), main_native);
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(0)), main_native);
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(1)), main_native);
            }
        }
    }

    private static void StopPreLoadMixUnlimitedAdsNative(String s, RelativeLayout main_native) {
        StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(mix_ads_native)), main_native);
        if (MyProHelperClass.getmix_ad_native().length() - 1 == mix_ads_native) {
            mix_ads_native = 0;
        } else {
            mix_ads_native++;
        }
    }

    private static void StopPreLoadMixAdsShowNative(String value, RelativeLayout main_native) {
        if (value.equals("g")) {
            GoogleSmallNativeLoadDialog(main_native);
            StopPreGoogleNative(main_native);
        } else if (value.equals("f")) {
            FacebookNativeLoadDialog(main_native);
            StopPreFacebookNative(main_native);
        } else if (value.equals("a")) {
            GoogleSmallNativeLoadDialog(main_native);
            StopPreAppLovingNative(main_native);
        } else if (value.equals("z")) {
            QurekaNative(main_native);
        } else {
            main_native.removeAllViews();
        }
    }

    public static void GoogleSmallNativeLoadDialog(RelativeLayout main_native) {

        if (Ad_Size.equals("small")) {
            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.load_google_native_banner, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        } else if (Ad_Size.equals("medium")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.load_google_medium_native, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        } else if (Ad_Size.equals("big")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.load_google_big_native, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        }


    }

    public static void FacebookNativeLoadDialog(RelativeLayout main_native) {
        if (Ad_Size.equals("small")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.load_google_native_banner, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        } else if (Ad_Size.equals("medium")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.load_fb_medium_native, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        } else if (Ad_Size.equals("big")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.load_fb_big_native, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        }
    }

    private static void GoogleNativePopulaterLargeShow(com.google.android.gms.ads.nativead.NativeAd native_ads, RelativeLayout main_native) {

        NativeAdView nativeAdView = (NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));


        nativeAdView.setMediaView((MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(native_ads.getMediaContent());

        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(native_ads.getHeadline());
            if (native_ads.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(native_ads.getBody());
            }

            if (native_ads.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) nativeAdView.getCallToActionView()).setText(native_ads.getCallToAction());
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
            }

            if (native_ads.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(native_ads.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(native_ads);
            VideoController videoController = native_ads.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }

        main_native.removeAllViews();
        main_native.addView(nativeAdView);
    }

    private static void GoogleNativePopulaterMediumShow(com.google.android.gms.ads.nativead.NativeAd nativeAd, RelativeLayout main_native) {
        NativeAdView nativeAdView = (NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_medium_native, (ViewGroup) null);
        nativeAdView.setMediaView((MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
            if (nativeAd.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
            }

            if (nativeAd.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
            }

            if (nativeAd.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            VideoController videoController = nativeAd.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }
        nativeAdView.setNativeAd(nativeAd);
        main_native.addView(nativeAdView);
    }

    private static void GoogleNativePopulaterSmallShow(com.google.android.gms.ads.nativead.NativeAd nativeAd, RelativeLayout main_native) {
        View layout_ad_view = LayoutInflater.from(main_context).inflate(R.layout.ad_google_native_banner, null);
        com.google.android.gms.ads.nativead.NativeAdView native_ad_view = layout_ad_view.findViewById(R.id.ad_view_small_banner);
        native_ad_view.setHeadlineView(native_ad_view.findViewById(R.id.ad_headline_small_banner));
        native_ad_view.setBodyView(native_ad_view.findViewById(R.id.ad_body_small_banner));
        native_ad_view.setCallToActionView(native_ad_view.findViewById(R.id.ad_call_to_action_small_banner));
        native_ad_view.setIconView(native_ad_view.findViewById(R.id.ad_app_icon_small_banner));
        ((TextView) Objects.requireNonNull(native_ad_view.getHeadlineView())).setText(nativeAd.getHeadline());
        ((TextView) Objects.requireNonNull(native_ad_view.getBodyView())).setText(nativeAd.getBody());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setText(nativeAd.getCallToAction());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));

        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(native_ad_view.getIconView()).setVisibility(View.GONE);
        } else {
            ((ImageView) Objects.requireNonNull(native_ad_view.getIconView())).setImageDrawable(nativeAd.getIcon().getDrawable());
            native_ad_view.getIconView().setVisibility(View.VISIBLE);
        }
        native_ad_view.setNativeAd(nativeAd);
        main_native.removeAllViews();
        main_native.addView(layout_ad_view);

    }

    //On demand Google fail code
    private static void Stop_Preload_google_fails_other_show(RelativeLayout main_native) {

        if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {

            facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    Stop_preload_google_fails_facebook_fail_other_show(main_native);

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                        if (Ad_Size.equals("medium")) {

                            FacebookNativePopulateMediumShow(main_native);

                        } else if (Ad_Size.equals("big")) {

                            FacebookNativePopulateShow(main_native);

                        } else if (Ad_Size.equals("small")) {

                            FacebookNativePopulateSmallShow(main_native);
                        }

                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());

        } else {

            Stop_preload_google_fails_facebook_fail_other_show(main_native);
        }
    }

    private static void Stop_preload_google_fails_facebook_fail_other_show(RelativeLayout main_native) {

        if (MyProHelperClass.getAppLovinNative() != null && !MyProHelperClass.getAppLovinNative().isEmpty()) {

            appLoving_native_ads_loader = new MaxNativeAdLoader(MyProHelperClass.getAppLovinNative(), main_context);
            appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
                @Override
                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                    appLoving_native_ads = ad;
                    max_nativeAdView = nativeAdView;
                    int width = ViewGroup.LayoutParams.MATCH_PARENT;
                    int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                    max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                    if (appLoving_native_ads != null) {
                        main_native.removeAllViews();
                        main_native.addView(max_nativeAdView);
                    }
                }

                @Override
                public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                    appLoving_native_ads = null;
                    QurekaNative(main_native);
                }

                @Override
                public void onNativeAdClicked(final MaxAd ad) {

                }
            });
            appLoving_native_ads_loader.loadAd();

        } else {
            QurekaNative(main_native);
        }

    }

    //On demand Facebook fail code
    private static void Stop_Preload_facebook_fails_other_show(RelativeLayout main_native) {

        if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {

            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
            builder.forNativeAd(nativeAd -> {
                google_native_ads = nativeAd;
                if (google_native_ads != null) {
                    if (Ad_Size.equals("small")) {
                        GoogleNativePopulaterSmallShow(google_native_ads, main_native);
                    } else if (Ad_Size.equals("medium")) {
                        GoogleNativePopulaterMediumShow(google_native_ads, main_native);
                    } else if (Ad_Size.equals("big")) {
                        GoogleNativePopulaterLargeShow(google_native_ads, main_native);
                    }
                }
            });
            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Stop_preload_google_fails_facebook_fail_other_show(main_native);
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else {

            Stop_preload_google_fails_facebook_fail_other_show(main_native);

        }
    }


    //On demand Applovin fail code
    private static void Stop_Preload_apploving_fails_other_show(RelativeLayout main_native) {

        if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {

            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    google_native_ads = nativeAd;
                    if (google_native_ads != null) {
                        if (Ad_Size.equals("small")) {
                            GoogleNativePopulaterSmallShow(google_native_ads, main_native);
                        } else if (Ad_Size.equals("medium")) {
                            GoogleNativePopulaterMediumShow(google_native_ads, main_native);
                        } else if (Ad_Size.equals("big")) {
                            GoogleNativePopulaterLargeShow(google_native_ads, main_native);
                        }
                    }
                }
            });
            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);

                    if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {

                        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                facebook_native_ads = null;
                                QurekaNative(main_native);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                                    if (Ad_Size.equals("medium")) {

                                        FacebookNativePopulateMediumShow(main_native);

                                    } else if (Ad_Size.equals("big")) {

                                        FacebookNativePopulateShow(main_native);

                                    } else if (Ad_Size.equals("small")) {

                                        FacebookNativePopulateSmallShow(main_native);
                                    }

                                }
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());

                    } else {

                        main_native.removeAllViews();

                    }

                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {


            facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_native_ads = null;
                    QurekaNative(main_native);

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                        if (Ad_Size.equals("medium")) {

                            FacebookNativePopulateMediumShow(main_native);

                        } else if (Ad_Size.equals("big")) {

                            FacebookNativePopulateShow(main_native);

                        } else if (Ad_Size.equals("small")) {

                            FacebookNativePopulateSmallShow(main_native);
                        }

                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());

        } else {

            QurekaNative(main_native);

        }


    }

    public static void FacebookNativePopulateSmallShow(RelativeLayout main_native) {

        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        NativeAdLayout adView = (NativeAdLayout) inflater.inflate(R.layout.ad_fb_small_native, main_native, false);
        facebook_native_ads.unregisterView();

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(main_context, facebook_native_ads, adView);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(facebook_native_ads.getAdvertiserName());
        nativeAdBody.setText(facebook_native_ads.getAdBodyText());
        nativeAdCallToAction.setVisibility(facebook_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(facebook_native_ads.getAdCallToAction());
        nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
        sponsoredLabel.setText(facebook_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        facebook_native_ads.registerViewForInteraction(adView, nativeAdIcon, clickableViews);

//        main_native.removeAllViews();
        main_native.addView(adView);

    }

    /**
     * App Qureka ADS
     */
    private static void QurekaNative(RelativeLayout main_native) {
        if (MyProHelperClass.getQurekaShow_AfterFails().equals("1") || MyProHelperClass.getQurekaADS().equals("1")) {

            if (Ad_Size.equals("small")) {

                int A = MyProHelperClass.getRandomNumber(0, 1);
                if (A == 0) {
                    LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_small_native, main_native, false);

                    Glide.with(main_context).load(MyProHelperClass.banner_ads.get(MyProHelperClass.getRandomNumber(0, MyProHelperClass.banner_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.q_banner));

                    ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
                    ((LinearLayout) load_view.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReportSmall(load_view));
                    load_view.findViewById(R.id.ad_show).setOnClickListener(v -> MyProHelperClass.BtnAutolink());

                    main_native.removeAllViews();
                    main_native.addView(load_view);

                } else {
                    LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_small_native_2, main_native, false);

                    Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                    int getNumber = getRandomNumber(0, native_ads.size() - 1);
                    ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                    ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                    ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
                    ((LinearLayout) load_view.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReportSmall(load_view));
                    load_view.findViewById(R.id.ad_show).setOnClickListener(v -> MyProHelperClass.BtnAutolink());


                    load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                    main_native.removeAllViews();
                    main_native.addView(load_view);
                }

            } else if (Ad_Size.equals("medium")) {

                LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_medium_native, main_native, false);

                Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                int getNumber = getRandomNumber(0, native_ads.size() - 1);
                Glide.with(main_context).load(native_ads.get(getNumber).getImage()).into((ImageView) load_view.findViewById(R.id.q_image));
                ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
                ((LinearLayout) load_view.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReportMedium(load_view));

                load_view.findViewById(R.id.ad_show).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                main_native.removeAllViews();
                main_native.addView(load_view);


            } else if (Ad_Size.equals("big")) {
                LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_big_native, main_native, false);

                Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                int getNumber = getRandomNumber(0, native_ads.size() - 1);
                Glide.with(main_context).load(native_ads.get(getNumber).getImage()).into((ImageView) load_view.findViewById(R.id.q_image));

                ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
                ((LinearLayout) load_view.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReportBig(load_view));

                load_view.findViewById(R.id.ad_show).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                main_native.removeAllViews();
                main_native.addView(load_view);
            }

        } else {
            main_native.removeAllViews();
        }

    }

    //Big Ads
    public static void AdReportBig(RelativeLayout load_view) {
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.VISIBLE);
        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);
        ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);

        //All Close
        ((ImageView) load_view.findViewById(R.id.close)).setOnClickListener(v -> {
            ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
            ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.GONE);
        });

        //Main Btn Click
        ((LinearLayout) load_view.findViewById(R.id.btn_hide)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.GONE);

        });
        ((LinearLayout) load_view.findViewById(R.id.btn_report)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.GONE);

        });

        //Back
        ((ImageView) load_view.findViewById(R.id.back_hide)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide)).setVisibility(View.GONE);

            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);


        });
        ((ImageView) load_view.findViewById(R.id.back_report)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report)).setVisibility(View.GONE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);

        });

        //Hide Click
        ((TextView) load_view.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNextBig("Irrelevant", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNextBig("Repetitive", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNextBig("Interrupted me", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNextBig("Unexpected", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNextBig("Too many ads", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNextBig("Offensive", "h", load_view));

        //Report Click
        ((TextView) load_view.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNextBig("Sexually inappropriate", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNextBig("Illegal", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNextBig("Offensive", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNextBig("Spam", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNextBig("Disagreeable", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNextBig("Other", "r", load_view));
    }

    public static void AdsNextBig(String problem, String type, RelativeLayout load_view) {

        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.GONE);
        ((RelativeLayout) load_view.findViewById(R.id.ll_done_problem)).setVisibility(View.VISIBLE);

        if (type.equals("h")) {
            ((LinearLayout) load_view.findViewById(R.id.ll_hide_done)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report_done)).setVisibility(View.GONE);
            ((TextView) load_view.findViewById(R.id.hide_problem)).setText(problem);
        } else {
            ((LinearLayout) load_view.findViewById(R.id.ll_report_done)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide_done)).setVisibility(View.GONE);
            ((TextView) load_view.findViewById(R.id.report_problem)).setText(problem);
        }

    }

    //Medium Ads
    public static void AdReportMedium(RelativeLayout load_view) {
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.VISIBLE);
        ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);
        ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);
        //All Close
        ((ImageView) load_view.findViewById(R.id.close)).setOnClickListener(v -> {
            ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
            ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.GONE);
        });

        //Main Btn Click
        ((LinearLayout) load_view.findViewById(R.id.btn_hide)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.GONE);

        });
        ((LinearLayout) load_view.findViewById(R.id.btn_report)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.GONE);

        });

        //Back
        ((ImageView) load_view.findViewById(R.id.back_hide)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide)).setVisibility(View.GONE);

            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);


        });
        ((ImageView) load_view.findViewById(R.id.back_report)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report)).setVisibility(View.GONE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);

        });

        //Hide Click
        ((TextView) load_view.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNextMedium("Irrelevant", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNextMedium("Repetitive", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNextMedium("Interrupted me", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNextMedium("Unexpected", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNextMedium("Too many ads", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNextMedium("Offensive", "h", load_view));

        //Report Click
        ((TextView) load_view.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNextMedium("Sexually inappropriate", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNextMedium("Illegal", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNextMedium("Offensive", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNextMedium("Spam", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNextMedium("Disagreeable", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNextMedium("Other", "r", load_view));
    }

    public static void AdsNextMedium(String problem, String type, RelativeLayout load_view) {

        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.GONE);
        ((RelativeLayout) load_view.findViewById(R.id.ll_done_problem)).setVisibility(View.VISIBLE);

        if (type.equals("h")) {
            ((LinearLayout) load_view.findViewById(R.id.ll_hide_done)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report_done)).setVisibility(View.GONE);
            ((TextView) load_view.findViewById(R.id.hide_problem)).setText(problem);
        } else {
            ((LinearLayout) load_view.findViewById(R.id.ll_report_done)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide_done)).setVisibility(View.GONE);
            ((TextView) load_view.findViewById(R.id.report_problem)).setText(problem);
        }

    }

    //Small Ads
    public static void AdReportSmall(RelativeLayout load_view) {
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.VISIBLE);
        ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);
        ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);
        //All Close
        ((ImageView) load_view.findViewById(R.id.close)).setOnClickListener(v -> {
            ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
            ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.GONE);
        });

        //Main Btn Click
        ((LinearLayout) load_view.findViewById(R.id.btn_hide)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.GONE);

        });
        ((LinearLayout) load_view.findViewById(R.id.btn_report)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.GONE);

        });

        //Back
        ((ImageView) load_view.findViewById(R.id.back_hide)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide)).setVisibility(View.GONE);

            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);


        });
        ((ImageView) load_view.findViewById(R.id.back_report)).setOnClickListener(v -> {
            ((LinearLayout) load_view.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report)).setVisibility(View.GONE);
            ((ImageView) load_view.findViewById(R.id.close)).setVisibility(View.VISIBLE);

        });

        //Hide Click
        ((TextView) load_view.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNextSmall("Irrelevant", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNextSmall("Repetitive", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNextSmall("Interrupted me", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNextSmall("Unexpected", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNextSmall("Too many ads", "h", load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNextSmall("Offensive", "h", load_view));

        //Report Click
        ((TextView) load_view.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNextSmall("Sexually inappropriate", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNextSmall("Illegal", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNextSmall("Offensive", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNextSmall("Spam", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNextSmall("Disagreeable", "r", load_view));
        ((TextView) load_view.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNextSmall("Other", "r", load_view));
    }

    public static void AdsNextSmall(String problem, String type, RelativeLayout load_view) {

        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.GONE);
        ((RelativeLayout) load_view.findViewById(R.id.ll_done_problem)).setVisibility(View.VISIBLE);

        if (type.equals("h")) {
            ((LinearLayout) load_view.findViewById(R.id.ll_hide_done)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_report_done)).setVisibility(View.GONE);
            ((TextView) load_view.findViewById(R.id.hide_problem)).setText(problem);
        } else {
            ((LinearLayout) load_view.findViewById(R.id.ll_report_done)).setVisibility(View.VISIBLE);
            ((LinearLayout) load_view.findViewById(R.id.ll_hide_done)).setVisibility(View.GONE);
            ((TextView) load_view.findViewById(R.id.report_problem)).setText(problem);
        }

    }
}

