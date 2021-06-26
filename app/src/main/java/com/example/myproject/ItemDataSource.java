package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
//coded by: Bell John Demetria
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


    public boolean insertItem(Item c){
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("item_name", c.getItemName());
            initialValues.put("price", c.getItemPrice());
            didSucceed = database.insert("item", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }

    public int getLastItemID(){

        int lastId;

        try {

            String query = "Select MAX(_ID) from item";

            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            lastId = cursor.getInt(0);

            cursor.close();

        } catch (Exception e) {

            lastId = -1;

        }

        return lastId;

    }
    public ArrayList<Item> getItems() {
        ArrayList<Item>  itemList = new ArrayList<Item>();
        try {
            String query = "SELECT * from item";
            Cursor cursor = database.rawQuery(query, null);
            Item newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                newItem = new Item();
                newItem.setItemId(cursor.getInt(0));
                newItem.setItemName(cursor.getString(1));
                newItem.setItemPrice(cursor.getDouble(2));
                itemList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception){
            itemList = new ArrayList<Item>();
        }
        return itemList;
    }
    public ArrayList<Double> getPrices() {
        ArrayList<Double>  itemList = new ArrayList<Double>();
        try {
            String query = "SELECT * from item";
            Cursor cursor = database.rawQuery(query, null);
            double newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                newItem = cursor.getDouble(2);
                itemList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception){
            itemList = new ArrayList<Double>();
        }
        return itemList;
    }
    public boolean deleteItem(int itemID){
        boolean didDelete = false;
        try {
            /*A delete method requires the table to delete from and the where clause*/
            didDelete = database.delete("item", "_id=" + itemID, null) > 0;
        } catch (Exception e) {
            //Do nothing - return value is alread set to false
        }
        return didDelete;
    }
    //For Matias's//Noted by:Bell John Demetria//You can add more methods in this area depending on what you need.

    public boolean insertCustomer(CustomerData c){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("customer_email", c.getCustomer_email());
        cv.put("customer_phone", c.getCustomer_phone());
        cv.put("customer_address", c.getCustomer_address());
        cv.put("customer_fname", c.getCustomer_fname());
        cv.put("customer_lname", c.getCustomer_lname());

        long insert = db.insert("customer_table", null, cv);
        if (insert == -1){
            return false;
        } else{
            return true;
        }
    }
//Matias section ends here// Noted by: Bell John Demetria==========================================================================================================

//For Attila // Noted by:Bell John Demetria// You can add more methods in this area depending on what you need.
public ArrayList<Double> getDate() {
    ArrayList<Double>  itemList = new ArrayList<Double>();
    try {
        String query = "SELECT * from item";
        Cursor cursor = database.rawQuery(query, null);
        double newItem;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            newItem = cursor.getDouble(2);
            itemList.add(newItem);
            cursor.moveToNext();
        }
        cursor.close();
    } catch (Exception exception){
        itemList = new ArrayList<Double>();
    }
    return itemList;
}

    public boolean insertOrder(Item c){
        boolean didSucceed = false;
        try {

            ContentValues initialValues = new ContentValues();


            initialValues.put("date", c.getItemName());

            //put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria
            initialValues.put("item_list", c.getItemPrice());//put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria
            initialValues.put("customer_name", c.getItemName());//put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria
            initialValues.put("contact text", c.getItemPrice());//put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria
            initialValues.put("order_type", c.getItemName());//put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria
            initialValues.put("email", c.getItemPrice());//put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria
            initialValues.put("address", c.getItemName());//put your class method here, make a class(see Item.java for example) noted by: Bell John Demetria


            didSucceed = database.insert("order_table", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }



    //Attila's section ends here// Noted by: Bell John Demetria==========================================================================================================






}