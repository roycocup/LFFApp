package uk.co.rodderscode.lffapp;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Schedule {

    static final String[] diciplines = {"BJJ", "BJJ Beginners", "Muay Thai", "Wrestling", "Boxing", "S&C", "Sambo", "MMA"};
    static final String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    static final String[] times = {"7.15", "12:00", "18:00", "18:15", "18:30", "19:00", "20:00", "20:30"};
    static final int[] rooms = {1,2};


    public void Schedule(){

    }

    public Hashtable getByWeekDay(String weekday){
        Hashtable<String, String>data = new Hashtable();
        data.put("7:15", "BJJ");
        return data;
    }

}
