package com.example.hitorigoto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000; // 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity after the splash time out
                Intent mainIntent = new Intent(MainActivity.this, GettingStarted.class);
                startActivity(mainIntent);
                finish(); // close the splash screen activity
            }
        }, SPLASH_TIME_OUT);
    }
}

