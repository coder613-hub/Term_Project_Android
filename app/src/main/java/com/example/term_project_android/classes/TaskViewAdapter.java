package com.example.term_project_android.classes;

import android.animation.Animator;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.term_project_android.R;

import java.util.ArrayList;


public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private ArrayList<TaskItem> taskList;


    public TaskViewAdapter(ArrayList<TaskItem> taskList) {
        this.taskList = taskList;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Make one "tile"; this will be called once per number of visible tiles on screen
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

        return new TaskViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.textViewName.setText(taskList.get(position).getTaskName());
        holder.textViewDueDate.setText(taskList.get(position).getDueDate());
        holder.textViewDescription.setText(taskList.get(position).getDescription());
        holder.textViewPriority.setText(taskList.get(position).getPriority());
    }


    public void onViewAttachedToWindow(@NonNull TaskViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCard(holder.cardView);
    }

    private void animateCard(CardView view) {
        if (Build.VERSION.SDK_INT >= 21) {
            int centerX = 0, centerY = 0, startRadius = 0;
            int endRadius = Math.max(view.getWidth(), view.getHeight());
            Animator circularRevealAnimationOfCard = ViewAnimationUtils.createCircularReveal
                    (view, centerX, centerY, startRadius, endRadius);
            circularRevealAnimationOfCard.start();
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
