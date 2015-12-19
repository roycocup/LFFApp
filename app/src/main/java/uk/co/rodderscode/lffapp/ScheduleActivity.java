package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;


public class ScheduleActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {


    Spinner weekdaysSpinner;
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    ScheduleModel schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setupSpinner();
        schedule = new ScheduleModel(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

        String weekDay = weekDays[position];
        List<String[]> day = schedule.getDayClasses(weekDay.toLowerCase());

        ListView listView = (ListView) findViewById(R.id.schedule_list_view);
        ScheduleAdapter adapter = new ScheduleAdapter(this, -1, day);
        listView.setAdapter(adapter);

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent){}

    void setupSpinner(){
        // weekdays spinner
        ArrayAdapter<String> a1 = new ArrayAdapter<>(
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


}
