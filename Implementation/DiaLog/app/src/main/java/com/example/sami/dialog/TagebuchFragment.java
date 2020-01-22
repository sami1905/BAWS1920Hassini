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


        bzList.add(new BzItem(0,2,"12:30", "99", "mg/dL", "3", "BE", "-", "4.5"));
        bzList.add(new BzItem(0,2,"10:06", "211", "mg/dL", "-", "BE", "2.5", "2.5"));
        bzList.add(new BzItem(0,2,"06:11", "142", "mg/dL", "6", "BE", "-", "9"));

        bzRecyclerView = v.findViewById(R.id.bz_recyclerview);
        bzLayoutManager = new LinearLayoutManager(getContext());
        bzAdapter = new BzAdapter(bzList);
        bzRecyclerView.setLayoutManager(bzLayoutManager);
        bzRecyclerView.setAdapter(bzAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                bzList.remove(viewHolder.getAdapterPosition());
                bzAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(bzRecyclerView);



        return v;
    }

   /* public void addEvent( Event nEvent, Meal nMeal, Sport nSport){

        ArrayList<Event> currentEvents =  new ArrayList<Event>();
        ArrayList<Meal> currentMeal = user.getMeals();
        ArrayList<Sport> currentSport = user.getSport();

        if(nEvent != null){
            if(user.getEvents() == null){
                user.setEvents(new ArrayList<Event>());
            }
            user.getEvents().add(nEvent);
        }


        if(nMeal != null){
            if(user.getMeals() == null){
                user.setMeals(new ArrayList<Meal>());
            }
            user.getMeals().add(nMeal);
        }


        if(nSport != null){
            if(user.getSport() == null){
                user.setSport(new ArrayList<Sport>());
            }
            user.getSport().add(nSport);

        }

        bzList.add(new BzItem(user.getEvents().get(0).getId(),user.getId(),user.getEvents().get(0).getTime(),
                String.valueOf(nEvent.getSugar()), user.getUnitBZ(), String.valueOf(nEvent.getBe()),
                user.getUnitKH(), String.valueOf(nEvent.getKie()), String.valueOf(nEvent.getIe())));

        changeUser(user);



    }*/

    public void changeUser(User user){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<User> call = jsonPlaceHolderApi.putUser(user.getId(), user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                return;

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                return;
            }
        });
    }



}
