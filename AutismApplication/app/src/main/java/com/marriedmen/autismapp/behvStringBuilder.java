package com.marriedmen.autismapp;

import android.util.Log;

/**
 * Created by Ramsdells on 12/9/2017.
 */


public class behvStringBuilder {

    //a collection of polymorphic methods for building a behvCount string

    public void behvStringBuilder(){

    }

    public String buildString(Integer[] behvs){
        //Log.d("test", "no profiles");
        String strBehvs = "";

        String temp = Integer.toString(0);
        strBehvs += temp;
        for(int i = 1; i < behvs.length; i++) {
            temp = Integer.toString(behvs[i]);
            strBehvs += "," + temp;
        }
        return strBehvs;
    }

    //sparse build
    public String buildString(DBHelper mDBHelper, Integer[][] behvs) {
        Integer size = mDBHelper.getBehvTableSize();
        return buildString(size,behvs);
    }

    //sparse build
    public String buildString(int size, Integer[][] behvs) {


        Integer[] behvCounts = new Integer[size];


        for (int i = 0; i < size; i++) {
            behvCounts[i] = -1;
        }


        for (Integer[] i : behvs) {
            int index = i[0];
            int count = i[1];

            behvCounts[index] = count;
        }
        return buildString(behvCounts);

    }

}
