package com.akhil.placementcelladmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.akhil.placementcelladmin.classes.Upcoming;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class AddOrUpdateUpcoming extends AppCompatActivity {

    Button bOK,bCancel;
    Upcoming person;
    int position;
    ImageButton img1,img2;
    EditText utitle,udesc;
    CoordinatorLayout cl;
    ConnectionDetector cd;
    DatabaseReference mdatabase;
    ProgressDialog mprogess;
    Boolean isInternetPresent = false;
    StorageReference storage;
    private Uri selectedImageUri1 = null;
    private Uri selectedImageUri2 = null;
    private static final int GALLERY_REQUEST = 1;
    private int result;
    ImageButton currentImageView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update_upcoming);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        position = getIntent().getIntExtra("Position", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cl = (CoordinatorLayout) findViewById(R.id.cdlayout);
        storage = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Campus Drives");
        mprogess = new ProgressDialog(this);
        utitle = (EditText) findViewById(R.id.utitle);
        udesc = (EditText) findViewById(R.id.udesc);
        img1 = (ImageButton) findViewById(R.id.img1);
        bOK = (Button) findViewById(R.id.bOk);
        bCancel = (Button) findViewById(R.id.bCancel);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallertIntent = new Intent(Intent.ACTION_GET_CONTENT);
                gallertIntent.setType("image/*");
                result=111;
                startActivityForResult(gallertIntent,GALLERY_REQUEST);
            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd = new ConnectionDetector(getApplicationContext());
                isInternetPresent = cd.isConnectingToInternet();
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(AddOrUpdateUpcoming.this, "Please Check Your Internet Connection.",Toast.LENGTH_LONG).show();
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
            if (resultCode == RESULT_OK) {
                if (requestCode == GALLERY_REQUEST) {
                    selectedImageUri1 = data.getData();
                    //imageuri = getPath(selectedImageUri);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri1);
                        img1.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   // img1.setImageURI(selectedImageUri1);
                }
            }

    }


    private void StartPosting() {

        final String title_val = utitle.getText().toString().trim();
        final String content_val = udesc.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(content_val)) {

            final StorageReference filepath = storage.child("placements").child(random());
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm", Locale.US);
            final String strDate = sdf.format(c.getTime());

            if (selectedImageUri1 != null) {
                //displaying a progress dialog while upload is going on
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading");
                progressDialog.show();
                final Uri[] downloaduri = new Uri[1];

                filepath.putFile(selectedImageUri1)
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
                                        newPost.child("title").setValue(title_val);
                                        newPost.child("content").setValue(content_val);
                                        newPost.child("image").setValue(downloaduri[0].toString());
                                        newPost.child("date").setValue(strDate);

                                        mprogess.dismiss();

                                        startActivity(new Intent(AddOrUpdateUpcoming.this, UpcomingDrives.class));
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