package uk.co.rodderscode.lffapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewsActivity extends ActionBarActivity {

    private TextView newsTxt;
    private ListView listView;
    private ProgressBar progress;
    private String twitterApiUrl = "http://v2.lffcup.co.uk/twitterTimeline";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsTxt = (TextView) findViewById(R.id.txt_news);
        progress = (ProgressBar) findViewById(R.id.progressBar_news);
        listView = (ListView) findViewById(R.id.listview_news);

        setupNews();
    }


    public void setupNews() {
        newsTxt.setText("There are no news yet");
        updateNewsField();
    }


    void updateNewsField() {
        newsTxt.setText("Waiting ... ");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                final String html = fetchWeb();
                // Handler post
                newsTxt.post(new Runnable() {
                    @Override
                    public void run() {
                        newsTxt.setText(html);
                    }
                });
            }
        };

        new Thread(r).start();
    }


    String fetchWeb() {
        String finalS = null;
        try {
            URL url = new URL(twitterApiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            StringBuilder sb = new StringBuilder("");

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }


            for (int i = 0; i <= 100; i++) {
                Thread.sleep(25);
                final int finalValue = i;
                progress.post(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(finalValue);
                    }
                });
            }

            finalS = sb.toString();


        } catch (Exception e) {
            Thread.currentThread().interrupt();
            Log.e(MainActivity.TAG, e.toString());
        }

        return finalS;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
