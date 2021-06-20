package com.example.myproject;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String tv_table = "TV_TABLE";
    private final String tv_id = "ID";
    private final String tv_name = "TV_NAME";
    private final String tv_channel = "TV_CHANNEL";
    private final String tv_language = "TV_LANGUAGE";
    private final String tv_genre = "TV_GENRE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "tv.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table = "CREATE TABLE "
                + tv_table + " ("
                +tv_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +tv_name+ " TEXT NOT NULL, "
                +tv_channel+ " INTEGER, "
                +tv_language+ " TEXT, "
                +tv_genre+ " TEXT)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(TelevisionData televisionData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(tv_name, televisionData.getTvName());
        cv.put(tv_channel, televisionData.getTvChannel());
        cv.put(tv_language, televisionData.getTvLanguage());
        cv.put(tv_genre, televisionData.getTvGenre());

        long insert = db.insert(tv_table, null, cv);
        if (insert == -1){
            return false;
        } else{
            return true;
        }
    }
    public boolean deleteOne(TelevisionData televisionData){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + tv_table + " WHERE " + tv_id + " = " + televisionData.getTvID();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return true;
        } else
            return false;
    }
    public List<TelevisionData> getData(){
        List<TelevisionData> returnList = new ArrayList<>();

        //get data from database
        String selectData = "SELECT * FROM " + tv_table;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectData, null);
        if (cursor.moveToNext()){
            //loop through the cursor <result set> and create new customer objects. Put them into the return list.
            do {
                int tvID = cursor.getInt(0);
                String tvName = cursor.getString(1);
                int tvChannel = cursor.getInt(2);
                String tvLanguage = cursor.getString(3);
                String tvGenre = cursor.getString(4);

                TelevisionData televisionData = new TelevisionData(tvID, tvName, tvChannel, tvLanguage, tvGenre);
                returnList.add(televisionData);

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
