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
                if (A == 0) {
                    LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.qureka_small_native, main_native, false);
                    Glide.with(main_context).load(MyProHelperClass.banner_ads.get(MyProHelperClass.getRandomNumber(0, MyProHelperClass.banner_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.q_banner));
                    load_view.findViewById(R.id.ad_unit_qureka).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                    main_native.removeAllViews();
                    main_native.addView(load_view);
                } else {

                    LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.qureka_small_native_2, main_native, false);

                    Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                    int getNumber = getRandomNumber(0, native_ads.size() - 1);
                    ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                    ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                    load_view.findViewById(R.id.qureka_big_native).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                    load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                    main_native.removeAllViews();
                    main_native.addView(load_view);
                }

            } else if (Ad_Size == 1) {
                //Medium Native Ad
                LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.qureka_medium_native, main_native, false);

                Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
                int getNumber = getRandomNumber(0, native_ads.size() - 1);
                Glide.with(main_context).load(native_ads.get(getNumber).getImage()).into((ImageView) load_view.findViewById(R.id.q_image));
                ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                load_view.findViewById(R.id.qureka_medium_native).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
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
                ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
                ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

                load_view.findViewById(R.id.qureka_big_native).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
                main_native.removeAllViews();
                main_native.addView(load_view);
            }
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
