package com.example.term_project_android.activities;

import static com.example.term_project_android.lib.DialogUtils.showInfoDialog;

import android.os.Bundle;

import com.example.term_project_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.term_project_android.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private Snackbar mSnackBar;


    /**
     * @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
     * super.onSaveInstanceState(outState);
     * outState.putString("GAME", mGame.getJSONStringFromThis());
     * }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFAB();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                showSettings();
                return true;
            }
            case R.id.action_about: {
                showAbout();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void showAbout() {
        dismissSnackBarIfShown();
        showInfoDialog(MainActivity.this, "About To-Do App",
                "A Helpful To_Do App!\n" +
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