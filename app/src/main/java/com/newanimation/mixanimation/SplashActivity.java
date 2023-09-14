package com.newanimation.mixanimation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.newanimation.mylibrary.Splash;

public class SplashActivity extends AppCompatActivity {

<<<<<<< HEAD
    public static String Token = "73a06ccf11f1736e29076e4387de472c494e9d06";
=======
    public static String Token = "e9b06db6a7c3dc0d4704550b393740c9ea3785fe";
>>>>>>> c1a761bbed8c7b3977b0747bb9f23d6e414d6f38

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Splash.StartAnimation(this, new Intent(this, MainActivity.class),
                "Test", "1", 0, "96e1d1b735a651d0ee7172b52d69ae2f/raw/" + Token + "/TestV13");
    }
}