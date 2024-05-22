package com.irawan.atttelkom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class checkin1 extends AppCompatActivity {

    TextView textTgl,textJam;
    Button butnext,butback;
    ImageButton office,home,satelit;
    public static String pilin1;
    public static String tglin1;
    public static String jamin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin1);

        textTgl=(TextView) findViewById(R.id.texttglhari1);
        textJam=(TextView) findViewById(R.id.textjam1);
        butnext=(Button) findViewById(R.id.butnext1);
        butback=(Button) findViewById(R.id.butback1);




        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("checkin1")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("checkin1"));




        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });


        butnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getBaseContext(),checkin2.class);
                startActivity(a);
            }
        });

        office=(ImageButton) findViewById(R.id.butstep11);
        home=(ImageButton) findViewById(R.id.butstep12);
        satelit=(ImageButton) findViewById(R.id.butstep13);

        office.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.officestate));
        home.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.homestate));
        satelit.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.satelitstate));


       /* office.setSelected(false);
        satelitstate.setSelected(false);
        home.setSelected(false);
*/
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               office.setSelected(!office.isSelected());
pilin1="office";


                if (office.isSelected()) {
                    office.setSelected(true);
                    home.setSelected(false);
                    satelit.setSelected(false);
                    //Handle selected state change
                } else {

                    //Handle de-select state change
                }
            }
        });




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setSelected(!home.isSelected());
                pilin1="home";

                if (home.isSelected()) {
                    home.setSelected(true);
                    office.setSelected(false);
                    satelit.setSelected(false);

                    //Handle selected state change
                } else {
                    //Handle de-select state change
                }
            }
        });

        satelit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                satelit.setSelected(!satelit.isSelected());
                pilin1="satelit";

                if (satelit.isSelected()) {
                    satelit.setSelected(true);
                    office.setSelected(false);
                    home.setSelected(false);

                    //Handle selected state change
                } else {
                    //Handle de-select state change
                }
            }
        });


        // SimpleDateFormat parseFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE MMMM dd,yyyy");
        //SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date =new Date();
        String s = parseFormat.format(date);
        // textTgl.setText(df.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date()));
        textTgl.setText(s);

        SimpleDateFormat parseFormat2 = new SimpleDateFormat("HH:mm ");
        Date date2 =new Date();
        String s2 = parseFormat2.format(date2);
        // textTgl.setText(df.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date()));
        textJam.setText(s2);

        SimpleDateFormat parseFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 =new Date();
        String s3 = parseFormat3.format(date3);
        tglin1=s3;
        jamin1=s2;


    }
}
