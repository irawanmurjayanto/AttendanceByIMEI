package com.irawan.atttelkom;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class BarangAdapterActivity extends RecyclerView.Adapter<BarangAdapterActivity.BarangViewHolder> {


    List<BarangActivity> barangs;
    Context mContext;
    public BarangAdapterActivity(List<BarangActivity> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_activity, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;


    }





    @Override

    public void onBindViewHolder(final BarangViewHolder barangViewHolder, final int i) {

     //   barangViewHolder.barangItemcode.setText(barangs.get(i).getID());

        DecimalFormat formatter = new DecimalFormat("#,###,###,###");
        // totalrp.setText(formatter.format(obj.getDouble("total")));

        //barangViewHolder.barangItemcode.setText(barangs.get(i).getKode());

        barangViewHolder.barangItemdesc.setText(barangs.get(i).getNama());

      //  barangViewHolder.butdelete.setText(String.valueOf(barangs.get(i).getID()));

/*

       barangViewHolder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
             // BarangDialog itemLabel = barangs.get(i);

                // Remove the item on remove/button click

            //    ADB_Master adb_master=new ADB_Master(barangViewHolder.mRemoveButton.getContext());
              //  adb_master.deleteDatacur(barangs.get(i).getID());

                barangs.remove(i);


                notifyItemRemoved(i);

                notifyItemRangeChanged(i,barangs.size());

               // adb_master.deleteDatacur(barangs.get(i).getID());

                // Show the removed item label
               // Toast.makeText(mContext,"Removed : "  , Toast.LENGTH_SHORT).show();
            }
        });

*/

    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }


    public void hapusDataServercur(int i){




      /*  String sql = "DELETE FROM kurs \n" +
                "WHERE idno = ?;\n";

        mDatabase.execSQL(sql, new String[]{ String.valueOf(barangs.get(i).getID())});*/


    }



    public BarangActivity getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;



        TextView barangItemdesc;

        ImageButton mRemoveButton;


        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv_dialog);



            barangItemdesc = (TextView) itemView.findViewById(R.id.textItem);

            mRemoveButton = (ImageButton) itemView.findViewById(R.id.ib_remove);


        }

    }




}
