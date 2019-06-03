package com.akhil.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class AboutCollege extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_college);
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

        webView = (WebView) findViewById(R.id.webView);

        String data = "<body style=\"bgcolor:#82b1ff\">A.G & S.G Siddhartha Degree College of Arts and Science, Vuyyuru\n" +
                "\n" +
                "Vuyyuru is situated 30 Km away from Vijayawada on N.H 9 that ends 45 Km after at Machilipatnam, once a famous port town, now the administrative Head Quarters of Krishna District. As history recorded, in 1000 A.D. the Cholas spread their power to Kolleru area and during that period some of them settled at this place and named it as Vuyyuru after “Urayur” one of the Capitals of Chola Dynasty. It had also another name “Urvari”. By the 14th Century the village grew in size and population, came under the rule of the Zamindaars of Nuzivid. A famous temple fair “Veeramma Thirunaalla” was started in the 16th Century during the Vijayanagara Dynasty. It has been continued and the “fair” has become a famous event for hundreds of villages and an agent of social and cultural cohesion.\n" +
                "\n" +
                "The K.C.P. Sugar and Industrial Corporation Ltd. was established in June 1941 at the initiative of Late Sri Adusumilli Gopalakrishnaiah, a visionary entrepreneur. The K.C.P, over a period brought a miraculous economic transformation of Vuyyuru and the surrounding villages.\n" +
                "\n" +
                "Lacking in easily accessible educational institutions nearby and deprived of opportunities for their children for higher education, the farmers of Vuyyuru area approached the K.C.P Management requesting for the establishment of a college. Already there was the “Adusumilli Gopalakrishnaiah Technological Institution” managing an Industrial training centre and a polytechnic college from 1947 to 1958. The society was revived by the K.C.P Ltd., with the help of local philanthropists and sugarcane growers and a college was born with the name of Adusumilli Gopalakrishnaiah and Sugarcane Growers College on 12th August 1975 and was inaugurated by the Hon’ble Chief Minister Sri. J. Vengala Rao.\n" +
                "\n" +
                "Velagapudi Ramakrishna and Sugarcane Growers Educational and Welfare Society was formed for financial support. The college started functioning with Rs. 26,00,000/- as an initial fund with 143 students in B.A., B.Com. and B.Sc. (T.M). Today its strength grew to 1164. In response to the growing educational needs of the community in and around Vuyyuru, another B.Sc. section in English medium was started during 1980-81. The college was admitted to grant-in-aid from 01-06-1981. The K.C.P Ltd. took over the college from the AGTA in 1994. A self-financing course in B.Sc. Computer Science was started in the year 1998-99. Another self-financing course in B.Sc. (M.P.C) English medium was introduced in the year 2006-07.\n" +
                "\n" +
                "In May 2001, the Management of K.C.P.S & I.C Ltd. handed over the college to  the management of Siddhartha Academy of General & Technical Education, Vijayawada which had ample experience in running many educational institutions for better maintenance. The Academy rechristened the college as A.G & S.G Siddhartha College of Arts and Science." +
                "\n" +
                "The NAAC Peer Team has visited AG&SG Siddhartha Degree College of Arts & Science during 6 - 7 March, 2017.\n" +
                "\n" +
                "The College is Re-accredited with Grade “A” by NAAC, Bengaluru on 27th March, 2017 with CGPA 3.05/4.00.</body>\n" +
                "\n";

        webView.loadData(data,"text/html; charset=UTF-8", null);


    }

}
