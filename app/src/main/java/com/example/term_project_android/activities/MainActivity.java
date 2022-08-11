package com.example.term_project_android.activities;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.term_project_android.R;
import com.example.term_project_android.classes.TaskItem;
import com.example.term_project_android.lib.DialogUtils;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Current Game Object
   /* private PMGame mCurrentGame;
    //Key used for save/restore during rotation and for preferences if autosave
    private final String mKeyGame = "GAME";*/

    // Preference booleans; indicates if these respective settings currently enabled/disabled
    private boolean mPrefClearOnAdd;

    // Name of Preference file on device
    private final String mKeyPrefsName = "PREFS";

    //Preference Key: value is already in strings.xml and will be assigned to it in onCreate
    private String mKeyAutoSave;



    // --Commented out by Inspection (8/10/2022 9:33 PM):private AppBarConfiguration appBarConfiguration;
    private Snackbar mSnackBar;


    private ArrayList<TaskItem> taskList;
    private final String mKEY_LIST = "KEY";
    private final String mKeyClearOnAdd = "CLEAR_ON_ADD";

    @Override
    protected void onStop() {
        //saveToSharedPref();
        super.onStop();
        saveOrDeleteGameInSharedPrefs();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        taskList = new ArrayList<>();
        setupFAB();
        /*if (savedInstanceState != null){
            mCurrentGame = (PMGame) savedInstanceState.get(String.valueOf(mCurrentGame));
        }*/

    }

    private void setupFAB() {
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTask(view);
            }
        });
    }

    private void addNewTask(View view) {
        EditText taskName = findViewById(R.id.et_taskName);
        EditText taskDescription = findViewById(R.id.et_taskDescription);
        EditText taskDue = findViewById(R.id.et_taskDue);
        EditText taskPriority = findViewById(R.id.et_taskPriority);

        String strTaskName = taskName.getText().toString();
        String strTaskDescription = taskDescription.getText().toString();
        String strTaskDue = taskDue.getText().toString();
        String strTaskPriority = taskPriority.getText().toString();
        strTaskPriority = strTaskPriority.length() > 0 ? strTaskPriority : "0";
        int intTaskPriority = Integer.parseInt(strTaskPriority);
        //can validate priority here, similar to lines 92-96
        if (strTaskName.length() == 0) {
            Snackbar.make(view, "Task name is a required field.", Snackbar.LENGTH_LONG).show();
        } else {
            TaskItem task = new TaskItem(strTaskName, strTaskDescription, strTaskDue, intTaskPriority);
            if (taskList.contains(task)) {
                Snackbar.make(view, "A task with that name already exists.", Snackbar.LENGTH_LONG).show();
            } else {
                taskList.add(task);
                Snackbar.make(view, "Task successfully added!", Snackbar.LENGTH_LONG).show();

            }
        }
    }

    public static String getGSONStringFromList(List<TaskItem> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    public static ArrayList<TaskItem> getListFromGSONString(String strList) {
        Gson gson = new Gson();
        Type TaskItemType = new TypeToken<ArrayList<TaskItem>>() {
        }.getType();
        return gson.fromJson(strList, TaskItemType);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(mKEY_LIST, getGSONStringFromList(taskList));
        outState.putBoolean(mKeyClearOnAdd, mPrefClearOnAdd);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String gson = savedInstanceState.getString(mKEY_LIST);
        taskList = getListFromGSONString(gson);
        mPrefClearOnAdd=savedInstanceState.getBoolean(mKeyClearOnAdd);
    }



    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.action_about: {
                showAbout();
                return true;
            }

            case R.id.action_settings: {
                showSettings();
                return true;
            }

            case R.id.action_tasks: {
                showTasks();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void showTasks() {

        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
        intent.putExtra(mKEY_LIST, getGSONStringFromList(taskList));

        startActivity(intent);

    }


    private void showAbout() {
        DialogUtils.showInfoDialog(MainActivity.this, "About To-Do App",
                "A Helpful To-Do App!\n" +
                        "\nAndroid app by SN and MTC.");
    }

    private void showSettings() {
        dismissSnackBarIfShown();
        //Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        //startActivityForResult(intent, 1);
    }

    private void dismissSnackBarIfShown() {
        if (mSnackBar.isShown()) {
            mSnackBar.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        restoreFromPreferences_SavedGameIfAutoSaveWasSetOn();
    }

    private void restoreFromPreferences_SavedGameIfAutoSaveWasSetOn() {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(this);

            String gameString = defaultSharedPreferences.getString(mKEY_LIST, null);
            if (gameString != null) {
                taskList = getListFromGSONString(gameString);
            }
            mPrefClearOnAdd = defaultSharedPreferences.getBoolean(mKeyClearOnAdd, true);
        }


    private void saveOrDeleteGameInSharedPrefs() {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = defaultSharedPreferences.edit();

        // Save current game
            editor.putString(mKEY_LIST, getGSONStringFromList(taskList));

        editor.putBoolean(mKeyClearOnAdd, mPrefClearOnAdd);
        editor.apply();
    }


}