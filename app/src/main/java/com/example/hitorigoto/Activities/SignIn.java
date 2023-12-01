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
                    setErrorForEmptyFields(); // Function to set error for empty fields
                } else {
                    int loginResult = db.checkAccount(email, password);

                    if (loginResult == 1) {
                        // Login success
                        clearFields();
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
}
