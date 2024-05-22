package com.irawan.atttelkom;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AdapterData21 extends RecyclerView.Adapter<AdapterData21.BarangViewHolder> {


    List<POJOData> barangs;

    public AdapterData21(List<POJOData> barangs) {

        this.barangs = barangs;
    }

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test222, viewGroup, false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(final BarangViewHolder barangViewHolder,final int i) {
        barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getIDNO()));
        barangViewHolder.barangJudul.setText(barangs.get(i).getJudul());
        barangViewHolder.barangdod.setText(barangs.get(i).getRemarks());

   

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





        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);

            barangIDNO = (TextView) itemView.findViewById(R.id.textIDNO);

            barangJudul = (TextView) itemView.findViewById(R.id.textJudul);
            barangdod = (TextView) itemView.findViewById(R.id.textdod);




        }

    }


}
