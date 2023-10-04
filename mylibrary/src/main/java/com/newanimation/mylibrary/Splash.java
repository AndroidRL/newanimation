package com.newanimation.mylibrary;

import static com.newanimation.mylibrary.MyProHelperClass.SetLinkAd;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Splash extends AppCompatActivity {

    public static String extra_switch_1;
    public static String extra_switch_2;
    public static String extra_switch_3;
    public static String extra_text_1;
    public static String extra_text_2;
    public static String extra_text_3;
    public static String extra_text_4;
    public static Context contextx;
    public static Intent intentx;
    public static int on_offAds;
    public static boolean isShowOpen = false;
    public static AppOpenManager appOpenManager;
    public static AppQOpenManager QappOpenManager;
    public static String PackName = "";
    public static boolean OpenAdsStatus = false;
    public static boolean QurekaOpenAdsStatus = false;
    public static boolean checkAppOpen = true;

    public static BottomSheetDialog bottomDialog;
    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }


    /*Splash*/
    public static void StartAnimation(Context context, Intent intent, String packageName, String versionCode, int on_off, String basic_) {
        PackName = packageName;
        contextx = context;
        intentx = intent;
        on_offAds = on_off;


        if (!MyProHelperClass.isOnline(context)) {
            context.startActivity(new Intent(context, InternetErrorActivity.class));
            return;
        }
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://gist.githubusercontent.com/AndroidRL/" + basic_,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        try {

                            /**
                             * Google
                             */

                            MyProHelperClass.setGoogleEnable(response.getString("enable_google_admob_id"));
                            //google Banner
                            if (response.getString("google_admob_banner_id") != null && !response.getString("google_admob_banner_id").isEmpty()) {
                                MyProHelperClass.SetGoogleBanner(response.getString("google_admob_banner_id"));
                            } else {
                                MyProHelperClass.SetGoogleBanner(null);
                            }
                            if (response.getString("google_admob_banner_id_1") != null && !response.getString("google_admob_banner_id_1").isEmpty()) {
                                MyProHelperClass.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                            } else {
                                MyProHelperClass.SetGoogleBanner1(null);
                            }
                            if (response.getString("google_admob_banner_id_2") != null && !response.getString("google_admob_banner_id_2").isEmpty()) {
                                MyProHelperClass.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                            } else {
                                MyProHelperClass.SetGoogleBanner2(null);
                            }
                            //google Native
                            if (response.getString("google_admob_native_id") != null && !response.getString("google_admob_native_id").isEmpty()) {
                                MyProHelperClass.SetGoogleNative(response.getString("google_admob_native_id"));
                            } else {
                                MyProHelperClass.SetGoogleNative(null);
                            }
                            if (response.getString("google_admob_native_id_1") != null && !response.getString("google_admob_native_id_1").isEmpty()) {
                                MyProHelperClass.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                            } else {
                                MyProHelperClass.SetGoogleNative1(null);
                            }
                            if (response.getString("google_admob_native_id_2") != null && !response.getString("google_admob_native_id_2").isEmpty()) {
                                MyProHelperClass.SetGoogleNative2(response.getString("google_admob_native_id_2"));
                            } else {
                                MyProHelperClass.SetGoogleNative2(null);
                            }

                            //google Native Btn Name
                            if (response.getString("google_button_name") != null && !response.getString("google_button_name").isEmpty()) {
                                MyProHelperClass.setGooglebutton_name(response.getString("google_button_name"));
                            } else {
                                MyProHelperClass.setGooglebutton_name(null);
                            }
                            //google Native Btn color
                            if (response.getString("google_button_color") != null && !response.getString("google_button_color").isEmpty()) {
                                MyProHelperClass.setGooglebutton_color(response.getString("google_button_color"));
                            } else {
                                MyProHelperClass.setGooglebutton_color("#000000");
                            }
                            // google Open ADS
                            if (response.getString("google_openapp_id") != null && !response.getString("google_openapp_id").isEmpty()) {
                                MyProHelperClass.setGoogle_OpenADS(response.getString("google_openapp_id"));
                            } else {
                                MyProHelperClass.setGoogle_OpenADS(null);
                            }
                            //google Interstitial
                            if (response.getString("google_admob_interstitial_id") != null && !response.getString("google_admob_interstitial_id").isEmpty()) {
                                NextAnimation.AutoGoogleInterID = 1;
                                MyProHelperClass.SetGoogleInter(response.getString("google_admob_interstitial_id"));
                            } else {
                                MyProHelperClass.SetGoogleInter(null);
                            }
                            if (response.getString("google_admob_interstitial_id_1") != null && !response.getString("google_admob_interstitial_id_1").isEmpty()) {
                                MyProHelperClass.SetGoogleInter1(response.getString("google_admob_interstitial_id_1"));
                            } else {
                                MyProHelperClass.SetGoogleInter1(null);
                            }
                            if (response.getString("google_admob_interstitial_id_2") != null && !response.getString("google_admob_interstitial_id_2").isEmpty()) {
                                MyProHelperClass.SetGoogleInter2(response.getString("google_admob_interstitial_id_2"));
                            } else {
                                MyProHelperClass.SetGoogleInter2(null);
                            }
                            /**
                             * Facebook
                             */
                            MyProHelperClass.setFacebookEnable(response.getString("enable_facebook_id"));
                            if (packageName.equals("Test")) {
                                AdSettings.setTestMode(true);
                            }
                            //Facebook Banner
                            if (response.getString("facebook_banner_id") != null && !response.getString("facebook_banner_id").isEmpty()) {
                                MyProHelperClass.setFacebookBanner(response.getString("facebook_banner_id"));
                            } else {
                                MyProHelperClass.setFacebookBanner(null);
                            }
                            if (response.getString("facebook_banner_id_1") != null && !response.getString("facebook_banner_id_1").isEmpty()) {
                                MyProHelperClass.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                            } else {
                                MyProHelperClass.setFacebookBanner1(null);
                            }
                            if (response.getString("facebook_banner_id_2") != null && !response.getString("facebook_banner_id_2").isEmpty()) {
                                MyProHelperClass.setFacebookBanner2(response.getString("facebook_banner_id_2"));
                            } else {
                                MyProHelperClass.setFacebookBanner2(null);
                            }
                            //Facebook Native
                            if (response.getString("facebook_native_id") != null && !response.getString("facebook_native_id").isEmpty()) {
                                MyProHelperClass.SetFacebookNative(response.getString("facebook_native_id"));
                            } else {
                                MyProHelperClass.SetFacebookNative(null);
                            }
                            if (response.getString("facebook_native_id_1") != null && !response.getString("facebook_native_id_1").isEmpty()) {
                                MyProHelperClass.SetFacebookNative1(response.getString("facebook_native_id_1"));
                            } else {
                                MyProHelperClass.SetFacebookNative1(null);
                            }
                            if (response.getString("facebook_native_id_2") != null && !response.getString("facebook_native_id_2").isEmpty()) {
                                MyProHelperClass.SetFacebookNative2(response.getString("facebook_native_id_2"));
                            } else {
                                MyProHelperClass.SetFacebookNative2(null);
                            }

                            //Facebook Open ADS
                            if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                                MyProHelperClass.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                            } else {
                                MyProHelperClass.setfacebook_open_ad_id(null);
                            }
                            //Facebook Interstitial
                            if (response.getString("facebook_interstitial_id") != null && !response.getString("facebook_interstitial_id").isEmpty()) {
                                MyProHelperClass.SetFacebookInter(response.getString("facebook_interstitial_id"));
                            } else {
                                MyProHelperClass.SetFacebookInter(null);
                            }
                            if (response.getString("facebook_interstitial_id_1") != null && !response.getString("facebook_interstitial_id_1").isEmpty()) {
                                MyProHelperClass.SetFacebookInter1(response.getString("facebook_interstitial_id_1"));
                            } else {
                                MyProHelperClass.SetFacebookInter1(null);
                            }
                            if (response.getString("facebook_interstitial_id_2") != null && !response.getString("facebook_interstitial_id_2").isEmpty()) {
                                MyProHelperClass.SetFacebookInter2(response.getString("facebook_interstitial_id_2"));
                            } else {
                                MyProHelperClass.SetFacebookInter2(null);
                            }
                            /**
                             *   Atme and qureka Link
                             */
                            /*link open with graphics*/
                            MyProHelperClass.setQurekaADS(response.getString("enable_quereka_graphic"));  //on_off  qureka Graphics
                            MyProHelperClass.set_q_link_btn_on_off(response.getString("enable_auto_quereka_link"));  //on_off Graphics not show direcyli opne link
                            MyProHelperClass.set_q_link_array(response.getString("quereka_link_graphic")); //link Array

                            /*Only link open and time*/
                            MyProHelperClass.setauto_link_on_off(response.getString("enable_auto_quereka_link"));  //on_off Auto link
                            if (MyProHelperClass.getauto_link_on_off().equals("1")) {
                                MyProHelperClass.setauto_link_array(response.getString("auto_quereka_link")); //link Array
                                MyProHelperClass.setauto_link_timer(response.getString("auto_quereka_time")); //open Timer
                                MyProHelperClass.Autolink();
                            }

                            /**
                             * App Loving
                             */
                            MyProHelperClass.setAppLovinEnable(response.getString("enable_applovin_id"));  //on_off App Lovin
                            if (response.getString("applovin_banner") != null && !response.getString("applovin_banner").isEmpty()) {   //Banner
                                MyProHelperClass.setAppLovinBanner(response.getString("applovin_banner"));
                            } else {
                                MyProHelperClass.setAppLovinBanner(null);
                            }
                            if (response.getString("applovin_native") != null && !response.getString("applovin_native").isEmpty()) {   //Native
                                MyProHelperClass.setAppLovinNative(response.getString("applovin_native"));
                            } else {
                                MyProHelperClass.setAppLovinNative(null);
                            }
                            if (response.getString("applovin_interstitial") != null && !response.getString("applovin_interstitial").isEmpty()) {   //Inter
                                MyProHelperClass.setAppLovinInter(response.getString("applovin_interstitial"));

                            } else {
                                MyProHelperClass.setAppLovinInter(null);
                            }

                            /**
                             *Unity
                             */
                            MyProHelperClass.setUnityEnable(response.getString("enable_unity_id"));  //on_off Unity
                            if (response.getString("unity_game_id") != null && !response.getString("unity_game_id").isEmpty()) {   //Unity ID
                                MyProHelperClass.setUnityAppID(response.getString("unity_game_id"));
                                UnityAds.initialize(MyProHelperClass.instance, MyProHelperClass.getUnityAppID(), false);
                            } else {
                                MyProHelperClass.setUnityAppID(null);
                            }
                            if (response.getString("unity_banner") != null && !response.getString("unity_banner").isEmpty()) { //Unity Banner ID
                                MyProHelperClass.setUnityBannerID(response.getString("unity_banner"));
                            } else {
                                MyProHelperClass.setUnityBannerID(null);
                            }
                            if (response.getString("unity_interstitial") != null && !response.getString("unity_interstitial").isEmpty()) {  //Unity Inter ID
                                MyProHelperClass.setUnityInterID(response.getString("unity_interstitial"));
                            } else {
                                MyProHelperClass.setUnityInterID(null);
                            }
                            /**
                             *Back Button
                             */
                            MyProHelperClass.setBackAdsOnOff(response.getString("enable_back_button"));
                            if (response.getString("back_button_inter_skip") != null && !response.getString("back_button_inter_skip").isEmpty()) {
                                MyProHelperClass.setBackCounter(Integer.parseInt(response.getString("back_button_inter_skip")));  //skip ads number
                            } else {
                                MyProHelperClass.setBackCounter(5000);
                            }

                            /**
                             * All Fail show qureka
                             * Fix Ad
                             */
                            if (response.getString("all_ads_show_qureka") != null && !response.getString("all_ads_show_qureka").isEmpty()) {
                                MyProHelperClass.setQurekaShow_AfterFails(response.getString("all_ads_show_qureka"));  //All Ads Fail after show qureka
                            } else {
                                MyProHelperClass.setQurekaShow_AfterFails("0");
                            }

                            if (response.getString("fix_qureka_show") != null && !response.getString("fix_qureka_show").isEmpty()) {
                                MyProHelperClass.setQurekaFixAds(response.getString("fix_qureka_show"));  //Fix ads show
                            } else {
                                MyProHelperClass.setQurekaFixAds("0");
                            }
                            if (response.getString("qureka_inter_ad_skip_time") != null && !response.getString("qureka_inter_ad_skip_time").isEmpty()) {
                                MyProHelperClass.setQurekaInterSkipTime(response.getString("qureka_inter_ad_skip_time"));  //Fix ads show
                            } else {
                                MyProHelperClass.setQurekaInterSkipTime("0");
                            }
                            if (response.getString("qureka_inter_ad_close_btn_click") != null && !response.getString("qureka_inter_ad_close_btn_click").isEmpty()) {
                                MyProHelperClass.setQurekaCloseBTNAutoOpenLink(response.getString("qureka_inter_ad_close_btn_click"));  //Fix ads show
                            } else {
                                MyProHelperClass.setQurekaCloseBTNAutoOpenLink("0");
                            }
                            if (response.getString("close_intent_open_link") != null && !response.getString("close_intent_open_link").isEmpty()) {
                                MyProHelperClass.setClose_intent_open_link(response.getString("close_intent_open_link"));  //Close ad open link
                            } else {
                                MyProHelperClass.setClose_intent_open_link("0");
                            }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 26b16ed09941370bd8bfb049ab1e0c3414fe0e53
                            if (response.getString("link_ad_type") != null && !response.getString("link_ad_type").isEmpty()) {
                                MyProHelperClass.setLink_ad_type(response.getString("link_ad_type"));  //Close ad open link
                            } else {
                                MyProHelperClass.setLink_ad_type("q");
                            }
                            SetLinkAd();
