package com.school.landon.lthys_fueltrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.Date;

import java.util.ArrayList;


public class MainLog extends ActionBarActivity {
    private Integer REQUEST_CODE = 123;
    private static final String FILENAME = "LogEntries.sav";
    private ListView EntriesList;
    private ArrayList<LogEntry> logEntries= new ArrayList<LogEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log);

        Button addEntry = (Button) findViewById(R.id.AddEntry);

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), AddLogEntry.class);
                startActivityForResult(intent, REQUEST_CODE);

                //go to add screen
            }
        });
    }

    protected void onStart(){
        super.onStart();
        //loadFromfile();
        //adapter = new ArrayAdapter<Entry>()
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_log, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
