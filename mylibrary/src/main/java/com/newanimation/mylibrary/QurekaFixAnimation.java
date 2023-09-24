package com.newanimation.mylibrary;

import static com.newanimation.mylibrary.MyProHelperClass.getRandomNumber;
import static com.newanimation.mylibrary.MyProHelperClass.native_ads;
import static com.newanimation.mylibrary.MyProHelperClass.round_ads;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class QurekaFixAnimation {

    public static void QurekaTopAnimation(Activity main_context, RelativeLayout main_native, int Ad_Size) {
        if (MyProHelperClass.getQurekaFixAds().equals("1")) {
            if (Ad_Size == 0) {
                //Small Native Ad
                int A = MyProHelperClass.getRandomNumber(0, 1);
                switch (A) {
                    case 0: {
                        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_small_native, main_native, false);
                        Glide.with(main_context).load(MyProHelperClass.banner_ads.get(MyProHelperClass.getRandomNumber(0, MyProHelperClass.banner_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.q_banner));

                        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);
                        ((LinearLayout) load_view.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReportSmall(load_view));
                        load_view.findViewById(R.id.ad_show).setOnClickListener(v -> MyProHelperClass.BtnAutolink());

                        main_native.removeAllViews();
                        main_native.addView(load_view);
                        break;
                    }
                    default: {

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
                        break;
                    }
                }


            } else if (Ad_Size == 1) {
                //Medium Native Ad
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


            } else if (Ad_Size == 2) {

                //Big Native Ad
                LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_big_native, main_native, false);

                Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                int getNumber = getRandomNumber(0, native_ads.size() - 1);
                Glide.with(main_context).load(native_ads.get(getNumber).getImage()).into((ImageView) load_view.findViewById(R.id.q_image));
                ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.VISIBLE);

                ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());
                ((LinearLayout) load_view.findViewById(R.id.ad_report)).setOnClickListener(v -> AdReportBig(load_view));

                load_view.findViewById(R.id.ad_show).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());

                main_native.removeAllViews();
                main_native.addView(load_view);
            }
        }
    }

    //Big Ads

    public static void AdReportBig(RelativeLayout load_view) {
        ((RelativeLayout) load_view.findViewById(R.id.problem)).setVisibility(View.VISIBLE);
        ((RelativeLayout) load_view.findViewById(R.id.ad_show)).setVisibility(View.GONE);

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
        ((TextView) load_view.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNextBig("Irrelevant", "h",load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNextBig("Repetitive", "h",load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNextBig("Interrupted me", "h",load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNextBig("Unexpected", "h",load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNextBig("Too many ads", "h",load_view));
        ((TextView) load_view.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNextBig("Offensive", "h",load_view));

        //Report Click
        ((TextView) load_view.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNextBig("Sexually inappropriate", "r",load_view));
        ((TextView) load_view.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNextBig("Illegal", "r",load_view));
        ((TextView) load_view.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNextBig("Offensive", "r",load_view));
        ((TextView) load_view.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNextBig("Spam", "r",load_view));
        ((TextView) load_view.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNextBig("Disagreeable", "r",load_view));
        ((TextView) load_view.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNextBig("Other", "r",load_view));
    }
    public static void AdsNextBig(String problem, String type,RelativeLayout load_view) {

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


    public static void QurekaBottomAnimation(Context context, RelativeLayout main_banner) {
        if (MyProHelperClass.getQurekaFixAds().equals("1")) {
            int A = MyProHelperClass.getRandomNumber(0, 1);
            if (A == 0) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.qureka_small_native, main_banner, false);
                Glide.with(context).load(MyProHelperClass.banner_ads.get(MyProHelperClass.getRandomNumber(0, MyProHelperClass.banner_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.q_banner));
                load_view.findViewById(R.id.ad_unit_qureka).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                main_banner.removeAllViews();
                main_banner.addView(load_view);
            } else {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.qureka_small_native_2, main_banner, false);

                Glide.with(context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                int getNumber = getRandomNumber(0, native_ads.size() - 1);
                ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                load_view.findViewById(R.id.qureka_big_native).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                main_banner.removeAllViews();
                main_banner.addView(load_view);
            }

        }
    }

    public static void QurekaRoundAnimation(Activity context, RelativeLayout roundLayout, int adSize, int roundNumber) {
        //adSize = 1 - 5
        //roundNumber = 1,2
        if (MyProHelperClass.getQurekaFixAds().equals("1")) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.qureka_round, roundLayout, false);
            if (roundNumber == 1){
                roundNumber1(adSize,context,load_view);
            }else if (roundNumber == 2){
                roundNumber2(adSize,context,load_view);

            }
            ((LinearLayout) load_view.findViewById(R.id.qureka_big_native)).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
            roundLayout.removeAllViews();
            roundLayout.addView(load_view);
        }
    }
    private static void roundNumber1(int adSize, Activity context, LinearLayout load_view) {
        if (adSize == 1) {
            ((RelativeLayout) load_view.findViewById(R.id.ll_1_1)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_1_1));
        } else if (adSize == 2) {
            ((RelativeLayout) load_view.findViewById(R.id.ll_1_2)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_1_2));
        } else if (adSize == 3) {
            ((RelativeLayout) load_view.findViewById(R.id.ll_1_3)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_1_3));
        } else if (adSize == 4) {
            ((RelativeLayout) load_view.findViewById(R.id.ll_1_4)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_1_4));
        } else if (adSize == 5) {
            ((RelativeLayout) load_view.findViewById(R.id.ll_1_5)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_1_5));
        }
    }
    private static void roundNumber2(int adSize, Activity context, LinearLayout load_view) {
        if (adSize == 1) {
            ((LinearLayout) load_view.findViewById(R.id.ll_2_1)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_1));
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_1_1));
        } else if (adSize == 2) {
            ((LinearLayout) load_view.findViewById(R.id.ll_2_2)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_2));
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_2_2));
        } else if (adSize == 3) {
            ((LinearLayout) load_view.findViewById(R.id.ll_2_3)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_3));
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_3_3));
        } else if (adSize == 4) {
            ((LinearLayout) load_view.findViewById(R.id.ll_2_4)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_4));
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_4_4));
        } else if (adSize == 5) {
            ((LinearLayout) load_view.findViewById(R.id.ll_2_5)).setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_5));
            Glide.with(context).
                    load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).
                    into((ImageView) load_view.findViewById(R.id.round_2_5_5));
        }
    }
}