<<<<<<< HEAD
=======
=======
>>>>>>> c6dd13fe6a3b00cec4ad4abe001a47344709469c
>>>>>>> 26b16ed09941370bd8bfb049ab1e0c3414fe0e53
                            /**
                             * Skip Ads
                             * 0 = stop Ads
                             */
                            if (response.getString("skip_inter_ad") != null && !response.getString("skip_inter_ad").isEmpty()) {
                                MyProHelperClass.setCounter_Inter(Integer.parseInt(response.getString("skip_inter_ad")));
                            } else {
                                MyProHelperClass.setCounter_Inter(5000);
                            }
                            if (response.getString("skip_native_ad") != null && !response.getString("skip_native_ad").isEmpty()) {
                                MyProHelperClass.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                            } else {
                                MyProHelperClass.setCounter_Native(5000);
                            }
                            if (response.getString("skip_banner_ad") != null && !response.getString("skip_banner_ad").isEmpty()) {
                                MyProHelperClass.setCounter_Banner(Integer.parseInt(response.getString("skip_banner_ad")));
                            } else {
                                MyProHelperClass.setCounter_Banner(5000);
                            }

                            if (response.getString("skip_open_ad") != null && !response.getString("skip_open_ad").isEmpty()) {
                                MyProHelperClass.setOpenAdsShow(Integer.parseInt(response.getString("skip_open_ad")));
                            } else {
                                MyProHelperClass.setOpenAdsShow(0);
                            }


                            /**
                             * Mix Ads
                             */
                            MyProHelperClass.setmix_ad_on_off(response.getString("mix_ad"));

                            //Mix Ads Number
                            if (response.getString("mix_ad_counter_banner") != null && !response.getString("mix_ad_counter_banner").isEmpty()) {
                                MyProHelperClass.setmix_ad_counter_banner(Integer.parseInt(response.getString("mix_ad_counter_banner")));
                            } else {
                                MyProHelperClass.setmix_ad_counter_banner(5000);
                            }

                            if (response.getString("mix_ad_counter_native") != null && !response.getString("mix_ad_counter_native").isEmpty()) {
                                MyProHelperClass.setmix_ad_counter_native(Integer.parseInt(response.getString("mix_ad_counter_native")));
                            } else {
                                MyProHelperClass.setmix_ad_counter_native(5000);
                            }

                            if (response.getString("mix_ad_counter_interstitial") != null && !response.getString("mix_ad_counter_interstitial").isEmpty()) {
                                MyProHelperClass.setmix_ad_counter_inter(Integer.parseInt(response.getString("mix_ad_counter_interstitial")));
                            } else {
                                MyProHelperClass.setmix_ad_counter_inter(5000);
                            }

                            //Mix Ads Name
                            if (response.getString("mix_ad_open") != null && !response.getString("mix_ad_open").isEmpty()) {
                                MyProHelperClass.setmix_ad_open(response.getString("mix_ad_open"));
                            } else {
                                MyProHelperClass.setmix_ad_open(null);
                            }

                            if (response.getString("mix_ad_banner") != null && !response.getString("mix_ad_banner").isEmpty()) {
                                MyProHelperClass.setmix_ad_banner(response.getString("mix_ad_banner"));
                            } else {
                                MyProHelperClass.setmix_ad_banner(null);
                            }

                            if (response.getString("mix_ad_native") != null && !response.getString("mix_ad_native").isEmpty()) {
                                MyProHelperClass.setmix_ad_native(response.getString("mix_ad_native"));
                            } else {
                                MyProHelperClass.setmix_ad_native(null);
                            }

                            if (response.getString("mix_ad_interstitial") != null && !response.getString("mix_ad_interstitial").isEmpty()) {
                                MyProHelperClass.setmix_ad_inter(response.getString("mix_ad_interstitial"));
                            } else {
                                MyProHelperClass.setmix_ad_inter(null);
                            }

                            /**
                             * Banner or native
                             */
                            if (response.getString("banner_replace_native") != null && !response.getString("banner_replace_native").isEmpty()) {
                                MyProHelperClass.setShowBannerNative(response.getString("banner_replace_native"));
                            } else {
                                MyProHelperClass.setShowBannerNative(null);
                            }

                            if (response.getString("native_size") != null && !response.getString("native_size").isEmpty()) {
                                MyProHelperClass.setNativeViewSize(response.getString("native_size"));
                            } else {
                                MyProHelperClass.setNativeViewSize(null);
                            }

                            /**
                             * Facebook SDK
                             */

                            if (response.getString("enable_facebook_sdk") != null && !response.getString("enable_facebook_sdk").isEmpty()) {
                                MyProHelperClass.setFacebookSDK(response.getString("enable_facebook_sdk"));
                            } else {
                                MyProHelperClass.setFacebookSDK(null);
                            }
                            if (MyProHelperClass.getFacebookSDK().equals("1")) {
                                MyProHelperClass.setACCESS_TOKEN(response.getString("ACCESS_TOKEN"));
                                MyProHelperClass.setAPP_SECRET(response.getString("APP_SECRET"));
                                MyProHelperClass.setACCOUNT_ID(response.getString("ACCOUNT_ID"));
                                TestFBJavaSDK fbSdkClass = new TestFBJavaSDK();
                                fbSdkClass.main(new String[]{});
                            }

                            /**
                             *Extra Data
                             */
                            extra_switch_1 = response.getString("extra_switch_1");
                            extra_switch_2 = response.getString("extra_switch_2");
                            extra_switch_3 = response.getString("extra_switch_3");

                            extra_text_1 = response.getString("extra_text_1");
                            extra_text_2 = response.getString("extra_text_2");
                            extra_text_3 = response.getString("extra_text_3");
                            extra_text_4 = response.getString("extra_text_4");


                            /**
                             *PreLoad On OFF
                             */
                            //Interstitial PreLoad
                            MyProHelperClass.setInterPreLoad(response.getString("interstitial_preload"));
                            //Native PreLoad
                            MyProHelperClass.setNative_preload(response.getString("native_preload"));
                            //Banner PreLoad
                            MyProHelperClass.setBanner_preload(response.getString("banner_preload"));


                            /**
                             * Other app on
                             */
                            MyProHelperClass.setOtherAppsShow(response.getString("replace_app"));
                            MyProHelperClass.setOtherAppsShowLink(response.getString("new_app_link"));
                            if (MyProHelperClass.getOtherAppsShow().equals("1")) {
                                MyProHelperClass.Entery_UpdateApps = 2;
                                context.startActivity(new Intent(context, UpdateAppActivity.class));
                                return;
                            }

                            /**
                             * Update app
                             */
                            MyProHelperClass.setUpdateApps(response.getString("update_app"));
                            MyProHelperClass.setAppversioncode(response.getString("version_code"));
                            if (MyProHelperClass.getUpdateApps().equals("1")) {
                                if (!MyProHelperClass.getAppversioncode().equals(versionCode)) {
                                    MyProHelperClass.Entery_UpdateApps = 1;
                                    context.startActivity(new Intent(context, UpdateAppActivity.class));
                                    return;
                                }
                            }
                            /**
                             * Next App
                             */
                            if (on_offAds == 2) {
                                MyProHelperClass.setGoogleEnable("0");
                                MyProHelperClass.setFacebookEnable("0");
                                MyProHelperClass.setauto_link_on_off("0");
                                MyProHelperClass.set_q_link_btn_on_off("0");
                                MyProHelperClass.setQurekaADS("0");
                                MyProHelperClass.setAppLovinEnable("0");
                                MyProHelperClass.setUnityEnable("0");
                                MyProHelperClass.setCustomEnable("0");
                                MyProHelperClass.setmix_ad_on_off("0");
                                MyProHelperClass.setBackAdsOnOff("0");
                                MyProHelperClass.setQurekaFixAds("0");
                                MyProHelperClass.setQurekaShow_AfterFails("0");
                                NextIntent(contextx, intentx);
                                return;
                            }
                            if (on_offAds == 1 || MyProHelperClass.getOpenAdsShow() == 1) {
                                on_offAds = 1;
                                AllAdsPreLoad();  //only Preload __ Not show open ads
                                return;
                            }
                            ShowADS();  //open ads show
                            AllAdsPreLoad();  //Pre load ads
                        } catch (
                                JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable
                            throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);

                    }
                });
    }

    public static void NextIntent(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    /**
     * Show Ads
     */
    private static void ShowADS() {
        if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
            MixOpenAds(MyProHelperClass.getsetmix_ad_open());
            return;
        }
        if (MyProHelperClass.getGoogleEnable().equals("1")) {
            GoogleAppOpen();
        } else if (MyProHelperClass.getFacebookEnable().equals("1")) {
            FaceBookAppOpen();
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            AppLovingAppOpen();
        } else if (MyProHelperClass.getUnityEnable().equals("1")) {
            UnityAppOpen();
        } else if (MyProHelperClass.getQurekaADS().equals("1")) {
            QurekaOpen();
        } else {
            NextIntent(contextx, intentx);
        }
    }

    /**
     * Show Ads
     */
    private static void GoogleAppOpen() {
        if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {
            try {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailsAds("g");
                        }
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            if (checkAppOpen) {
                checkAppOpen = false;
                FailsAds("g");
            }
        }

    }

    private static void FaceBookAppOpen() {

        if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {

            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    FailsAds("f");
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {

                        FailsAds("f");

                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else {

            FailsAds("f");
        }

    }

    private static void AppLovingAppOpen() {

        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        FailsAds("a");
                        /*AppLoving Inter PreLoad*/
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                            NextAnimation.AppLovingInterPreLoad();
                        }
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                }

                @Override
                public void onAdHidden(MaxAd ad) {

                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                    FailsAds("a");
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            FailsAds("a");
        }

    }

    private static void UnityAppOpen() {

        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
            UnityAds.load(MyProHelperClass.getUnityInterID(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String placementId) {
                    UnityAds.show((Activity) contextx, MyProHelperClass.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                        @Override
                        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                            /*Unity Mix Auto Load Inter*/
                            if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                                NextAnimation.UnityInterPreLoad();
                            }
                            FailsAds("u");

                        }

                        @Override
                        public void onUnityAdsShowStart(String placementId) {


                        }

                        @Override
                        public void onUnityAdsShowClick(String placementId) {

                        }

                        @Override
                        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                            NextIntent(contextx, intentx);
                            /*Unity Mix Auto Load Inter*/
                            if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                                NextAnimation.UnityInterPreLoad();
                            }
                        }
                    });
                }

                @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                    /*Unity Mix Auto Load Inter*/
                    if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                        NextAnimation.UnityInterPreLoad();
                    }
                    FailsAds("u");
                }
            });
        } else {
            FailsAds("u");
        }
    }

    private static void QurekaOpen() {

        AppQOpenManager.OnAppOpenClose onAppOpenClose = () -> {
            if (!QurekaOpenAdsStatus) {
                QurekaOpenAdsStatus = true;
                NextIntent(contextx, intentx);
            }
        };
        QappOpenManager = new AppQOpenManager(MyProHelperClass.getInstant(), (Activity) contextx, onAppOpenClose);

    }

    /**
     * Fails Ads
     */
    public static void FailsAds(String Skip) {

        if (Skip.equals("g")) {

            if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        GoogleandFacebookFails();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        } else {
                            GoogleandFacebookFails();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("f")) {

            if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            GoogleandFacebookFails();
                        }

                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }

                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("a")) {

            if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {

                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailAdsAppLovin_ShowFacebookUnityCustom();
                        }


                    }


                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);

            } else {
                FailAdsAppLovin_ShowFacebookUnityCustom();
            }

        } else if (Skip.equals("u")) {

            if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailUnity_ShowFacebookAppLovinCustom();
                        }


                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);

            } else {
                FailUnity_ShowFacebookAppLovinCustom();
            }

        } else {
            NextIntent(contextx, intentx);
        }
    }

    private static void GoogleandFacebookFails() {

        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {

            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        /*AppLoving Inter PreLoad*/
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                            NextAnimation.AppLovingInterPreLoad();
                        }

                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            QurekaOpen();
                        }

                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                    //Fail Code
                    if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        QurekaOpen();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    //
                }
            });
            interstitialAd.loadAd();


        } else if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
            FailsAdsUnityShow();
        } else {
            QurekaOpen();
        }
    }

    public static void FailAdsAppLovin_ShowFacebookUnityCustom() {
        if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        QurekaOpen();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            QurekaOpen();
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
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
        } else if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
            FailsAdsUnityShow();
        } else {
            QurekaOpen();
        }
    }

    public static void FailUnity_ShowFacebookAppLovinCustom() {

        if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
                        interstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                } else {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                        NextAnimation.AppLovingInterPreLoad();
                                    }
                                    QurekaOpen();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                NextIntent(contextx, intentx);
                                /*AppLoving Inter PreLoad*/
                                if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                    NextAnimation.AppLovingInterPreLoad();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                //Fail Code
                                /*AppLoving Inter PreLoad*/
                                if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                    NextAnimation.AppLovingInterPreLoad();
                                }
                                QurekaOpen();
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                //
                            }
                        });
                        interstitialAd.loadAd();
                    } else {
                        QurekaOpen();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {

                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    } else {
                                        /*AppLoving Inter PreLoad*/
                                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                            NextAnimation.AppLovingInterPreLoad();
                                        }
                                        QurekaOpen();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                        NextAnimation.AppLovingInterPreLoad();
                                    }
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                        NextAnimation.AppLovingInterPreLoad();
                                    }
                                    //Fail Code
                                    QurekaOpen();

                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else {
                            QurekaOpen();
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
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        /*AppLoving Inter PreLoad*/
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                            NextAnimation.AppLovingInterPreLoad();
                        }
                        QurekaOpen();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                    QurekaOpen();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            QurekaOpen();
        }
    }

    private static void FailsAdsUnityShow() {
        UnityAds.load(MyProHelperClass.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show((Activity) contextx, MyProHelperClass.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                        /*Unity Mix Auto Load Inter*/
                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            NextAnimation.UnityInterPreLoad();
                        }
                        QurekaOpen();
                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {


                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                        NextIntent(contextx, intentx);
                        /*Unity Mix Auto Load Inter*/
                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            NextAnimation.UnityInterPreLoad();
                        }

                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                /*Unity Mix Auto Load Inter*/
                if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                    NextAnimation.UnityInterPreLoad();
                }
                QurekaOpen();
            }
        });
    }

    /**
     * All Ads PreLoad
     */

    public static void AllAdsPreLoad() {
        NextAnimation.main_context = (Activity) contextx;
        SmallAnimation.main_context = (Activity) contextx;
        BigAnimation.main_context = (Activity) contextx;

        if (MyProHelperClass.getBanner_preload().equals("1")) {
            MixAdOnBanner();
        }
        if (MyProHelperClass.getNative_preload().equals("1")) {
            MixAdOnNative();
        }
        if (MyProHelperClass.getInterPreLoad().equals("1")) {
            MixAdOnInter();
        }
        //Off Open Ads
        if (on_offAds == 1) {
            new Handler().postDelayed(() -> NextIntent(contextx, intentx), 3000);
        }
    }

    private static void MixAdOnBanner() {
        /**
         * Banner
         */
        /*Google Banner*/
        if (MyProHelperClass.getGoogleBanner() != null && !MyProHelperClass.getGoogleBanner().isEmpty()) {

            if (MyProHelperClass.getGoogleBanner().equals(MyProHelperClass.getGoogleBanner1()) && MyProHelperClass.getGoogleBanner().equals(MyProHelperClass.getGoogleBanner2()) && MyProHelperClass.getGoogleBanner1().equals(MyProHelperClass.getGoogleBanner2())) {
                MyProHelperClass.Google_banner_number = 1;
                SmallAnimation.AutoGoogleBannerID = 1;
                SmallAnimation.GoogleBannerPreload();

            } else {
                if (MyProHelperClass.getGoogleBanner2() == null) {
                    MyProHelperClass.Google_banner_number = 2;
                    SmallAnimation.GoogleBannerPreload1();
                    SmallAnimation.GoogleBannerPreload2();

                } else {
                    MyProHelperClass.Google_banner_number = 3;
                    SmallAnimation.GoogleBannerPreload1();
                    SmallAnimation.GoogleBannerPreload2();
                    SmallAnimation.GoogleBannerPreload3();

                }
            }
        }

        /*Facebook Banner*/
        if (MyProHelperClass.getShowBannerNative().equals("0")) {
            if (MyProHelperClass.getNative_preload() != null && !MyProHelperClass.getNative_preload().isEmpty()) {
                SmallAnimation.FacebookNativeBannerPreLoad();
            }
        } else {
            if (MyProHelperClass.getFacebookBanner() != null && !MyProHelperClass.getFacebookBanner().isEmpty()) {
                SmallAnimation.AutoLoadFBBannerID = 1;
                SmallAnimation.FacebookBannerPreLoad();
            }
        }


        /*AppLoving Banner*/
        if (MyProHelperClass.getAppLovinBanner() != null && !MyProHelperClass.getAppLovinBanner().isEmpty()) {
            SmallAnimation.AppLovingBannerPreLoad();
        }

        /*Unity Banner*/
        if (MyProHelperClass.getUnityBannerID() != null && !MyProHelperClass.getUnityBannerID().isEmpty()) {
            SmallAnimation.UnityBannerPreLoad();
        }
    }

    private static void MixAdOnNative() {
        /**
         * Native
         */
        /*Google Native*/
        if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
            if (MyProHelperClass.getGoogleNative().equals(MyProHelperClass.getGoogleNative1()) && MyProHelperClass.getGoogleNative().equals(MyProHelperClass.getGoogleNative2()) && MyProHelperClass.getGoogleNative1().equals(MyProHelperClass.getGoogleNative2())) {
                MyProHelperClass.Google_native_number = 1;
                BigAnimation.AutoGoogleNativeID = 1;
                BigAnimation.GoogleNativePreload();
            } else {
                if (MyProHelperClass.getGoogleNative2() == null) {
                    MyProHelperClass.Google_native_number = 2;
                    BigAnimation.GoogleNativePreload1();
                    BigAnimation.GoogleNativePreload2();
                } else {
                    MyProHelperClass.Google_native_number = 3;
                    BigAnimation.GoogleNativePreload1();
                    BigAnimation.GoogleNativePreload2();
                    BigAnimation.GoogleNativePreload3();
                }
            }
        }

        /*Facebook Native*/
        if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {
            BigAnimation.AutoLoadFBNativeID = 1;
            BigAnimation.FacebookNativePreLoad();
        }

        /*AppLoving Native*/
        if (MyProHelperClass.getAppLovinNative() != null && !MyProHelperClass.getAppLovinNative().isEmpty()) {
            BigAnimation.AppLovingNativePreLoad();
        }

    }

    private static void MixAdOnInter() {
        /**
         * Inter
         */
        /*Google Inter*/
        if (MyProHelperClass.getGoogleInter() != null && !MyProHelperClass.getGoogleInter().isEmpty()) {

            //Inter
            if (MyProHelperClass.getGoogleInter().equals(MyProHelperClass.getGoogleInter1()) && MyProHelperClass.getGoogleInter().equals(MyProHelperClass.getGoogleInter2()) && MyProHelperClass.getGoogleInter1().equals(MyProHelperClass.getGoogleInter2())) {
                MyProHelperClass.Google_inter_number = 1;
                NextAnimation.AutoGoogleInterID = 1;
                NextAnimation.GoogleInterPreload();
            } else {
                if (MyProHelperClass.getGoogleInter2() == null) {
                    MyProHelperClass.Google_inter_number = 2;
                    NextAnimation.GoogleInterPreload1();
                    NextAnimation.GoogleInterPreload2();
                } else {
                    MyProHelperClass.Google_inter_number = 3;
                    NextAnimation.GoogleInterPreload1();
                    NextAnimation.GoogleInterPreload2();
                    NextAnimation.GoogleInterPreload3();
                }
            }
        }

        /*Facebook Mix Auto Load Inter*/
        if (MyProHelperClass.getFacebookInter() != null && !MyProHelperClass.getFacebookInter().isEmpty()) {
            NextAnimation.AutoLoadFBInterID = 1;
            NextAnimation.FacebookInterPreLoad();
        }
    }


    /**
     * Mix Open Ads
     */

    /*Mix Open*/
    private static void MixOpenAds(String valueOf) {
        if (valueOf.equals("g")) {
            GoogleAppOpen();
        } else if (valueOf.equals("f")) {
            FaceBookAppOpen();
        } else if (valueOf.equals("a")) {
            AppLovingAppOpen();
        } else if (valueOf.equals("u")) {
            UnityAppOpen();
        } else if (valueOf.equals("q")) {
            NextIntent(contextx, intentx);
            MyProHelperClass.BtnAutolink();
        } else if (valueOf.equals("z")) {
            QurekaOpen();
        } else if (valueOf.equals("c")) {
            NextIntent(contextx, intentx);
        } else {
            NextIntent(contextx, intentx);
        }
    }

    public static void ShareApp(Context context, String AppName) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, AppName);
            String shareMessage = "\nInstall this cool application\n\n";
            shareMessage = shareMessage + "Check out the App at : https://play.google.com/store/apps/details?id=" + PackName + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public static void RateApp(Context context) {
        try {
            Uri marketUri = Uri.parse("market://details?id=" + PackName);
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        } catch (ActivityNotFoundException e) {
            Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + PackName);
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        }
    }

}
