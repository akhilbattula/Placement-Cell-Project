package com.akhil.placementcell;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akhil.placementcell.classes.MessageClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Message extends AppCompatActivity {

    ConnectionDetector cd;
    Boolean isInternetPresent = false;

    EditText name,clg,group,email,phno,query;
    Button send;
    static final String TAG = "Upcoming Drives";
    private DatabaseReference mDatabase;
    private FloatingActionButton fab;
    CoordinatorLayout cl;
    MessageClass person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cl = (CoordinatorLayout) findViewById(R.id.cdlayout);
        name = (EditText)findViewById(R.id.name);
        clg = (EditText)findViewById(R.id.college);
        group = (EditText)findViewById(R.id.group);
        email = (EditText)findViewById(R.id.email);
        phno = (EditText)findViewById(R.id.phno);
        query = (EditText)findViewById(R.id.query);

        send = (Button) findViewById(R.id.send);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("Messages");
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equals("") || group.getText().toString().trim().equals("") ||
                        clg.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") ||
                        phno.getText().toString().length()<=9 ||  phno.getText().toString().trim().equals("") ||
                        query.getText().toString().trim().equals("")) {
                    final Snackbar snackBar = Snackbar.make(cl, "Please enter all the fields.", Snackbar.LENGTH_LONG);
                    snackBar.setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackBar.dismiss();
                        }
                    });
                    snackBar.show();
                }

                else
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(Message.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }
                    else {
                    Log.d("akhill","enters....");
                    MessageClass p = new MessageClass();
                    p.setName(name.getText().toString());
                    p.setCollege(clg.getText().toString());
                    p.setGroup(group.getText().toString());
                    p.setEmail(email.getText().toString());
                    p.setPhno(phno.getText().toString());
                    p.setQuery(query.getText().toString());
                    String key = mDatabase.child("Messages").push().getKey();
                    Map<String, Object> postValues = p.toMap();
                    Log.d("akhill","enters....");
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(key, postValues);
                    mDatabase.updateChildren(childUpdates);
                    Log.d("akhill","enters....");
                    Toast.makeText(Message.this, "Success...", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Feedback sent.",
                                Toast.LENGTH_LONG).show();

                    name.setText("");
                    clg.setText("");
                    group.setText("");
                    email.setText("");
                    phno.setText("");
                    query.setText("");
                }

                }

        });

    }

}
