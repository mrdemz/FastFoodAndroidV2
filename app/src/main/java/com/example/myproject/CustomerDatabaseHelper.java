package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CustomerDatabaseHelper extends SQLiteOpenHelper {

    private final String customer_table = "customer_table";
    private final String customer_id = "id";
    private final String customer_fname = "customer_fname";
    private final String customer_lname = "customer_lname";
    private final String customer_email = "customer_email";
    private final String customer_phone = "customer_phone";
    private final String customer_address = "customer_address";
    private final String customer_password = "customer_password";



    public CustomerDatabaseHelper(@Nullable Context context) {
        super(context, "fast_food.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table = "CREATE TABLE "
                +customer_table+ " ("
                +customer_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +customer_fname+ " TEXT NOT NULL, "
                +customer_lname+ " TEXT NOT NULL, "
                +customer_phone+ " TEXT NOT NULL, "
                +customer_address+ " TEXT NOT NULL, "
                +customer_email+ " TEXT NOT NULL, "
                +customer_password+ " TEXT NOT NULL)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean save(CustomerData customerData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(customer_fname, customerData.getCustomer_fname());
        cv.put(customer_lname, customerData.getCustomer_lname());
        cv.put(customer_phone, customerData.getCustomer_phone());
        cv.put(customer_address, customerData.getCustomer_address());
        cv.put(customer_email, customerData.getCustomer_email());
        cv.put(customer_password, customerData.getPassword());

        long insert = db.insert(customer_table, null, cv);
        if (insert == -1){
            return false;
        } else{
            return true;
        }
    }

    public boolean delete(CustomerData customerData){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + customer_table + " WHERE " + customer_id + " = " + customerData.getCustomer_id();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return true;
        } else
            return false;
    }

    public List<CustomerData> getAll(){
        List<CustomerData> returnList = new ArrayList<>();

        //get data from database
        String selectData = "SELECT * FROM " + customer_table;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectData, null);
        if (cursor.moveToNext()){
            //loop through the cursor <result set> and create new customer objects. Put them into the return list.
            do {
                int customerID = cursor.getInt(0);
                String customerFname = cursor.getString(1);
                String customerLname = cursor.getString(1);
                String customerPhone = cursor.getString(2);
                String customerAddress = cursor.getString(3);
                String customerEmail = cursor.getString(4);
                String customerPassword = cursor.getString(5);


                CustomerData customerData = new CustomerData(customerID, customerFname, customerLname, customerPhone, customerAddress, customerEmail, customerPassword);
                returnList.add(customerData);

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
