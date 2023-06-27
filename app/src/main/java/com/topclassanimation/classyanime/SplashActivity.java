package com.topclassanimation.classyanime;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.topclassanimation.mylibrary.Splash;

public class SplashActivity extends AppCompatActivity {

    public static String Token = "3143af01ae4c976d9b3c5944a8f4484ce81e899d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Splash.StartAnimation(this, new Intent(this, MainActivity.class),
                "Test", "1", 0, "96e1d1b735a651d0ee7172b52d69ae2f/raw/" + Token + "/TestV13");
    }
}