package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;


public class ScheduleActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {


    Spinner weekdaysSpinner;
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    //TODO: Thinkimg of a good way to hold the whole schedule here.
    HashMap<String, String[]> schedule = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setupSpinner();
        Schedule s = new Schedule(this);
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
        txt_schedule.setText("Looking at: " + weekDay);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent){}



}
