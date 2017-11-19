package com.marriedmen.autismapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    protected DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        TextView text = (TextView) findViewById(R.id.textView);
        mDBHelper = new DBHelper(this);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        //for testing
        profileObj fakeprofile = new profileObj("Jeffery Dahmer", "bad man");
        profileObj fakeprofile2 = new profileObj("Chistopher Scarver", "Killed Jeff");
        mDBHelper.addProfileObj(fakeprofile);
        mDBHelper.addProfileObj(fakeprofile2);
        text.setText( mDBHelper.getProfileName());

    }
}
