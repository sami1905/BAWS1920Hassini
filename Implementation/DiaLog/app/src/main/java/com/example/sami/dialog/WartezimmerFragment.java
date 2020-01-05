package com.example.sami.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WartezimmerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private User user;
    private TextView text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wartezimmer, container, false);

        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }

        ArrayList<PostItem> postList = new ArrayList<>();
        postList.add(new PostItem("SamiNo | 2s", "Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut.",
                "0", "0"));
        postList.add(new PostItem("Anni95 | 32min", "Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut. Hallo, ich habe seit gestern Dexcom G6 und mein Sensor ist voll mit Blut.",
                "-1", "1"));
        postList.add(new PostItem("MaxiMax | 7w", "Haasdsdasdasd edgf ergrg reg l mit BlutHaasdsdasdasd edgf ergrg reg l mit Blut. Haasdsdasdasd edgf ergrg reg l mit Blut Haasdsdasdasd edgf ergrg reg l mit Blut.",
                "7", "15"));

        mRecyclerView = v.findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new PostAdapter(postList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }
}
