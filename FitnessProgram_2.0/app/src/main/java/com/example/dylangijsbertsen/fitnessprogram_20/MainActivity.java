package com.example.dylangijsbertsen.fitnessprogram_20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Exercise> exerciseArrayAdapter;
    public static final String EXTRA_EXERCISE = "extraExercise";
    private DataSource datasource;
    public static final String EXTRA_EXERCISE_ID = "extraExerciseId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main_list);
        TextView emptyView = (TextView)findViewById(R.id.main_list_empty);
        listView.setEmptyView(emptyView);

        datasource = new DataSource(this);
        List<Exercise> exercise = null;
        try {
            exercise = datasource.getAllAssignments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        exerciseArrayAdapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_list_item_1, exercise);
        listView.setAdapter(exerciseArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_bar_menu_delete_all) {

            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
        if(button.getId() == R.id.add_exercise){
            startActivityForResult(new Intent(this, AddExercise.class), 1);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK) {
                long exerciseId = data.getLongExtra(EXTRA_EXERCISE_ID, -1);
                if(exerciseId != -1)
                {
                    Exercise exercise = null;
                    try {
                        exercise = datasource.getAssignment(exerciseId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    exerciseArrayAdapter.add(exercise);
                }
            }

        }
    }
}
