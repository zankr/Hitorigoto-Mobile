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

    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUp.class));
        onStop();
    }

    public void goToSignIn(View view) {
        startActivity(new Intent(this, SignIn.class));
        onStop();
    }

}