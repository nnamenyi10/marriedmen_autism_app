package com.marriedmen.autismapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CounterActivity extends AppCompatActivity {
    String[] behvs;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        TextView text = (TextView) findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();

        behvs= bundle.getStringArray("behaviors");
        id = bundle.getString("ID");

        //text.setText(Integer.toString(behvs.length));

        for (int i = 0; i < behvs.length; i++){

            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
             //       LinearLayout.LayoutParams.MATCH_PARENT,
             //       LinearLayout.LayoutParams.WRAP_CONTENT );

            LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);

            Button myButton = new Button(this);
            myButton.setText("Push Me");
            myButton.setId(i);
            myButton.setText("button" + Integer.toString(i));
            layout.addView(myButton);


        }
    }
}
