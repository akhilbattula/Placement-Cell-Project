package com.akhil.placementcelladmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Message_Single extends AppCompatActivity {

    TextView m_name,m_college,m_group,m_email,m_phno,m_query;
    DatabaseReference mDatabase;
    String post_key;

    Button removepost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message__single);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("Messages");

        m_name = (TextView)findViewById(R.id.tv_name);
        m_college = (TextView)findViewById(R.id.tv_college);
        m_group = (TextView)findViewById(R.id.tv_group);
        m_email = (TextView)findViewById(R.id.tv_email);
        m_phno = (TextView)findViewById(R.id.tv_phno);
        m_query = (TextView)findViewById(R.id.tv_query);
        removepost = (Button)findViewById(R.id.b_remove);
        post_key = getIntent().getExtras().getString("message_id");
        Toast.makeText(getApplicationContext(), post_key, Toast.LENGTH_SHORT).show();

        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              try{

                String name = dataSnapshot.child("name").getValue().toString();
                String college = dataSnapshot.child("college").getValue().toString();
                String group = dataSnapshot.child("group").getValue().toString();
                String email = dataSnapshot.child("phno").getValue().toString();
                String phno = dataSnapshot.child("email").getValue().toString();
                String query = dataSnapshot.child("query").getValue().toString();

                m_name.setText(name);
                m_college.setText(college);
                m_group.setText(group);
                m_email.setText(email);
                m_phno.setText(phno);
                m_query.setText(query);

            }catch (NullPointerException e) {
                e.printStackTrace();
            }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        removepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(post_key).removeValue();
                finish();
//                startActivity(new Intent(Message_Single.this,Message.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}



