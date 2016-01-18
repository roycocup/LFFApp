package uk.co.rodderscode.lffapp;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;



public class NewsAdapter extends ArrayAdapter<String> {

    List<String[]> values;
    Context context;

    public NewsAdapter(Context context, int resource, List values)  {
        super(context, resource, values);
        this.values = values;
        this.context = context;
    }

    @Override
    public int getCount() {
        return values.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.news_row, parent, false);

        TextView text = (TextView) rowView.findViewById(R.id.news_row_text);
        ImageView image = (ImageView) rowView.findViewById(R.id.news_row_image);

//        Log.d( MainActivity.TAG, String.valueOf(values.get(position)));

        try {
            JSONObject json = new JSONObject(String.valueOf(values.get(position)));
            text.setText(json.getString("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        image.setImageResource(R.drawable.);

        return rowView;
    }
}
