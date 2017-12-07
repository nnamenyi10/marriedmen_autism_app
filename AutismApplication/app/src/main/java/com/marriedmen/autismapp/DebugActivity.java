package com.marriedmen.autismapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DebugActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }

    public void profileClearAll(View v) {
        DBHelper db = new DBHelper(this);
        db._profilesclearAll();
    }

    public void behvClearAll(View v) {
        DBHelper db = new DBHelper(this);
        db._behvClearAll();
    }

    public void activityClearAll(View v) {
        DBHelper db = new DBHelper(this);
        db._activityClearAll();
    }

    public void logClearAll(View v) {
        //DBHelper db = new DBHelper(this);
        //db._logClearAll();
    }
}
