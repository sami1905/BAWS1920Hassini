package com.example.sami.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BzAdapter extends RecyclerView.Adapter<BzAdapter.BzViewHolder> {
    private  ArrayList<BzItem> mBzList;

    @NonNull
    @Override
    public BzViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bz_item, viewGroup, false);
        BzViewHolder bzvh = new BzViewHolder(v);
        return bzvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BzViewHolder bzViewHolder, int i) {
        BzItem currentItem = mBzList.get(i);

        bzViewHolder.time.setText(currentItem.getTime());
        bzViewHolder.bz.setText(currentItem.getBz());
        bzViewHolder.bzUnit.setText(currentItem.getBzUnit());
        bzViewHolder.kh.setText(currentItem.getKh());
        bzViewHolder.khUnit.setText(currentItem.getKhUnit());
        bzViewHolder.korrektur.setText(currentItem.getKorrektur());
        bzViewHolder.insulin.setText(currentItem.getInsulin());

    }

    @Override
    public int getItemCount() {
        return mBzList.size();
    }

    public static class BzViewHolder extends RecyclerView.ViewHolder{

        public TextView time;
        public TextView bz;
        public TextView bzUnit;
        public TextView kh;
        public TextView khUnit;
        public TextView korrektur;
        public TextView insulin;

        public BzViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.text_uhrzeit);
            bz = itemView.findViewById(R.id.text_bz);
            bzUnit = itemView.findViewById(R.id.text_bz_unit);
            kh = itemView.findViewById(R.id.text_be);
            khUnit = itemView.findViewById(R.id.text_kh_unit);
            korrektur = itemView.findViewById(R.id.text_korrektur);
            insulin = itemView.findViewById(R.id.text_insulin);

        }
    }

    public BzAdapter(ArrayList<BzItem> bzList){
        mBzList = bzList;
    }


}
