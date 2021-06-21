package com.example.myproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TempTableHelper extends SQLiteOpenHelper { // a subclass of SQLiteOpenHelper
    private static final String DATABASE_NAME = "fast_food.db"; //a static variable to name the db
    private static final int DATABASE_VERSION = 1; //a variable to hold the version number

    //Database creation sql statement
    private static final String CREATE_TABLE_SHOW = //a string variable for query
            "create table temp_table (_id integer primary key autoincrement, "

                    + "selected_id int);";

    public TempTableHelper(Context context) { // a constructor method to call the superclass constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SHOW);
    } //method to create

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //method to upgrade version
        Log.w(BagItemDataHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS temp_table");
        onCreate(db);
    }
}
