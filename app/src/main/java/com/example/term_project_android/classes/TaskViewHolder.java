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
    TextView textView;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView);
        textView = itemView.findViewById(R.id.textView);
    }

}
