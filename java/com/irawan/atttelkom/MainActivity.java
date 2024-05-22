package com.irawan.atttelkom;



import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;


public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    SeekBar simpleSeekBar;
    TextView nilaibar1,keycheckin,keycheckout,textdeviceid,textjabatan,textTgl,ketsign,ketjam;
    Button butcheckin,butcheckot;
    ProgressDialog pDialog;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    public static String kunciin;
    public static String kunciout;
    public static String kuncisaja;
    public static String nikman;
    public static String statusatt;
    public static String unikid;

    TabLayout tabLayout;
    ViewPager viewPager;
    private List<POJOData> barangListData2 = new ArrayList<POJOData>();
    private AdapterData23 rvAdapterData2;
    private RecyclerView  mRecyclerViewData;
    private BarangAdapterActivity rvAdapterdlg;
    private RecyclerView.LayoutManager mLayoutManager;
    Context context;
    ImageButton imgprofile,butcheckin1,butcheckout1;
    Uri gmmIntentUri,imageUri;
    Button butadaktivitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // textTgl=(TextView) findViewById(R.id.textTgl);
        // SimpleDateFormat parseFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
        SimpleDateFormat parseFormat2 = new SimpleDateFormat("EEEE MMMM dd,yyyy");
        Date date =new Date();
        String s = parseFormat2.format(date);
        // textTgl.setText(df.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date()));
       // textTgl.setText(s);

verifyStoragePermissions(this);





        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
              //  showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)

                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
                   // showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        } else {

        }


        mRecyclerViewData= (RecyclerView) findViewById(R.id.bedulkubu);
        mRecyclerViewData.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewData.setLayoutManager(mLayoutManager);
        mRecyclerViewData.setEnabled(false);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(s);
        setSupportActionBar(toolbar);

        imgprofile=(ImageButton) findViewById(R.id.imgprofile) ;

        butcheckin1=(ImageButton) findViewById(R.id.checkin1);
        butcheckout1=(ImageButton) findViewById(R.id.checkot1);

        butcheckin1.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.checkinstate));
        butcheckout1.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.checkoutstate));


        //set state
        butcheckin1.setSelected(true);
        butcheckin1.setEnabled(true);
        butcheckout1.setSelected(false);
        butcheckout1.setEnabled(false);

        butcheckin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent a=new Intent(getBaseContext(),checkintanya.class);
startActivityForResult(a,555);
            }
        });


        butcheckout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getBaseContext(),checkintanya2.class);
                startActivityForResult(a,5555);
            }
        });

        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
openCamera();
            }
        });


        ketsign=(TextView) findViewById(R.id.ketsign);
        ketjam=(TextView) findViewById(R.id.ketjam);
     //   butcheckot=(Button) findViewById(R.id.checkout);




        /*
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });*/


        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("mainmenu")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("mainmenu"));


     //   simpleSeekBar=(SeekBar)findViewById(R.id.simpleSeekBar);
      // nilaibar1=(TextView) findViewById(R.id.nilaibar1);
        textdeviceid=(TextView) findViewById(R.id.textdeviceid);
        textjabatan=(TextView) findViewById(R.id.textjabatan);
        butadaktivitas=(Button) findViewById(R.id.butaddaktivitas);

        keycheckin=(TextView) findViewById(R.id.keycheckin);
        keycheckout=(TextView) findViewById(R.id.keycheckout);

        pDialog=new ProgressDialog(this);


        //butadaktivitas.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.butactivity));
        butadaktivitas.setBackgroundResource(R.color.colorKuningtua);

        butadaktivitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent a=new Intent(getBaseContext(),checkin4.class);
                startActivityForResult(a,111);
            }
        });


   /*      butcheckot.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent a=new Intent(getBaseContext(),checkin11.class);
                 startActivityForResult(a,1112);
             }
         });
*/

       // textdeviceid.setText(ambilid(this));


