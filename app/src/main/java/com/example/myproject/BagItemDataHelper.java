package com.example.myproject;
// coded by: Bell John Demetria
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BagItemDataHelper extends SQLiteOpenHelper { // a subclass of SQLiteOpenHelper
    private static final String DATABASE_NAME = "fast_food.db"; //a static variable to name the db
    private static final int DATABASE_VERSION = 1; //a variable to hold the version number

    //Database creation sql statement
    private static final String CREATE_TABLE_SHOW = //a string variable for query
            "create table bag_item (_id integer primary key autoincrement, "
                    + "uid text not null, "
                    + "item_id int);";

    public BagItemDataHelper(Context context) { // a constructor method to call the superclass constructor
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
        db.execSQL("DROP TABLE IF EXISTS bag_item");
        onCreate(db);
    }
}