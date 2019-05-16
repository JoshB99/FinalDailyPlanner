package com.example.finaldailyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Goals&Tasks";

    private static final int DATABASE_VERSION = 1;

    private static final String TTAG = "DatabaseHelperTask";
    private static final String GTAG = "DatabaseHelperGoals";

    private static final String TTABLE_NAME = "task_table";
    private static final String TCOL1 = "ID";
    private static final String TCOL2 = "task";
    private static final String TCOL3 = "linkedGoal";
    private static final String TCOL4 = "isSelected";

    private static final String GTABLE_NAME = "goal_table";
    private static final String GCOL0 = "ID";
    private static final String GCOL1 = "name";
    private static final String GCOL2 = "type";
    private static final String GCOL3 = "date";
    private static final String GCOL4 = "description";


    private static final String Create_TTABLE_NAME = "CREATE TABLE " + TTABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TCOL2 +" TEXT, " + TCOL3 +" TEXT, " + TCOL4 + " BOOL)";
    private static final String Create_GTABLE_NAME = "CREATE TABLE " + GTABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GCOL1 + " TEXT, " + GCOL2 + " TEXT, " + GCOL3 + " TEXT, " + GCOL4 + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_TTABLE_NAME);
        db.execSQL(Create_GTABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GTABLE_NAME);

        onCreate(db);
    }

    public boolean addTData(String task, String goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TCOL2, task);
        contentValues.put(TCOL3, goal);
        contentValues.put(TCOL4, false);


        Log.d(TTAG, "addData: Adding " + task + " to " + TTABLE_NAME);
        Log.d(TTAG, "addData: Adding " + goal + " to " + TTABLE_NAME);

        long result = db.insert(TTABLE_NAME, null, contentValues);

        return (result != -1);
    }

    public boolean addGData(String name, String spinner, String date, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GCOL1, name);
        contentValues.put(GCOL2, spinner);
        contentValues.put(GCOL3, date);
        contentValues.put(GCOL4, description);

        Log.d(GTAG, "addData: Adding " + name + " to " + GTABLE_NAME);

        long result = db.insert(GTABLE_NAME, null, contentValues);


        return (result != -1);

    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getTData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TTABLE_NAME;
        Cursor tdata = db.rawQuery(query, null);
        return tdata;
    }

    public Cursor getGData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + GTABLE_NAME;
        Cursor gdata = db.rawQuery(query, null);
        return gdata;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getTItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + TCOL1 + " FROM " + TTABLE_NAME +
                " WHERE " + TCOL2 + " = '" + name + "'";
        Cursor tdata = db.rawQuery(query, null);
        return tdata;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + GCOL0 + " FROM " + GTABLE_NAME +
                " WHERE " + GCOL1 + " = '" + name + "'";
        Cursor gdata = db.rawQuery(query, null);
        return gdata;
    }
    /**
     * Returns only the ID that matches the name passed in
     * @param ID
     * @return
     */
    public Cursor getTLinkedGoal(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + TCOL3 + " FROM " + TTABLE_NAME +
                " WHERE " + TCOL1 + " = '" + ID + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     * @param newLinkedGoal
     */
    public void updateTask(String newName, int id, String oldName, String newLinkedGoal){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TTABLE_NAME + " SET " + TCOL2 +
                " = '" + newName + "', " + TCOL3 + " = '" + newLinkedGoal + "' WHERE " + TCOL1 + " = '" + id + "'" +
                " AND " + TCOL2 + " = '" + oldName + "'";
        Log.d(TTAG, "updateName: query: " + query);
        Log.d(TTAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Updates checkbox
     * @param selected
     */
    public void selectCheckbox(int selected, int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TTABLE_NAME + " SET " + TCOL4 +
                " = '" + selected + "' WHERE " + TCOL1 + " = '" + id + "'" +
                " AND " + TCOL2 + " = '" + name + "'";
        db.execSQL(query);
    }
    public void updateGoal(int id, String oldName, String newName, String newType, String newDate, String newDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + GTABLE_NAME + " SET " + GCOL1 +
                " = '" + newName + "', " + GCOL2 + " = '" + newType + "' "+ GCOL3 + " = '" + newDate
                + GCOL4 + " = '" + newDescription + "' WHERE " + GCOL0 + " = '" + id + "'" +
                " AND " + GCOL2 + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void deleteGoal(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + GTABLE_NAME + " WHERE "
                + GCOL0 + " = '" + id + "'" +
                " AND " + GCOL1 + " = '" + name + "'";
        db.execSQL(query);
    }
    /**
     * Delete from database
     * @param id
     * @param task
     */
    public void deleteTask(int id, String task){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TTABLE_NAME + " WHERE "
                + TCOL1 + " = '" + id + "'" +
                " AND " + TCOL2 + " = '" + task + "'";
        Log.d(TTAG, "deleteName: query: " + query);
        Log.d(TTAG, "deleteName: Deleting " + task + " from database.");
        db.execSQL(query);
    }

    public Cursor getGoalType(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + GCOL2 + " FROM " + GTABLE_NAME +
                " WHERE " + GCOL0 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getGoalDate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + GCOL3 + " FROM " + GTABLE_NAME +
                " WHERE " + GCOL0 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getGoalDescription(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + GCOL4 + " FROM " + GTABLE_NAME +
                " WHERE " + GCOL0 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
