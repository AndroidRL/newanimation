package com.topclassanimation.mylibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class QurekaInterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qureka_inter);
        int A = MyProHelperClass.inter_ads.size() - 1;
        Glide.with(QurekaInterActivity.this).load(MyProHelperClass.inter_ads.get(MyProHelperClass.getRandomNumber(0, A))).into((ImageView) findViewById(R.id.inter_image));
        ((ImageView) findViewById(R.id.inter_image)).setOnClickListener(v -> MyProHelperClass.BtnAutolink());
    }

    public void close(View view) {
        if (NextAnimation.qureka_intent == null) {
            finish();
        } else {
            if (NextAnimation.qureka_finish == 1) {
                startActivity(NextAnimation.qureka_intent);
                finish();
            } else {
                startActivity(NextAnimation.qureka_intent);
            }
        }
    }

    @Override
    public void onBackPressed() {

    }
}
