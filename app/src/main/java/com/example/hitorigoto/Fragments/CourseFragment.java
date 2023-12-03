package com.example.hitorigoto.Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitorigoto.Adapters.CourseAdapter;
import com.example.hitorigoto.Models.CourseData;
import com.example.hitorigoto.R;

public class CourseFragment extends Fragment {

    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        // Call the customizeStatusBar method when the fragment view is created
        customizeStatusBar();

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_course);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize Adapter
        CourseAdapter courseAdapter = new CourseAdapter(CourseData.getListData(), getActivity());
        recyclerView.setAdapter(courseAdapter);

        return view;
    }

    private void customizeStatusBar() {
        // Check if the device is running on Android 5.0 (Lollipop) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color to match the color of the top component
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#E4DFFF"));

            // Set the status bar text and icon color to black
            View decor = getActivity().getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
