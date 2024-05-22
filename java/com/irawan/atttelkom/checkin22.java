package com.irawan.atttelkom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class checkin22 extends AppCompatActivity {

    TextView textTgl,textJam;
Button butnext,butback;
ImageButton sakit,sehat,notfit;

    public static String pilin2;
    public static String tglin2;
    public static String jamin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin22);

        textTgl=(TextView) findViewById(R.id.texttglhari1);
        textJam=(TextView) findViewById(R.id.textjam1);

        butnext=(Button) findViewById(R.id.butnext1);
        butback=(Button) findViewById(R.id.butback1);




        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("checkin22")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("checkin22"));


        sakit=(ImageButton) findViewById(R.id.butstep11);
        notfit=(ImageButton) findViewById(R.id.butstep12);
        sehat =(ImageButton) findViewById(R.id.butstep13);

        sakit.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.sakitstate));
        notfit.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.notfitstate));
        sehat.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.sehatstate));


       /* office.setSelected(false);
        satelitstate.setSelected(false);
        home.setSelected(false);
*/
        sakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sakit.setSelected(!sakit.isSelected());

                pilin2="sakit";


                if (sakit.isSelected()) {
                    sakit.setSelected(true);
                    notfit.setSelected(false);
                    sehat.setSelected(false);
                    //Handle selected state change
                } else {

                    //Handle de-select state change
                }
            }
        });




        notfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notfit.setSelected(!notfit.isSelected());


                pilin2="notfit";


                if (notfit.isSelected()) {
                    notfit.setSelected(true);
                    sehat.setSelected(false);
                    sakit.setSelected(false);

                    //Handle selected state change
                } else {
                    //Handle de-select state change
                }
            }
        });

        sehat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sehat.setSelected(!sehat.isSelected());


                pilin2="sehat";


                if (sehat.isSelected()) {
                    sehat.setSelected(true);
                    notfit.setSelected(false);
                    sakit.setSelected(false);

                    //Handle selected state change
                } else {
                    //Handle de-select state change
                }
            }
        });











        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        butnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getBaseContext(),checkin33.class);
                startActivity(a);
            }
        });

       // SimpleDateFormat parseFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE MMMM dd,yyyy");
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
        tglin2=s3;
        jamin2=s2;


    }
}
