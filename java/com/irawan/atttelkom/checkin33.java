package com.irawan.atttelkom;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class checkin33 extends AppCompatActivity {

    TextView textTgl,textJam;
public static String tglin3;
public static  String jamin3;
    public static String pilin3;


ProgressDialog pDialog;

Button butnext,butback;
EditText editkondisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin33);

        textTgl=(TextView) findViewById(R.id.texttglhari1);
        textJam=(TextView) findViewById(R.id.textjam1);
        editkondisi=(EditText) findViewById(R.id.editkondisi);

        butnext=(Button) findViewById(R.id.butnext1);
        butback=(Button) findViewById(R.id.butback1);



        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("checkin33")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("checkin33"));


pDialog=new ProgressDialog(this);

        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        butnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getBaseContext(),checkin44.class);
                startActivity(a);
                MainActivity.statusatt="keluar";
                pilin3=editkondisi.getText().toString();

               // Toast.makeText(getBaseContext(),"nilai : "+checkin2.tglin2+"-"+checkin1.jamin1+"-"+checkin1.pilin1,Toast.LENGTH_LONG).show();
                savecheckin3();

                Intent intent11 = new Intent("checkin11");
                sendBroadcast(intent11);
                Intent intent12 = new Intent("checkin22");
                sendBroadcast(intent12);
                Intent intent13 = new Intent("checkin33");
                sendBroadcast(intent13);
                Intent intent14 = new Intent("checkintanya2");
                sendBroadcast(intent14);

              //  Toast.makeText(getBaseContext(),"Status :"+MainActivity.statusatt,Toast.LENGTH_LONG).show();

                finish();

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
        tglin3=s3;
        jamin3=s2;

    }




    private void savecheckin3(){

        //refreshFlag="1";






        String url = "http://anugrahsoftware.xyz/andro/savecheckin1.php";

        pDialog.setMessage("Checking DeviceID...");

        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                hideDialog();

                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                processResponse3(response);

                //   finish();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();


                        if (error instanceof NetworkError) {
                            Toast.makeText(getBaseContext(),
                                    "Oops. Network Error",
                                    Toast.LENGTH_LONG).show();

                        } else if (error instanceof ServerError) {
                            Toast.makeText(getBaseContext(),
                                    "Oops. Server Time out",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getBaseContext(),
                                    "Oops. No Connection!",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(getBaseContext(),
                                    "Oops. Timeout error!",
                                    Toast.LENGTH_LONG).show();
                        }






                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams()

            {

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                params.put("kunci",MainActivity.kunciout);
                params.put("kuncisaja",MainActivity.kuncisaja);
                params.put("statusatt",MainActivity.statusatt);
                params.put("nik",MainActivity.nikman);


                //checkin1
                params.put("tgl1",checkin11.tglin1);
                params.put("jam1",checkin11.jamin1);
                params.put("nilai1",checkin11.pilin1);

                //checkin2
                params.put("tgl2",checkin22.tglin2);
                params.put("jam2",checkin22.jamin2);
                params.put("nilai2",checkin22.pilin2);

                //checkin3
                params.put("tgl3", checkin33.tglin3);
                params.put("jam3", checkin33.jamin3);
                params.put("nilai3", checkin33.pilin3);

                // params.put("nama",nama1);




          /*      if (action_flag.equals("add")){

                    params.put("id","0");

                }else{
                    //for add setup saving bro...
                    params.put("id","99");

                }*/

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);


    }



    public void onErrorResponse3(VolleyError error) {
        if (error instanceof NetworkError) {
            //DialogErrorNetwork();
        } else if (error instanceof ServerError) {
            //DialogErrorServer();
            Toast.makeText(this,
                    "Oops. Server Time out",
                    Toast.LENGTH_LONG).show();
        } else if (error instanceof AuthFailureError) {
        } else if (error instanceof ParseError) {
        } else if (error instanceof NoConnectionError) {
            //DialogErrorNetwork();
            Toast.makeText(this,
                    "Oops. No Connection!",
                    Toast.LENGTH_LONG).show();
        } else if (error instanceof TimeoutError) {
            //DialogErrorNetwork();
            Toast.makeText(this,
                    "Oops. Timeout error!",
                    Toast.LENGTH_LONG).show();
        }}



    private void processResponse3(String response){

        try {

            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");



            /*
            if (errormsg.equals("fail"))
            {
                hideDialog();

                Intent a=new Intent(getBaseContext(),checkintanya.class);
                startActivity(a);

            }else {

                //  Toast.makeText(getBaseContext(),"Ambilnama",Toast.LENGTH_LONG).show();

                hideDialog();
//                finish();

            }*/

            //  Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }

    }



    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }
}
