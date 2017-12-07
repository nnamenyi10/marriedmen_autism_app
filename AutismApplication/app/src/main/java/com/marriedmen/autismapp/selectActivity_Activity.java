package com.marriedmen.autismapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        // get position from last activity
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        //set up database
        DBHelper db = new DBHelper(this);
        String[] profiles = db.getProfiles();

        //for testing purposes: confirms correct profile
        TextView view = (TextView)findViewById(R.id.select_activity_text);
        view.setText(profiles[position]);
    }

    public void goBack(View view)
    {
        finish();
    }
}
