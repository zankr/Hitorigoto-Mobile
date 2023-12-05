package com.example.hitorigoto.Activities;

import static com.example.hitorigoto.Activities.StartingQuiz.KEY_HIGHSCORE;
import static com.example.hitorigoto.Activities.StartingQuiz.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
        textViewTotalScore.setText(String.valueOf(totalScore * 20));

        // textViewTotalScore.setText(totalScore * 20);
        updateHighscore(totalScore);
    }

    public void backToChapterLists(View view) {
        finish();
    }

    private void updateHighscore(int currentScore) {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int savedHighscore = prefs.getInt(KEY_HIGHSCORE, 0);

        if (currentScore > savedHighscore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(KEY_HIGHSCORE, currentScore);
            editor.apply();
        }
    }

}



