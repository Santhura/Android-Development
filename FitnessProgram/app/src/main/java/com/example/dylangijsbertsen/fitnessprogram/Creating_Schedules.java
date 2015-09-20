package com.example.dylangijsbertsen.fitnessprogram;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Creating_Schedules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating__schedules);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_creating__schedules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.getItemId() == R.id.action_bar_back){
            startActivity(new Intent(Creating_Schedules.this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
            switch (button.getId()){
                case R.id.button_M:
                    startActivity(new Intent(Creating_Schedules.this, Monday.class));
                    break;
                case R.id.button_T:
                    startActivity(new Intent(Creating_Schedules.this, Tuesday.class));
                    break;
                case R.id.button_W:
                    startActivity(new Intent(Creating_Schedules.this, Wednesday.class));
                    break;
                case R.id.button_TH:
                    startActivity(new Intent(Creating_Schedules.this, Thursday.class));
                    break;
                case R.id.button_F:
                    startActivity(new Intent(Creating_Schedules.this, Friday.class));
                    break;
                case R.id.button_SA:
                    startActivity(new Intent(Creating_Schedules.this, Saturday.class));
                    break;
                case R.id.button_SU:
                    startActivity(new Intent(Creating_Schedules.this, Sunday.class));
                    break;
            }
    }

}
