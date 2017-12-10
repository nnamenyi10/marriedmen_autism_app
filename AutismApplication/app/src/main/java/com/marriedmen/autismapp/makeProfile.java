package com.marriedmen.autismapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class makeProfile extends AppCompatActivity {
    protected DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_profile);
        //TextView text = (TextView) findViewById(R.id.textView);

        mDBHelper = new DBHelper(this);
        /*Integer[][] sparse = {{0,11},{1,22}};
        Integer[] list = {1,2,3,4};
        behvStringBuilder test = new behvStringBuilder();

        text.setText(test.buildString(3,sparse));
        */
    }


    public void makeProfile(View view){
        EditText nameET = (EditText)findViewById(R.id.editTextName);
        //EditText info = (EditText)findViewById(R.id.editTextInformation);
        String name = nameET.getText().toString();

        if (name.matches("")) {

            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
        }
        else {
            profileObj profile = new profileObj(name, null);//info.toString());
            mDBHelper.addProfileObj(profile);
            finish();
        }
    }

    public void goBack(View view){
        finish();
        //onBackPressed(); ----don't need this
    }
}
