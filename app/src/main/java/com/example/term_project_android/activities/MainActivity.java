package com.example.term_project_android.activities;


import android.content.Intent;
import android.os.Bundle;

import com.example.term_project_android.R;
import com.example.term_project_android.lib.DialogUtils;
import com.google.android.material.snackbar.Snackbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //Current Game Object
   /* private PMGame mCurrentGame;
    //Key used for save/restore during rotation and for preferences if autosave
    private final String mKeyGame = "GAME";*/

    // Preference booleans; indicates if these respective settings currently enabled/disabled
    private boolean mPrefUseAutoSave, mPrefShowErrors;

    // Name of Preference file on device
    private final String mKeyPrefsName = "PREFS";

    //Preference Key: value is already in strings.xml and will be assigned to it in onCreate
    private String mKeyAutoSave;

    // --Commented out by Inspection (8/10/2022 9:33 PM):private AppBarConfiguration appBarConfiguration;
    private Snackbar mSnackBar;

    @Override
    protected void onStop() {
        //saveToSharedPref();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        /*if (savedInstanceState != null){
            mCurrentGame = (PMGame) savedInstanceState.get(String.valueOf(mCurrentGame));
        }*/

    }

  /*  @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(mCurrentGame, String.valueOf(mCurrentGame));
    }*/


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

}