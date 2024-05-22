package com.irawan.atttelkom;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class checkintanya2 extends Activity {
    Button but1,but2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkintanya2);

        but1=(Button) findViewById(R.id.but1);
        but2=(Button) findViewById(R.id.but2);

        this.setFinishOnTouchOutside(false);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("checkintanya2")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("checkintanya2"));


        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent a=new Intent(getBaseContext(),checkin11.class);
                startActivityForResult(a,9999);

            }
        });
    }



}
