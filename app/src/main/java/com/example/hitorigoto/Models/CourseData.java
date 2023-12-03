package com.example.hitorigoto.Models;

import com.example.hitorigoto.R;

import java.util.ArrayList;

public class CourseData {
    private static String[] Title = {
            "JLPT N5",
            "JLPT N4",
            "JLPT N3",
            "JLPT N2",
            "JLPT N1"
    };

    private static String[] Desc = {
            "Level N5 merupakan pintu gerbang awal dalam memahami bahasa Jepang. Pada tingkatan ini, Anda akan diperkenalkan dengan hiragana, kata-kata dasar, dan pola kalimat sederhana. Cocok untuk mereka yang baru memulai perjalanan belajar bahasa Jepang.",
            "N4 adalah langkah selanjutnya setelah N5, menuntut pemahaman yang lebih dalam. Di sini, Anda akan mulai mengasah kemampuan tata bahasa dan memperluas kosakata Anda. Cocok untuk mereka yang ingin menguatkan dasar mereka dan memasuki tingkat menengah.",
            "Tingkatan N3 menandai peralihan ke level menengah. Anda akan mulai memahami struktur kalimat yang lebih kompleks, membaca teks yang lebih panjang, dan memperdalam pemahaman Anda tentang bahasa Jepang sehari-hari.",
            "N2 menantang kemampuan Anda lebih jauh dengan penggunaan bahasa formal dan situasional. Anda akan mendalami keterampilan membaca dan mendengar serta mulai memahami nuansa bahasa Jepang yang lebih halus.",
            "N1 merupakan level tertinggi yang menuntut penguasaan bahasa Jepang secara luas. Di sini, Anda akan menguasai struktur bahasa yang rumit, memahami teks ilmiah, dan berkomunikasi dengan tingkat keahlian yang tinggi. Cocok untuk mereka yang ingin menjadi ahli bahasa Jepang."
    };

    private static int[] Image = {
            R.drawable.img_n5,
            R.drawable.img_n4,
            R.drawable.img_n3,
            R.drawable.img_n2,
            R.drawable.img_n1
    };

    public static ArrayList<Course> getListData() {
        ArrayList<Course> listCourse = new ArrayList<>();
        for (int i = 0; i < Title.length; i++){
            Course course = new Course(
                    Title[i],
                    Desc[i],
                    Image[i]
            );
            listCourse.add(course);
        }
        return listCourse;
    }

}
