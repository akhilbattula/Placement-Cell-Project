package com.akhil.placementcell;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Aptitude extends AppCompatActivity {
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    Button link1,link2,link3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        link1 = (Button)findViewById(R.id.link1);
        link2 = (Button)findViewById(R.id.link2);
        link3 = (Button)findViewById(R.id.link3);


        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(Aptitude.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }else {
                   Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.indiabix.com/"));
                    startActivity(intent);
                }
            }
        });

        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(Aptitude.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.careercup.com/"));
                    startActivity(intent);
                }
            }
        });

        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(Aptitude.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://openseesame.tcs.com/ignite/"));
                    startActivity(intent);
                }
            }
        });


    }

}
