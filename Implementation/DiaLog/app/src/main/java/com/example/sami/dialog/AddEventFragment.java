package com.example.sami.dialog;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddEventFragment extends Fragment {
    private User user;
    private TextView textPost;
    private ImageButton ibPost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);

        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }
        textPost = v.findViewById(R.id.text_post);
        ibPost = v.findViewById(R.id.image_button_post);

        textPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewPostActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });




        return v;
    }
}
