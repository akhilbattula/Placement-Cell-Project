package com.akhil.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class AboutTPC extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tpc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_SUBJECT, "Placement Cell Application");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.akhil.placementcell&hl=en");

                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.wv_tpc);

        String data = "<div class=\"col-md-9 col-sm-9\">\n" +
                "\n" +
                "\n" +
                "<h1>Goals and Objectives</h1>\n" +
                "\n" +
                "<article id=\"post-394\" class=\"post-394 page type-page status-publish hentry\">\n" +
                "\n" +
                "\t\t\t\t<p>\n" +
                "\t<span style=\"color:#000000;\"><span style=\"font-size:18px;\"><strong>Training and Placement Cell Goals and Objectives</strong></span></span>\n" +
                "</p>\n" +
                "<p style=\"text-align: justify;\">\n" +
                "\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">The college has established a Training and Placement Cell, Counseling and guiding of students, arranging project works in industries, industrial visits, industrial training during vacation, organizing campus interviews, providing job market information to students and necessary inputs to face the interviews, organizing Guest Lectures and Campus Selections, arranging industrial training for students, arranging industrial internships for faculty are some of the activities of the Training &amp; Placement Cell. Organize training programmes on all the issues related placement activity. Training programmes are held in four sessions. The College has a separate Training and Placement Cell, which invites various companies in all branches of Engineering. It has good recognition of providing successful engineers after completion of their degree. The shortlist of students is prepared based on their merit and the requirement of particular industry. The same list is sent to the concerned industries for their review and further shortlisted candidates are tested and interviewed in the campus.</span></strong></span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t.<br>\n" +
                "\t<span style=\"color:#000000;\"><span style=\"font-size:18px;\"><strong>Functions of the T&amp;P Cell:</strong></span></span>\n" +
                "</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Interaction between industry and institute.&nbsp;</span></strong></span>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Campus Recruitment Training to students.</span></strong></span>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Arranging Campus Interviews to students.&nbsp;</span></strong></span>\n" +
                "\t</li>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Guest lecturers by eminent industry experts.&nbsp;</span></strong></span>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Faculty training in the industry.</span></strong></span>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Entrepreneurship Development Programme.&nbsp;</span></strong></span>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Counseling and Personality Development.</span></strong></span>\n" +
                "\t</li>\n" +
                "<li>\n" +
                "\t\t<span style=\"color:#000000;\"><strong><span style=\"font-size:14px;\">Conducting Mock Interviews and Group Discussions.</span></strong></span>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "\n" +
                "<br class=\"clear\">\n" +
                "\n" +
                "\n" +
                "</article>\n" +
                "\n" +
                "</div>";

        webView.loadData(data,"text/html; charset=UTF-8", null);


    }

}
