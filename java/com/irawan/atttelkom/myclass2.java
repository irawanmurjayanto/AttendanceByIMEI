package com.irawan.atttelkom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class myclass2 extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        throw new UnsupportedOperationException("Not yet implemented");
    }



    private  void angkat(){

        Intent a=new Intent(context,checkin11.class);
    }
}
