package uk.co.rodderscode.lffapp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * Created by rodrigodias on 19/12/2015.
 */
public class ScheduleAdapter extends ArrayAdapter<String> {

    List<String[]> values;
    Context context;

    public ScheduleAdapter(Context context, int resource, List values)  {
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
        View rowView = inflater.inflate(R.layout.schedule_row, parent, false);

        TextView times = (TextView) rowView.findViewById(R.id.sch_row_time);
        TextView discipline = (TextView) rowView.findViewById(R.id.sch_row_discipline);

        times.setText(values.get(position)[0]);
        discipline.setText(values.get(position)[1]);


        /*
        // Image stuff
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        if (s.startsWith("iPhone"))
            imageView.setImageResource(R.drawable.no);
        else
            imageView.setImageResource(R.drawable.ok);
        */
        return rowView;
    }
}
