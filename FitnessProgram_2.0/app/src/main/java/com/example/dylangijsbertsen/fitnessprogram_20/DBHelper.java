package com.example.dylangijsbertsen.fitnessprogram_20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dylan Gijsbertsen on 20-9-2015.
 */
public class DBHelper extends SQLiteOpenHelper
{
    // Database info
    private static final String DB_NAME = "fitnessProgram.db";
    private static final int DB_VERSION = 1;

    // Assignments
    public static final String TABLE_EXERCISES = "assignments";
    public static final String COLUMN_EXERCISE_ID = "assignment_id";
    public static final String COLUMN_EXERCISE = "assignment";

    // Creating the table
    private static final String DATABASE_CREATE_ASSIGNMENTS =
            "CREATE TABLE " + TABLE_EXERCISES +
                    "(" +
                    COLUMN_EXERCISE_ID + " integer primary key autoincrement, " +
                    COLUMN_EXERCISE + " text not null" +
                    ");";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        // Execute the sql to create the table assignments
        database.execSQL(DATABASE_CREATE_ASSIGNMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(db);
    }
}
