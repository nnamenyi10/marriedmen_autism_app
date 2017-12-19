package com.marriedmen.autismapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class What2Graph extends AppCompatActivity implements ProfilesFragment.OnProfileSelectedListener {

   /* findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //pass an ArrayList to GraphView
        }
    });*/
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.what2graph);
       setupActionBar();


       BehaviorsFragment behavsfrag = new BehaviorsFragment();
       getSupportFragmentManager().beginTransaction()
               .add(R.id.behaviors_fragment, behavsfrag).commit();

   }

    public void startGraphActivity(View v) {
        Intent intent = new Intent(What2Graph.this, GraphView.class);
        startActivity(intent);
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
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onProfileSelected(int position)
    {
        Intent intent = new Intent(this, selectActivity_Activity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("position", position);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void onBehaviorsSelected(int position)
    {
        Intent intent = new Intent(this, selectActivity_Activity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("position", position);
        intent.putExtras(bundle);

        startActivity(intent);

        //I think we will need this, gonna have to modify it though
    }


    public void refreshFragment()
    {
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragMan.beginTransaction();
        ProfilesFragment profFrag = new ProfilesFragment();
        fragTrans.replace(R.id.profiles_fragment, profFrag);
        fragTrans.commit();

        FragmentManager fragMan2 = getSupportFragmentManager();
        FragmentTransaction fragTrans2 = fragMan2.beginTransaction();
        BehaviorsFragment profFrag2 = new BehaviorsFragment();
        fragTrans2.replace(R.id.behaviors_fragment, profFrag2);
        fragTrans2.commit();

    }

    @Override
    public void onStart(){
        super.onStart();
        refreshFragment();
    }
}

