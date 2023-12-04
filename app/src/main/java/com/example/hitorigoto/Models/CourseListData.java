package com.example.hitorigoto.Models;

import com.example.hitorigoto.R;

import java.util.ArrayList;

public class CourseListData {
    private static String[] Title = {
            "JLPT N5",
            "JLPT N4",
            "JLPT N3",
            "JLPT N2",
            "JLPT N1"
    };

    private static int[] Image = {
            R.drawable.img_courselist_n5,
            R.drawable.img_courselist_n4,
            R.drawable.img_courselist_n3,
            R.drawable.img_courselist_n2,
            R.drawable.img_courselist_n1
    };

    public static ArrayList<CourseList> getListData() {
        ArrayList<CourseList> listCourseList = new ArrayList<>();
        for (int i = 0; i < Title.length; i++){
            CourseList courseList = new CourseList(
                    Title[i],
                    Image[i]
            );
            listCourseList.add(courseList);
        }
        return listCourseList;
    }
}
