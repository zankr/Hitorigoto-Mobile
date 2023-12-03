package com.example.hitorigoto.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hitorigoto.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textViewTotalScore = findViewById(R.id.text_view_total_score_result);

        // Retrieve the total score from the intent
        int totalScore = getIntent().getIntExtra(QuizActivity.EXTRA_SCORE, 0);

        // Display the total score
        textViewTotalScore.setText("Total Score: " + totalScore*20 +"%");
    }
}
