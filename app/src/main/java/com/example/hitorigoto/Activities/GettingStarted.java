package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hitorigoto.R;

public class GettingStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
    }

    public void goGetStarted(View view) {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    public void goHaveAccount(View view) {
        startActivity(new Intent(this, SignIn.class));
        finish();
    }

}