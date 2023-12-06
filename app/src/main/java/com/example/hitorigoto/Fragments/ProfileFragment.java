package com.example.hitorigoto.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.hitorigoto.Activities.EditProfile;
import com.example.hitorigoto.Activities.GettingStarted;
import com.example.hitorigoto.R;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Still inflating the components
        TextView tvProfileFullName = view.findViewById(R.id.profile_fullname);
        TextView tvProfileEmail = view.findViewById(R.id.profile_email);

        // Fetching the name and email
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("UserName", "Nama Pengguna");
        String email = sharedPreferences.getString("UserEmail", "email@contoh.com");

        // Displaying them
        tvProfileFullName.setText(fullName);
        tvProfileEmail.setText(email);

        // Buttons for 2 things
        Button btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        Button btnLogout = view.findViewById(R.id.btn_logout);

        // Button edit profile
        btnEditProfile.setOnClickListener(v -> {
            // Navigate to EditProfile activity and pass user data
            goToEditProfile(fullName, email);
        });

        // Button Log out
        btnLogout.setOnClickListener(v -> {
            // Show logout confirmation dialog
            showLogoutConfirmationDialog();
        });

        // Call the customizeStatusBar method when the fragment view is created
        customizeStatusBar();

        return view;
    }

    private void goToEditProfile(String fullName, String email) {
        // Create an intent to start EditProfile activity
        Intent intent = new Intent(getActivity(), EditProfile.class);

        // Pass user information as extras to the intent
        intent.putExtra("fullName", fullName);
        intent.putExtra("email", email);

        // Start the EditProfile activity
        startActivity(intent);
    }

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                // Message
                .setTitle("Konfirmasi Logout")
                // Confirmation?
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Keluar", (dialog, which) -> {
                    // User clicked "Keluar" button, handle logout
                    logoutUser();
                })
                .setNegativeButton("Batal", null)
                .setIcon(R.drawable.ic_logout)
                .show();
    }

    private void logoutUser() {
        // Clear the login session, for example using SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // This clears all the stored data in SharedPreferences
        editor.putBoolean("IsLoggedIn", false);
        editor.clear();
        editor.apply();

        // Navigate back to the Main Activity screen
        Intent intent = new Intent(getActivity(), GettingStarted.class);
        // Backstack cleaner
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // Close the current activity
        getActivity().finish();
    }

    private void customizeStatusBar() {
        // Check if the device is running on Android 6.0 (Marshmallow) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar color to your desired color
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#5A52B0"));

            // Set the status bar text and icon color to white
            View decor = getActivity().getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // Fetching the name and email again
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("UserName", "Nama Pengguna");
        String email = sharedPreferences.getString("UserEmail", "email@contoh.com");

        // Update the TextViews
        TextView tvProfileFullName = getView().findViewById(R.id.profile_fullname);
        TextView tvProfileEmail = getView().findViewById(R.id.profile_email);
        tvProfileFullName.setText(fullName);
        tvProfileEmail.setText(email);
    }
}
