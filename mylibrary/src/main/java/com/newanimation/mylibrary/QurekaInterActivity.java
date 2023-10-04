package com.newanimation.mylibrary;

<<<<<<< HEAD
import static com.newanimation.mylibrary.MyProHelperClass.BtnAutolink;
import static com.newanimation.mylibrary.MyProHelperClass.getQurekaCloseBTNAutoOpenLink;

=======
<<<<<<< HEAD
import static com.newanimation.mylibrary.MyProHelperClass.BtnAutolink;
import static com.newanimation.mylibrary.MyProHelperClass.getQurekaCloseBTNAutoOpenLink;

=======
>>>>>>> c6dd13fe6a3b00cec4ad4abe001a47344709469c
>>>>>>> 26b16ed09941370bd8bfb049ab1e0c3414fe0e53
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class QurekaInterActivity extends AppCompatActivity {

    private int counter = Integer.valueOf(MyProHelperClass.getQurekaInterSkipTime());
    private TextView adTitle;
    private TextView adDis;
    private TextView txtSkip;
    private int getNumber;
    BottomSheetDialog dialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        int A = MyProHelperClass.getRandomNumber(0, 1);
        if (A == 0) {
            setContentView(R.layout.qureka_inter);
        } else {
            setContentView(R.layout.qureka_inter_2);
        }

        txtSkip = (TextView) findViewById(R.id.txt_skip);
        txtSkip.setText("Skip " + MyProHelperClass.getQurekaInterSkipTime());
        getNumber = MyProHelperClass.getRandomNumber(0, MyProHelperClass.inter_ads.size() - 1);
        Glide.with(QurekaInterActivity.this).load(MyProHelperClass.inter_ads.get(getNumber).getImage()).into((ImageView) findViewById(R.id.inter_image));
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 26b16ed09941370bd8bfb049ab1e0c3414fe0e53

        ((ImageView) findViewById(R.id.inter_image)).setOnClickListener(v -> Next());
        ((RelativeLayout) findViewById(R.id.lin_as_header)).setOnClickListener(v -> Next());
        ((LinearLayout) findViewById(R.id.ad_report)).setOnClickListener(v -> AdReport());

<<<<<<< HEAD
        initView();
    }

=======
        initView();
