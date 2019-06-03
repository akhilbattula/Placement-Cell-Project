package com.akhil.placementcell;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
       // progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter Phone No!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length()<10) {
                Toast.makeText(getApplicationContext(), "Enter Correct Phone No!", Toast.LENGTH_SHORT).show();
                return;
            }
        //    progressBar.setVisibility(View.VISIBLE);

            mprogrss.setMessage("Signing Up...");
            mprogrss.show();

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // there was an error
                            Log.d("akhill", "Working....");
                            mprogrss.dismiss();
                            mdatabase.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Log.d("akhill", dataSnapshot.child("designation").getValue().toString());

                                    if(dataSnapshot.child("designation").getValue().toString().equalsIgnoreCase("student")){
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
                        LoginActivity.super.onBackPressed();
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
}

