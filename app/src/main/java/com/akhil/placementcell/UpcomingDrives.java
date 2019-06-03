package com.akhil.placementcell;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akhil.placementcell.classes.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UpcomingDrives extends AppCompatActivity {

    private RecyclerView mnewslist;
    DatabaseReference mdatabase;
    LinearLayoutManager mLayoutManager;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    public ProgressDialog mpro;
    static final String TAG = "Upcoming Drives";

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_drives);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mdatabase = FirebaseDatabase.getInstance().getReference("Campus Drives");





        mpro = new ProgressDialog(this);

        mnewslist = (RecyclerView) findViewById(R.id.place_list);

        mLayoutManager = new LinearLayoutManager(UpcomingDrives.this);
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
            Toast.makeText(UpcomingDrives.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
            //  Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //  startActivity(intent);
        }

        FirebaseRecyclerAdapter<News, NewsViewHolder> adapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(
                News.class,
                R.layout.news_row,
                UpcomingDrives.NewsViewHolder.class,
                mdatabase

        ) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, News model, int position) {
                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(getApplicationContext(), model.getImage());


                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), post_key, Toast.LENGTH_SHORT).show();

                        Intent single_intent = new Intent(UpcomingDrives.this, UpcomingDetailActivity.class);
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


        }

    }



}


