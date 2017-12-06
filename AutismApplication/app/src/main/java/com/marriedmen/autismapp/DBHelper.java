package com.marriedmen.autismapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {
    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "clients";

    private static final String DATABASE_TABLE = "profiles";
    private static final String DATABASE_TABLE_BEHV = "behaviors";
    private static final String DATABASE_TABLE_ACTIVITY = "activity";
    private static final String DATABASE_TABLE_LOGS = "logs";

    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String KEY_PROFILE_ID = "_id";
    private static final String KEY_PROFILE_ID2 = "_id2";
    private static final String KEY_PROFILE_ID3 = "_id3";

    private static final String KEY_NAME = "name";
    private static final String KEY_INFORMATION = "information";
    private static final String KEY_BEHVS = "behaviors";
    private static final String KEY_BEHV_ID = "behv_id";
    //log table
    private static final String KEY_LOG_ID = "_logId";
    private static final String KEY_DATE = "date";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";

    private static final String KEY_ACTIVITY_ID = "_logId"; // ?? do we need this?
    private static final String KEY_ACTIVITY_ID2 = "_logId2";

    private static final String KEY_BEHV_COUNTER = "behvCounter";

    private static final String KEY_ACTIVITIES = "activities";

    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String profileTable = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT NOT NULL, "
                + KEY_INFORMATION + " TEXT" + ")";

        String activityTable = "CREATE TABLE " + DATABASE_TABLE_ACTIVITY + "("
                + KEY_ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ACTIVITIES + " TEXT NOT NULL"
                +  ")";

        String behaviorTable = "CREATE TABLE " + DATABASE_TABLE_BEHV + "("
                + KEY_BEHV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_BEHVS + " TEXT NOT NULL"
                + ")";

        String logTable = "CREATE TABLE " + DATABASE_TABLE_LOGS + "("
                + KEY_LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                // the way to do this parent/child thing is with a foreign key
                + KEY_PROFILE_ID3 + " INTEGER NOT NULL, "
                //+ " FOREIGN KEY ("+ KEY_PROFILE_ID3 + ") INTEGER REFERENCES "+ DATABASE_TABLE + "("+ KEY_PROFILE_ID+"), "
                + KEY_DATE + " TEXT, "
                //+ KEY_START_TIME + " TEXT, "
                + KEY_END_TIME + " TEXT, "
                // this is the id from the activity table, so we know what activity is being done.
                + KEY_ACTIVITY_ID2 + " INTEGER, "
                // options, sql way is to have
                //an string "1,2,3" with , as parser, will have to convert string 1 to int in analytics
                + KEY_BEHV_COUNTER + " TEXT, "
                + "FOREIGN KEY ("+ KEY_PROFILE_ID3 + ") REFERENCES "+ DATABASE_TABLE + "("+ KEY_PROFILE_ID+"))";


        //this is block only for debugging should be deleted later
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_BEHV);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LOGS);
        //end block

        db.execSQL(profileTable);
        db.execSQL(activityTable);

        db.execSQL(behaviorTable);
        db.execSQL(logTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // DROP OLDER TABLE IF EXISTS
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_BEHV);
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACTIVITY);
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LOGS);

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


    public void addLogTest(profileObj profile) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = date.format(cal.getTime());
        String formattedTime = time.format(cal.getTime());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(KEY_PROFILE_ID3, 0);
        values.put(KEY_DATE, formattedDate);

        //values.put(KEY_START_TIME, formattedTime);

        //same start and end if fine for now
        values.put(KEY_END_TIME, formattedTime);
        //this is saying the activity taking place has id 1, in this case its dinner (see init in mainactivity)
        values.put(KEY_ACTIVITY_ID2, 1);
        //just adding a string for now
        //this is saying first behv happened once, second twice, ect... There are 4 total behvs currently
        values.put(KEY_BEHV_COUNTER, "1,2,0,0");

        db.insert(DATABASE_TABLE_LOGS, null, values);
        db.close();
    }

    public void addBehavior(String behv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_BEHVS, behv);

        db.insert(DATABASE_TABLE_BEHV, null, values);
        db.close();
    }

    public String[] getProfiles() {
        //get length of profiles databases
        /*String[] namelist = new String[10];
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + KEY_NAME + " FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        //String str = cursor.getString(0);
        int count = 0;
        if (cursor.moveToFirst()) {
            do {
                String str = cursor.getString(0);
                namelist[count] = str;
                count++;
            } while (cursor.moveToNext());
            count = 0;
        }

        return namelist;*/
        SQLiteDatabase db = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, DATABASE_TABLE);
        //db.close();
        Log.d("test", "getProfiles: " + count);
        String[] profiles = new String[(int)count];
        Cursor mCursor = db.rawQuery("select * from " + DATABASE_TABLE, null);
        mCursor.moveToFirst();
        for (int i = 0; i < count; i++)
        {
            if (mCursor.isAfterLast() || mCursor == null){ break; }
            String name = mCursor.getString(mCursor.getColumnIndex(KEY_NAME));
            profiles[i] = name;
            mCursor.moveToNext();
        }

        //String[] test = new String[] {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10"};
        //return test;
        return profiles;
    }

    //for testing, can be generalized
    public String testingquery() {
        SQLiteDatabase db = this.getReadableDatabase();

        //doesn't work
        String test_query = "SELECT * FROM " +DATABASE_TABLE+  " INNER JOIN " +DATABASE_TABLE_LOGS+ " ON "
                + DATABASE_TABLE_LOGS+ "." +KEY_PROFILE_ID3+ " = " +DATABASE_TABLE+ "." + KEY_PROFILE_ID;

        /*
        String MY_QUERY =
                "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});
        */

        //GET ALL THE TASK ITEMS ON THE LIST
        //List<profileObj> profileList = new ArrayList<ToDo_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT " + KEY_BEHVS + " FROM " + DATABASE_TABLE_BEHV;


        Cursor cursor = db.rawQuery(selectQuery, null);


        //Cursor cursor = db.rawQuery(test_query, null);

        cursor.moveToFirst();

        String str = cursor.getString(0);

        //cursor.moveToNext();
        //String name2 = cursor.getString(0);
        return str;
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
