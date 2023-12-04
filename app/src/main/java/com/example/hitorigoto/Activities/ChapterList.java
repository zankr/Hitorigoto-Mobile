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
import com.example.hitorigoto.Adapters.ChapterAdapter;
import com.example.hitorigoto.Models.ChapterData;
import com.example.hitorigoto.Models.Chapter;
import com.example.hitorigoto.R;

import java.util.ArrayList;

public class ChapterList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        customizeStatusBar();

        // Find the RecyclerView in your layout
        RecyclerView recyclerView = findViewById(R.id.rv_chapter);

        // Create a layout manager for the RecyclerView (e.g., LinearLayoutManager)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the ChapterAdapter with the list of chapters
        ChapterAdapter chapterAdapter = new ChapterAdapter(this, getChapterList());

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(chapterAdapter);
    }

    // Sample method to get a list of chapters
    private ArrayList<Chapter> getChapterList() {
        return ChapterData.getListData();
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
