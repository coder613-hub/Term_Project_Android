package com.example.term_project_android.classes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.term_project_android.R;

class TaskViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    TextView textViewName;
    TextView textViewDueDate;
    TextView textViewPriority;
    TextView textViewDescription;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewDueDate = itemView.findViewById(R.id.textViewDueDate);
        textViewPriority = itemView.findViewById(R.id.textViewPriority);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
    }

}
