package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hitorigoto.Database.dbUser;
import com.example.hitorigoto.Models.User;
import com.example.hitorigoto.databinding.ActivitySigninBinding;

public class SignIn extends AppCompatActivity {

    ActivitySigninBinding binding;
    dbUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        customizeStatusBar();

        db = new dbUser(this);

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.tfEmail.getText().toString();
                String password = binding.tfPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    setErrorForEmptyFields(); // Function to set error for empty fields
                } else {
                    int loginResult = db.checkAccount(email, password);

                    if (loginResult == 1) {
                        // Login success
                        clearFields();
                        // Taking the user as inputted
                        // and save it in the SharedPreferences
                        User loggedInUser = db.getAccount(email, password);
                        saveLoginSession(loggedInUser.getFull_name(), loggedInUser.getEmail());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (loginResult == 2) {
                        // Invalid credentials
                        clearLayouts();
                        binding.layoutPassword.setError("Kata sandi salah");
                    } else {
                        // Account not found
                        clearLayouts();
                        Toast.makeText(SignIn.this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void customizeStatusBar() {
        // Check if the device is running on Android 5.0 (Lollipop) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color to md_theme_light_surface
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#F8FDFF"));

            // Set the status bar text and icon color to black
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void setErrorForEmptyFields() {
        if (binding.tfEmail.getText().toString().equals("")) {
            binding.layoutEmail.setError("Masukkan email");
        }
        if (binding.tfPassword.getText().toString().equals("")) {
            binding.layoutPassword.setError("Masukkan kata sandi");
        }
    }

    private void clearFields() {
        binding.tfEmail.setText("");
        binding.tfPassword.setText("");
    }

    private void clearLayouts() {
        binding.layoutEmail.setError("");
        binding.layoutPassword.setError("");
    }

    public void backToGettingStarted(View view) {;
        finish();
    }

    public void goToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    private void saveLoginSession(String fullname, String email){
        SharedPreferences sharedPreferences = getSharedPreferences("LoginSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // This right here will handle all the cache
        editor.putBoolean("IsLoggedIn", true);
        editor.putString("UserName", fullname);
        editor.putString("UserEmail", email);
        editor.apply();
    }
}
