package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hitorigoto.Database.dbHelper;
import com.example.hitorigoto.R;
import com.example.hitorigoto.databinding.ActivitySigninBinding;

public class SignIn extends AppCompatActivity {

    ActivitySigninBinding binding;
    dbHelper DbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DbHelper = new dbHelper(this);

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.tfEmail.getText().toString();
                String password = binding.tfPassword.getText().toString();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(SignIn.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkCredentials = DbHelper.checkUsernamePassword(username, password);

                    if (checkCredentials == true){
                        Toast.makeText(SignIn.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignIn.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
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