package com.akhil.placementcelladmin.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhil.placementcelladmin.R;
import com.akhil.placementcelladmin.AddOrUpdateUpcoming;
import com.akhil.placementcelladmin.classes.Upcoming;

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
            holder.EditPerson = (ImageView) v.findViewById(R.id.EditPerson);
            holder.DeletePerson = (ImageView) v.findViewById(R.id.DeletePerson);
            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }

        holder.title.setText(arrayListPerson.get(position).getTitle());
        holder.date.setText(String.valueOf(arrayListPerson.get(position).getDod()));
        holder.place.setText(arrayListPerson.get(position).getPlace());
        holder.EditPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddOrUpdateUpcoming.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Position", position);
                context.getApplicationContext().startActivity(intent);
            }
        });
        holder.DeletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowConfirmDialog(context, position);
            }
        });
        return v;
    }

    class Holder {
        TextView title,date,place;
        ImageView DeletePerson, EditPerson;
    }

    public static void ShowConfirmDialog(Context context, final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder
                .setMessage("Are you sure you want to delete this entry?")
                .setCancelable(true)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                //        UpcomingDrives.getInstance().deletePerson(position);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}