package com.irawan.atttelkom;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class farewell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farewell);






        new CountDownTimer(3000, 1000) {
            public void onFinish() {
                // When timer is finished

                Intent intent11 = new Intent("checkin11");
                sendBroadcast(intent11);
                Intent intent12 = new Intent("checkin22");
                sendBroadcast(intent12);
                Intent intent13 = new Intent("checkin33");
                sendBroadcast(intent13);
                Intent intent132 = new Intent("checkin44");
                sendBroadcast(intent132);
                Intent intent14 = new Intent("checkintanya2");
                sendBroadcast(intent14);
//                Intent intent15 = new Intent("mainmenu");
  //              sendBroadcast(intent15);


                finish();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }
}
