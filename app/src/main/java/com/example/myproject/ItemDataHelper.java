package com.example.myproject;
//coded by: Bell John Demetria
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ItemDataHelper extends SQLiteOpenHelper { // a subclass of SQLiteOpenHelper
    private static final String DATABASE_NAME = "fast_food.db"; //a static variable to name the db
    private static final int DATABASE_VERSION = 1; //a variable to hold the version number

    //Database creation sql statement
    private static final String CREATE_TABLE_SHOW = //a string variable for query
            "create table item (_id integer primary key autoincrement, "
                    + "item_name text not null, "
                    + "price decimal);";

    private static final String CREATE_TABLE_CUSTOMER = //a string variable for query
            "create table customer_table (id integer primary key autoincrement, "
                    + "customer_email text, "
                    + "customer_phone text, "
                    + "customer_address text, "
                    + "customer_fname text, "
                    + "customer_lname text)";


    private static final String CREATE_TABLE_ORDER = //a string variable for query
            "create table order_table (_id integer primary key autoincrement, "

                    + "date text, "
                    + "item_list text , "
                    + "total_amount text, "
                    + "customer_name text, "
                    + "contact text, "
                    + "order_type text, "
                    + "email text, "
                    + "address text);";



    public ItemDataHelper(Context context) { // a constructor method to call the superclass constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_SHOW);
        db.execSQL(CREATE_TABLE_ORDER);
    } //method to create

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //method to upgrade version
        Log.w(ItemDataHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS customer_table");
        db.execSQL("DROP TABLE IF EXISTS order_table");
        db.execSQL("DROP TABLE IF EXISTS item");
        onCreate(db);
    }
}
