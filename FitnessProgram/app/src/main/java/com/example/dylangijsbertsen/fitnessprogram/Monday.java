package com.example.dylangijsbertsen.fitnessprogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Monday extends AppCompatActivity {


    private ListView listView;
    public static final String EXTRA_EXERCISE = "extraExercise";
    public static final int DAY = 1;
    private ArrayAdapter<String> exerciseArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        listView = (ListView)findViewById(R.id.main_list);
        TextView emptyView = (TextView) findViewById(R.id.main_list_empty);
        listView.setEmptyView(emptyView);

        exerciseArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(exerciseArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monday, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_exersice) {
            startActivityForResult(new Intent(this, AddExercise.class), 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String exercise = data.getStringExtra(EXTRA_EXERCISE);
                exerciseArrayAdapter.add(exercise);
            }
        }
    }
}
