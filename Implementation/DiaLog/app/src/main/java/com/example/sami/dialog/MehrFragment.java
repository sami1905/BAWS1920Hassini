package com.example.sami.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MehrFragment extends Fragment {

    private User user;
    private TextView text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mehr, container, false);
        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }


        text = v.findViewById(R.id.testtext);
        text.setText("User: " + user.getFirstname() + " " + user.getName() + "\n" + "Nickname: " + user.getNickname() + "\n" + "ID: " + user.getId() + " Score: " + user.getScore());


        return v;
    }
}
