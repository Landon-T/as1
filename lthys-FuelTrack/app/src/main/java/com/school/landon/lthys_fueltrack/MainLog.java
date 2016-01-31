package com.school.landon.lthys_fueltrack;

import android.content.Intent;
import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;


public class MainLog extends ActionBarActivity {
    private Integer REQUEST_CODE_ADD= 100;
    private Integer REQUEST_CODE_UPDATE = 200;
    private static final String FILENAME = "LogEntries6.sav";
    private ListView EntriesList;
    private ArrayList<LogEntry> logEntries= new ArrayList<LogEntry>();
    private ArrayAdapter<LogEntry> adapter;
    private Integer Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log);

        Button addEntry = (Button) findViewById(R.id.AddEntry);
        EntriesList = (ListView) findViewById(R.id.EntriesList);
        Button totalCost = (Button) findViewById(R.id.totalCost);

        totalCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float sum = Float.parseFloat("0.0");
                for(int i = 0; i < logEntries.size(); i++){
                   sum += logEntries.get(i).getTotalCost();
                }
                Toast.makeText(v.getContext(), sum.toString(), Toast.LENGTH_LONG).show();
            }
        });

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddLogEntry.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
                //go to add screen
            }
        });

        EntriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), AddLogEntry.class);
                //intent.putExtra("position", position);
                Position = position;
                startActivityForResult(intent,REQUEST_CODE_UPDATE);
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<LogEntry>(this, R.layout.list_view, logEntries);
        EntriesList.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent dataRet){
        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == RESULT_OK) {
                Integer position;
                String tmpDate;
                String tmpStation;
                Integer tmpOdometer;
                String tmpGrade;
                Integer tmpAmount;
                Float tmpCost;
                String x;
                Boolean y  = dataRet.hasExtra("date");

                if (dataRet.hasExtra("date")){
                    tmpDate = dataRet.getStringExtra("date");
                    /*
                    x = dataRet.getStringExtra("date");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
                    try {
                        tmpDate = dateFormat.parse(x);
                    } catch (ParseException e) {
                        tmpDate = null;
                    }
                    */
                }else{tmpDate = "2000-01-22";}

                if (dataRet.hasExtra("station") ){
                    tmpStation = dataRet.getStringExtra("station");
                }else{tmpStation = "nowhere";}

                if (dataRet.hasExtra("odometer")) {
                    x = dataRet.getStringExtra("odometer");
                    tmpOdometer = Integer.parseInt(x);
                }else{tmpOdometer = 0;}

                if (dataRet.hasExtra("grade")) {
                    tmpGrade = dataRet.getStringExtra("grade");
                }else{tmpGrade = "regular";}

                if (dataRet.hasExtra("amount")) {
                    x = dataRet.getStringExtra("amount");
                    tmpAmount = Integer.parseInt(x);
                }else{tmpAmount = 0;}

                if(dataRet.hasExtra("cost")) {
                    x = dataRet.getStringExtra("cost");
                    tmpCost = Float.parseFloat(x);
                }else{tmpCost = Float.parseFloat("0.0");}

                LogEntry newEntry = new LogEntry(tmpDate, tmpStation, tmpOdometer, tmpGrade, tmpAmount, tmpCost);

                logEntries.add(newEntry);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }

        if (REQUEST_CODE_UPDATE == requestCode){
            if (resultCode == RESULT_OK){
                String tmpDate;
                String tmpStation;
                Integer tmpOdometer;
                String tmpGrade;
                Integer tmpAmount;
                Float tmpCost;
                String x;


                if (dataRet.hasExtra("date")) {
                    if (dataRet.getStringExtra("date").length() != 0) {
                        logEntries.get(Position).updateDate(dataRet.getStringExtra("date"));
                    }
                }
                if (dataRet.hasExtra("station")) {
                    if (dataRet.getStringExtra("station").length() != 0) {
                        logEntries.get(Position).updateStation(dataRet.getStringExtra("station"));
                    }
                }
                if (dataRet.hasExtra("odometer")) {
                    if (dataRet.getStringExtra("odometer").length() != 0) {
                        logEntries.get(Position).updateOdometer(Integer.parseInt(dataRet.getStringExtra("odometer")));
                    }
                }
                if (dataRet.hasExtra("grade")) {
                    if (dataRet.getStringExtra("grade").length() != 0) {
                        logEntries.get(Position).updateGrade(dataRet.getStringExtra("grade"));
                    }
                }
                if (dataRet.hasExtra("amount")) {
                    if (dataRet.getStringExtra("amount").length() != 0) {
                        logEntries.get(Position).updateAmount(Integer.parseInt(dataRet.getStringExtra("amount")));
                    }
                }
                if (dataRet.hasExtra("cost")) {
                    if (dataRet.getStringExtra("cost").length() != 0) {
                        logEntries.get(Position).updateCost(Float.parseFloat(dataRet.getStringExtra("cost")));
                    }
                }
            }
            adapter.notifyDataSetChanged();
            saveInFile();
        }

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 2015-09-22
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            logEntries = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            logEntries = new ArrayList<LogEntry>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(logEntries, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
