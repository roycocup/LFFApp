package uk.co.rodderscode.lffapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;


public class MainActivity extends Activity {

    public static final String TAG = "Rodderscode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: this should just be one call with similar functionlity
        setupExitButton();
        setupNewsButton();
        setupScheduleButton();
    }

    private void setupExitButton() {
        Button btn_exit = (Button) findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }


    private void setupScheduleButton() {
        Button btn_schedule = (Button) findViewById(R.id.schedule_btn);
        btn_schedule.setOnClickListener(
                new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
                }
            }
        );
    }

    private void setupNewsButton(){
        Button btn_news = (Button) findViewById(R.id.news_btn);
        btn_news.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this, NewsActivity.class));
                    }
                }
        );
    }
}
