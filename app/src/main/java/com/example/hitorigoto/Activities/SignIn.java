package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hitorigoto.Database.dbUser;
import com.example.hitorigoto.R;
import com.example.hitorigoto.databinding.ActivitySigninBinding;

public class SignIn extends AppCompatActivity {

    ActivitySigninBinding binding;
    dbUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new dbUser(this);

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.tfEmail.getText().toString();
                String password = binding.tfPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(SignIn.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    int signinResult = db.checkAccount(email, password);

                    if (signinResult == 1) {
                        Toast.makeText(SignIn.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (signinResult == 2) {
                        Toast.makeText(SignIn.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn.this, "Account not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void backGetStarted(View view) {
        startActivity(new Intent(this, GettingStarted.class));
        finish();
    }

    public void goMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void goSignUp(View view) {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }
}
