package com.example.sami.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<PostItem> mPostItem;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        PostViewHolder pvh = new PostViewHolder(v, mListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostItem currentItem = mPostItem.get(position);

        holder.mNickname.setText(currentItem.getmNickname());
        holder.mText.setText(currentItem.getmText());
        holder.mAnzahlComments.setText(currentItem.getmAnzahlComment());
        holder.mCommentRessource.setImageResource(currentItem.getmImageResource());

    }

    @Override
    public int getItemCount() {
        return mPostItem.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        public TextView mNickname;
        public TextView mText;
        public TextView mAnzahlComments;
        public ImageView mCommentRessource;



        public PostViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mNickname = itemView.findViewById(R.id.text_nickname);
            mText = itemView.findViewById(R.id.text);
            mAnzahlComments = itemView.findViewById(R.id.comment_anzahl);
            mCommentRessource = itemView.findViewById(R.id.button_comment);

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

    public PostAdapter(ArrayList<PostItem> postItems){
        mPostItem = postItems;
    }


}
