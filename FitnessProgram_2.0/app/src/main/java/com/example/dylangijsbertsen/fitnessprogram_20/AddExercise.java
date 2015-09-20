package com.example.dylangijsbertsen.fitnessprogram_20;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLException;

public class AddExercise extends AppCompatActivity {

    private EditText exerciseEditText;
    private DataSource datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        datasource = new DataSource(this);
        exerciseEditText = (EditText) findViewById(R.id.add_exercise_edittext);
    }

  /*  public void newProduct(View v){
        DBHelper dbHelper = new DBHelper(this, null,null,1);
        Product product = dbHelper.findProduct(exerciseEditText.getText().toString());
        dbHelper.addProduct(product);
    }*/

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
        if (id == R.id.save_exercise) {
            long exerciseId = 0;
            try {
                exerciseId = datasource.createAssignment(exerciseEditText.getText().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.EXTRA_EXERCISE_ID, exerciseId);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
