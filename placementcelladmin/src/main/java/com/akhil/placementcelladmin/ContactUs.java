package com.akhil.placementcelladmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class ContactUs extends AppCompatActivity {

    ImageButton facebook,gmail,facebook1,gmail1,facebook3,gmail3;

    ImageButton directions;

    final Double latitude =16.361337;
    final Double longitude=80.839907;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        facebook = (ImageButton)findViewById(R.id.fb);
        gmail = (ImageButton)findViewById(R.id.gmail);

        facebook3 = (ImageButton)findViewById(R.id.fb3);
        gmail3 = (ImageButton)findViewById(R.id.gmail3);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(ContactUs.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/akhilian"));
                startActivity(intent);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(ContactUs.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent("android.intent.action.SEND");
                i.putExtra("android.intent.extra.EMAIL", new String[] {
                        "akhilprojects1997@gmail.com"
                });
                i.putExtra("android.intent.extra.SUBJECT", "");
                i.putExtra("android.intent.extra.TEXT", "");
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i, "Choose  email client"));
            }
        });

       facebook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(ContactUs.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/sridharkavuri"));
                startActivity(intent);
            }
        });

        gmail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(ContactUs.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent("android.intent.action.SEND");
                i.putExtra("android.intent.extra.EMAIL", new String[] {
                        "sridharkavuri@yahoo.com"
                });
                i.putExtra("android.intent.extra.SUBJECT", "");
                i.putExtra("android.intent.extra.TEXT", "");
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i, "Choose  email client"));
            }
        });

        directions = (ImageButton)findViewById(R.id.btn_directions);

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%s,%s",latitude,longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }


}
