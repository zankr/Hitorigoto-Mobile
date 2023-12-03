package com.example.hitorigoto.Models;

import java.util.ArrayList;

public class ChapterData {
    private static String[] Title = {
            "Chapter 1",
            "Chapter 2",
            "Chapter 3"
    };

    private static String[] Desc = {
            "あ，い，う，え，お",
            "か，き，く，け，こ",
            "さ，し，す，せ，そ"
    };

    public static ArrayList<Chapter> getListData() {
        ArrayList<Chapter> listChapter = new ArrayList<>();
        for (int i = 0; i < Title.length; i++){
            Chapter chapter = new Chapter(
                    Title[i],
                    Desc[i]
            );
            listChapter.add(chapter);
        }
        return listChapter;
    }
}
