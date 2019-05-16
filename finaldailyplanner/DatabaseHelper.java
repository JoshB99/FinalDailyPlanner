package com.example.finaldailyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelperGoals";

    private static final String TABLE_NAME = "goal_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "type";
    private static final String COL3 = "date";
    private static final String COL4 = "description";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String spinner, String date, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, spinner);
        contentValues.put(COL3, date);
        contentValues.put(COL4, description);

        Log.d(TAG, "addData: Adding " + name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);


        return (result != -1);

    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 +
                " = '" + newName + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Updates the type field
     * @param newType
     * @param id
     * @param oldType
     */
    public void updateType(String newType, int id, String oldType){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newType + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldType + "'";
        Log.d(TAG, "updateType: query: " + query);
        Log.d(TAG, "updateType: Setting type to " + newType);
        db.execSQL(query);
    }

    /**
     * Updates the date field
     * @param newDate
     * @param id
     * @param oldDate
     */
    public void updateDate(String newDate, int id, String oldDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newDate + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldDate + "'";
        Log.d(TAG, "updateDate: query: " + query);
        Log.d(TAG, "updateDate: Setting date to " + newDate);
        db.execSQL(query);
    }

    /**
     * Updates the date field
     * @param newDescription
     * @param id
     * @param oldDescription
     */
    public void updateDescription(String newDescription, int id, String oldDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL4 +
                " = '" + newDescription + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + oldDescription + "'";
        Log.d(TAG, "updateDescription: query: " + query);
        Log.d(TAG, "updateDescription: Setting description to " + newDescription);
        db.execSQL(query);
    }
    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0+ " = '" + id + "'" +
                " AND " + COL1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param type
     */
    public void deleteType(int id, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + type + "'";
        Log.d(TAG, "deleteType: query: " + query);
        Log.d(TAG, "deleteType: Deleting " + type + " from database.");
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param date
     */
    public void deleteDate(int id, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + date + "'";
        Log.d(TAG, "deleteDate: query: " + query);
        Log.d(TAG, "deleteDate: Deleting " + date + " from database.");
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param description
     */
    public void deleteDescription(int id, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + description + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + description + " from database.");
        db.execSQL(query);
    }

}
