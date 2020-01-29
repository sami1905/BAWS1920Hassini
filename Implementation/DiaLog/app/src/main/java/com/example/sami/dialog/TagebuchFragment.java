package com.example.sami.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagebuchFragment extends Fragment {
    private User user;
    private RecyclerView bzRecyclerView;
    private RecyclerView.Adapter bzAdapter;
    private RecyclerView.LayoutManager bzLayoutManager;
    private ArrayList<BzItem> bzList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tagebuch, container, false);
        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }



        bzRecyclerView = v.findViewById(R.id.bz_recyclerview);
        bzLayoutManager = new LinearLayoutManager(getContext());
        bzAdapter = new BzAdapter(bzList);
        bzRecyclerView.setLayoutManager(bzLayoutManager);
        bzRecyclerView.setAdapter(bzAdapter);
        getEvents("23.1.2020");

        bzAdapter.notifyDataSetChanged();

        return v;
    }


    public void getEvents(final String date){

        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<Event>> call = jsonPlaceHolderApi.getEvents(user.getId());

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                ArrayList<Event> dateEvents = new ArrayList<>();
                float kh = 0;
                List<Event> events = response.body();
                for(Event e : events){
                    if (e.getDate().equals(date)){
                        dateEvents.add(e);
                        for(Meal m : e.getMeals()){
                            kh = kh + m.getKh();
                        }
                        bzList.add(new BzItem(0, user.getId(), e.getTime(), String.valueOf(e.getSugar()), user.getUnitBZ(), String.valueOf(kh), user.getUnitKH(),
                                String.valueOf(e.getKie()), String.valueOf(e.getIe() )));

                        bzAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }



}
