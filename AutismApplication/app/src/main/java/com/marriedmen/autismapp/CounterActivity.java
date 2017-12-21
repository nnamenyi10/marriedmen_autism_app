package com.marriedmen.autismapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CounterActivity extends AppCompatActivity {
    String[] behvs;
    String id;

    Button[] buttons;
    TextView[] countViews;
    Integer[] counts;
    DBHelper mDBHelper;
    Integer size;
    //Chronometer chrono;
    //private long elapsed;
    //private String clock = "00:00";
    private String MM = "00";
    private String SS = "00";
    private boolean isRunning = false;
    private int totalSec = 0;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = new DBHelper(this);
        setContentView(R.layout.activity_counter);
        //TextView text = (TextView) findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();
        //chrono = new Chronometer(this);
        //chrono = (Chronometer) findViewById(R.id.timer);
        //init(this);
        tv = (TextView) findViewById(R.id.time_display);

        behvs= bundle.getStringArray("behaviors");
        id = bundle.getString("ID");

        //text.setText(behvs[1]);//Integer.toString();
        size = behvs.length;
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
            buttons[i].setText(behvs[i]+"  "+ counts[i]);
            buttons[i].setId(i+1);

            countViews[i] = new TextView(this);

            String count = Integer.toString(counts[i]);

            countViews[i].setId(i+1);
            //countViews[i].setText(count);
            countViews[i].setTextSize(36);
            countViews[i].setLayoutParams(lp);
            countLayout.addView(countViews[i]);

            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    View v = findViewById(R.id.myLayout);

                    counts[index]++;
                    buttons[index].setText(behvs[index] +"  "+ counts[index]);
                    v.invalidate();
                    view.invalidate();
                }
            });
            layout.addView(buttons[i]);
        }

    }

    public void stop(View view) {
        isRunning = false;
        //Toast.makeText(this, "Total time elapsed: " + totalSec + " seconds", Toast.LENGTH_SHORT).show();
        //chrono.stop();
        //String time = chrono.toString();
        behvStringBuilder builder = new behvStringBuilder();

        Integer[][] info = new Integer[size][2];

        for(int i = 0; i < size; i++) {
            info[i][0] = mDBHelper.getBehvId(behvs[i]);
            info[i][1] = counts[i];
        }

        String stringArray = builder.buildString(mDBHelper.getBehvTableSize(), info);

        mDBHelper.addLog(0,0,stringArray);
        finish();
    }

    public void start(View view){
        //Log.d("test", "starting timer");
        isRunning = true;
        new timer().execute();
        //chrono.start();
        //chrono.setBase(SystemClock.elapsedRealtime());
        //chrono.start();
    }

    public void pause (View view) {
        //Log.d("test", "Stopping timer");
        isRunning = false;
        //chrono.stop();
        //((Chronometer) findViewById(R.id.timer)).stop();
    }

    /*private void init(Context c) {
        chrono = new Chronometer(this);
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Log.d("test", "get to tick?");
                String MM = ((elapsed / 60) < 10 ? "0" : "") + (elapsed/60);
                String SS = ((elapsed % 60) < 10 ? "0" : "") + (elapsed%60);
                clock = MM + ":" + SS;
                elapsed = (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000;
                chronometer.setText(clock);
            }
        });
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
    }*/

    private class timer extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... nothing) {
            while (isRunning) {
                try {
                    MM = ((totalSec / 60) < 10 ? "0" : "") + (totalSec / 60);
                    SS = ((totalSec % 60) < 10 ? "0" : "") + (totalSec % 60);
                    //TextView tv = (TextView)findViewById(R.id.time_display);
                    //tv.setText(MM + ":" + SS);
                    publishProgress(MM + ":" + SS);
                    totalSec++;
                    Thread.sleep(1000); // unusual time accounts for updating UI
                    // @967mil
                    // at 5 minutes, timer stayed accurate to the second on emulator
                    // on Galaxy S8+ it is fast by 9 seconds at 5 minutes...

                } catch (Exception e) {}
            }
            return MM + ":" + SS;
        }

        @Override
        protected void onProgressUpdate(String... time) {
            //TextView tv = (TextView) findViewById(R.id.time_display);
            tv.setText(time[0]);
            //Log.d("test", MM + ":" + SS);
    }

    }
}
