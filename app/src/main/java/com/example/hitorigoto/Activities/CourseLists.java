package com.example.hitorigoto.Activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitorigoto.Adapters.CourseListAdapter;
import com.example.hitorigoto.Models.CourseList;
import com.example.hitorigoto.Models.CourseListData;
import com.example.hitorigoto.R;

import java.util.ArrayList;

public class CourseLists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_lists);

        customizeStatusBar();

        // Find the RecyclerView in your layout
        RecyclerView recyclerView = findViewById(R.id.rv_courselist);

        // Create a layout manager for the RecyclerView (e.g., LinearLayoutManager)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter on the RecyclerView
        CourseListAdapter courseListAdapter = new CourseListAdapter(this, getCourseList());
        recyclerView.setAdapter(courseListAdapter);
    }

    // Sample method to get a list of courses
    private ArrayList<CourseList> getCourseList() {
        return CourseListData.getListData();
    }

    public void backToMain(View view) {
        finish();
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
