package com.marriedmen.autismapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Jason on 12/3/2017.
 */

public class BehaviorsFragment extends ListFragment
{
    What2GraphListener mCallback;
    DBHelper db;
    Integer[] behvOnArray;
    Integer behvSize;


    public interface What2GraphListener
    {
        //TODO add function to activity main
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mDBHelper = new DBHelper(this);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        db = new DBHelper(getActivity());
        try
        {
            String[] behavs = db.getBehaviors();
            behvSize = behavs.length;
            setListAdapter(new ArrayAdapter<>(getActivity(), layout, behavs));
        }
        catch (Exception e)
        {
            Log.d("test", "no profiles");
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        //index correction.
        //position = position - 1;

        if (behvOnArray[position] == 1) {
            behvOnArray[position] = 0;
        }
        else {
            behvOnArray[position] = 1;
        }
        getListView().setItemChecked(position, true);
    }

}
