package com.example.hitorigoto.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitorigoto.Activities.CourseLists;
import com.example.hitorigoto.Adapters.LessonAdapter;
import com.example.hitorigoto.Models.Lesson;
import com.example.hitorigoto.Models.LessonData;
import com.example.hitorigoto.R;

import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvHeroHome = view.findViewById(R.id.tv_hero_home);
        String userName = getUserName();
        if (userName != null) {
            tvHeroHome.setText(getString(R.string.title_hero_home, userName));
        }

        customizeStatusBar();

        // Setting up RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_lesson);
        setupLessonRecyclerView(recyclerView);

        Button btnCourseLists= view.findViewById(R.id.btn_course_lists);
        Button btnLevelTest = view.findViewById(R.id.btn_level_test);

        // Button CourseLists
        btnCourseLists.setOnClickListener(v -> {
            // Navigate to CourseLists activity
            startActivity(new Intent(getActivity(), CourseLists.class));
        });

        // Button Level Test
        btnLevelTest.setOnClickListener(v -> {
            // Show coming soon dialog
            showNotCompleteDialog();
        });

        return view;
    }

    private String getUserName() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserName", "Pengguna");
    }

    private void customizeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#C4F18C"));
            View decor = getActivity().getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void setupLessonRecyclerView(RecyclerView recyclerView) {
        List<Lesson> lessonList = LessonData.getListData();
        LessonAdapter lessonAdapter = new LessonAdapter(lessonList, getActivity());

        // Set up GridLayoutManager to display the items in a grid
        int spanCount = 2; // Number of columns in the grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(lessonAdapter);
    }

    public void showNotCompleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Level Test Belum Selesai")
                .setMessage("Maaf, Anda belum menyelesaikan Course. Silahkan selesaikan Course terlebih dahulu untuk mengikuti Level Test.")
                .setIcon(R.drawable.ic_test)
                .setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}