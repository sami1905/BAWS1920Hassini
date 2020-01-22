package com.example.sami.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.YEAR;

public class AddEventFragment extends Fragment {

    private User user;
    private ArrayList<Event> event;
    private ArrayList<Meal> meal = new ArrayList<>();
    private Sport sport;
    private TextView nDate;
    private TextView nTime;
    private EditText nBZ;
    private EditText nBE;
    private EditText nKIE;
    private LinearLayout nMeal;

    private EditText nIE;
    private EditText nSport;

    private RecyclerView mRecyclerviewSearch;
    private SearchAdapter mAdapterSearch;
    private RecyclerView.LayoutManager mLayoutManagerSearch;



    private DatePickerDialog.OnDateSetListener mDateSetListener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);

        if (getArguments().getParcelable("User") != null) {
            user = getArguments().getParcelable("User");
        }
        if(getArguments().getParcelable("Meal") != null){

            meal.add((Meal) getArguments().getParcelable("Meal"));
        }

        ArrayList<SearchItem> searchItems= new ArrayList<>();
        if(!meal.isEmpty()){
            for(Meal nMeal : meal){
                searchItems.add(new SearchItem(nMeal.getId(), nMeal.getDescription() + ", " + nMeal.getAmount() + nMeal.getUnit(),
                        nMeal.getKcal()  + " kcal - " + nMeal.getKh() + "g Kohlenhydrate - " + nMeal.getE() + "g Eiweiß - " + nMeal.getF() + "g Fett"));
            }
        }

        mRecyclerviewSearch = v.findViewById(R.id.recycleview_search_meal);
        mLayoutManagerSearch = new LinearLayoutManager(getContext());
        mAdapterSearch = new SearchAdapter(searchItems);

        mRecyclerviewSearch.setLayoutManager(mLayoutManagerSearch);
        mRecyclerviewSearch.setAdapter(mAdapterSearch);


        nDate = (TextView) v.findViewById(R.id.input_date);
        nTime = (TextView) v.findViewById(R.id.input_time);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        int currentMin = cal.get(Calendar.MINUTE);
        currentMonth = currentMonth + 1;
        String date = currentDay + "." + currentMonth + "." + currentYear;
        String time = currentHour + ":" + currentMin;

        if (time.length() == 4 && time.indexOf(":") == 2) {
            time = time.substring(0, 3) + "0" + time.substring(3, 4);
        }
        if (time.length() == 4 && time.indexOf(":") == 1) {
            time = "0" + time;
        }
        if (time.length() == 3 && time.indexOf(":") == 1) {
            time = "0" + time.substring(0, 2) + "0" + time.substring(2, 3);
        }
        nDate.setText(date);
        nTime.setText(time);

        nDate.setOnClickListener(new View.OnClickListener() {
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
                nDate.setText(date);
            }
        };
        nTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String uhrzeit = hourOfDay + ":" + minute;
                        if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 2) {
                            uhrzeit = uhrzeit.substring(0, 3) + "0" + uhrzeit.substring(3, 4);
                        }
                        if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 1) {
                            uhrzeit = "0" + uhrzeit;
                        }
                        if (uhrzeit.length() == 3 && uhrzeit.indexOf(":") == 1) {
                            uhrzeit = "0" + uhrzeit.substring(0, 2) + "0" + uhrzeit.substring(2, 3);
                        }
                        nTime.setText(uhrzeit);
                    }
                }, hour, min, true);
                tpd.show();

            }
        });
        nBZ = v.findViewById(R.id.input_bz);
        if (user.getUnitBZ().equals("mg/dL")) {
            nBZ.setHint("mg/dL");
            nBZ.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        nMeal = v.findViewById(R.id.input_meal);
        nMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchItemActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


        return v;
    }

}
