package uk.co.rodderscode.lffapp;


import android.content.res.AssetManager;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;


import android.content.Context;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


public class Schedule {

    public XmlPullParser parser;
    public DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
    public DocumentBuilder db;
    public Document d;
    public XPath xp = XPathFactory.newInstance().newXPath();;
    public NodeList nl;
    public Context context;

    public Schedule(Context context){
        this.context = context;
        load();
    }


    public void load() {
        try{
            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource is = new InputSource(context.getResources().openRawResource(R.raw.schedules));
            NodeList nodes = (NodeList) xpath.evaluate("/root", is, XPathConstants.NODESET);
            for(int i = 0; i < nodes.getLength(); i++)
                Log.d(MainActivity.TAG, nodes.item(i).toString());
        } catch (Exception e) {
            Log.e(MainActivity.TAG, e.getMessage());
        }
    }

    /*
    private void read(){
        try {
            parser = context.getResources().getXml(R.xml.schedules);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(MainActivity.TAG, "Start document");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.i(MainActivity.TAG, "Start tag " + parser.getName());
                        break;
                    case XmlPullParser.END_TAG:
                        Log.i(MainActivity.TAG, "End tag " + parser.getName());
                        break;
                    case XmlPullParser.TEXT:
                        Log.i(MainActivity.TAG, "Text " + parser.getText());
                        break;
                }
                eventType = parser.next();
            }
        }catch(Exception e) {
            Log.i(MainActivity.TAG, "failing with: " + e.getMessage());
        }
    }
    */


}

