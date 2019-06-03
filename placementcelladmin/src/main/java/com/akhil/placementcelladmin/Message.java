package com.akhil.placementcelladmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akhil.placementcelladmin.classes.MessageClass;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Message extends AppCompatActivity {

    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    String str, ph;

    EditText name, clg, group, email, phno, query;
    Button send;

    CoordinatorLayout cl;

    private RecyclerView mnewslist;
    DatabaseReference mdatabase;
    LinearLayoutManager mLayoutManager;


    public ProgressDialog mpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mdatabase = FirebaseDatabase.getInstance().getReference("Messages");
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        mpro = new ProgressDialog(this);

        mnewslist = (RecyclerView) findViewById(R.id.message_list);

        mLayoutManager = new LinearLayoutManager(Message.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

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
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                Log.d("", ie.toString());
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
            Toast.makeText(Message.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
            //  Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //  startActivity(intent);
        }

        FirebaseRecyclerAdapter<MessageClass, NewsViewHolder> adapter = new FirebaseRecyclerAdapter<MessageClass, NewsViewHolder>(
                MessageClass.class,
                R.layout.message_row,
                NewsViewHolder.class,
                mdatabase

        ) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, final MessageClass model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setCollege(model.getCollege());


                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), post_key, Toast.LENGTH_SHORT).show();

                        Intent single_intent = new Intent(Message.this, Message_Single.class);
                        single_intent.putExtra("message_id", post_key);
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

        public void setName(String title) {

            TextView post_t = (TextView) mview.findViewById(R.id.message_name);
            post_t.setText(title);
        }

        public void setCollege(String title) {

            TextView post_t = (TextView) mview.findViewById(R.id.message_clg);
            post_t.setText(title);
        }
    }
}


