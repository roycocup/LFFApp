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

        //TODO: this should just be one call with similar functionality
        setupExitButton();
        setupNewsButton();
        setupScheduleButton();
        addShortcut();
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

    private void addShortcut() {
        Intent shortcutIntent = new Intent(getApplicationContext(),
                MainActivity.class);

        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "HelloWorldShortcut");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                        R.mipmap.ic_launcher));

        addIntent
                .setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }
}
