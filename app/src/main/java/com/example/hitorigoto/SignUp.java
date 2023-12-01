package com.example.hitorigoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void backGetStarted(View view) {
        startActivity(new Intent(this, GettingStarted.class));
        finish();
    }

    public void goMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void goSignIn(View view) {
        startActivity(new Intent(this, SignIn.class));
        finish();
    }
}