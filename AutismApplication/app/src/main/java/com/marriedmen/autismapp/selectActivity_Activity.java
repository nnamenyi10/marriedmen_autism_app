package com.marriedmen.autismapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jason on 12/5/2017.
 */

public class selectActivity_Activity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);
    }

    public void goBack(View view)
    {
        finish();
    }
}
