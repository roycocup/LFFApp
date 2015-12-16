package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.*;


public class ScheduleActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {


    Spinner weekdaysSpinner;
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setupSpinner();
        schedule = new Schedule(this);
    }

    void setupSpinner(){
        // weekdays spinner
        ArrayAdapter<String> a1 = new ArrayAdapter<String>(
                ScheduleActivity.this,
                android.R.layout.simple_spinner_item,
                weekDays);
        weekdaysSpinner = (Spinner) findViewById(R.id.sp_weekday);
        weekdaysSpinner.setAdapter(a1);
        //default the selection to today
        Calendar c = Calendar.getInstance();
        weekdaysSpinner.setSelection(c.get(Calendar.DAY_OF_WEEK) - 2);
        weekdaysSpinner.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        TextView txt_schedule = (TextView) findViewById(R.id.txt_schedule);
        String weekDay = weekDays[position];
        HashMap<Integer, String[]> daySchedule = schedule.getDayClasses(weekDay.toLowerCase());
        txt_schedule.setText("Looking at: " + weekDay + "\n");

        if (daySchedule.size() < 1){
            txt_schedule.append("There are no classes today. \n");
            return;
        }

        Iterator it = daySchedule.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Log.d(MainActivity.TAG, pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        /*
        for (int i = 0; i < daySchedule.size(); i++) {
            Log.w(MainActivity.TAG, daySchedule.get(i).get);
            String time = n.getNamedItem("time").getNodeValue().toString();
            String discipline = n.getNamedItem("discipline").getNodeValue().toString();
            txt_schedule.append("Time: " + time + " Class: " + discipline + "\n");

        }
        */

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent){}



}
