package com.example.rendongliu.brightomdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rendongliu.brightomdb.R;
import com.example.rendongliu.brightomdb.dao.MovieData;

/**
 * Created by rendong.liu on 03/09/15.
 */
public class MovieListAdapter extends BaseAdapter {

    protected MovieData data;
    protected Context context;

    public MovieListAdapter(MovieData data, Context context) {
        this.data = data;
        this.context= context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = layoutInflater.inflate(R.layout.list_row,null);
        }
        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView value = (TextView)vi.findViewById(R.id.value);
        switch (position){

            case 0:
                name.setText("Title:");
                value.setText(data.getTitle());
                break;

            case 1:
                name.setText("Year:");
                value.setText(data.getYear());
                break;

            case 2:
                name.setText("Director");
                value.setText(data.getDirector());
                break;

            case 3:
                name.setText("Language:");
                value.setText(data.getLanguage());
                break;

            case 4:
                name.setText("Type:");
                value.setText(data.getType());
                break;

        }
        return vi;
    }
}
