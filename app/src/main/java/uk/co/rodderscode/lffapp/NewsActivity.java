package uk.co.rodderscode.lffapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

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
    }

}
