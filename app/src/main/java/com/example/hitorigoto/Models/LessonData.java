package com.example.hitorigoto.Models;

import com.example.hitorigoto.R;

import java.util.ArrayList;

public class LessonData {
    private static String[] Title = {
            "Hiragana",
            "Katakana",
            "Kanji",
            "Moji Goi",
            "Bunpou",
            "Dokkai",
            "Choukai"
    };

    private static String[] Desc = {
            "Lesson 1",
            "Lesson 2",
            "Lesson 3",
            "Lesson 4",
            "Lesson 5",
            "Lesson 6",
            "Lesson 7"
    };

    private static int[] Image = {
            R.drawable.img_n5_hiragana,
            R.drawable.img_n5_katakana,
            R.drawable.img_n5_kanji,
            R.drawable.img_n5_mojigoi,
            R.drawable.img_n5_bunpou,
            R.drawable.img_n5_dokkai,
            R.drawable.img_n5_choukai,
    };

    public static ArrayList<Lesson> getListData() {
        ArrayList<Lesson> listLesson = new ArrayList<>();
        for (int i = 0; i < Title.length; i++){
            Lesson lesson = new Lesson(
                    Title[i],
                    Desc[i],
                    Image[i]
            );
            listLesson.add(lesson);
        }
        return listLesson;
    }

}
