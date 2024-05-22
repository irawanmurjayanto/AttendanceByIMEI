package com.irawan.atttelkom;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class checkin44 extends AppCompatActivity {

    //data Terbaru
    private List<POJOData> barangListData2 = new ArrayList<POJOData>();
    private AdapterData22 rvAdapterData2;




    private RecyclerView.LayoutManager mLayoutManager;


    TextView textTgl,textJam,nilaibar1;
    private RecyclerView  mRecyclerViewData;
    private BarangAdapterActivity rvAdapterdlg;

    ProgressDialog pDialog;
    EditText edit1,edit2;
    Button tambah,simpan;
    LocationManager locationManager;

    private Context context = checkin44.this;

    FloatingActionButton fab;
    Toolbar toolbar;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    SeekBar simpleSeekBar;



Button butnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin44);

        textTgl=(TextView) findViewById(R.id.texttglhari1);
pDialog=new ProgressDialog(this);
edit1=(EditText) findViewById(R.id.edit1);
edit2=(EditText) findViewById(R.id.edit2);

tambah=(Button) findViewById(R.id.tambah);


       this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

       // SimpleDateFormat parseFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE MMMM dd,yyyy");
        Date date =new Date();
        String s = parseFormat.format(date);
        // textTgl.setText(df.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date()));
        textTgl.setText(s);




        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("check44")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("check44"));


        mRecyclerViewData= (RecyclerView) findViewById(R.id.bedulkubu);
        mRecyclerViewData.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewData.setLayoutManager(mLayoutManager);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savecheckin3();
                loaddatavolley();

                new CountDownTimer(1200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        loaddatavolley();
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

            }
        });


        simpan=(Button) findViewById(R.id.butSimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
finish();
                Intent a=new Intent(getBaseContext(),farewell.class);
                startActivity(a);
                //finish();
            }
        });

loaddatavolley();

    }










    private void gambarDatakeRecyclerViewIDNO2() {

        // rvAdapterpos = new BarangAdapterMasterUser(barangListPOS);

        //mRecyclerViewpos.setAdapter(rvAdapterpos);
        //rvAdapterpos.notifyDataSetChanged();



        rvAdapterData2 = new AdapterData22(barangListData2);
        mRecyclerViewData.setAdapter(rvAdapterData2);
        rvAdapterData2.notifyDataSetChanged();



        mRecyclerViewData.addOnItemTouchListener(new RecyclerItemListener(context, new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {



                        // Toast.makeText(getBaseContext(), "Posisi no: " + position, Toast.LENGTH_SHORT).show();

                      POJOData barang = rvAdapterData2.getItem(position);

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





    private void savecheckin3(){

        //refreshFlag="1";






        String url = "http://anugrahsoftware.xyz/andro/saveactivity.php";

        pDialog.setMessage("Saving Data...");

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

                params.put("kunci",MainActivity.kunciin);
                //checkin1
                params.put("isian",edit1.getText().toString());
                params.put("dod",edit2.getText().toString());




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
        edit1.setText("");
        edit2.setText("");
        edit1.requestFocus();
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
