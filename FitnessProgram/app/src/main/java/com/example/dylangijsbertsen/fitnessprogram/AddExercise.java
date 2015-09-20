package com.example.dylangijsbertsen.fitnessprogram;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddExercise extends AppCompatActivity {

    private EditText exerciseEditText;
    private String whatDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        exerciseEditText = (EditText) findViewById(R.id.add_assignment_exercise_edittext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_assignment_menu_save) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Monday.EXTRA_EXERCISE, exerciseEditText.getText().toString());

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

