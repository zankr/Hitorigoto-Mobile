package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hitorigoto.Database.dbUser;
import com.example.hitorigoto.Models.User;
import com.example.hitorigoto.R;
import com.example.hitorigoto.databinding.ActivitySignupBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignupBinding binding;
    dbUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new dbUser(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = binding.tfFullName.getText().toString();
                String email = binding.tfEmail.getText().toString();
                String password = binding.tfPassword.getText().toString();

                if (fullName.equals("") || email.equals("") || password.equals("")) {
                    Toast.makeText(SignUp.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setFull_name(fullName);
                    user.setEmail(email);
                    user.setPassword(password);

                    int signupResult = db.checkAccount(email, password);

                    if (signupResult == 0) {
                        long insert = db.addUserDetail(user);

                        if (insert != -1) {
                            Toast.makeText(SignUp.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else if (signupResult == 3) {
                        Toast.makeText(SignUp.this, "Email already in use", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
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

    public void goSignIn(View view) {
        startActivity(new Intent(this, SignIn.class));
        finish();
    }
}