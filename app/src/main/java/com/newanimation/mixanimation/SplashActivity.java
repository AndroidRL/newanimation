package com.newanimation.mixanimation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.newanimation.mylibrary.Splash;

public class SplashActivity extends AppCompatActivity {

    public static String Token = "e9b06db6a7c3dc0d4704550b393740c9ea3785fe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Splash.StartAnimation(this, new Intent(this, MainActivity.class),
                "Test", "1", 0, "96e1d1b735a651d0ee7172b52d69ae2f/raw/" + Token + "/TestV13");
    }
}