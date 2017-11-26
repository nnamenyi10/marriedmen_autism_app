package com.marriedmen.autismapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected DBHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DBHelper(this);

        //for testing
        profileObj fakeprofile = new profileObj("Jeffery Dahmer", "bad man");
        profileObj fakeprofile2 = new profileObj("Chistopher Scarver", "Killed Jeff");
        mDBHelper.addProfileObj(fakeprofile);
        mDBHelper.addProfileObj(fakeprofile2);

        behvTableInit(mDBHelper);
        activityTableInit(mDBHelper);

    }


    public void activityTableInit(DBHelper db) {
        db.addActivity("bedtime");
        db.addActivity("dinner");
    }

    public void behvTableInit(DBHelper db) {
        db.addBehavior("eating people");
        db.addBehavior("murder");
        db.addBehavior("drugging men");
        db.addBehavior("necrophilia");
    }

    public void startActivity_2(View v) {
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }
    public void startSettingsActivity(View v) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);

    }
}
