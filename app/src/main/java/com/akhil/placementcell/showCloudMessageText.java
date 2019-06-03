package com.akhil.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class showCloudMessageText extends AppCompatActivity {


    public String ReceivedMessage="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_show_cloud_message_text);

        Intent intent = getIntent();
        ReceivedMessage=intent.getStringExtra("WholeMessage");

       final String msg = ReceivedMessage;
        TextView textView1=(TextView)findViewById(R.id.t2);
        textView1.setText(msg);
     //   TextView textView=(TextView)findViewById(R.id.setText);
     //   String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
     //   textView.setText(date);
        }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }
}
