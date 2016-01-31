package com.school.landon.lthys_fueltrack;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AddLogEntry extends ActionBarActivity {
    private static final String FILENAME = "LogEntries.sav";
    private ArrayList<LogEntry> loglist = new ArrayList<LogEntry>();

    private EditText dateText;
    private EditText stationText;
    private EditText odometerText;
    private EditText gradeText;
    private EditText amountText;
    private EditText costText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log_entry);

        dateText = (EditText) findViewById(R.id.enterDate);
        stationText = (EditText) findViewById(R.id.enterStation);
        odometerText = (EditText) findViewById(R.id.enterOdometer);
        gradeText = (EditText) findViewById(R.id.enterGrade);
        amountText = (EditText) findViewById(R.id.enterAmount);
        costText = (EditText) findViewById(R.id.enterCost);

        Button cancel  =  (Button) findViewById(R.id.cancelButton);
        Button finish = (Button) findViewById(R.id.finishButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tmpDate;
                String tmpStation;
                Integer tmpOdometer;
                String tmpGrade;
                Integer tmpAmount;
                Integer tmpCost;

                Bundle extras = getIntent().getExtras();
                String position = null;
                if (extras != null){
                    position = extras.getString("position");
                }

                Intent dataRet = new Intent();

                if(position != null){
                    dataRet.putExtra("position", position);
                }
                dataRet.putExtra("date",dateText.getText().toString());
                dataRet.putExtra("station",stationText.getText().toString());
                dataRet.putExtra("odometer",odometerText.getText().toString());
                dataRet.putExtra("grade",gradeText.getText().toString());
                dataRet.putExtra("amount",amountText.getText().toString());
                dataRet.putExtra("cost",costText.getText().toString());
                setResult(RESULT_OK,dataRet);
                finish();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_log_entry, menu);
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
