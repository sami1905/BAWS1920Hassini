package com.example.sami.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class TagebuchFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private User user;
    private RecyclerView bzRecyclerView;
    private RecyclerView.Adapter bzAdapter;
    private RecyclerView.LayoutManager bzLayoutManager;
    private ArrayList<BzItem> bzList = new ArrayList<>();


    private RecyclerView mealRecyclerView;
    private RecyclerView.Adapter mealAdapter;
    private RecyclerView.LayoutManager mealLayoutManager;
    private ArrayList<EventItem> mealList = new ArrayList<>();


    private RecyclerView sportRecyclerView;
    private RecyclerView.Adapter sportAdapter;
    private RecyclerView.LayoutManager sportLayoutManager;
    private ArrayList<EventItem> sportList = new ArrayList<>();


    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView datum;
    private TextView lastBZ;
    private TextView gesamtBE;
    private TextView gesamtIE;
    private TextView beUnit;
    private TextView bzUnit;
    private TextView kcalGegessen;
    private TextView kcalOffen;
    private TextView kcalSport;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tagebuch, container, false);
        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }

        datum = v.findViewById(R.id.text_datum);
        lastBZ = v.findViewById(R.id.input_last_bz);
        gesamtBE = v.findViewById(R.id.input_gesamt_be);
        gesamtIE = v.findViewById(R.id.input_gesamt_ie);
        beUnit = v.findViewById(R.id.text_be_unit);
        beUnit.setText(user.getUnitKH());
        bzUnit = v.findViewById(R.id.text_bz_unit);

        if(user.getUnitBZ().equals("mg/dL")){
            bzUnit.setText("MG/DL");
        }
        if(user.getUnitBZ().equals("mmol/L")){
            bzUnit.setText("MMOL/L");
        }


        kcalGegessen = v.findViewById(R.id.input_kcal_gegessen);
        kcalOffen = v.findViewById(R.id.input_kcal_offen);
        int kcal = 0;
        String birthday = user.getBirthday();
        Date birthdayDate = null;
        try {
            birthdayDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c =Calendar.getInstance();

        c.setTime(birthdayDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(YEAR) - c.get(YEAR);
        if (c.get(MONTH) > today.get(MONTH) || c.get(MONTH) == today.get(MONTH) && c.get(DATE) > today.get(DATE)){
            age--;
        }

        if(user.getGender().equals("männlich")){
            kcal = Math.round(66.47f + (13.7f * user.getWeight()) + (5.0f * user.getHeight()) - (6.8f * age));
        }
        if(user.getGender().equals("weiblich")){
            kcal = Math.round(655.1f + (9.6f * user.getWeight()) + (1.8f * user.getHeight()) - (4.7f * age));
        }

        float leistung = (float) (user.getCalorieDegreeOne()*0.95 + user.getCalorieDegreeTwo() * 1.2f + user.getCalorieDegreeThree() * 1.45f + user.getCalorieDegreeFour() * 1.65f + user.getCalorieDegreeFive() * 1.85f + user.getCalorieDegreeSix() * 2.2f);
        leistung = leistung /(user.getCalorieDegreeOne() + user.getCalorieDegreeTwo() + user.getCalorieDegreeThree() + user.getCalorieDegreeFour() + user.getCalorieDegreeFive() + user.getCalorieDegreeSix());
        if(leistung != 0){

            kcal = Math.round(kcal*leistung);
        }

        if(user.getWeightGoal().equals("abnehmen")){
            kcal = kcal - 300;
        }

        if(user.getWeightGoal().equals("zunehmen")){
            kcal = kcal +300;
        }

        kcalSport = v.findViewById(R.id.input_kcal_sport);







        bzRecyclerView = v.findViewById(R.id.bz_recyclerview);
        bzLayoutManager = new LinearLayoutManager(getContext());
        bzAdapter = new BzAdapter(bzList);
        bzRecyclerView.setLayoutManager(bzLayoutManager);
        bzRecyclerView.setAdapter(bzAdapter);

        mealRecyclerView = v.findViewById(R.id.meal_recyclerview);
        mealLayoutManager = new LinearLayoutManager(getContext());
        mealAdapter = new EventAdapter(mealList);
        mealRecyclerView.setLayoutManager(mealLayoutManager);
        mealRecyclerView.setAdapter(mealAdapter);

        sportRecyclerView = v.findViewById(R.id.sport_recyclerview);
        sportLayoutManager = new LinearLayoutManager(getContext());
        sportAdapter = new EventAdapter(sportList);
        sportRecyclerView.setLayoutManager(sportLayoutManager);
        sportRecyclerView.setAdapter(sportAdapter);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        currentMonth = currentMonth + 1;
        String date1 = currentDay + "." + currentMonth + "." + currentYear;
        datum.setText(date1);

        getEvents(date1);
        bzAdapter.notifyDataSetChanged();
        mealAdapter.notifyDataSetChanged();
        sportAdapter.notifyDataSetChanged();

        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(YEAR);
                int currentMonth = cal.get(Calendar.MONTH);
                int currentDay = cal.get(Calendar.DAY_OF_MONTH);
                month = month + 1;
                String date = day + "." + month + "." + year;
                datum.setText(date);
                bzList.clear();
                mealList.clear();
                sportList.clear();


                bzAdapter.notifyDataSetChanged();
                mealAdapter.notifyDataSetChanged();
                sportAdapter.notifyDataSetChanged();

                getEvents(date);


                bzAdapter.notifyDataSetChanged();
                mealAdapter.notifyDataSetChanged();
                sportAdapter.notifyDataSetChanged();



            }
        };



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
                float be = 0;
                float ie = 0;
                float kGegessen = 0;
                int sportKCAL = 0;
                List<Event> events = response.body();
                if(events !=null) {
                    for (Event e : events) {
                        if (e.getDate().equals(date)) {
                            dateEvents.add(e);
                            for (Meal m : e.getMeals()) {
                                kh = m.getKh();
                                kGegessen = kGegessen + m.getKcal();
                                mealList.add(new EventItem(0, user.getId(), e.getTime(), m.getDescription() + ", " + String.valueOf(m.getAmount())
                                        + m.getUnit(), String.valueOf(m.getKcal()) + " kcal - " + String.valueOf(m.getKh()) + "g Kohlenhydrate - " + String.valueOf(m.getE()) +
                                        "g Eiweiß - " + String.valueOf(m.getF()) + "g Fett"));


                                mealAdapter.notifyDataSetChanged();

                            }

                            for (Sport s : e.getSports()) {
                                sportKCAL = sportKCAL + s.getKcal();
                                sportList.add(new EventItem(0, user.getId(), e.getTime(), s.getDescription() + ", " + s.getDuration() + "h",
                                        String.valueOf(s.getKcal()) + " kcal verbrannt"));


                                sportAdapter.notifyDataSetChanged();
                            }
                            if(e.getSugar() != 0.0f && e.getBe() != 0.0f && e.getKie() != 0.0f && e.getIe() != 0.0f){
                                bzList.add(new BzItem(0, user.getId(), e.getTime(), String.valueOf(e.getSugar()), user.getUnitBZ(), String.valueOf(e.getBe()), user.getUnitKH(),
                                        String.valueOf(e.getKie()), String.valueOf(e.getIe())));
                            }



                            bzAdapter.notifyDataSetChanged();
                            mealAdapter.notifyDataSetChanged();
                            sportAdapter.notifyDataSetChanged();

                            be = be + e.getBe();
                            ie = ie + e.getIe();
                        }
                    }

                    gesamtBE.setText(String.valueOf(be));
                    gesamtIE.setText(String.valueOf(ie));
                    kcalGegessen.setText(String.valueOf(Math.round(kGegessen)));
                    kcalSport.setText(String.valueOf(sportKCAL));



                    int kcal = 0;
                    String birthday = user.getBirthday();
                    Date birthdayDate = null;
                    try {
                        birthdayDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar c =Calendar.getInstance();

                    c.setTime(birthdayDate);
                    Calendar today = Calendar.getInstance();
                    int age = today.get(YEAR) - c.get(YEAR);
                    if (c.get(MONTH) > today.get(MONTH) || c.get(MONTH) == today.get(MONTH) && c.get(DATE) > today.get(DATE)){
                        age--;
                    }

                    if(user.getGender().equals("männlich")){
                        kcal = Math.round(66.47f + (13.7f * user.getWeight()) + (5.0f * user.getHeight()) - (6.8f * age));
                    }
                    if(user.getGender().equals("weiblich")){
                        kcal = Math.round(655.1f + (9.6f * user.getWeight()) + (1.8f * user.getHeight()) - (4.7f * age));
                    }
                    float a = user.getCalorieDegreeOne() + user.getCalorieDegreeTwo() + user.getCalorieDegreeThree() + user.getCalorieDegreeFour() + user.getCalorieDegreeFive() + user.getCalorieDegreeSix();
                    float leistung = (float) (((user.getCalorieDegreeOne()*0.95)/a) + ((user.getCalorieDegreeTwo() * 1.2f)/a) + ((user.getCalorieDegreeThree() * 1.45f)/a) +
                            ((user.getCalorieDegreeFour() * 1.65f)/a) + ((user.getCalorieDegreeFive() * 1.85f)/a) + ((user.getCalorieDegreeSix() * 2.2f)/a));

                    if(a != 0){

                        kcal = Math.round(kcal*leistung);
                    }

                    if(user.getWeightGoal().equals("abnehmen")){
                        kcal = kcal - 300;
                    }

                    if(user.getWeightGoal().equals("zunehmen")){
                        kcal = kcal +300;
                    }
                    kcal = kcal - Integer.parseInt(kcalGegessen.getText().toString()) + Integer.parseInt(kcalSport.getText().toString());

                    kcalOffen.setText(String.valueOf(kcal));
                }
                else{



                    int kcal = 0;
                    String birthday = user.getBirthday();
                    Date birthdayDate = null;
                    try {
                        birthdayDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar c =Calendar.getInstance();

                    c.setTime(birthdayDate);
                    Calendar today = Calendar.getInstance();
                    int age = today.get(YEAR) - c.get(YEAR);
                    if (c.get(MONTH) > today.get(MONTH) || c.get(MONTH) == today.get(MONTH) && c.get(DATE) > today.get(DATE)){
                        age--;
                    }

                    if(user.getGender().equals("männlich")){
                        kcal = Math.round(66.47f + (13.7f * user.getWeight()) + (5.0f * user.getHeight()) - (6.8f * age));
                    }
                    if(user.getGender().equals("weiblich")){
                        kcal = Math.round(655.1f + (9.6f * user.getWeight()) + (1.8f * user.getHeight()) - (4.7f * age));
                    }

                    float a = user.getCalorieDegreeOne() + user.getCalorieDegreeTwo() + user.getCalorieDegreeThree() + user.getCalorieDegreeFour() + user.getCalorieDegreeFive() + user.getCalorieDegreeSix();
                    float leistung = (float) (((user.getCalorieDegreeOne()*0.95)/a) + ((user.getCalorieDegreeTwo() * 1.2f)/a) + ((user.getCalorieDegreeThree() * 1.45f)/a) +
                            ((user.getCalorieDegreeFour() * 1.65f)/a) + ((user.getCalorieDegreeFive() * 1.85f)/a) + ((user.getCalorieDegreeSix() * 2.2f)/a));
                    if(a != 0){

                        kcal = Math.round(kcal*leistung);
                    }

                    if(user.getWeightGoal().equals("abnehmen")){
                        kcal = kcal - 300;
                    }

                    if(user.getWeightGoal().equals("zunehmen")){
                        kcal = kcal +300;
                    }

                    kcalOffen.setText(String.valueOf(kcal));

                    gesamtBE.setText("0");
                    lastBZ.setText("-");
                    gesamtIE.setText("0");


                }
                if( !bzList.isEmpty()){
                    lastBZ.setText(bzList.get(bzList.size()-1).getBz());
                }
                else{

                    lastBZ.setText("-");
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
