package com.akhil.placementcell.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akhil.placementcell.R;
import com.akhil.placementcell.classes.Upcoming;

import java.util.ArrayList;

/**
 * Created by Battula's on 9/27/2016.
 */
public class UpcomingDetailAdapter extends BaseAdapter {
    private ArrayList<Upcoming> arrayListPerson;
    private Context context;
    private LayoutInflater inflater;

    public UpcomingDetailAdapter(Context context, ArrayList<Upcoming> arrayListPerson) {
        this.context = context;
        this.arrayListPerson = arrayListPerson;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayListPerson.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListPerson.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder holder;
        if (v == null) {
            v = inflater.inflate(R.layout.list_person, null);
            holder = new Holder();
            holder.title = (TextView) v.findViewById(R.id.title);
            holder.date = (TextView) v.findViewById(R.id.date);
            holder.place = (TextView) v.findViewById(R.id.place);

            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }

        holder.title.setText(arrayListPerson.get(position).getTitle());
        holder.date.setText(String.valueOf(arrayListPerson.get(position).getDod()));
        holder.place.setText(arrayListPerson.get(position).getPlace());

        return v;
    }

    class Holder {
        TextView title,date,place;
    }


}