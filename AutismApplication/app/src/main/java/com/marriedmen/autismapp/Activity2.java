package com.marriedmen.autismapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity2 extends AppCompatActivity {
    protected DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        TextView text = (TextView) findViewById(R.id.textView);

        //information added in MainActivity
        mDBHelper = new DBHelper(this);

        text.setText( mDBHelper.testingquery());
        /*
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = date.format(cal.getTime());
        String formattedTime = time.format(cal.getTime());
        text.setText(formattedDate + "   " + formattedTime);
        */

        //text.setText("Must input a profile name, all other fields may be blank");

    }


    public void makeProfile(View view){
        EditText nameET = (EditText)findViewById(R.id.editTextName);
        EditText info = (EditText)findViewById(R.id.editTextInformation);
        String name = nameET.getText().toString();

        if (name.matches("")) {

            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();

            //do nothing, profile needs a name
            //should also check to make sure there are no duplicate names?
        }
        else {
            profileObj profile = new profileObj(name, info.toString());
            mDBHelper.addProfileObj(profile);
            finish();
        }
    }

    public void goBack(View view){
        finish();
        //onBackPressed(); ----don't need this
    }
}
