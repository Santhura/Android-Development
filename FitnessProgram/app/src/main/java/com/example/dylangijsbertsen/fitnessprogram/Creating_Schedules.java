package com.example.dylangijsbertsen.fitnessprogram;

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

        //BackToMainMenu();
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

    private void Monday(){
        Button monday = (Button) findViewById(R.id.button_M);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Monday.class));
            }
        });
    }
    private void Tuesday(){
        Button Tuesday = (Button) findViewById(R.id.button_T);
        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Tuesday.class));
            }
        });
    }
    private void Wednesday(){
        Button Wednesday = (Button) findViewById(R.id.button_W);
        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Wednesday.class));
            }
        });
    }
    private void Thursday(){
        Button Thursday = (Button) findViewById(R.id.button_TH);
        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Thursday.class));
            }
        });
    }
    private void Friday(){
        Button Friday = (Button) findViewById(R.id.button_F);
        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Friday.class));
            }
        });
    }
    private void Saturday(){
        Button Saturday = (Button) findViewById(R.id.button_SA);
        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Saturday.class));
            }
        });
    }
    private void Sunday(){
        Button Sunday = (Button) findViewById(R.id.button_SU);
        Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Creating_Schedules.this, Sunday.class));
            }
        });
    }
}
