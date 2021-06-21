package com.example.myproject;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class aboutDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fast_food.db";
    private static final int DATABASE_VERSION = 1;

    //Database creation sql statement
    private static final String CREATE_TABLE_fastfood_table =
            "create table vaccine (fas_id integer primary key autoincrement, "
                    + "fas_name text not null, "
                    + "contact text, "
                    + "address text);";


    public aboutDBHelper(Context context) { // a constructor method to call the superclass constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_fastfood_table);
    } //method to create

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //method to upgrade version
        Log.w(aboutDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS fast_food");
        onCreate(db);
    }
}