package com.topclassanimation.classyanime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.topclassanimation.mylibrary.BigAnimation;
import com.topclassanimation.mylibrary.NextAnimation;
import com.topclassanimation.mylibrary.SmallAnimation;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation), "medium");//Native Code
        SmallAnimation.BottomAnimation(this, findViewById(R.id.bottom_animation));//Banner Code
    }

    public void ADS(View view) {
        NextAnimation.NextSliderAnimation(this, new Intent(this, TestingActivity.class), 0);//Next Interstitial
    }

    @Override
    public void onBackPressed() {
        NextAnimation.BackAnimation(this, null);//Back Interstitial
    }
}