//getimei4();
//getIMEINumber();
//        getDeviceid();
        getDeviceIMEI2();
        checkdeviceid();

        unikid=textdeviceid.getText().toString();
        // SimpleDateFormat parseFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
      //  SimpleDateFormat parseFormat = new SimpleDateFormat("ddMMyyyy");
      //  String s2 = parseFormat.format(date);


        // perform seek bar change listener event used for getting the progress value
    /*    simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                nilaibar1.setText(progressChangedValue+"%");



            }
        });*/


 //alarm2();



    }



    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }


    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE




            );
        }
    }


    private void openCamera() {
        ContentValues values = new ContentValues();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 2222);
    }


    private  void alarm()
    {
        Calendar calendar = Calendar.getInstance();
// 11:59 PM
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent(context, checkin11.class),PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    private void alarm2()
    {

        Calendar calendar = Calendar.getInstance();
// 11:59 PM
        calendar.set(Calendar.HOUR_OF_DAY, 07);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);
       //setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        Intent intent = new Intent(MainActivity.this, checkintanya2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 9999, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
       // ((AlarmManager) getSystemService(ALARM_SERVICE)).setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
    }


    @SuppressWarnings("deprecation")
    private String getDeviceIMEI() {
        String IMEINumber = "";
        final int reqcode = 1;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                String[] per = {Manifest.permission.READ_PHONE_STATE};
                requestPermissions(per, reqcode);


                TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        IMEINumber = tm.getImei();
                        textdeviceid.setText(IMEINumber);
                    }
                } else {
                    IMEINumber = tm.getDeviceId();
                    textdeviceid.setText(IMEINumber);
                }
            }




        return IMEINumber;
    }


    private void getDeviceID()
    {



    }






    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String ambilid(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }


        }


        return uniqueID;
    }



    private void getDeviceid(){
      //  if (null == textdeviceid.getText().toString() || 0 == textdeviceid.getText().toString().length()) {
            textdeviceid.setText( Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));

            if (textdeviceid.getText().toString().length()==0)
            {
               // textdeviceid.setText();

            }

      //  }

    }


    public String getDeviceIMEI2() {




      String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);




    /*      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
              textdeviceid.setText( Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
        }else
          {
              textdeviceid.setText(tm.getDeviceId());
          }*/

        if (null != tm) {
            textdeviceid.setText(tm.getDeviceId());
        }


        if (null == textdeviceid.getText().toString() || 0 == textdeviceid.getText().toString().length()) {
            textdeviceid.setText( Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
        }

        return deviceUniqueIdentifier;
    }


    private void checkerror()
    {
        Toast.makeText(getBaseContext(),"Nilai :"+kunciin,Toast.LENGTH_LONG).show();
    }




    public void checkkeynik(){

        //refreshFlag="1";

      //Toast.makeText(getBaseContext(),"Nilai :"+kuncisaja,Toast.LENGTH_LONG).show();

        hideDialog();

     //   final String deviceid = textdeviceid.getText().toString();

        String url = "http://anugrahsoftware.xyz/andro/checknikbedul.php";

      //  pDialog.setMessage("Checking Data Check In...");

        //showDialog();



        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                //hideDialog();

                //Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                Log.d("bedulkubu", "response :" + response);



                processResponse4(response);

                //   finish();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                    //    hideDialog();


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

                params.put("keynikbedul",kuncisaja);



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



    private void processResponse4(String response){

        try {

            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");




            //Toast.makeText(getBaseContext(),"Masuk ke checkkeynik"+errormsg,Toast.LENGTH_LONG).show();







            if (errormsg.equals("fail"))
            {

              //  Toast.makeText(getBaseContext(),"Masuk ke checkkeynik",Toast.LENGTH_LONG).show();

                hideDialog();

                statusatt="masuk";

Intent a=new Intent(getBaseContext(),checkintanya.class);
startActivityForResult(a,555);

            }else {





          //  Toast.makeText(getBaseContext(),"errormsg : "+errormsg,Toast.LENGTH_LONG).show();

                JSONArray jsonArray = jsonObj.getJSONArray("data");
                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject obj = jsonArray.getJSONObject(i);

                    //textjabatan.setText(obj.getString("nama")+"|"+obj.getString("nik"));
                    //nikman=obj.getString("nik");
                  //  Toast.makeText(getBaseContext(),"errormsg : "+kuncisaja,Toast.LENGTH_LONG).show();

                 //   ketsign.setText("Sign Out|");

                    String status=obj.getString("statusatt");
           //         Toast.makeText(getBaseContext(),"status"+status,Toast.LENGTH_LONG).show();

                 if (status.equals("masuk")) {
                        ketsign.setText("Sign In|");
                     butcheckin1.setSelected(false);
                     butcheckin1.setEnabled(false);
                     butcheckout1.setSelected(true);
                     butcheckout1.setEnabled(true);
                     //butadaktivitas.setSelected(true);
                     butadaktivitas.setBackgroundResource(R.color.colorKuningtua);
                     //     alarm2();
                    }else
                    {
                        butcheckin1.setSelected(false);
                        butcheckin1.setEnabled(false);
                        butcheckout1.setSelected(false);
                        butcheckout1.setEnabled(false);
                       // butadaktivitas.setSelected(false);
                        butadaktivitas.setBackgroundResource(R.color.colorGreymuda);
                        statusatt="keluar";
                        ketsign.setText("Sign Out|");
                            butadaktivitas.setEnabled(false);


                    }

                    ketjam.setText(obj.getString("tgltime"));

                   // Toast.makeText(getBaseContext(),"errormsg : "+errormsg,Toast.LENGTH_LONG).show();

//up starttanya jika check out
                    if (errormsg.equals("1")) {

                    //    Intent a=new Intent(getBaseContext(),checkintanya2.class);
                      //  startActivityForResult(a,555);
//alarm2();
                    }

                }




              //  }



               /// Toast.makeText(getBaseContext(),"Ambilnama",Toast.LENGTH_LONG).show();







//ambilniknama();
                hideDialog();
//                finish();

            }







          //  Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }

    }





    private void checkdeviceid(){

        //refreshFlag="1";

        final String deviceid = textdeviceid.getText().toString();

      //  Toast.makeText(getBaseContext(),"device id "+deviceid,Toast.LENGTH_LONG).show();

        String url = "http://anugrahsoftware.xyz/andro/checkdevice.php";

        pDialog.setMessage("Checking DeviceID...");

        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                hideDialog();

                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                processResponse(response);

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



    private void processResponse(String response){

        try {

            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");

            if (errormsg.equals("fail"))
            {
                hideDialog();
               // dialogform();

                Intent a=new Intent(getBaseContext(),registerdevice0.class);
                a.putExtra("imei",textdeviceid.getText().toString());
                startActivityForResult(a,444);

            }else {

                //  Toast.makeText(getBaseContext(),"Ambilnama",Toast.LENGTH_LONG).show();
                 ambilniknama();

               //checkkeynik();
                hideDialog();
//                finish();

            }

            //  Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }





        loaddatavolley();

     /*   new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loaddatavolley();

            }
        }, 0, 2000);*/

        new CountDownTimer(1500, 1000) {
            public void onFinish() {
                // When timer is finished
                loaddatavolley();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();



    }












    private void ambilniknama(){

        //refreshFlag="1";




        final String deviceid = textdeviceid.getText().toString();
        //final String nik=editnik.getText().toString();

        String url = "http://anugrahsoftware.xyz/andro/checkniknama.php";

        pDialog.setMessage("Checking NIK...");

        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                hideDialog();

                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                processResponse2(response);

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
//                params.put("nik",nik);


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



    public void onErrorResponse2(VolleyError error) {
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



    private void processResponse2(String response){

        try {






            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");

                JSONArray jsonArray = jsonObj.getJSONArray("data");

                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject obj = jsonArray.getJSONObject(i);

            textjabatan.setText(obj.getString("nama")+"|"+obj.getString("nik"));
            nikman=obj.getString("nik");

                    SimpleDateFormat parseFormat = new SimpleDateFormat("ddMMyyyy");

                    Date date =new Date();

                    String s2 = parseFormat.format(date);

                    keycheckin.setText(s2+"MASUK"+nikman);
                    keycheckout.setText(s2+"KELUAR"+nikman);


                    kunciin=keycheckin.getText().toString();
                    kunciout=keycheckout.getText().toString();
                    kuncisaja=s2+nikman;

                    checkkeynik();

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




    public void onActivityResult(int requestCode, int resultCode, Intent intent) {



        if (requestCode == 2222 ) {



            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                //myImageView.setImageBitmap(bitmap);

                File tome        = new File(getRealPathFromURI(imageUri));
                String toto=tome.getName();

                // Toast.makeText(getBaseContext()," nilai awal 2222" +tome,Toast.LENGTH_LONG).show();
               // savepicvolley(getRealPathFromURI(imageUri),toto);
                //butopenulang.performClick();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }




        if (requestCode==555)
        {

            butcheckin1.setSelected(false);
            butcheckin1.setEnabled(false);
            butcheckout1.setSelected(true);
            butcheckout1.setEnabled(true);
            butadaktivitas.setBackgroundResource(R.color.colorKuningtua);


            //  Toast.makeText(getBaseContext(),"Load 5555",Toast.LENGTH_LONG).show();
            if (statusatt=="masuk") {
                ketsign.setText("Sign In|");
                ketjam.setText(checkin1.jamin1);
             //   alarm2();
                loaddatavolley();
            }

        }



        if (requestCode==5555)
        {

            butcheckin1.setSelected(false);
            butcheckin1.setEnabled(false);
            butcheckout1.setSelected(false);
            butcheckout1.setEnabled(false);
            butadaktivitas.setSelected(false);
            butadaktivitas.setBackgroundResource(R.color.colorGreymuda);


            //  Toast.makeText(getBaseContext(),"Load 5555",Toast.LENGTH_LONG).show();
            if (statusatt=="keluar") {
                ketsign.setText("Sign Out|");
                ketjam.setText(checkin1.jamin1);
                //   alarm2();
                loaddatavolley();
            }

        }



        if (requestCode==9999)
        {

            Toast.makeText(getBaseContext(),"Load 9999",Toast.LENGTH_LONG).show();
          //  if (statusatt=="keluar") {
                ketsign.setText("Sign Out|");
                ketjam.setText(checkin11.jamin1);
              //  alarm2();
                loaddatavolley();
            //}





        }


        if (requestCode==111) {

           // Toast.makeText(getBaseContext(),"Load 11111",Toast.LENGTH_LONG).show();

            loaddatavolley();
        }



        if (requestCode==444) {

            checkdeviceid();
        }

    }



    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

         getMenuInflater().inflate(R.menu.menuutama, menu);

        // MenuItem item = menu.findItem(R.id.action_new_print);
        // shareActionProvider = (ShareActionProvider) item.getActionProvider();
        return true;
        // Return true to display menu
        // return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        // final String kodecabang1 = intent.getStringExtra("kodecabang");

        int id = item.getItemId();


        switch (item.getItemId()) {







            case (R.id.report1):
                /*Intent intent3 = new Intent("finish");
                sendBroadcast(intent3);
                finish();
                Intent intent4 = new Intent("finishmain");
                sendBroadcast(intent4);
                finish();
                finish();
*/
                Intent a =new Intent(getBaseContext(),checkin44Rpt.class);
                startActivity(a);

                break;








        }


        return super.onOptionsItemSelected(item);

    }



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        finish();
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



    private void dialogform() {
        dialog = new AlertDialog.Builder(this);



        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.reg_warning, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Device ID Registration");


        //  txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        // txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        //txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        //txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        // kosong();

        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    nama    = txt_nama.getText().toString();
                //  usia    = txt_usia.getText().toString();
                //alamat  = txt_alamat.getText().toString();
                //status = txt_status.getText().toString();

                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
               // dialog.dismiss();
                //finish();




                Intent a=new Intent(getBaseContext(),registerdevice.class);
                a.putExtra("imei",textdeviceid.getText().toString());
                startActivityForResult(a,444);
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });




        dialog.show();



    }



/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, "Touched layout", Toast.LENGTH_SHORT).show();
        Log.d("TOUCH", "Touched layout");
        return super.onTouchEvent(event);
    }*/


    private void DialogErrorNetwork() {
        dialog = new AlertDialog.Builder(this);


        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.error_network, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Network Error");

        //  txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        // txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        //txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        //txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        // kosong();

        dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    nama    = txt_nama.getText().toString();
                //  usia    = txt_usia.getText().toString();
                //alamat  = txt_alamat.getText().toString();
                //status = txt_status.getText().toString();

                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
                finish();
            }
        });
/*
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }

    private void DialogErrorServer() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.error_server, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Server Error");

        //  txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        // txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        //txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        //txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        // kosong();

        dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    nama    = txt_nama.getText().toString();
                //  usia    = txt_usia.getText().toString();
                //alamat  = txt_alamat.getText().toString();
                //status = txt_status.getText().toString();

                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
                finish();
            }
        });
/*
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }






    private void gambarDatakeRecyclerViewIDNO2() {

        // rvAdapterpos = new BarangAdapterMasterUser(barangListPOS);

        //mRecyclerViewpos.setAdapter(rvAdapterpos);
        //rvAdapterpos.notifyDataSetChanged();



        rvAdapterData2 = new AdapterData23(barangListData2);
        mRecyclerViewData.setAdapter(rvAdapterData2);
        rvAdapterData2.notifyDataSetChanged();



        mRecyclerViewData.addOnItemTouchListener(new RecyclerItemListener(getBaseContext(), new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {





                          //  POJOData barang = rvAdapterData2.getItem(position);

                      //  Toast.makeText(getBaseContext(), "Posisi no: " + barang.getIDNO(), Toast.LENGTH_SHORT).show();

                        //  Intent intent = new Intent(getBaseContext(), MainMap2.class);
                        //   Intent intent = new Intent(MasterUser.this, ADB_TambahUser.class);
                        //startActivity(intent);

                        //intent.putExtra("barang", barang);

                        //startActivityForResult(intent, 350);


                    }

                })

        );

    }



    public void loaddatavolley(){
        // pDialog=new ProgressDialog(MainActivity2.this);
        //pDialog.setMessage("Opening Data..");
        //showDialog();

        // Toast.makeText(this,"Load Data "+kode,Toast.LENGTH_LONG).show();
        //String url = "http://kinipi.net/hrd/newtbmaster.php";
        String url = "http://anugrahsoftware.xyz/andro/loadaktivitas.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                Log.d("Dialog POS","response:"+response);

                //   hideDialog();

                //   Toast.makeText(Emp_RegAss.this,"MAsuk Respon",Toast.LENGTH_LONG).show();

                barangListData2.clear();
                processResponsePOSIDNO2(response);
                gambarDatakeRecyclerViewIDNO2();


//                pDialog.hide();
                // hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {





                        if (error instanceof NetworkError) {
                            hideDialog();
                            Intent a=new Intent(getBaseContext(),koneksi.class);
                            startActivity(a);

                        } else if (error instanceof ServerError) {
                            hideDialog();
                            Intent a=new Intent(getBaseContext(),koneksi.class);
                            startActivity(a);
                        } else if (error instanceof AuthFailureError) {
                            hideDialog();
                        } else if (error instanceof ParseError) {
                            hideDialog();
                        } else if (error instanceof NoConnectionError) {
                            hideDialog();
                            Intent a=new Intent(getBaseContext(),koneksi.class);
                            startActivity(a);
                        } else if (error instanceof TimeoutError) {
                            hideDialog();
                            Intent a=new Intent(getBaseContext(),koneksi.class);
                            startActivity(a);
                        }


                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams()

            {

                // Toast.makeText(Transaksi_POS.this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                params.put("keynik",MainActivity.kunciin);

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);
        //   hideDialog();
        //  Toast.makeText(this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();
    }


    private void processResponsePOSIDNO2(String response){

        try {

            Log.d("TAG", "data response DATA POS: "+response);

            JSONObject jsonObj = new JSONObject(response);

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            Log.d("TAG", "data length: " + jsonArray.length());

            POJOData objectbarangPI = null;

            barangListData2.clear();

            //NumberFormat numberFormat  = new DecimalFormat("##");

            // DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            //  totalrp.setText(formatter.format(obj.getDouble("total")));

            //  myBitmap = BitmapFactory.decodeFile(imageUri);
            //    ImageView ivBasicImage = (ImageView) findViewById(R.id.photoabsen4);
            //  ivBasicImage.setImageBitmap(myBitmap);

            // Picasso.get().load("http://kinipi.net/hrd/uploademp/11001keluar22112019.jpg").into(ivBasicImage);

            // Glide.with(this).load(imageUri).into(ivBasicImage);
            //  ImageView img_add = (ImageView) findViewById(R.id.photoabsen4);


            //  img_add.setImageBitmap(getBitmapFromURL("http://kinipi.net/hrd/uploademp/11001keluar22112019.jpg"));

        /*    ImageView bindImage = (ImageView)findViewById(R.id.photoabsen4);
            String pathToFile = "http://kinipi.net/hrd/uploademp/11001keluar22112019.jpg";
            DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(bindImage);
            downloadTask.execute(pathToFile);*/


            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);


                objectbarangPI= new POJOData();
                // objectbarangAtt.setIDNO(obj.getInt("idno"));
                //  objectbarangAtt.setNIK(obj.getString("nik"));
                //  final String pi2=obj.getString("pisaja");
                objectbarangPI.setIDNO(obj.getInt("idno"));
                objectbarangPI.setJudul(obj.getString("keterangan"));
                objectbarangPI.setRemarks(obj.getString("dod"));
                objectbarangPI.setTagstatus(obj.getInt("taskpersen"));
                // String imageUri = "http://kinipi.net/hrd/uploademp/"+obj.getString("photo");
                //objectbarangAtt.setPhoto(imageUri);
                //textSection.setText( obj.getString("section")+" / ");
                // objectbarangAtt.setPhoto(imageUri);


           /*     ImageView bindImage = (ImageView)findViewById(R.id.loadimage);
                String pathToFile = "http://kinipi.net/hrd/uploademp/11001keluar22112019.jpg";
                DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(bindImage);
                downloadTask.execute(pathToFile);*/



                barangListData2.add(objectbarangPI);

                //  carinik.setText(obj.getString("nik"));
                // Toast.makeText(this,"NO NIK : "+obj.getString("nik"),Toast.LENGTH_LONG).show();
                //    final ArrayAdapter<String> myAdapter = new ArrayAdapter(this, R.layout.activity_dialog_tran_posedit);
                //  list1.setAdapter(myAdapter);

                /*
                ListAdapter adapter = new SimpleAdapter(
                        DialogTranPOS.this, R.layout.activity_dialog_tran_posedit,
                        new String[]{objectbarang.setKode(obj.getString("kode")),objectbarang.setNama(obj.getString("nama"))},
                        new int[]{R.id.textBarcode,R.id.textItem});*/


            }
//pDialog.hide();
        } catch (JSONException e) {

            Log.d("MainActivity", "errorJSON");
            hideDialog();

        }
        hideDialog();
    }




}
