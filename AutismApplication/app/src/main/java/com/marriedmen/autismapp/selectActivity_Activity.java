package com.marriedmen.autismapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jason on 12/5/2017.
 */

public class selectActivity_Activity extends AppCompatActivity
{
    private int numberSelected = 0;
    private final int MAX_BEHV = 10;

    private String[] activities;
    private String[] behaviors;
    private int[] IDs;
    private int[] actIDs;

    private String profile;
    private int ID;

    private DBHelper db;
    private List<Behv_Item> behvList;
    private BehvAdapter behvAdapt;
    private ArrayAdapter actAdapt;
    private List<String> actList;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up(Back) button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // get position from last activity
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        //set up database
        db = new DBHelper(this);
        String[] profiles = db.getProfiles();
        activities = db.getActivities();
        behaviors = db.getBehaviors();
        IDs = db.getIDs();
        actIDs = db.getActIDs();
        behvList = new ArrayList<>();

        //have to convert array to list
        actList = new ArrayList<>();
        for (int i = 0; i < behaviors.length; i++) {
            Behv_Item behv = new Behv_Item(behaviors[i], 0);
            behvList.add(behv);
        }

        profile = profiles[position];
        ID = IDs[position];

        //Log.d("test", "activities: " + activities.toString());
        //Log.d("test", "behaviors: " + behaviors.toString());

        //for testing purposes: confirms correct profile
        TextView view = (TextView)findViewById(R.id.select_activity_text);
        view.setText(profile);

        // set up the activity selector
        Spinner actSpin = (Spinner)findViewById(R.id.activity_spinner);
        //actList = Arrays.asList(activities);
        for (int i = 0; i < activities.length; i++) { actList.add(activities[i]); }
        actAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, actList);
        actSpin.setAdapter(actAdapt);

        // set up the behavior selector
        /*Spinner behvSpin = (Spinner)findViewById(R.id.behavior_spinner);
        ArrayAdapter behvAdapt = new ArrayAdapter(this, android.R.layout.select_dialog_multichoice, behaviors);
        behvSpin.setAdapter(behvAdapt);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        behvAdapt = new BehvAdapter(this, R.layout.behavior_item, behvList);
        ListView listBehv = (ListView) findViewById(R.id.behv_sel_frame);
        listBehv.setAdapter(behvAdapt);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //when you hit back arrow call finish
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void refreshFragment()
    {
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragMan.beginTransaction();
        fragTrans.replace(R.id.behv_sel_frame, behvFrag);
        fragTrans.commit();
    }*/

    public void goBack(View view)
    {
        finish();
    }

    @Override
    public void onStart(){
        super.onStart();
        //refreshFragment();
    }

    private class BehvAdapter extends ArrayAdapter<Behv_Item> {
        Context context;
        List<Behv_Item> behaviorList = new ArrayList<>();

        public BehvAdapter(Context c, int rId, List<Behv_Item> behvs) {
            super(c, rId, behvs);
            behaviorList = behvs;
            context = c;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox selected = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.behavior_item, parent, false);

                selected = (CheckBox) convertView.findViewById(R.id.check);
                convertView.setTag(selected);

                selected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox check = (CheckBox) view;
                        Behv_Item tag = (Behv_Item)check.getTag();
                        if (check.isChecked() == true) {
                            if (numberSelected >= MAX_BEHV) {
                                Toast.makeText(context, "Please choose no more than " + MAX_BEHV + " behaviors", Toast.LENGTH_SHORT).show();
                                check.setChecked(false);
                            }
                            else {
                                tag.setSelected(1);
                                numberSelected++;
                            }
                        }
                        else {
                            tag.setSelected(0);
                            numberSelected--;
                        }

                    }
                });
            }
            else { selected = (CheckBox) convertView.getTag(); }
            Behv_Item current = behaviorList.get(position);
            selected.setText(current.getBehv());
            selected.setChecked(current.selected == 1);
            selected.setTag(current);
            return convertView;
        }
    }

    private class Behv_Item {
        private int _id;
        private String behavior;
        private int selected;

        public Behv_Item(String behv, int sel) {
            behavior = behv;
            selected = sel;
        }

        public int getId() { return _id; }

        public void setId(int id) { _id = id; }

        public String getBehv() { return behavior; }

        public int getSelected() { return selected; }

        public void setSelected(int sel) { selected = sel; }
    }

    public void start(View view) {
        if (numberSelected == 0) {
            Toast.makeText(this, "Please select at least one behavior", Toast.LENGTH_SHORT).show();
            return;
        }

        Spinner spin = (Spinner) findViewById(R.id.activity_spinner);
        String act = spin.getSelectedItem().toString();
        int idPointer = 0;
        for (int i = 0; i < activities.length; i++) {
            if (activities[i].equals(act)) {idPointer = i; }
        }

        Intent intent = new Intent(this, CounterActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("profile", profile);
        bundle.putInt("ID", ID);
        bundle.putString("activity", act);
        bundle.putInt("actID", actIDs[idPointer]);

        String[] selected = new String[numberSelected];
        int pointer = 0;
        for(Behv_Item bi:behvList) {
            if (bi.getSelected() == 1) {
                selected[pointer] = bi.getBehv();
                pointer++;
            }
        }

        bundle.putStringArray("behaviors", selected);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void addBehavior(View view) {
        AlertDialog.Builder prompt = new AlertDialog.Builder(this);
        prompt.setTitle("Add Behavior");
        prompt.setMessage("Add a new unique behavior to track");

        final EditText input = new EditText(this);
        prompt.setView(input);
        prompt.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                //Log.d("test", input.getText().toString());
                String behavior = input.getText().toString();

                for (Behv_Item bi:behvList) {
                    if(bi.getBehv().equals(behavior) || behavior.equals("")) {
                        Toast.makeText(selectActivity_Activity.this, "Behavior must be unique and not empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                db.addBehavior(behavior);
                Behv_Item bi = new Behv_Item(behavior, 0);
                behvList.add(bi);
                behvAdapt.notifyDataSetChanged();
            }
        });

        prompt.setNegativeButton("Cancel", null);
        prompt.show();
    }

    public void addActivity(View view) {
        AlertDialog.Builder prompt = new AlertDialog.Builder(this);
        prompt.setTitle("Add Activity");
        prompt.setMessage("Add a new unique activity to track");

        final EditText input = new EditText(this);
        prompt.setView(input);
        prompt.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                //Log.d("test", input.getText().toString());
                String activity = input.getText().toString();

                for(String act:actList) {
                    if (act.equals(activity) || activity.equals("")) {
                        Toast.makeText(selectActivity_Activity.this, "Activity must be unique and not empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                db.addActivity(activity);
                //Behv_Item bi = new Behv_Item(behavior, 0);
                //actList.add(activity);
                //activities = db.getActivities();
                actList.add(activity);
                actAdapt.notifyDataSetChanged();
            }
        });

        prompt.setNegativeButton("Cancel", null);
        prompt.show();
    }
}
