package com.marriedmen.autismapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Jason on 12/5/2017.
 */

public class selectActivity_Activity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up(Back) button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // get position from last activity
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        //set up database
        DBHelper db = new DBHelper(this);
        String[] profiles = db.getProfiles();
        String[] activities = db.getActivities();
        String[] behaviors = db.getBehaviors();
        //Log.d("test", "activities: " + activities.toString());
        //Log.d("test", "behaviors: " + behaviors.toString());

        //for testing purposes: confirms correct profile
        TextView view = (TextView)findViewById(R.id.select_activity_text);
        view.setText(profiles[position]);

        // set up the activity selector
        Spinner actSpin = (Spinner)findViewById(R.id.activity_spinner);
        ArrayAdapter actAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, activities);
        actSpin.setAdapter(actAdapt);

        // set up the behavior selector
        /*Spinner behvSpin = (Spinner)findViewById(R.id.behavior_spinner);
        ArrayAdapter behvAdapt = new ArrayAdapter(this, android.R.layout.select_dialog_multichoice, behaviors);
        behvSpin.setAdapter(behvAdapt);*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //when you hit back arrow call finish
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goBack(View view)
    {
        finish();
    }
}
