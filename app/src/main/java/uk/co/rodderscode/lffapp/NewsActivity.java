package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewsActivity extends ActionBarActivity {

    TextView newsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsTxt = (TextView) findViewById(R.id.txt_news);
        setupNews();

    }

    // TODO: Since realising that the facebook SDK only works for Android sdk 15 (Kit Kat!)
    // I decided to move the calls into a 3rd party restful server where I can get the latest xml update
    public void setupNews() {
        newsTxt.setText("There are no news yet");
//        testVolley();
        testVanilla();
    }

    void testVolley(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        newsTxt.setText("Response is: "+ response.substring(0,500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        newsTxt.setText("That didn't work!");
                    }
                }
        );
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    void testVanilla(){

            newsTxt.setText("Waiting ... ");

            StringBuilder s = new StringBuilder("");

            // start new thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://rodderscode.co.uk");
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

                        // update the main text on the UIThread
                        // as we cannot update views from a subthread
                        final String s = sb.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                newsTxt.setText(s);
                            }
                        });

                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                        Log.e(MainActivity.TAG, e.toString());
                    }
                }
            }).start();

    }


}
