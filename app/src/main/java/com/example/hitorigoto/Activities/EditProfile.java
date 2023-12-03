package com.example.hitorigoto.Activities;

import static android.app.PendingIntent.getActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitorigoto.Databases.dbUser;
import com.example.hitorigoto.databinding.ActivityEditProfileBinding;

public class EditProfile extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    private dbUser userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        customizeStatusBar();

        // Get data from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Storing the fetched data
            String fullName = extras.getString("fullName", "");
            String email = extras.getString("email", "");

            // Set the values using data binding
            binding.tfFullName.setText(fullName);
            binding.tfEmail.setText(email);
        }

        userDatabase = new dbUser(this);
    }

    public void backToMain(View view) {
        finish();
    }

    public void goToMain(View view) {
        String currentEmail = getIntent().getStringExtra("email");
        String fullName = binding.tfFullName.getText().toString();
        String newEmail = binding.tfEmail.getText().toString();
        String newPassword = binding.tfPasswordNew.getText().toString();
        String confirmPassword = binding.tfPasswordConfirm.getText().toString();

        // Validate input
        if (validateInput(fullName, newEmail, newPassword, confirmPassword)) {
            // Logic to edit profile
            int result = userDatabase.updateUserDetail(currentEmail, newEmail, fullName, newPassword);

            if (result > 0) {
                showToast("Perubahan berhasil disimpan!");
                finish();
            } else if (result == -1) {
                // Email already in use
                binding.layoutEmail.setError("Email sudah terdaftarkan");
            } else {
                showToast("Gagal memperbarui perubahan");
            }
        }
    }


    private boolean validateInput(String fullName, String email, String newPassword, String confirmPassword) {
        clearErrors();

        if (fullName.isEmpty() || email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            setErrorForEmptyFields();
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            binding.layoutPasswordNew.setError("Kata sandi tidak sama");
            binding.layoutPasswordConfirm.setError("Kata sandi tidak sama");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setErrorForEmptyFields() {
        if (binding.tfFullName.getText().toString().isEmpty()) {
            binding.layoutFullName.setError("Masukkan nama lengkap");
        }
        if (binding.tfEmail.getText().toString().isEmpty()) {
            binding.layoutEmail.setError("Masukkan email");
        }
        if (binding.tfPasswordNew.getText().toString().isEmpty()) {
            binding.layoutPasswordNew.setError("Masukkan kata sandi baru");
        }
        if (binding.tfPasswordConfirm.getText().toString().isEmpty()) {
            binding.layoutPasswordConfirm.setError("Konfirmasi kata sandi baru");
        }
    }

    private void clearErrors() {
        binding.layoutFullName.setError("");
        binding.layoutEmail.setError("");
        binding.layoutPasswordNew.setError("");
        binding.layoutPasswordConfirm.setError("");
    }

    private void customizeStatusBar() {
        // Check if the device is running on Android 5.0 (Lollipop) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color to top component color
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#F8FDFF"));

            // Set the status bar text and icon color to black
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}