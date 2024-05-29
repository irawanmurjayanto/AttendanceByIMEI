package com.irawan.atttelkom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerdevice0 extends AppCompatActivity {

    Button butprocess;
    ProgressDialog pDialog;
    EditText editnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerdevice0);

        pDialog=new ProgressDialog(this);
        butprocess=(Button) findViewById(R.id.butprocess);
        editnik=(EditText) findViewById(R.id.editnik);

        butprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checknik();
            }
        });

    }




    private void checknik(){

        //refreshFlag="1";




        final String deviceid = MainActivity.unikid;
        final String nik=editnik.getText().toString();

        String url = AppConfig.IP_SERVER+"andro/checknik.php";

        pDialog.setMessage("Checking NIK...");

        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                hideDialog();

                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                processResponse("Save Data",response);

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

                params.put("deviceid",deviceid);
                params.put("nik",nik);


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



    public void onErrorResponse(VolleyError error) {
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



    private void processResponse(String paction, String response){

        try {

         /*  JSONObject jsonObj2 = new JSONObject(response);

           String errormsg = jsonObj2.getString("errormsg");





            if (errormsg.equals("fail"))
            {
                hideDialog();
                textwarning.setText("Maaf NIK Anda belum terdaftar,Mohon diulangi lagi");


            }*/

            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");

            if (errormsg.equals("fail"))
            {
                hideDialog();
                // DialogForm();
                Intent a=new Intent(getBaseContext(),warning.class);
                startActivity(a);
            }else {

                hideDialog();




                JSONArray jsonArray = jsonObj.getJSONArray("data");

                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject obj = jsonArray.getJSONObject(i);

                    // textwarning.setText(obj.getString("nik"));

                    Intent a=new Intent();
                    a.putExtra("nik",obj.getString("nik"));
                    a.putExtra("nama",obj.getString("nama"));
                    a.putExtra("unit",obj.getString("unit"));
                    setResult(RESULT_OK,a);

                    finish();

                }








            }


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




    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
       // Toast.makeText(this, "Maaf Anda harus Register ", Toast.LENGTH_SHORT).show();
      /*  if (doubleBackToExitPressedOnce) {
            Intent a=new Intent();
            a.putExtra("balikmainmapsub","ok");
            setResult(RESULT_OK,a);
            super.onBackPressed();
            super.finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);*/
    }



}
