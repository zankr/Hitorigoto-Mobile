package com.example.hitorigoto.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hitorigoto.Models.Question;
import com.example.hitorigoto.Models.QuizContract.*;
import java.util.ArrayList;
import java.util.Arrays;


public class dbQuiz extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public dbQuiz(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Cara baca \"あ\"", "A", "I", "U", 1);
        addQuestion(q1);
        Question q2 = new Question("Cara baca \"い\"", "E", "I", "A", 2);
        addQuestion(q2);
        Question q3 = new Question("Cara baca \"う\"", "O", "A", "U", 3);
        addQuestion(q3);
        Question q4 = new Question("Cara baca \"え\"", "E", "O", "U", 1);
        addQuestion(q4);
        Question q5 = new Question("Cara baca \"お\"", "I", "O", "A", 2);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                // Log the column names to identify any issues
                String[] columnNames = c.getColumnNames();
                Log.d("ColumnNames", Arrays.toString(columnNames));

                // Use proper error handling in case the column is not found
                int questionIndex = c.getColumnIndex(QuestionsTable.COLUMN_QUESTION);
                int option1Index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION1);
                int option2Index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION2);
                int option3Index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION3);
                int answerNrIndex = c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR);

                if (questionIndex != -1) {
                    question.setQuestion(c.getString(questionIndex));
                }
                if (option1Index != -1) {
                    question.setOption1(c.getString(option1Index));
                }
                if (option2Index != -1) {
                    question.setOption2(c.getString(option2Index));
                }
                if (option3Index != -1) {
                    question.setOption3(c.getString(option3Index));
                }
                if (answerNrIndex != -1) {
                    question.setAnswerNr(c.getInt(answerNrIndex));
                }

                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}