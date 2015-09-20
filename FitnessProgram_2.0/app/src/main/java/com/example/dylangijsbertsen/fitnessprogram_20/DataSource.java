package com.example.dylangijsbertsen.fitnessprogram_20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan Gijsbertsen on 20-9-2015.
 */

public class DataSource
{
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] exerciseAllColumns = { DBHelper.COLUMN_EXERCISE_ID, DBHelper.COLUMN_EXERCISE };

    public DataSource(Context context)
    {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close()
    {
        dbHelper.close();
    }

    public long createAssignment(String assignment)throws SQLException
    {
        // If the database is not open yet, open it
        if (!db.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_EXERCISE, assignment);
        long insertId = db.insert(DBHelper.TABLE_EXERCISES, null, values);

        // If the database is open, close it
        if (db.isOpen())
            close();

        return insertId;
    }

    public void deleteExercise(Exercise assignment)throws SQLException
    {
        if (!db.isOpen())
            open();

        db.delete(DBHelper.TABLE_EXERCISES, DBHelper.COLUMN_EXERCISE_ID + " =?", new String[] { Long.toString(assignment.getId())});

        if (db.isOpen())
            close();
    }

    public void updateExercise(Exercise exercise)throws SQLException
    {
        if (!db.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_EXERCISE, exercise.getExercise());
        db.update(DBHelper.TABLE_EXERCISES, args, DBHelper.COLUMN_EXERCISE_ID + "=?", new String[] { Long.toString(exercise.getId()) });

        if (db.isOpen())
            close();
    }

    public List<Exercise> getAllAssignments()throws SQLException
    {
        if (!db.isOpen())
            open();

        List<Exercise> assignments = new ArrayList<Exercise>();

        Cursor cursor = db.query(DBHelper.TABLE_EXERCISES, exerciseAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Exercise assignment = cursorToAssignment(cursor);
            assignments.add(assignment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (db.isOpen())
            close();

        return assignments;
    }

    public Exercise getAssignment(long columnId)throws SQLException
    {
        if (!db.isOpen())
            open();

        Cursor cursor = db.query(DBHelper.TABLE_EXERCISES, exerciseAllColumns, DBHelper.COLUMN_EXERCISE_ID + "=?", new String[] { Long.toString(columnId)}, null, null, null);

        cursor.moveToFirst();
        Exercise assignment = cursorToAssignment(cursor);
        cursor.close();

        if (db.isOpen())
            close();

        return assignment;
    }

    private Exercise cursorToAssignment(Cursor cursor)
    {
        try
        {
            Exercise exercise = new Exercise();
            exercise.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EXERCISE_ID)));
            exercise.setExercise(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EXERCISE)));
            return exercise;
        }catch(CursorIndexOutOfBoundsException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
