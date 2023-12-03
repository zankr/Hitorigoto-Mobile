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

import com.example.hitorigoto.Activities.GettingStarted;
import com.example.hitorigoto.Activities.SignIn;
import com.example.hitorigoto.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement intent function to move
            }
        });

        // Button Log out
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        // Call the customizeStatusBar method when the fragment view is created
        customizeStatusBar();

        return view;
    }

    private void logoutUser() {
        // Clear the login session, for example using SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // This clears all the stored data in SharedPreferences
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

}