package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemDataSource {
    private SQLiteDatabase database; //variables to hold instances of the database
    private ItemDataHelper dbHelper; //variable to ref helper class

    public ItemDataSource(Context context) { //instantiating the helper class
        dbHelper = new ItemDataHelper(context);
    }

    public void open() throws SQLException { //open db
        database = dbHelper.getWritableDatabase();
    }

    public void close() { //close db
        dbHelper.close();
    }


    public boolean insertVaccine(Item c){
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();




            initialValues.put("item_name", c.getItemName());
            initialValues.put("price", c.getItemPrice());
            initialValues.put("item_desc", c.getItemDesc());
            initialValues.put("fast_id", c.getFastId());

            didSucceed = database.insert("show", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }

    public boolean updateVaccine(Item c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getItemId();

            ContentValues updateValues = new ContentValues();
            updateValues.put("show_title", c.getItemName());
            updateValues.put("show_channel", c.getItemPrice());
            updateValues.put("show_language", c.getItemDesc());
            updateValues.put("show_genre", c.getFastId());


            didSucceed = database.update("show", updateValues, "_id=" + rowId, null) > 0;


        }
        catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastVaccineID(){

        int lastId;

        try {

            String query = "Select MAX(_ID) from show";

            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            lastId = cursor.getInt(0);

            cursor.close();

        } catch (Exception e) {

            lastId = -1;

        }

        return lastId;

    }
}