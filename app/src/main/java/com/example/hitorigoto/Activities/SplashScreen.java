package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hitorigoto.R;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000; // 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        customizeStatusBar();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, GettingStarted.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void customizeStatusBar() {
        // Check if the device is running on Android 5.0 (Lollipop) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color to seed
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#645CBB"));
        }
    }
}