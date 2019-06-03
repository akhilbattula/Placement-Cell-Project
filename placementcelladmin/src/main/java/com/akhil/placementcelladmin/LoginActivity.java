package com.akhil.placementcelladmin;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
   // private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private ProgressDialog mprogrss;

    private DatabaseReference mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        // set the view now
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mprogrss = new ProgressDialog(this);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mprogrss.setMessage("Signing Up...");
                mprogrss.show();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                // progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // there was an error
                                    Log.d("akhill", "Working....");
                                    mprogrss.dismiss();
                                    mdatabase.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Log.d("akhill", dataSnapshot.child("designation").getValue().toString());

                                            if(dataSnapshot.child("designation").getValue().toString().equalsIgnoreCase("admin")){
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                Log.d("akhill", "Working....1");
                                                finish();
                                                Log.d("akhill", "Working....2");
                                            }else{
                                                Toast.makeText(LoginActivity.this, "Invalid credentials. Please login with valid Admin credentials...", Toast.LENGTH_SHORT).show();
                                                inputEmail.setText("");
                                                inputPassword.setText("");
                                                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                                    auth.signOut();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            mprogrss.dismiss();
                                            Log.d("akhill", databaseError.toString());
                                        }
                                    });
                                } else {
                                    Log.d("akhill", "failed");
                                    mprogrss.dismiss();

                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }

        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Exit")
                .setMessage("Do you want to exit now?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alert.show();
        
    }

    @Override
    protected void onStart() {
        super.onStart();



        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private boolean checkAndRequestPermissions() {
        int permissionNETWORK = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE);


        int storagePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionNETWORK != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,


                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 123);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permissions were given sucessfully...", Toast.LENGTH_SHORT).show();
                    //Permission Granted Successfully. Write working code here.
                } else {
                    Toast.makeText(this, "Need Permissions to continue our services", Toast.LENGTH_SHORT).show();
                    //You did not accept the request can not use the functionality.
                }
                break;
        }
    }

}

