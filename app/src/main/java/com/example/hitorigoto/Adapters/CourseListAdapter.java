package com.example.hitorigoto.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitorigoto.Activities.MainActivity;
import com.example.hitorigoto.Models.CourseList;
import com.example.hitorigoto.R;

import java.util.ArrayList;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListViewHolder> {

    private final ArrayList<CourseList> courseList;
    private final Context context;

    public CourseListAdapter(Context context, ArrayList<CourseList> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_courselist, parent, false);
        return new CourseListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListViewHolder holder, int position) {
        CourseList course = courseList.get(position);

        holder.courseTitle.setText(course.getTitle());
        holder.courseImage.setImageResource(course.getImage());

        holder.itemView.setOnClickListener(view -> {
            // Start the StartingQuiz activity or perform any action you want on item click
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

            // Finish the activity
            ((Activity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseListViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTitle;
        public ImageView courseImage;

        public CourseListViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courselist_title);
            courseImage = itemView.findViewById(R.id.courselist_image);
        }
    }
}
