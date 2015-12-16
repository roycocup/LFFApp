package uk.co.rodderscode.lffapp;

import android.util.Log;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


public class Schedule {

    public XPath xp = XPathFactory.newInstance().newXPath();;
    public InputSource is;
    public Context context;
    public NodeList nodeList;

    public Schedule(Context context){
        this.context = context;
        is = new InputSource(context.getResources().openRawResource(R.raw.schedules));
        nodeList = search("/root/item");
    }

    // get an ordered list of classes by week day
    public List<String[]> getDayClasses(String dow){
        List<String[]> l = new ArrayList();
        for(int i = 0; i < nodeList.getLength(); i++) {
            NamedNodeMap nodeMap = nodeList.item(i).getAttributes();
            //if the node attribute dow is whatever we ask for...
            if (nodeMap.getNamedItem("dow").getNodeValue().equals(dow)) {
                String[] r = new String[2];
                r[0] = nodeMap.getNamedItem("time").getNodeValue().toString();
                r[1] = nodeMap.getNamedItem("discipline").getNodeValue().toString();
                l.add(r);
            }
        }

        return l;
    }


    private NodeList search(String expression) {
        NodeList nodes = null;
        try {
            nodes = (NodeList) xp.evaluate(expression, is, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            Log.w(MainActivity.TAG, e.getMessage());
        }
        return nodes;
    }


}

