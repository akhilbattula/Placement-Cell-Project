package com.akhil.placementcelladmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    int REQUEST_SMS=1;
    EditText name,city,phno,comments,college;
    Button send;
    String str,ph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.name);
        city = (EditText)findViewById(R.id.city);
        phno = (EditText)findViewById(R.id.phno);
        comments = (EditText)findViewById(R.id.comments);
        college = (EditText)findViewById(R.id.college);
        send = (Button)findViewById(R.id.send);




        ph="+919030422224";
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(name.getText().length() == 0||name.getText().length() == 0||city.getText().length() == 0||phno.getText().length()==0||comments.getText().length() == 0||phno.getText().length()<=9){

                    Toast.makeText(Feedback.this, "* marked Fields are required", Toast.LENGTH_SHORT).show();
                    if(name.getText().length()==0){
                        name.setError("Enter Your Name");
                    }
                    if(city.getText().length()==0){
                        city.setError("Enter Your City");
                    }
                    if(phno.getText().length()<=9){
                        phno.setError("Enter Your Phone No.");
                    }
                    if(comments.getText().length()==0){
                        comments.setError("Enter Your Comments");
                    }
                    if(college.getText().length()==0){
                        college.setError("Enter Your Comments");
                    }

                } else{
                    try {
                        final String sname = name.getText().toString();
                        final String scity = city.getText().toString();
                        final String sphno = phno.getText().toString();
                        final String scomments = comments.getText().toString();
                        final String scollege = college.getText().toString();
                        str = "Name: "+sname+"\nCity: "+scity+"\nCollege:"+scollege+"\nPhone No: "+sphno+"\nComments: "+scomments;
                        System.out.println(str);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(ph, null, str, null, null);
                        Toast.makeText(getApplicationContext(), "Feedback sent.",
                                Toast.LENGTH_LONG).show();
                        name.getText().clear();
                        city.getText().clear();
                        phno.getText().clear();
                        comments.getText().clear();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "Feedback failed, please try again.",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }

        });
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }

}