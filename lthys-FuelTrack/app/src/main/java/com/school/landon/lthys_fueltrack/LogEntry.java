package com.school.landon.lthys_fueltrack;

import java.util.Date;

/**
 * Created by Landon on 29/01/2016.
 */
public class LogEntry extends Object {
    private String date;
    private String station;
    private Integer odometer;
    private String grade;
    private Integer amount;
    private Float cost;

    public LogEntry(String date, String station, Integer odometer, String grade,Integer amount, Float cost){
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.grade = grade;
        this.amount = amount;
        this.cost = cost;

    }

    public Float getTotalCost(){
        return amount*(cost/100);
    }

    public String getDate(){
        return this.date;
    }

    public String getStation(){
        return station;
    }

    public  Integer getOdometer(){
        return odometer;
    }

    public void updateDate(String d){this.date = d;}
    public void updateStation(String s){this.station = s;}
    public void updateOdometer(Integer i){this.odometer = i;}
    public void updateGrade(String s){this.grade = s;}
    public void updateAmount(Integer i){this.amount = i;}
    public void updateCost(Float f){this.cost = f;}


    @Override
    public String toString(){
        return this.date.toString()+ " || "+this.station+"\n"+this.amount.toString()+"L  @"+this.cost.toString()+"(Cents/L)  of: "+this.grade+"\n Odemeter: "+this.odometer.toString();
    }




}
