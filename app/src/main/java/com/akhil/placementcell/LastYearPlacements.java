package com.akhil.placementcell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LastYearPlacements extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_year_placements);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("I L M");
        listDataHeader.add("Aditya Birla Fashion and Retail Limited");
        listDataHeader.add("AIRCEL");
        listDataHeader.add("TCS IT IS");
        listDataHeader.add("TCS BPO");
        listDataHeader.add("TCS IGNITE");
        listDataHeader.add("INFOSYS");
        listDataHeader.add("L & T");
        listDataHeader.add("GENPACT");
        listDataHeader.add("SUTHERLAND GLOBAL SERVICES");
        listDataHeader.add("EENADU");
        listDataHeader.add("TECH MAHINDRA");
        listDataHeader.add("IPROPAT");
        listDataHeader.add("LAURUS");
        listDataHeader.add("AVONTIX");


        String[] ilmstr = getResources().getStringArray(R.array.ilm);
        String[] adityastr = getResources().getStringArray(R.array.aditya);
        String[] aircelstr = getResources().getStringArray(R.array.aircel);
        String[] tcs_itstr = getResources().getStringArray(R.array.tcs_it);
        String[] tcs_bpostr = getResources().getStringArray(R.array.tcs_bpo);
        String[] tcs_ignitestr = getResources().getStringArray(R.array.tcs_ignite);
        String[] infosysstr = getResources().getStringArray(R.array.infosys);
        String[] ltstr = getResources().getStringArray(R.array.lt);
        String[] genpactstr = getResources().getStringArray(R.array.genpact);
        String[] suthernlandstr = getResources().getStringArray(R.array.suthernland);
        String[] eenadustr = getResources().getStringArray(R.array.eenadu);
        String[] techmahindrastr = getResources().getStringArray(R.array.techmahindra);
        String[] ipropatstr = getResources().getStringArray(R.array.ipropat);
        String[] laurusstr = getResources().getStringArray(R.array.laurus);
        String[] avontixstr = getResources().getStringArray(R.array.avontix);

        // Adding child data
        List<String> ilm = new ArrayList<String>(Arrays.asList(ilmstr));
        List<String> aditya = new ArrayList<String>(Arrays.asList(adityastr));
        List<String> aircel = new ArrayList<String>(Arrays.asList(aircelstr));
        List<String> tcs_it = new ArrayList<String>(Arrays.asList(tcs_itstr));
        List<String> tcs_bpo = new ArrayList<String>(Arrays.asList(tcs_bpostr));
        List<String> tcs_ignite = new ArrayList<String>(Arrays.asList(tcs_ignitestr));
        List<String> infosys = new ArrayList<String>(Arrays.asList(infosysstr));
        List<String> lt = new ArrayList<String>(Arrays.asList(ltstr));
        List<String> genpact = new ArrayList<String>(Arrays.asList(genpactstr));
        List<String> suthernland = new ArrayList<String>(Arrays.asList(suthernlandstr));
        List<String> eenadu = new ArrayList<String>(Arrays.asList(eenadustr));
        List<String> techmahindra = new ArrayList<String>(Arrays.asList(techmahindrastr));
        List<String> ipropat = new ArrayList<String>(Arrays.asList(ipropatstr));
        List<String> laurus = new ArrayList<String>(Arrays.asList(laurusstr));
        List<String> avontix = new ArrayList<String>(Arrays.asList(avontixstr));





        listDataChild.put(listDataHeader.get(0), ilm); // Header, Child data
        listDataChild.put(listDataHeader.get(1), aditya);
        listDataChild.put(listDataHeader.get(2), aircel);
        listDataChild.put(listDataHeader.get(3), tcs_it);
        listDataChild.put(listDataHeader.get(4), tcs_bpo);
        listDataChild.put(listDataHeader.get(5), tcs_ignite);
        listDataChild.put(listDataHeader.get(6), infosys);
        listDataChild.put(listDataHeader.get(7), lt);
        listDataChild.put(listDataHeader.get(8), genpact);
        listDataChild.put(listDataHeader.get(9), suthernland);
        listDataChild.put(listDataHeader.get(10), eenadu);
        listDataChild.put(listDataHeader.get(11), techmahindra);
        listDataChild.put(listDataHeader.get(12), ipropat);
        listDataChild.put(listDataHeader.get(13), laurus);
        listDataChild.put(listDataHeader.get(14), avontix);
    }
}