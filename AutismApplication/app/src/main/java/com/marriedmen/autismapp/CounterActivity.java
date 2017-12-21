package com.marriedmen.autismapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        text.setText(behvs[1]);//Integer.toString();

        Button[] buttons = new Button[behvs.length];
        TextView[] counts = new TextView[behvs.length];

        LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);

        for (int i = 0; i < behvs.length; i++){

            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
             //       LinearLayout.LayoutParams.MATCH_PARENT,
             //       LinearLayout.LayoutParams.WRAP_CONTENT );

            buttons[i] = new Button(this); //initialize the button here
            buttons[i].setText(behvs[i]);
            buttons[i].setId(i+1);

            //counts[i] = new TextView

            final int index = i;

            buttons[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    //do something
                }
            });
            layout.addView(buttons[i]);
        }
    }
}
