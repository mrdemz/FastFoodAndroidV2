package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
            didSucceed = database.insert("item", null, initialValues) > 0;
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



    public Item getSpecificVaccine(int vaccineID) { /*The id for the specific record*/
        Item item = new Item();
        String query = "SELECT * FROM item WHERE _id =" + vaccineID; /*.Query has where clause*/
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            item.setItemId(cursor.getInt(0));
            item.setItemName(cursor.getString(1));
            item.setItemPrice(cursor.getDouble(2));

            cursor.close();
        }
        return item;
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




}