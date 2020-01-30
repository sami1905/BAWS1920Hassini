package com.example.sami.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private ArrayList<EventItem> mEventList;

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item, viewGroup, false);
        EventViewHolder evh = new EventViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        EventItem currentItem = mEventList.get(i);

        eventViewHolder.mTime.setText(currentItem.getTime());
        eventViewHolder.mText.setText(currentItem.getText());
        eventViewHolder.mInfo.setText(currentItem.getInfo());

    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView mTime;
        public TextView mText;
        public TextView mInfo;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            mTime = itemView.findViewById(R.id.input_time);
            mText = itemView.findViewById(R.id.input_text);
            mInfo = itemView.findViewById(R.id.input_info);
        }
    }

    public EventAdapter(ArrayList<EventItem> eventList){
        mEventList = eventList;
    }
}
