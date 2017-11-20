package com.marriedmen.autismapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "profiles";
    private static final String DATABASE_TABLE = "profiles";
    private static final String DATABASE_TABLE_BEHV = "behaviors";
    private static final String DATABASE_TABLE_ACTIVITY = "behaviors";

    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String KEY_PROFILE_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INFORMATION = "information";
    private static final String KEY_BEHVS = "behaviors";
    private static final String KEY_access = "access";

    private static final String KEY_ACTIVITIES = "activities";

    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String profileTable = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_PROFILE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " NAME, "
                + KEY_INFORMATION + " INFORMATION" + ")";

        String activityTable = "CREATE TABLE " + DATABASE_TABLE_ACTIVITY + "("
                + KEY_PROFILE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACTIVITIES + " activies, "
                +  ")";

        String behaviorTable = "CREATE TABLE " + DATABASE_TABLE_BEHV + "("
                + KEY_PROFILE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_BEHVS + " BEHVS, "
                + KEY_access + " ACCESS" + ")";

        db.execSQL(profileTable);
        db.execSQL(activityTable);
        db.execSQL(behaviorTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // DROP OLDER TABLE IF EXISTS
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_BEHV);
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACTIVITY);

        // CREATE TABLE AGAIN
        onCreate(database);
    }


    //********** DATABASE OPERATIONS:  ADD, EDIT, DELETE
    // Adding new profile
    public void addProfileObj(profileObj profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION
        values.put(KEY_NAME, profile.getName()); // task name

        //ADD KEY-VALUE PAIR INFORMATION FOR
        //IS_DONE VALUE: 0- NOT DONE, 1 - IS DONE
        values.put(KEY_INFORMATION, profile.getInfo());

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values);
        //taskCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }
    public void addActivity(String activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ACTIVITIES, activity);
        db.insert(DATABASE_TABLE_ACTIVITY, null, values);
        db.close();
    }
    public void addBehavior(String behv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_BEHVS, behv);
        values.put(KEY_access, "all");
        db.insert(DATABASE_TABLE_BEHV, null, values);
        db.close();
    }

    //for testing, can be generalized
    public String getProfileName() {

        //GET ALL THE TASK ITEMS ON THE LIST
        //List<profileObj> profileList = new ArrayList<ToDo_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  name FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String name = cursor.getString(0);
        return name;
    }
/*
        // LOOP THROUGH THE TODO TASKS
        if (cursor.moveToFirst()) {
            do {
                ToDo_Item task = new ToDo_Item();
                task.setId(cursor.getInt(0));
                task.setDescription(cursor.getString(1));
                task.setIs_done(cursor.getInt(2));
                todoList.add(task);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF TASKS FROM THE TABLE
        return todoList;
    }

    public void clearAll(List<ToDo_Item> list) {
        //GET ALL THE LIST TASK ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{});
        db.close();
    }

    public void deleteSelected(List<ToDo_Item> list) {

        for(Iterator<ToDo_Item> i=list.iterator() ; i.hasNext();){
            ToDo_Item item=i.next();
            if(item.getIs_done()==1) i.remove();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_IS_DONE+"=1", new String[]{});
        db.close();
    }

    public void updateTask(ToDo_Item task) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_IS_DONE, task.getIs_done());
        db.update(DATABASE_TABLE, values, KEY_TASK_ID + " = ?", new String[]{String.valueOf(task.getId())});
        db.close();
    }
*/
}
