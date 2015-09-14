package com.example.dylangijsbertsen.homework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dylan Gijsbertsen on 14-9-2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    //database info
    private static final String DATABASE_NAME = "myhomeworktutorial.db";
    private static final int DATABASE_VERSION =  1;

    //assignments
    public static final String TABLE_ASSIGNMENTS = "assingments",
                                COLUMN_ASSIGNMENT_ID = "assingment_id",
                                COLUMN_ASSIGNMENT = "assignment";


    //creating the table
    private static final String DATABASE_CREATE_ASSIGNMENTS = "CREATE TABLE " + TABLE_ASSIGNMENTS
                                                                + "(" + COLUMN_ASSIGNMENT_ID
                                                                + "interger primary key autoincrement, "
                                                                + COLUMN_ASSIGNMENT + "text not null" + ");";





    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        //execute the sql to create the table assignemts
        database.execSQL(DATABASE_CREATE_ASSIGNMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        /*
         * When the database gets upgraded you should handle the update to make sure there is no data loss.
         * This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
         */

        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);
        onCreate(db);
    }
}
