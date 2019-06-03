package com.akhil.placementcelladmin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akhil.placementcelladmin.classes.Activities;
import com.akhil.placementcelladmin.classes.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class RecentActivities extends AppCompatActivity {


    private RecyclerView mnewslist;
    DatabaseReference mdatabase;
    LinearLayoutManager mLayoutManager;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
   public ProgressDialog mpro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_activities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("Activity");

        mpro = new ProgressDialog(this);

        mnewslist = (RecyclerView) findViewById(R.id.news_list);

        mLayoutManager = new LinearLayoutManager(RecentActivities.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mpro.setMessage("Loading...");

        mnewslist.setHasFixedSize(true);
        mnewslist.setLayoutManager(mLayoutManager);

        new Wait().execute();
    }

    private class Wait extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mpro.setMessage("Loading...");
            mpro.setCancelable(true);
            mpro.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(7000);
            }
            catch (InterruptedException ie) {
                Log.d("",ie.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            mpro.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            // Internet Connection is not present
            Toast.makeText(RecentActivities.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
            //  Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //  startActivity(intent);
        }

        Query snoQuery = mdatabase.orderByChild("sno");

        FirebaseRecyclerAdapter<Activities, NewsViewHolder> adapter = new FirebaseRecyclerAdapter<Activities, NewsViewHolder>(
                Activities.class,
                R.layout.news_row,
                NewsViewHolder.class,
                snoQuery
        ) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, final Activities model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(getApplicationContext(), post_key, Toast.LENGTH_SHORT).show();

                        Intent single_intent = new Intent(RecentActivities.this, Activities_Single.class);
                        single_intent.putExtra("post_id", post_key);
                        startActivity(single_intent);
                    }
                });

            }
        };

        mnewslist.setAdapter(adapter);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        View mview;

        public NewsViewHolder(View itemView) {
            super(itemView);

            mview = itemView;
        }

        public void setTitle(String title) {

            TextView post_t = (TextView) mview.findViewById(R.id.post_title);
            post_t.setText(title);
        }
        /*public void setDate(String date){

            TextView post_t = (TextView) mview.findViewById(R.id.post_date);
            post_t.setText(date);
        }*/

        public void setImage(Context cxt, String image) {

            ImageView post_i = (ImageView) mview.findViewById(R.id.post_image);

            Picasso.get().load(image).into(post_i);
            //Picasso.with(cxt).load(image).into(post_i);

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nss_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            cd = new ConnectionDetector(getApplicationContext());
            isInternetPresent = cd.isConnectingToInternet();
            if (!isInternetPresent) {
                // Internet Connection is not present
                Toast.makeText(RecentActivities.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
                //  Intent intent = new Intent(Settings.ACTION_SETTINGS);
                //  startActivity(intent);
            }
            startActivity(new Intent(RecentActivities.this, PostActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}


