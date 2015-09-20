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
    private ArrayAdapter<Assignment> exerciseArrayAdapter;
    public static final String EXTRA_EXERCISE = "extraExercise";
    private DataSource datasource;
    public static final String EXTRA_ASSIGNMENT_ID = "extraAssignmentId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main_list);
        TextView emptyView = (TextView)findViewById(R.id.main_list_empty);
        listView.setEmptyView(emptyView);

        datasource = new DataSource(this);
        List<Assignment> assignments = null;
        try {
            assignments = datasource.getAllAssignments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        exerciseArrayAdapter = new ArrayAdapter<Assignment>(this, android.R.layout.simple_list_item_1, assignments);
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
       /* if (id == R.id.add_exercise) {
            startActivityForResult(new Intent(this, AddExercise.class), 1);
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
                long assignmentId = data.getLongExtra(EXTRA_ASSIGNMENT_ID, -1);
                if(assignmentId != -1)
                {
                    Assignment assignment = null;
                    try {
                        assignment = datasource.getAssignment(assignmentId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    exerciseArrayAdapter.add(assignment);
                }
            }

        }
    }
}
