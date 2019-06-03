package com.akhil.placementcelladmin;

import android.os.Bundle;
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

public class Activities_Single extends AppCompatActivity {

    DatabaseReference mDatabase;
    String post_key;
    ImageView s_image;
    TextView s_title,s_content,s_date;
    Button removepost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_single);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Activity");

        s_image = (ImageView)findViewById(R.id.p_image);
        s_title = (TextView) findViewById(R.id.p_title);
        s_content = (TextView) findViewById(R.id.p_content);
        s_date = (TextView)findViewById(R.id.p_date);
        post_key = getIntent().getExtras().getString("post_id");
        removepost = (Button)findViewById(R.id.b_remove);
        Toast.makeText(getApplicationContext(), post_key, Toast.LENGTH_SHORT).show();
//        Log.d("akhilll", post_key);

        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_title = dataSnapshot.child("title").getValue().toString();
                String post_content = dataSnapshot.child("content").getValue().toString();
               String post_image = dataSnapshot.child("image").getValue().toString();
                String post_date = dataSnapshot.child("date_post").getValue().toString();

                s_title.setText(post_title);
                s_content.setText(post_content);
                s_date.setText(post_date);

                //with(News_Single.this).load(post_image).into(s_image);
                Picasso.get().load(post_image).into(s_image);
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
}
