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

    Button[] buttons;
    TextView[] countViews;
    Integer[] counts;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        TextView text = (TextView) findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();

        behvs= bundle.getStringArray("behaviors");
        id = bundle.getString("ID");

        //text.setText(behvs[1]);//Integer.toString();
        int size = behvs.length;
        //Button[]
                buttons = new Button[size];
        //TextView[]
                countViews = new TextView[size];
        //Integer[]
                counts = new Integer[size];
        //for some reason does not init to 0s even tho it's supposed to!!!
        for (int i = 0; i < size; i++) {
            counts[i] = 0;
        }



        LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);

        LinearLayout countLayout = (LinearLayout) findViewById(R.id.countLayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < behvs.length; i++){

            buttons[i] = new Button(this); //initialize the button here
            buttons[i].setText(behvs[i]);
            buttons[i].setId(i+1);

            countViews[i] = new TextView(this);

            String count = Integer.toString(counts[i]);

            countViews[i].setId(i+1);
            countViews[i].setText(count);
            countViews[i].setTextSize(36);
            countViews[i].setLayoutParams(lp);
            countLayout.addView(countViews[i]);

            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    View v = findViewById(R.id.myLayout);

                    counts[index]++;
                    buttons[index].setText("test");
                    v.invalidate();
                    view.invalidate();
                }
            });
            layout.addView(buttons[i]);
        }
    }
}
