package com.akhil.placementcelladmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class PostActivity extends AppCompatActivity {

   private EditText postTitle,postContent,postSno,postDate;
   private ImageButton imgbutton;
    private Button submit_data;
    Context contex;
    private Uri selectedImageUri = null;
    StorageReference storage;
    DatabaseReference mdatabase;
    ProgressDialog mprogess;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;


    private static final int GALLERY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storage = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Activity");

        postSno = (EditText)findViewById(R.id.postsno);
        postTitle = (EditText)findViewById(R.id.posttitle);
        postContent = (EditText)findViewById(R.id.post_content);
        postDate = (EditText)findViewById(R.id.post_date);
        submit_data = (Button)findViewById(R.id.submit);


        mprogess = new ProgressDialog(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgbutton = (ImageButton)findViewById(R.id.postimage);

        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallertIntent = new Intent(Intent.ACTION_GET_CONTENT);
                gallertIntent.setType("image/*");
                startActivityForResult(gallertIntent,GALLERY_REQUEST);
            }
        });

        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd = new ConnectionDetector(getApplicationContext());
                isInternetPresent = cd.isConnectingToInternet();
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(PostActivity.this, "Please Check Your Internet Connection.",Toast.LENGTH_LONG).show();
                    //  Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    //  startActivity(intent);
                }
                StartPosting();
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       /* if(requestCode == GALLERY_REQUEST && requestCode == RESULT_OK){
            imageuri = data.getData();
            imgbutton.setImageURI(imageuri);*/
            if (resultCode == RESULT_OK)
            {
                if (requestCode == GALLERY_REQUEST)
                {
                    selectedImageUri = data.getData();
                    //imageuri = getPath(selectedImageUri);
                    imgbutton.setImageURI(selectedImageUri);
                }
        }
    }

   private void StartPosting() {

       mprogess.setMessage("Posting ...");

       final String sno_val = postSno.getText().toString().trim();
       final String title_val = postTitle.getText().toString().trim();
       final String content_val = postContent.getText().toString().trim();
       final String date_val = postDate.getText().toString().trim();

       if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(content_val) && !TextUtils.isEmpty(sno_val) && !TextUtils.isEmpty(date_val)) {
           mprogess.show();
           final StorageReference filepath = storage.child("image").child(random());
           Calendar c = Calendar.getInstance();
           SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm", Locale.US);
           final String strDate = sdf.format(c.getTime());

           if (selectedImageUri != null) {
               //displaying a progress dialog while upload is going on
               final ProgressDialog progressDialog = new ProgressDialog(this);
               progressDialog.setTitle("Uploading");
               progressDialog.show();
               final Uri[] downloaduri = new Uri[1];
               filepath.putFile(selectedImageUri)
                       .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                           @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               //if the upload is successfull
                               //hiding the progress dialog
                               filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                       downloaduri[0] = uri;
                                       Toast.makeText(getBaseContext(), "Upload success! URL - " + downloaduri[0].toString() , Toast.LENGTH_SHORT).show();
                                       DatabaseReference newPost = mdatabase.push();
                                       newPost.child("sno").setValue(Integer.parseInt(sno_val));
                                       newPost.child("title").setValue(title_val);
                                       newPost.child("content").setValue(content_val);
                                       newPost.child("image").setValue(downloaduri[0].toString());
                                       newPost.child("date_post").setValue(date_val);
                                       newPost.child("date").setValue(strDate);

                                       mprogess.dismiss();
                                        finish();
                                       //startActivity(new Intent(PostNewsActivity.this, RecentDrives.class));
                                       Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                                   }
                               });

                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception exception) {
                               //if the upload is not successfull
                               //hiding the progress dialog
                               progressDialog.dismiss();

                               //and displaying error message
                               Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                           }
                       })
                       .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                           @Override
                           public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                               //calculating progress percentage
                               double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                               //displaying percentage in progress dialog
                               progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                           }
                       });
           }

       }
   }


    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(7);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
