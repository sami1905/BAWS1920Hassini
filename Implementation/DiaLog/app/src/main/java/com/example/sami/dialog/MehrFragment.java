package com.example.sami.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MehrFragment extends Fragment {

    private User user;
    private FrameLayout mHighscore;
    private TextView textAbmelden;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mehr, container, false);
        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }

        mHighscore = v.findViewById(R.id.highscore);
        mHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HighscoreActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);

            }
        });

        textAbmelden = v.findViewById(R.id.text_abmeldung);
        textAbmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });




        return v;
    }
}
