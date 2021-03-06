package uk.co.rodderscode.lffapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



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


    @Override
    protected void onResume() {
        super.onResume();
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
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        boolean shortCutWasAlreadyAdded = sharedPreferences.getBoolean("PREF_KEY_SHORTCUT_ADDED", false);
        if (shortCutWasAlreadyAdded) return;

        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "London Fight Factory");
        addIntent.putExtra(
                Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher)
        );
        addIntent.putExtra("duplicate", false);
        getApplicationContext().sendBroadcast(addIntent);

        // Remembering that ShortCut was already added
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("PREF_KEY_SHORTCUT_ADDED", true);
        editor.commit();
    }
}
