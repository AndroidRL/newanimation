package com.newanimation.mixanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.newanimation.mylibrary.BigAnimation;
import com.newanimation.mylibrary.NextAnimation;
import com.newanimation.mylibrary.SmallAnimation;


public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation));
        SmallAnimation.BottomAnimation(this, findViewById(R.id.bottom_animation));//Banner Code
    }

    @Override
    public void onBackPressed() {
         NextAnimation.BackAnimation(TestingActivity.this,null);
    }

    public void ADS(View view) {
        NextAnimation.NextSliderAnimation(this, new Intent(this, TestingActivity.class),0);
    }


}