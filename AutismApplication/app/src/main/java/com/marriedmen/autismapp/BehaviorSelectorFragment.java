package com.marriedmen.autismapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * Created by Jason on 12/16/2017.
 */
// TODO fix checkboxes
public class BehaviorSelectorFragment extends ListFragment {
    DBHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mDBHelper = new DBHelper(this);

        int layout = android.R.layout.simple_list_item_multiple_choice;

        // get database to make list
        //DBHelper db = new DBHelper(getActivity());
        //SQLiteDatabase db = this.getReadableDatabase();

        db = new DBHelper(getActivity());
        try
        {
            String[] behaviors = db.getBehaviors();

            // this adds a way to add activities inline
            /*String[] behvList = new String[behaviors.length+1];
            behvList[0] = "Add New Behavior";
            System.arraycopy(behaviors, 0, behvList, 1, behaviors.length);*/

            setListAdapter(new ArrayAdapter<>(getActivity(), layout, behaviors));
        }
        catch (Exception e)
        {
            Log.d("test", "no behaviors");
        }

    }
}
