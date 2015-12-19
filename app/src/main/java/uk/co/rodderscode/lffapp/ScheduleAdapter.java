package uk.co.rodderscode.lffapp;



import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by rodrigodias on 19/12/2015.
 */
public class ScheduleAdapter extends BaseAdapter {

    List<String[]> day;

    public ScheduleAdapter(List<String[]> day) {
        this.day = day;
    }

    @Override
    public int getCount() {
        return day.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
