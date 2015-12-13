package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.*;


public class ScheduleActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Rodderscode";

    Spinner weekdaysSpinner, roomSpinner;
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] rooms = {"Room 1", "Room 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setupSpinners();
    }

    void setupSpinners(){

        // weekdays spinner
        ArrayAdapter<String> a1 = new ArrayAdapter<String>(ScheduleActivity.this, android.R.layout.simple_spinner_item , weekDays);
        weekdaysSpinner = (Spinner) findViewById(R.id.sp_weekday);
        weekdaysSpinner.setAdapter(a1);
        //default the selection to today
        weekdaysSpinner.setSelection(Calendar.DAY_OF_WEEK - 1);
        weekdaysSpinner.setOnItemSelectedListener(this);

        // rooms spinner
        ArrayAdapter<String> a2 = new ArrayAdapter<String>(ScheduleActivity.this, android.R.layout.simple_spinner_item , rooms);
        roomSpinner = (Spinner) findViewById(R.id.sp_room);
        roomSpinner.setAdapter(a2);
        roomSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        //This is how you would know them apart
        //if (parent.getId() == R.id.schedule_btn)
        showSchedule(weekdaysSpinner.getSelectedItemPosition(), roomSpinner.getSelectedItemPosition());


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void showSchedule(int weekDay, int room){
        Log.w(TAG, weekDay+" "+room);
    }

}
