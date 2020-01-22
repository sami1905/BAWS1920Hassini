package com.example.sami.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<SearchItem> mSearchItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        SearchViewHolder svh = new SearchViewHolder(v, mListener);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        SearchItem currentItem = mSearchItems.get(i);
        searchViewHolder.text.setText(currentItem.getText());
        searchViewHolder.info.setText(currentItem.getInfo());
    }
    public SearchAdapter(ArrayList<SearchItem> searchItems){
        mSearchItems = searchItems;


    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        public TextView text;
        public TextView info;

        public SearchViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            text = itemView.findViewById(R.id.search_input_text);
            info = itemView.findViewById(R.id.search_input_info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }

                    }

                }
            });
        }
    }

    public void filterList(ArrayList<SearchItem> filteredList){
        mSearchItems = filteredList;
        notifyDataSetChanged();
    }
}