=======
        ((ImageView) findViewById(R.id.inter_image)).setOnClickListener(v ->
                {
                    if (NextAnimation.qureka_intent == null) {
                        finish();
                    } else {
                        startActivity(NextAnimation.qureka_intent);
                        finish();
                    }
                    MyProHelperClass.BtnAutolink();
                }
        );
        ((RelativeLayout) findViewById(R.id.lin_as_header)).setOnClickListener(v ->
                {
                    if (NextAnimation.qureka_intent == null) {
                        finish();
                    } else {
                        startActivity(NextAnimation.qureka_intent);
                        finish();
                    }
                    MyProHelperClass.BtnAutolink();
                }
        );
        ((LinearLayout) findViewById(R.id.ad_report)).setOnClickListener(v ->
                {
                    AdReport();
                }
        );
        initView();
    }

    private void AdReport() {


        dialogs = new BottomSheetDialog(this);
        dialogs.setContentView(R.layout.q_dialog);
        dialogs.setCancelable(true);
        dialogs.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);

            //Main Btn Click
            ((LinearLayout) dialogs.findViewById(R.id.btn_hide)).setOnClickListener(v -> {
                ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.GONE);
                ((LinearLayout) dialogs.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);

            });
            ((LinearLayout) dialogs.findViewById(R.id.btn_report)).setOnClickListener(v -> {
                ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.GONE);
                ((LinearLayout) dialogs.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            });

        //Back
        ((ImageView) dialogs.findViewById(R.id.back_hide)).setOnClickListener(v -> {
            ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) dialogs.findViewById(R.id.ll_hide)).setVisibility(View.GONE);

        });
        ((ImageView) dialogs.findViewById(R.id.back_report)).setOnClickListener(v -> {
            ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) dialogs.findViewById(R.id.ll_report)).setVisibility(View.GONE);

        });

        //Hide Click
        ((TextView) dialogs.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNext("Irrelevant", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNext("Repetitive", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNext("Interrupted me", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNext("Unexpected", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNext("Too many ads", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNext("Offensive", "h"));

        //Report Click
        ((TextView) dialogs.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNext("Sexually inappropriate", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNext("Illegal", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNext("Offensive", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNext("Spam", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNext("Disagreeable", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNext("Other", "r"));

        dialogs.show();


    }

    private void AdsNext(String problem, String type) {
        dialogs.cancel();
        ((ImageView) findViewById(R.id.inter_image)).setVisibility(View.GONE);
        ((RelativeLayout) findViewById(R.id.lin_as_header)).setVisibility(View.GONE);
        ((RelativeLayout) findViewById(R.id.ll_main)).setVisibility(View.VISIBLE);
        if (type.equals("h")) {
            ((LinearLayout) findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.ll_report)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.hide_p_1)).setText(problem);
        } else {
            ((LinearLayout) findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.ll_hide)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.report_p_1)).setText(problem);
        }
        ((ImageView) findViewById(R.id.close_2)).setOnClickListener(v -> {
            startActivity(NextAnimation.qureka_intent);
            finish();
        });
>>>>>>> c6dd13fe6a3b00cec4ad4abe001a47344709469c
    }

>>>>>>> 26b16ed09941370bd8bfb049ab1e0c3414fe0e53
    private void Next() {
        if (NextAnimation.qureka_intent == null) {
            finish();
        } else {
            startActivity(NextAnimation.qureka_intent);
            finish();
        }
        if (getQurekaCloseBTNAutoOpenLink().equals("1")) {
            MyProHelperClass.g_openAds_show = false;
            MyProHelperClass.q_openAds_show = false;
            BtnAutolink();
        }
    }

    public void close(View view) {
        Next();
    }

    private void AdReport() {


        dialogs = new BottomSheetDialog(this);
        dialogs.setContentView(R.layout.q_dialog);
        dialogs.setCancelable(true);
        dialogs.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);

        //Main Btn Click
        ((LinearLayout) dialogs.findViewById(R.id.btn_hide)).setOnClickListener(v -> {
            ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) dialogs.findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);

        });
        ((LinearLayout) dialogs.findViewById(R.id.btn_report)).setOnClickListener(v -> {
            ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.GONE);
            ((LinearLayout) dialogs.findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
        });

        //Back
        ((ImageView) dialogs.findViewById(R.id.back_hide)).setOnClickListener(v -> {
            ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) dialogs.findViewById(R.id.ll_hide)).setVisibility(View.GONE);

        });
        ((ImageView) dialogs.findViewById(R.id.back_report)).setOnClickListener(v -> {
            ((LinearLayout) dialogs.findViewById(R.id.ll_one)).setVisibility(View.VISIBLE);
            ((LinearLayout) dialogs.findViewById(R.id.ll_report)).setVisibility(View.GONE);

        });

        //Hide Click
        ((TextView) dialogs.findViewById(R.id.hide_p_1)).setOnClickListener(v -> AdsNext("Irrelevant", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_2)).setOnClickListener(v -> AdsNext("Repetitive", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_3)).setOnClickListener(v -> AdsNext("Interrupted me", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_4)).setOnClickListener(v -> AdsNext("Unexpected", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_5)).setOnClickListener(v -> AdsNext("Too many ads", "h"));
        ((TextView) dialogs.findViewById(R.id.hide_p_6)).setOnClickListener(v -> AdsNext("Offensive", "h"));

        //Report Click
        ((TextView) dialogs.findViewById(R.id.report_p_1)).setOnClickListener(v -> AdsNext("Sexually inappropriate", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_2)).setOnClickListener(v -> AdsNext("Illegal", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_3)).setOnClickListener(v -> AdsNext("Offensive", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_4)).setOnClickListener(v -> AdsNext("Spam", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_5)).setOnClickListener(v -> AdsNext("Disagreeable", "r"));
        ((TextView) dialogs.findViewById(R.id.report_p_6)).setOnClickListener(v -> AdsNext("Other", "r"));

        dialogs.show();


    }

    private void AdsNext(String problem, String type) {
        dialogs.cancel();
        ((ImageView) findViewById(R.id.inter_image)).setVisibility(View.GONE);
        ((RelativeLayout) findViewById(R.id.lin_as_header)).setVisibility(View.GONE);
        ((RelativeLayout) findViewById(R.id.ll_main)).setVisibility(View.VISIBLE);
        if (type.equals("h")) {
            ((LinearLayout) findViewById(R.id.ll_hide)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.ll_report)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.hide_p_1)).setText(problem);
        } else {
            ((LinearLayout) findViewById(R.id.ll_report)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.ll_hide)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.report_p_1)).setText(problem);
        }
        ((ImageView) findViewById(R.id.close_2)).setOnClickListener(v -> {
            startActivity(NextAnimation.qureka_intent);
            finish();
        });
    }


    @Override
    public void onBackPressed() {

    }

    private void initView() {
        adTitle = (TextView) findViewById(R.id.ad_title);
        adDis = (TextView) findViewById(R.id.ad_dis);

        adTitle.setText(MyProHelperClass.inter_ads.get(getNumber).getTitle());
        adDis.setText(MyProHelperClass.inter_ads.get(getNumber).getDis());
        Glide.with(QurekaInterActivity.this).load(MyProHelperClass.round_ads.get(MyProHelperClass.getRandomNumber(0, MyProHelperClass.round_ads.size() - 1))).into((ImageView) findViewById(R.id.round));

        startCounter();

    }

    private void startCounter() {
        if (counter == 0) {
            findViewById(R.id.close).setVisibility(View.VISIBLE);
            findViewById(R.id.txt_skip).setVisibility(View.GONE);
            return;
        }
        new Handler().postDelayed(() -> {
            int A = counter - 1;
            txtSkip.setText("Skip " + A);
            counter = A;
            if (counter == 0) {
                findViewById(R.id.close).setVisibility(View.VISIBLE);
                findViewById(R.id.txt_skip).setVisibility(View.GONE);
            } else {
                startCounter();
            }
        }, 1000);
    }
}
