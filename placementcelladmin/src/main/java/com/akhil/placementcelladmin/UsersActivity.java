package com.akhil.placementcelladmin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akhil.placementcelladmin.classes.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView mnewslist;
    DatabaseReference mdatabase;
    LinearLayoutManager mLayoutManager;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    public ProgressDialog mpro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mpro = new ProgressDialog(this);

        mnewslist = (RecyclerView) findViewById(R.id.user_list);

        mLayoutManager = new LinearLayoutManager(UsersActivity.this);
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
            Toast.makeText(UsersActivity.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
            //  Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //  startActivity(intent);
        }

        FirebaseRecyclerAdapter<Users, NewsViewHolder> adapter = new FirebaseRecyclerAdapter<Users, NewsViewHolder>(
                Users.class,
                R.layout.userlist,
                NewsViewHolder.class,
                mdatabase

        ) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, Users model, int position) {
                //final String post_key = getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setGroup(model.getGroup());
                viewHolder.setCollege(model.getCollege());
                viewHolder.setPhnNo(model.getPhno());
                viewHolder.setRollNo(model.getRollno());

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

            TextView post_t = (TextView) mview.findViewById(R.id.user_name);
            post_t.setText(title);
        }
        /*public void setDate(String date){

            TextView post_t = (TextView) mview.findViewById(R.id.post_date);
            post_t.setText(date);
        }*/

        public void setGroup(String group) {

            TextView post_t = (TextView) mview.findViewById(R.id.user_group);
            post_t.setText(group);
        }
        public void setCollege(String college) {

            TextView post_t = (TextView) mview.findViewById(R.id.user_college);
            post_t.setText(college);
        } public void setRollNo(String rollno) {

            TextView post_t = (TextView) mview.findViewById(R.id.user_rollno);
            post_t.setText(rollno);
        } public void setPhnNo(String phno) {

            TextView post_t = (TextView) mview.findViewById(R.id.user_phno);
            post_t.setText(phno);
        }

    }


}