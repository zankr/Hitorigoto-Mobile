package com.example.hitorigoto.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        customizeStatusBar();

        db = new dbUser(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = binding.tfFullName.getText().toString();
                String email = binding.tfEmail.getText().toString();
                String password = binding.tfPassword.getText().toString();

                if (fullName.equals("") || email.equals("") || password.equals("")) {
                    setErrorForEmptyFields(); // Function to set error for empty fields
                } else {
                    User user = new User();
                    user.setFull_name(fullName);
                    user.setEmail(email);
                    user.setPassword(password);

                    int checkUser = db.checkAccount(email, password);

                    if (checkUser == 0) {
                        long insert = db.addUserDetail(user);

                        if (insert != -1) {
                            // Signup success
                            clearFields();
                            Intent intent = new Intent(getApplicationContext(), SignIn.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Signup failed
                            Toast.makeText(SignUp.this, "Daftar akun gagal dilakukan", Toast.LENGTH_SHORT).show();
                        }
                    } else if (checkUser == 2) {
                        // Email already in use
                        clearLayouts();
                        binding.layoutEmail.setError("Email sudah terdaftarkan");
                    } else {
                        // User already exists
                        clearLayouts();
                        Toast.makeText(SignUp.this, "Akun sudah terdaftarkan", Toast.LENGTH_SHORT).show();
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
        if (binding.tfFullName.getText().toString().equals("")) {
            binding.layoutFullName.setError("Masukkan nama lengkap");
        }
        if (binding.tfEmail.getText().toString().equals("")) {
            binding.layoutEmail.setError("Masukkan email");
        }
        if (binding.tfPassword.getText().toString().equals("")) {
            binding.layoutPassword.setError("Masukkan kata sandi");
        }
    }

    private void clearFields() {
        binding.tfFullName.setText("");
        binding.tfEmail.setText("");
        binding.tfPassword.setText("");
    }

    private void clearLayouts() {
        binding.layoutFullName.setError("");
        binding.layoutEmail.setError("");
        binding.layoutPassword.setError("");
    }

    public void backToGettingStarted(View view) {
        finish();
    }

    public void goToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void goToSignIn(View view) {
        startActivity(new Intent(this, SignIn.class));
        finish();
    }
}