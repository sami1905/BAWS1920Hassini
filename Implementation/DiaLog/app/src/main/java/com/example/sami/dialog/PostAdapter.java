package com.example.sami.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<PostItem> mPostItem;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        PostViewHolder pvh = new PostViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostItem currentItem = mPostItem.get(position);

        holder.mNickname.setText(currentItem.getmNickname());
        holder.mText.setText(currentItem.getmText());
        holder.mRating.setText(currentItem.getmRating());
        holder.mAnzahlComments.setText(currentItem.getmAnzahlComment());

    }

    @Override
    public int getItemCount() {
        return mPostItem.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        public TextView mNickname;
        public TextView mText;
        public TextView mRating;
        public TextView mAnzahlComments;



        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            mNickname = itemView.findViewById(R.id.text_nickname);
            mText = itemView.findViewById(R.id.text);
            mRating = itemView.findViewById(R.id.rating);
            mAnzahlComments = itemView.findViewById(R.id.comment_anzahl);
        }
    }

    public PostAdapter(ArrayList<PostItem> postItems){
        mPostItem = postItems;
    }


}
