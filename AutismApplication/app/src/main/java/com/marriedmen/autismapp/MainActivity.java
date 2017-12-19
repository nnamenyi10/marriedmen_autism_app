package com.marriedmen.autismapp;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements ProfilesFragment.OnProfileSelectedListener {
    protected DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DBHelper(this);
        behvTableInit(mDBHelper);
        activityTableInit(mDBHelper);

        //for testing
        /*profileObj fakeprofile = new profileObj("Jeffery Dahmer", "bad man");
        profileObj fakeprofile2 = new profileObj("Chistopher Scarver", "Killed Jeff");
        mDBHelper.addProfileObj(fakeprofile);
        mDBHelper.addProfileObj(fakeprofile2);

        //for testing
        mDBHelper.addLogTest(fakeprofile);*/

    }

    public void activityTableInit(DBHelper db) {
        db.addActivity("playtime");
        db.addActivity("bedtime");
        db.addActivity("dinner");
    }

    public void debugClearAll(View v) {
        DBHelper db = new DBHelper(this);
        db._profilesclearAll();
        refreshFragment();
    }

    public void behvTableInit(DBHelper db) {
        db.addBehavior("Self-injury");
        db.addBehavior("Property destruction");
        db.addBehavior("Talking back");
        db.addBehavior("Work refusal");
    }

    public void onProfileSelected(int position)
    {
        Intent intent = new Intent(this, selectActivity_Activity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("position", position);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void startActivity_2(View v) {
        Intent intent = new Intent(MainActivity.this, makeProfile.class);
        startActivity(intent);
    }

    public void startSettingsActivity(View v) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);

    }

    public void startGraphActivity(View v) {
        Intent intent = new Intent(MainActivity.this, GraphView.class);
        startActivity(intent);
    }

    public void startW2GraphActivity(View v) {
        Intent intent = new Intent(MainActivity.this, What2Graph.class);
        startActivity(intent);
    }

    /*public void ()
    {
        super.onStart();
        Fragment frag = getFragmentManager().findFragmentById(R.id.profiles_fragment);
    }*/

    public void refreshFragment()
    {
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragMan.beginTransaction();
        ProfilesFragment profFrag = new ProfilesFragment();
        fragTrans.replace(R.id.profiles_fragment, profFrag);
        fragTrans.commit();
    }

    @Override
    public void onStart(){
        super.onStart();
        refreshFragment();
    }
}
