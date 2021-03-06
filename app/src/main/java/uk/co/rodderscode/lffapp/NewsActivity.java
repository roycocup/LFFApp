package uk.co.rodderscode.lffapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


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
        newsTxt.setText("Please wait...");

        if (isNetworkAvailable())
            updateNewsField();
        else
            newsTxt.setText("No internet connection."); // TODO: Show cached file


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void updateNewsField() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                final String rawData = fetchWeb();
                // save it
                mkCache(rawData);
                JSONArray json;
                ArrayList list = new ArrayList();

                try {
                    json = new JSONArray(rawData);
                    for(int i = 0; i < json.length(); i++){
                        list.add(json.get(i).toString());
                    }
                } catch (JSONException e) {
                    Log.e(MainActivity.TAG, e.getMessage());
                }

                final ListAdapter listAdapter = new NewsAdapter(getApplicationContext(), -1, list);
                listView.post(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(listAdapter);
                        newsTxt.setText("");
                    }
                });

            }
        };
        new Thread(r).start();
    }

    private boolean mkCache(String rawData) {
        String filename = "cacheFile.txt";
        if (new File(filename).exists()) {
            Log.d(MainActivity.TAG, "File already exists");
        }
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(rawData.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String fetchWeb() {

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

            // Making a fake counter with the progress bar
            // TODO: make progress bar disappear when results arrive and thread dies.
            for (int i = 0; i <= 100; i++) {
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




}
