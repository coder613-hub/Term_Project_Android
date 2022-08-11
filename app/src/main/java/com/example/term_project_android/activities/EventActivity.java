package com.example.term_project_android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.term_project_android.R;
import com.example.term_project_android.classes.TaskItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    private ArrayList<TaskItem> taskList;
    private final String mKEY_LIST = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setUpToolbar();
        getIncomingData();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getIncomingData() {
        Intent intent = getIntent();
        String listJSON = intent.getStringExtra(mKEY_LIST);
        taskList = getListFromGSONString(listJSON);
    }

    public static ArrayList<TaskItem> getListFromGSONString(String strList) {
        Gson gson = new Gson();
        Type TaskItemType = new TypeToken<ArrayList<TaskItem>>() {
        }.getType();
        return gson.fromJson(strList, TaskItemType);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}