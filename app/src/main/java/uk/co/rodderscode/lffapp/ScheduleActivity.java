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
        TextView txt_schedule_content = (TextView) findViewById(R.id.txt_schedule_content);
        String weekDay = weekDays[position];
        List<String[]> day = schedule.getDayClasses(weekDay.toLowerCase());

        txt_schedule.append("");
        txt_schedule_content.setText("");

        txt_schedule.setText("Looking at: " + weekDay + "\n");

        if (day.size() < 1){
            txt_schedule.append("There are no classes today. \n");
            txt_schedule_content.setText("");
            return;
        }

        for (int i = 0; i < day.size(); i++) {
            txt_schedule_content.append("Time: " + day.get(i)[0] + " Class: " + day.get(i)[1] + "\n");
        }


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent){}



}
