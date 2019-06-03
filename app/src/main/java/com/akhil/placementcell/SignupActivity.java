package com.akhil.placementcell;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPhno,inputname,inputgroup,inputyear,inputcollege,inputrollno,inputcity;
    private Button btnSignIn, btnSignUp, btnResetPassword;
   // private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mdatabase;
    private ProgressDialog mprogrss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPhno = (EditText) findViewById(R.id.phno);
        inputname = (EditText) findViewById(R.id.fullname);
        inputgroup = (EditText) findViewById(R.id.group);
        inputyear = (EditText) findViewById(R.id.yearofstudy);
        inputcollege = (EditText) findViewById(R.id.college);
        inputrollno = (EditText) findViewById(R.id.rollno);
        inputcity = (EditText) findViewById(R.id.city);
      //  progressBar = (ProgressBar) findViewById(R.id.progressBar);
       // btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        mprogrss = new ProgressDialog(this);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                final String phno = inputPhno.getText().toString().trim();
                final String fullname = inputname.getText().toString().trim();
                final String group = inputgroup.getText().toString().trim();
                final String year = inputyear.getText().toString().trim();
                final String college = inputcollege.getText().toString().trim();
                final String rollno = inputrollno.getText().toString().trim();
                final String city = inputcity.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phno)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(group)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Group!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(year)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Year of Study!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(college)) {
                    Toast.makeText(getApplicationContext(), "Enter Your College Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    Toast.makeText(getApplicationContext(), "Enter Your City!", Toast.LENGTH_SHORT).show();
                    return;
                }
               /* if (TextUtils.isEmpty(rollno)) {
                    Toast.makeText(getApplicationContext(), "Enter Your College Roll Number!", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (phno.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

             //   progressBar.setVisibility(View.VISIBLE);
                //create user
                mprogrss.setMessage("Signing Up...");
                mprogrss.show();
                auth.createUserWithEmailAndPassword(email, phno)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();


                                if (task.isSuccessful()) {

                                    String user_id = auth.getCurrentUser().getUid();
                                    DatabaseReference currecnt_user_db = mdatabase.child(user_id);

                                    currecnt_user_db.child("Email").setValue(email);
                                    currecnt_user_db.child("Phno").setValue(phno);
                                    currecnt_user_db.child("Name").setValue(fullname);
                                    currecnt_user_db.child("Group").setValue(group);
                                    currecnt_user_db.child("Year").setValue(year);
                                    currecnt_user_db.child("College").setValue(college);
                                    currecnt_user_db.child("Rollno").setValue(rollno);
                                    currecnt_user_db.child("City").setValue(city);

                                    mprogrss.dismiss();

                                    Log.d("akhill","Working....1");
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    Log.d("akhill","Working....2");
                                    finish();
                                    Log.d("akhill","Working....3");

                                } else {
                                    Log.d("akhill","failed...");
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }


}