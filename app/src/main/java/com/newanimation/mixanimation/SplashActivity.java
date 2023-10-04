package com.newanimation.mixanimation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.newanimation.mylibrary.Splash;

public class SplashActivity extends AppCompatActivity {

<<<<<<< HEAD
    public static String Token = "a223ff7e2cf20dacb211f250b0ad949bc6e30f05";
=======
<<<<<<< HEAD
    public static String Token = "a223ff7e2cf20dacb211f250b0ad949bc6e30f05";
=======
<<<<<<< HEAD
    public static String Token = "a223ff7e2cf20dacb211f250b0ad949bc6e30f05";
=======
    public static String Token = "251e936997779db164b456fc0d09721f0a8b0cf8";
>>>>>>> c6dd13fe6a3b00cec4ad4abe001a47344709469c
>>>>>>> 26b16ed09941370bd8bfb049ab1e0c3414fe0e53
>>>>>>> 13559315ab5c88fea668186f17607a978d75438d

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Splash.StartAnimation(this, new Intent(this, TestingActivity.class),
                "Test", "1", 0, "96e1d1b735a651d0ee7172b52d69ae2f/raw/" + Token + "/TestV13");
    }
}