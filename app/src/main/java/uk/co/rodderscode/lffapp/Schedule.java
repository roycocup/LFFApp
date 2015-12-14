package uk.co.rodderscode.lffapp;


import android.content.res.AssetManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.content.Context;

public class Schedule {

    public XmlPullParser parser;

    public Schedule(Context context){
        parser = context.getResources().getXml(R.xml.schedules);
//        Log.i(MainActivity.TAG, parser.getText());
        read();
    }

    private void read(){
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                    Log.i(MainActivity.TAG, "Start document");
                } else if(eventType == XmlPullParser.START_TAG) {
                    Log.i(MainActivity.TAG, "Start tag " + parser.getName());
                } else if(eventType == XmlPullParser.END_TAG) {
                    Log.i(MainActivity.TAG, "End tag " + parser.getName());
                } else if(eventType == XmlPullParser.TEXT) {
                    Log.i(MainActivity.TAG, "Text " + parser.getText());
                }
                eventType = parser.next();
            }
        }catch(Exception e) {
            Log.i(MainActivity.TAG, "failing with: " + e.getMessage());
        }
    }


}

