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


    public void setupNews() {
        newsTxt.setText("");
    }

}
