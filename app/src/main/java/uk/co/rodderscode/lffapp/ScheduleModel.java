package uk.co.rodderscode.lffapp;

import android.util.Log;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


public class ScheduleModel {


    public XPath xp = XPathFactory.newInstance().newXPath();;
    public InputSource is;
    public Context context;
    public NodeList nodeList;

    public ScheduleModel(Context context){
        this.context = context;
        is = new InputSource(context.getResources().openRawResource(R.raw.schedules));
        nodeList = search("/root/item");
    }

    /**
     * Get an ordered list of classes by week day.
     * @param dow
     * @return {List<String[]>} a list of arrays of strings ([0]17:30, [1]bjj)
     */
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

    /**
     * This makes the search through the xml document that we are using for a "database"
     * Should the query needed to be a little more complex either than just asking for "daily classes by day"
     * we should have to use a database or some other persistence method.
     * It returns a
     * @param {String} the string for an xpath where we get the first item, ie."/root/item"
     * @return {NodeList} a DOM list of Nodes that we need to then iterate.
     */
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

