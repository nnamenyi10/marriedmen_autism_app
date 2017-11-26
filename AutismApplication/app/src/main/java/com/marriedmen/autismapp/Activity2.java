package com.marriedmen.autismapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    protected DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        TextView text = (TextView) findViewById(R.id.textView);

        //information added in MainActivity
        mDBHelper = new DBHelper(this);
        //test
        text.setText( mDBHelper.testingquery());
    }


    public void makeProfile(String name, String info){


    }

    public void goBack(View view){
        finish();
        //onBackPressed(); ----don't need this
    }
}
