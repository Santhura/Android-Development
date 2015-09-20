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
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] assignmentAllColumns = { DBHelper.COLUMN_ASSIGNMENT_ID, DBHelper.COLUMN_ASSIGNMENT };

    public DataSource(Context context)
    {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close()
    {
        dbHelper.close();
    }

    public long createAssignment(String assignment)throws SQLException
    {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ASSIGNMENT, assignment);
        long insertId = database.insert(DBHelper.TABLE_ASSIGNMENTS, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();

        return insertId;
    }

    public void deleteAssignment(Assignment assignment)throws SQLException
    {
        if (!database.isOpen())
            open();

        database.delete(DBHelper.TABLE_ASSIGNMENTS, DBHelper.COLUMN_ASSIGNMENT_ID + " =?", new String[] { Long.toString(assignment.getId())});

        if (database.isOpen())
            close();
    }

    public void updateAssignment(Assignment assignment)throws SQLException
    {
        if (!database.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_ASSIGNMENT, assignment.getAssignment());
        database.update(DBHelper.TABLE_ASSIGNMENTS, args, DBHelper.COLUMN_ASSIGNMENT_ID + "=?", new String[] { Long.toString(assignment.getId()) });

        if (database.isOpen())
            close();
    }

    public List<Assignment> getAllAssignments()throws SQLException
    {
        if (!database.isOpen())
            open();

        List<Assignment> assignments = new ArrayList<Assignment>();

        Cursor cursor = database.query(DBHelper.TABLE_ASSIGNMENTS, assignmentAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Assignment assignment = cursorToAssignment(cursor);
            assignments.add(assignment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return assignments;
    }

    public Assignment getAssignment(long columnId)throws SQLException
    {
        if (!database.isOpen())
            open();

        Cursor cursor = database.query(DBHelper.TABLE_ASSIGNMENTS, assignmentAllColumns, DBHelper.COLUMN_ASSIGNMENT_ID + "=?", new String[] { Long.toString(columnId)}, null, null, null);

        cursor.moveToFirst();
        Assignment assignment = cursorToAssignment(cursor);
        cursor.close();

        if (database.isOpen())
            close();

        return assignment;
    }

    private Assignment cursorToAssignment(Cursor cursor)
    {
        try
        {
            Assignment assignment = new Assignment();
            assignment.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ASSIGNMENT_ID)));
            assignment.setAssignment(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ASSIGNMENT)));
            return assignment;
        }catch(CursorIndexOutOfBoundsException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
