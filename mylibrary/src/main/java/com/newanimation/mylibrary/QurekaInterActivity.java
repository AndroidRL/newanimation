package com.newanimation.mylibrary;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class QurekaInterActivity extends AppCompatActivity {

    private int counter = Integer.valueOf(MyProHelperClass.getQurekaInterSkipTime());
    private TextView adTitle;
    private TextView adDis;
    private TextView txtSkip;
    private int getNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qureka_inter);
        txtSkip = (TextView) findViewById(R.id.txt_skip);
        txtSkip.setText("Skip " + MyProHelperClass.getQurekaInterSkipTime());
        getNumber = MyProHelperClass.getRandomNumber(0, MyProHelperClass.inter_ads.size() - 1);
        Glide.with(QurekaInterActivity.this).load(MyProHelperClass.inter_ads.get(getNumber).getImage()).into((ImageView) findViewById(R.id.inter_image));
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
        initView();


    }

    public void close(View view) {
        if (NextAnimation.qureka_intent == null) {
            finish();
        } else {
            startActivity(NextAnimation.qureka_intent);
            finish();
        }
        if (MyProHelperClass.getQurekaCloseBTNAutoOpenLink().equals("1")) {
            MyProHelperClass.BtnAutolink();
        }
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
