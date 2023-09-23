package com.newanimation.mylibrary;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import static com.newanimation.mylibrary.MyProHelperClass.q_openAds_show;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AppQOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private final Application myApplication;
    AppQOpenManager.OnAppOpenClose onAppOpenClose;
    Dialog dialog;
    BottomSheetDialog bottomDialog;
    Activity activity;

    public interface OnAppOpenClose {
        void OnAppOpenClose();
    }

    public AppQOpenManager(Application myApplication, Activity activity1, AppQOpenManager.OnAppOpenClose onAppOpenClose) {
        this.activity = activity1;
        this.myApplication = myApplication;
        this.onAppOpenClose = onAppOpenClose;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void showAdIfAvailable() {
        if (q_openAds_show){
            if (MyProHelperClass.getQurekaShow_AfterFails().equals("1") || MyProHelperClass.getQurekaADS().equals("1")) {
                dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);
                if (MyProHelperClass.getRandomNumber(0, 1) == 0) {
                    dialog.setContentView(R.layout.qureka_open);
                } else {
                    dialog.setContentView(R.layout.qureka_open_2);
                }
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                dialog.findViewById(R.id.next_view).setOnClickListener(v ->
                        {
                            dialog.cancel();
                            onAppOpenClose.OnAppOpenClose();
                        }
                );
                dialog.findViewById(R.id.close).setOnClickListener(v -> {
                    {
                        dialog.cancel();
                        onAppOpenClose.OnAppOpenClose();
                    }
                });
                dialog.findViewById(R.id.Ad_click).setOnClickListener(v -> {
                    dialog.cancel();
                    MyProHelperClass.BtnAutolink();
                });
                int getNumber = MyProHelperClass.getRandomNumber(0, MyProHelperClass.inter_ads.size() - 1);
                Glide.with(myApplication).load(MyProHelperClass.inter_ads.get(getNumber).getImage()).into((ImageView) dialog.findViewById(R.id.q_image));
                ((TextView) dialog.findViewById(R.id.ad_title)).setText(MyProHelperClass.inter_ads.get(getNumber).getTitle());
                ((TextView) dialog.findViewById(R.id.ad_dis)).setText(MyProHelperClass.inter_ads.get(getNumber).getDis());
                Glide.with(myApplication).load(MyProHelperClass.round_ads.get(MyProHelperClass.getRandomNumber(0, MyProHelperClass.round_ads.size() - 1))).into((ImageView) dialog.findViewById(R.id.round));
                ((RelativeLayout) dialog.findViewById(R.id.Ad_click)).setVisibility(View.VISIBLE);

                ((LinearLayout) dialog.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReport());
                dialog.show();
            }
        }else {
            MyProHelperClass.q_openAds_show = true;
        }
    }
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activitys) {
        activity = activitys;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activitys) {
        activity = activitys;

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activitys) {
        activitys = null;
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
    }


    //Open Ads Problem
    public void AdReport() {

        bottomDialog = new BottomSheetDialog(activity);
        bottomDialog.setContentView(R.layout.q_dialog);
        bottomDialog.setCancelable(true);
        bottomDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((LinearLayout) bottomDialog.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);

        //Main Btn Click
        ((LinearLayout) bottomDialog.findViewById(R.id.btn_hide)).setOnClickListener(v -> {
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);

        });
        ((LinearLayout) bottomDialog.findViewById(R.id.btn_report)).setOnClickListener(v -> {
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
        });

        //Back
        ((ImageView) bottomDialog.findViewById(R.id.back_hide)).setOnClickListener(v -> {
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_hide)).setVisibility(View.GONE);

        });
        ((ImageView) bottomDialog.findViewById(R.id.back_report)).setOnClickListener(v -> {
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) bottomDialog.findViewById(R.id.ll_report)).setVisibility(View.GONE);

        });

        //Hide Click
        ((TextView) bottomDialog.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNext("Irrelevant", "h"));
        ((TextView) bottomDialog.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNext("Repetitive", "h"));
        ((TextView) bottomDialog.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNext("Interrupted me", "h"));
        ((TextView) bottomDialog.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNext("Unexpected", "h"));
        ((TextView) bottomDialog.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNext("Too many ads", "h"));
        ((TextView) bottomDialog.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNext("Offensive", "h"));

        //Report Click
        ((TextView) bottomDialog.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNext("Sexually inappropriate", "r"));
        ((TextView) bottomDialog.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNext("Illegal", "r"));
        ((TextView) bottomDialog.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNext("Offensive", "r"));
        ((TextView) bottomDialog.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNext("Spam", "r"));
        ((TextView) bottomDialog.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNext("Disagreeable", "r"));
        ((TextView) bottomDialog.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNext("Other", "r"));

        bottomDialog.show();


    }

    public void AdsNext(String problem, String type) {
        bottomDialog.cancel();
        ((RelativeLayout) dialog.findViewById(R.id.Ad_click)).setVisibility(View.GONE);
        ((RelativeLayout) dialog.findViewById(R.id.ll_main)).setVisibility(View.VISIBLE);

        if (type.equals("h")) {
            ((LinearLayout) dialog.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);
            ((LinearLayout) dialog.findViewById(R.id.ll_report)).setVisibility(View.GONE);
            ((TextView) dialog.findViewById(R.id.hide_p_1)).setText(problem);
        } else {
            ((LinearLayout) dialog.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            ((LinearLayout) dialog.findViewById(R.id.ll_hide)).setVisibility(View.GONE);
            ((TextView) dialog.findViewById(R.id.report_p_1)).setText(problem);
        }


    }
}