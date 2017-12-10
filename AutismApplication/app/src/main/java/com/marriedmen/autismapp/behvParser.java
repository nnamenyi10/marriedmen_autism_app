package com.marriedmen.autismapp;

/**
 * Created by Ramsdells on 12/9/2017.
 */

public class behvParser {
    private int[] behvCounts;
    //private DBHelper mDBHelper;

    //constructor
    public behvParser(DBHelper mDBHelper) {
        //this.mDBHelper = mDBHelper;
        Integer size = mDBHelper.getBehvTableSize();

        //each index corresponds to a behv in db behv_table
        behvCounts = new int[size];
        //init all to untracked rep as -1
        for (int i = 0; i < behvCounts.length; i++) {
            behvCounts[i] = -1;
        }
    }

    public void fillBehvCounts(String behvs) {
        String delims = "[,]";
        String[] tokens = behvs.split(delims);

        int size = tokens.length;

        for(int i = 0; i < size; i++) {
            String temp = tokens[i];
            behvCounts[i] = Integer.parseInt(temp);
        }
    }

    //There never should be more trackable behvs than in the behv table.
    public int[] getBehvCounts(String behvs){
        fillBehvCounts(behvs);
        return behvCounts;
    }


    public int[] getBehvCounts() {
        return behvCounts;
    }

    public String toString() {
        String temp = "";
        for (Integer i : behvCounts) {
            temp += " " + Integer.toString(i);
        }
        return temp;
    }
}
