package com.akhil.placementcelladmin;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Battula's on 8/25/2016.
 */
public class MyFirebaseApp extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

    /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}