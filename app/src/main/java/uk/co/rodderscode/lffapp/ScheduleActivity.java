package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ScheduleActivity extends ActionBarActivity {

    Spinner spinner;
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScheduleActivity.this, android.R.layout.simple_spinner_item , weekDays);
        spinner = (Spinner) findViewById(R.id.sp_schedule);
        spinner.setAdapter(adapter);
    }

}
