package com.marriedmen.autismapp;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;



public class GraphView extends AppCompatActivity{   // (List<BarEntries>)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_graph);
        setupActionBar();

        final String[] weekdays = new String[]
                {"Mon","Tues","Wed","Thurs","Fri","Sat","Sun"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return weekdays[(int) value];
            }
        };//use this to build a string of values the axis can use
        BarChart chart = (BarChart) findViewById(R.id.chart);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 2f));
        entries.add(new BarEntry(1f, 8f));
        entries.add(new BarEntry(2f, 6f));
        entries.add(new BarEntry(3f, 5f));
        entries.add(new BarEntry(4f, 0f));
        entries.add(new BarEntry(5f, 7f));
        entries.add(new BarEntry(6f, 6f));

        /*
        * ways to make this outside of here:
        *
        * List<BarEntries> entries = new ArrayList<>();
        *
        * for(int i = 0; i < 8; i++){
        *   an array of counts from the last 7 logs;
         *  log[i];
         *  entries.add(new BarEntry(i, log[i]));
        * }
        *
        * once entries is filled out we can pass it to GraphView
        *
        *
        *
        * */

        BarDataSet set = new BarDataSet(entries, "Occurrences of bad behaviors");

        BarData data = new BarData(set);

        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);

        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(true); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(true); // no grid lines
        left.setDrawZeroLine(true); // draw a zero line
        chart.getAxisRight().setEnabled(false); // no right axis

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // min interval is 1
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setValueFormatter(formatter);//set the x-axis values
        xAxis.setDrawGridLines(false);

        chart.setFitBars(true); // make the x-axis fit exactly all bars

        chart.setPinchZoom(true); // lets you zoom in on the chart

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        //set the chart description to nothing

        chart.invalidate(); // refresh
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up(Back) button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //when you hit back arrow call finish
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(item));
    }

}

