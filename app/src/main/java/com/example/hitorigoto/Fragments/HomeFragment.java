package com.example.hitorigoto.Fragments;

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

import com.example.hitorigoto.Activities.StartingQuiz;
import com.example.hitorigoto.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Still inflating the components
        TextView tvHeroHome = view.findViewById(R.id.tv_hero_home);
        String userName = getUserName();
        if (userName != null) {
            tvHeroHome.setText(getString(R.string.title_hero_home, userName));
        }

        // Call the customizeStatusBar method when the fragment view is created
        customizeStatusBar();

        Button btnStartQuiz = view.findViewById(R.id.btn_level_test);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the StartingQuiz activity
                Intent intent = new Intent(getActivity(), StartingQuiz.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private String getUserName() {
        // This will fetch the class that is shared in sharedPreferences, that is current session user
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserName", "Pengguna");
    }

    private void customizeStatusBar() {
        // Check if the device is running on Android 5.0 (Lollipop) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color to match the color of the top component
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#C4F18C"));

            // Set the status bar text and icon color to black
            View decor = getActivity().getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


}