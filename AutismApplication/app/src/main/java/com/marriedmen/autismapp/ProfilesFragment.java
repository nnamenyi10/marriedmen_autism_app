package com.marriedmen.autismapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Jason on 12/3/2017.
 */

public class ProfilesFragment extends ListFragment
{
    OnProfileSelectedListener mCallback;
    //DBHelper mDBHelper;

    public interface OnProfileSelectedListener
    {
        //TODO add function to activity main
        void onProfileSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mDBHelper = new DBHelper(this);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // get database to make list
        //DBHelper db = new DBHelper(getActivity());
        //SQLiteDatabase db = this.getReadableDatabase();
        // test array
        String[] test = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        setListAdapter(new ArrayAdapter<>(getActivity(), layout, test));

    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.profiles_fragment) != null)
        {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    /*@Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallback = (OnProfileSelectedListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + "must implement OnProfileSelectedListener");
        }
    }*/

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        mCallback.onProfileSelected(position);

        getListView().setItemChecked(position, true);
    }
}
