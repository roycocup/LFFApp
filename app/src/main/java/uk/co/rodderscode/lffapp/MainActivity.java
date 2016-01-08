package uk.co.rodderscode.lffapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends Activity {

    // TODO: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = " 1TSWX9xQ6fkzCivMeqcklJvL2";
    private static final String TWITTER_SECRET = "Gnqh9CTW0s8K8ZGTBWS9jwEPvBIWUKizblLhgUlARDByqMG6P0";


    public static final String TAG = "Rodderscode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        final UserTimeline userTimeline = new UserTimeline.Builder().screenName("lff4ever").build();
        final TweetTimelineListAdapter twitterAdapter = new TweetTimelineListAdapter.Builder(this).setTimeline(userTimeline).build();

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
