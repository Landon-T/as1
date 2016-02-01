package com.school.landon.lthys_fueltrack;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Landon on 29/01/2016.
 */
public class LogEntry extends Object {
    private String date;
    private String station;
    private Float odometer;
    private String grade;
    private Float amount;
    private Float cost;

    public LogEntry(String date, String station, Float odometer, String grade,Float amount, Float cost){
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.grade = grade;
        this.amount = amount;
        this.cost = cost;

    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public Float getTotalCost(){
        return round(amount*(cost/100),2);
    }

    public String getDate(){
        return this.date;
    }

    public String getStation(){
        return station;
    }

    public  Float getOdometer(){
        return odometer;
    }

    public void updateDate(String d){this.date = d;}
    public void updateStation(String s){this.station = s;}
    public void updateOdometer(Float i){this.odometer = round(i,1);}
    public void updateGrade(String s){this.grade = s;}
    public void updateAmount(Float i){this.amount = round(i,3);}
    public void updateCost(Float f){this.cost = round(f,1);}


    @Override
    public String toString(){
        return this.date.toString()+ " || "+this.station+"\n"+this.amount.toString()+"L  @"+this.cost.toString()+"(Cents/L)  of: "+this.grade+"\n Odemeter: "+this.odometer.toString();
    }




}
