package com.school.landon.lthys_fueltrack;

import java.util.Date;

/**
 * Created by Landon on 29/01/2016.
 */
public class LogEntry extends Object {
    private Date date;
    private String station;
    private Integer odometer;
    private String grade;
    private Integer amount;
    private Integer cost;

    public LogEntry(Date date, String station, Integer odometer, String grade,Integer amount, Integer cost){
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.grade = grade;
        this.amount = amount;
        this.cost = cost;

    }

    public Integer getTotalCost(){
        return amount*cost;
    }

    public Date getDate(){
        return this.date;
    }

    public String getStation(){
        return station;
    }

    public  Integer getOdometer(){
        return odometer;
    }


}
