package com.irawan.atttelkom;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdapterData23 extends RecyclerView.Adapter<AdapterData23.BarangViewHolder> {


    List<POJOData> barangs;
    SeekBar sBar;
    private Context context;


    ProgressDialog pDialog;
    public static int nilaibar;


    public AdapterData23(List<POJOData> barangs) {


        this.barangs = barangs;
        this.context=context;

    }

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testbedul, viewGroup, false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(final BarangViewHolder barangViewHolder,final int i) {

        barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getIDNO()));
        barangViewHolder.barangJudul.setText(barangs.get(i).getJudul());
        barangViewHolder.barangdod.setText(barangs.get(i).getRemarks());
        barangViewHolder.nilaibar1.setText(String.valueOf(barangs.get(i).getTagstatus()+"%"));

        barangViewHolder.seekbar.setProgress(barangs.get(i).getTagstatus());



       barangViewHolder.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
               barangViewHolder.nilaibar1.setText(progressChangedValue+"%");

              //  nilaibar=progressChangedValue;
                savecheckin3(barangs.get(i).getIDNO(),progressChangedValue,barangViewHolder.seekbar.getContext());


            }
        });


    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public POJOData getItem(int position) {



        return barangs.get(position);


    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView barangIDNO;
        TextView barangJudul;
        TextView barangdod;
        SeekBar seekbar;
        TextView nilaibar1;






        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);

            barangIDNO = (TextView) itemView.findViewById(R.id.textIDNO);

            barangJudul = (TextView) itemView.findViewById(R.id.textJudul);
            barangdod = (TextView) itemView.findViewById(R.id.textdod);
            seekbar = (SeekBar) itemView.findViewById(R.id.seekbar1);
            nilaibar1 = (TextView) itemView.findViewById(R.id.nilaibar1);



        }

    }




    private void savecheckin3(final int idno, final int nilaibar, final Context context ){

        //refreshFlag="1";

        pDialog=new ProgressDialog(context);




        String url = "http://anugrahsoftware.xyz/andro/saveseekbar.php";

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
                            Toast.makeText(context,
                                    "Oops. Network Error",
                                    Toast.LENGTH_LONG).show();

                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    "Oops. Server Time out",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    "Oops. No Connection!",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(context,
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

                params.put("idno",String.valueOf(idno));
                params.put("nilaibar",String.valueOf(nilaibar));
                //checkin1
              //  params.put("isian",edit1.getText().toString());
               // params.put("dod",edit2.getText().toString());




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

        Volley.newRequestQueue(context).add(postRequest);


    }



    public void onErrorResponse3(VolleyError error) {
        if (error instanceof NetworkError) {
            //DialogErrorNetwork();
        } else if (error instanceof ServerError) {
            //DialogErrorServer();
            Toast.makeText(context,
                    "Oops. Server Time out",
                    Toast.LENGTH_LONG).show();
        } else if (error instanceof AuthFailureError) {
        } else if (error instanceof ParseError) {
        } else if (error instanceof NoConnectionError) {
            //DialogErrorNetwork();
            Toast.makeText(context,
                    "Oops. No Connection!",
                    Toast.LENGTH_LONG).show();
        } else if (error instanceof TimeoutError) {
            //DialogErrorNetwork();
            Toast.makeText(context,
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
