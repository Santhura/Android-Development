package com.example.dylangijsbertsen.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Dylan Gijsbertsen on 14-9-2015.
 */
public class DataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] assignmentAllColumns = {MySQLiteHelper.COLUMN_ASSIGNMENT_ID, MySQLiteHelper.COLUMN_ASSIGNMENT };


    public DataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long createAssignment(String assignment){
        //if the database is not open yet, open it
        if(!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ASSIGNMENT, assignment);
        long insertId = database.insert(MySQLiteHelper.TABLE_ASSIGNMENTS, null, values);

        if(database.isOpen())
            close();


        return insertId;
    }
}
