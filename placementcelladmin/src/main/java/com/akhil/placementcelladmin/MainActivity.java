
package com.akhil.placementcelladmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.akhil.placementcelladmin.adapters.CustomGrid;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    GridView grid;
    String[] web = {
            "About College",
            "About T & P Cell",
/*
            "About T & P Officer",
*/
            "2015-16 Placements",
            "Gallery",
            "Upcoming Drives",
            "Activities",
            "Recent Drives",
            "Registration",
            "Aptitude",
            "Queries",
            "Users",
            "Contact Us"

    } ;
    int[] imageId = {
            R.drawable.aboutcollege,
            R.drawable.aboutcell,
          /*  R.drawable.aboutofficer,*/
            R.drawable.sponsors,
            R.drawable.gallery1,
            R.drawable.aboutevent,
            R.drawable.activities,
            R.drawable.recent1,
            R.drawable.register,
            R.drawable.aptitude1,
          //  R.drawable.event51,
            R.drawable.messageto,
            R.drawable.users,
            R.drawable.contactus
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }
            }
        };

        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent;
              //  final Double latitude =16.505106;
             //   final Double longitude=80.648295;

                String url = "https://docs.google.com/forms/d/e/1FAIpQLSdNGy-mK8DMzZcS8rfGqQ_9A-ISrbRORIQPbiljzhLg6-VaHg/viewform?c=0&w=1";

                switch (position) {

                    case 0:
                        intent = new Intent(MainActivity.this, AboutCollege.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, AboutTPC.class);
                        startActivity(intent);
                        break;
/*
                    case 2:
                        intent = new Intent(MainActivity.this, AboutTPO.class);
                        startActivity(intent);
                        break;
*/
                    case 2:
                        intent = new Intent(MainActivity.this, LastYearPlacements.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, Gallery.class);
                        startActivity(intent);
                        break;
                    case 4:
                        if (!isInternetPresent) {
                            // Internet Connection is not present
                            Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                        } else {
                            intent = new Intent(MainActivity.this, UpcomingDrives.class);
                            startActivity(intent);
                        }
                            break;
                    case 5:
                        if (!isInternetPresent) {
                            // Internet Connection is not present
                            Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                        } else {
                            intent = new Intent(MainActivity.this, RecentActivities.class);
                            startActivity(intent);
                        }
                        break;
                    case 6: if (!isInternetPresent) {
                        // Internet Connection is not present
                        Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                    } else {
                        intent = new Intent(MainActivity.this, RecentDrives.class);
                        startActivity(intent);
                    }
                        break;
                    case 7: if (!isInternetPresent) {
                        // Internet Connection is not present
                        Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
                    }
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);

                        break;

                    case 8:intent = new Intent(MainActivity.this, Aptitude.class);
                        startActivity(intent);
                        break;

                    case 9:
                        intent = new Intent(MainActivity.this, Message.class);
                        startActivity(intent);
                        break;
                    case 10:  intent = new Intent(MainActivity.this, UsersActivity.class);
                        startActivity(intent);
                        break;
                    case 11:  intent = new Intent(MainActivity.this, ContactUs.class);
                        startActivity(intent);
                        break;

                }

            }
        });






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this)
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            auth.signOut();
        } else  if (id == R.id.action_exit) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("EXIT");
            alert.setMessage("Do You Want to Exit Now");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getApplicationContext(), "Bye... Visit Our App Again", Toast.LENGTH_SHORT).show();

                    finish();
                }
            });


            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getApplicationContext(), "You Clicked No...", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });


            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_home) {
            intent = new Intent(MainActivity.this, AboutCollege.class);
            startActivity(intent);
        } else if (id == R.id.nav_registration) {
            if (!isInternetPresent) {
                // Internet Connection is not present
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
            }
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdNGy-mK8DMzZcS8rfGqQ_9A-ISrbRORIQPbiljzhLg6-VaHg/viewform?c=0&w=1"));
            startActivity(intent);

        } else if (id == R.id.nav_campus) {
            if (!isInternetPresent) {
                // Internet Connection is not present
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
            }
            intent = new Intent(MainActivity.this, UpcomingDrives.class);
            startActivity(intent);
        } else if (id == R.id.nav_previous) {
            intent = new Intent(MainActivity.this, LastYearPlacements.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            intent = new Intent(MainActivity.this, Gallery.class);
            startActivity(intent);

        } else if (id == R.id.nav_aptitude) {
            if (!isInternetPresent) {
                // Internet Connection is not present
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
            }else {
                intent = new Intent(MainActivity.this, Aptitude.class);
                startActivity(intent);
            }
        }
        else if (id == R.id.nav_fb) {
            if (!isInternetPresent) {
                // Internet Connection is not present
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
            }
             intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/groups/pbsiddharthaplacements/"));
            startActivity(intent);
        } else if (id == R.id.nav_gmail) {
            if (!isInternetPresent) {
                // Internet Connection is not present
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection And Try Again", Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent("android.intent.action.SEND");
            i.putExtra("android.intent.extra.EMAIL", new String[] {
                    "sridharkavuri@yahoo.com"
            });
            i.putExtra("android.intent.extra.SUBJECT", "");
            i.putExtra("android.intent.extra.TEXT", "");
            i.setType("message/rfc822");
            startActivity(Intent.createChooser(i, "Choose  email client"));

        } else if (id == R.id.nav_share) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            share.putExtra(Intent.EXTRA_SUBJECT, "PBSC T&P CELL Application");
            share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.akhil.placementcelladmin&hl=en");

            startActivity(Intent.createChooser(share, "Share link!"));
        }
        else if(id == R.id.nav_logout) {

            auth.signOut();

        }
        else if(id == R.id.nav_exit){
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("EXIT");
            alert.setMessage("Do you want to exit now?");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getApplicationContext(), "Bye... Visit Our App Again", Toast.LENGTH_SHORT).show();

                    finish();
                }
            });


            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getApplicationContext(), "You Clicked No...", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });


            alert.show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }


}
