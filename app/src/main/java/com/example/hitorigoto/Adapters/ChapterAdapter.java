package com.example.hitorigoto.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitorigoto.Activities.StartingQuiz;
import com.example.hitorigoto.Models.Chapter;
import com.example.hitorigoto.R;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private final ArrayList<Chapter> chapterList;
    private final Context context;

    public ChapterAdapter(Context context, ArrayList<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);

        holder.chapterTitle.setText(chapter.getTitle());
        holder.chapterDesc.setText(chapter.getDescription());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, StartingQuiz.class);
            context.startActivity(intent);

            // Finish the activity
            ((Activity) context).finish();
        });
    }



    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        public TextView chapterTitle;
        public TextView chapterDesc;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterTitle = itemView.findViewById(R.id.chapter_title);
            chapterDesc = itemView.findViewById(R.id.chapter_desc);
        }
    }
}
