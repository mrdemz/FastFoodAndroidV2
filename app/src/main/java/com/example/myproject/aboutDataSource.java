package com.example.myproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class aboutDataSource {

    private SQLiteDatabase database;
    private aboutDBHelper dbHelper;

    public aboutDataSource(Context context) {
        dbHelper = new aboutDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }



    public boolean insertfastfood_table(fastfood_table c){
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("fas_name", c.getFas_name());
            initialValues.put("contact", c.getContact());
            initialValues.put("address", c.getAddress());


            didSucceed = database.insert("fastfood_table", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }

    public boolean updatefastfood_table(fastfood_table c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getFas_Id();

            ContentValues updateValues = new ContentValues();
            updateValues.put("fas_name", c.getFas_name());
            updateValues.put("contact", c.getContact());
            updateValues.put("address", c.getAddress());


            didSucceed = database.update("fastfood_table", updateValues, "fas_id=" + rowId, null) > 0;


        }
        catch (Exception e) {

        }
        return didSucceed;
    }
}