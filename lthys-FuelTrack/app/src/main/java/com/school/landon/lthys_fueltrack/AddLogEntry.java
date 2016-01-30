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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddLogEntry extends ActionBarActivity {
    private static final String FILENAME = "LogEntries.sav";
    private Date tmpDate;
    private String tmpStation;
    private Integer tmpOdometer;
    private String tmpGrade;
    private Integer tmpAmount;
    private Integer tmpCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log_entry);

        EditText dateText = (EditText) findViewById(R.id.enterDate);
        EditText stationText = (EditText) findViewById(R.id.enterStation);
        EditText odometerText = (EditText) findViewById(R.id.enterOdometer);
        EditText gradeText = (EditText) findViewById(R.id.enterGrade);
        EditText amountText = (EditText) findViewById(R.id.enterAmount);
        EditText costText = (EditText) findViewById(R.id.enterCost);

        Button cancel  =  (Button) findViewById(R.id.cancelButton);
        Button finish = (Button) findViewById(R.id.finishButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
                String tempString = dateText.getText().toString();
                try {
                    tmpDate = dateFormat.parse(tempString);
                }catch(ParseException e){}

                tmpStation = stationText.getText().toString();

                String tempodo = odometerText.getText().toString();
                tmpOdometer = Integer.parseInt(tempodo);

                tmpGrade = gradeText.getText().toString();

                String tempamount = amountText.getText().toString();
                tmpAmount = Integer.parseInt(tempamount);

                String tempcost = costText.getText().toString();
                tmpCost = Integer.parseInt(tempcost);

                LogEntry entry = new LogEntry(tmpDate,tmpStation,tmpOdometer,tmpGrade,tmpAmount,tmpCost);



            }
        });
/*
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
*/
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

    Intent intent = getIntent();





}
