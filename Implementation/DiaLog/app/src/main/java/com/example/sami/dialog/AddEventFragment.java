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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<Sport> sport = new ArrayList<>();


    private TextView nDate;
    private TextView nTime;
    private EditText nBZ;
    private EditText nKIE;
    private LinearLayout nMeal;
    private TextView nBEUnit;
    private EditText nBE;
    private EditText nIE;
    private LinearLayout nSport;
    private Button save;

    private RecyclerView mRecyclerviewMeal;
    private SearchAdapter mAdapterMeal;
    private RecyclerView.LayoutManager mLayoutManagerMeal;

    private RecyclerView mRecyclerviewSport;
    private SearchAdapter mAdapterSport;
    private RecyclerView.LayoutManager mLayoutManagerSport;



    private DatePickerDialog.OnDateSetListener mDateSetListener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);

        if (getArguments().getParcelable("User") != null) {
            user = getArguments().getParcelable("User");
        }
        if (getArguments().getParcelableArrayList("Meals") != null) {
            meals = getArguments().getParcelableArrayList("Meals");
        }
        if (getArguments().getParcelableArrayList("Sport") != null) {
            sport = getArguments().getParcelableArrayList("Sport");
        }
        final ArrayList<SearchItem> searchMeals= new ArrayList<>();

        final ArrayList<SearchItem> searchSports= new ArrayList<>();

        if(!meals.isEmpty()){
            for(Meal m : meals){
                searchMeals.add(new SearchItem(0, m.getDescription() +", " + m.getAmount() + m.getUnit(),
                        m.getKcal() + " kcal - " + m.getKh() +"g Kohlenhydrate - " + m.getE() + "g Eiweiß - " + m.getF() + "g Fett"));
            }
        }

        if(!sport.isEmpty()){
            for(Sport s : sport){
                searchSports.add(new SearchItem(0, s.getDescription() +", " + s.getDuration() + "h",
                        s.getKcal() + " kcal"));
            }
        }


        mRecyclerviewMeal = v.findViewById(R.id.recycleview_search_meal);
        mLayoutManagerMeal = new LinearLayoutManager(getContext());
        mAdapterMeal = new SearchAdapter(searchMeals);

        mRecyclerviewMeal.setLayoutManager(mLayoutManagerMeal);
        mRecyclerviewMeal.setAdapter(mAdapterMeal);

        mRecyclerviewSport = v.findViewById(R.id.recycleview_search_sport);
        mLayoutManagerSport = new LinearLayoutManager(getContext());
        mAdapterSport = new SearchAdapter(searchSports);

        mRecyclerviewSport.setLayoutManager(mLayoutManagerSport);
        mRecyclerviewSport.setAdapter(mAdapterSport);


        nDate = (TextView) v.findViewById(R.id.input_date);
        nTime = (TextView) v.findViewById(R.id.input_time);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        final int currentHour = cal.get(Calendar.HOUR_OF_DAY);
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

        nKIE = v.findViewById(R.id.input_kie);
        nKIE.setHint("IE");

        nBZ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String bzInput = nBZ.getText().toString();
                if(!bzInput.isEmpty() && Float.valueOf(bzInput) >= user.getUpperLimit()){
                    float correctur = Float.valueOf(bzInput);
                    float zielbereich = (user.getLowlimit() + user.getUpperLimit())/2;
                    correctur = correctur - zielbereich;
                    correctur = (correctur/user.getCorrectionFactor())*10;
                    correctur = Math.round(correctur);
                    correctur = correctur/10;
                    nKIE.setText(String.valueOf(correctur));
                }
                else{
                    nKIE.setText("");
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Meal[] selectedMeals = meals.toArray(new Meal[meals.size()]);

        nMeal = v.findViewById(R.id.input_meal);
        nMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchItemActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putParcelableArrayList("Meals", meals);
                extra.putInt("Art", 0);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        nBEUnit = v.findViewById(R.id.be_unit);

        if(user.getUnitKH().equals("KE")){
            nBEUnit.setText("KE: ");
        }
        nBE = v.findViewById(R.id.input_be);
        float be = 0;
        if(selectedMeals.length != 0){
            float kh = 0.0f;
            for(int i = 0; i< selectedMeals.length; i++){
                kh = kh + selectedMeals[i].getKh();
            }
            if(user.getUnitKH().equals("BE")){

                be = kh/12.0f;
                be = be *10;
                be = Math.round(be);
                be = be/10;
            }
            if(user.getUnitKH().equals("KE")){
                be = kh/10.0f;
                be = be *10;
                be = Math.round(be);
                be = be/10;
            }
        }
         nBE.setText(String.valueOf(be));
        if(be == 0.0f){
            nBE.setText("");
        }

        nIE = v.findViewById(R.id.input_ie);
        nKIE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float ie = 0;
                if(!nKIE.getText().toString().isEmpty()){
                    ie = Float.valueOf(nKIE.getText().toString());

                }
                if(!nBE.getText().toString().isEmpty()){
                    ie = ie + (Float.valueOf(nBE.getText().toString())*user.getBeFactor());
                }
                nIE.setText(String.valueOf(ie));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nBE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float ie = 0;
                if(!nKIE.getText().toString().isEmpty()){
                    ie = Float.valueOf(nKIE.getText().toString());

                }
                if(!nBE.getText().toString().isEmpty()){
                    ie = ie + (Float.valueOf(nBE.getText().toString())*user.getBeFactor());
                }
                nIE.setText(String.valueOf(ie));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nSport = v.findViewById(R.id.input_sport);
        nSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchItemActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putParcelableArrayList("Sport", sport);
                extra.putInt("Art", 1);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

        save = v.findViewById(R.id.posten_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nBZ.getText().toString().equals("") && nKIE.getText().toString().equals("") && searchMeals.isEmpty() && searchSports.isEmpty() && nBE.getText().toString().equals("") &&
                        nIE.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getContext(), " Tätige mindestens eine Eingabe! ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                }
                else{
                    if(nBZ.getText().toString().equals("")) nBZ.setText("0");

                    if(nKIE.getText().toString().equals("")) nKIE.setText("0");
                    if(nBE.getText().toString().equals("")) nBE.setText("0");
                    if(nIE.getText().toString().equals("")) nIE.setText("0");
                    Calendar cal = Calendar.getInstance();
                    int nYear = cal.get(YEAR);
                    int nMonth = cal.get(Calendar.MONTH);
                    int nDay = cal.get(Calendar.DAY_OF_MONTH);
                    nMonth = nMonth + 1;
                    String datum = nDay + "." + nMonth + "." + nYear;


                    int nhour = cal.get(Calendar.HOUR_OF_DAY);
                    int nmin = cal.get(Calendar.MINUTE);

                    String uhrzeit = nhour + ":" + nmin;
                    if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 2) {
                        uhrzeit = uhrzeit.substring(0, 3) + "0" + uhrzeit.substring(3, 4);
                    }
                    if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 1) {
                        uhrzeit = "0" + uhrzeit;
                    }
                    if (uhrzeit.length() == 3 && uhrzeit.indexOf(":") == 1) {
                        uhrzeit = "0" + uhrzeit.substring(0, 2) + "0" + uhrzeit.substring(2, 3);
                    }

                    Meal[] postMeal = meals.toArray(new Meal[meals.size()]);
                    for(int i = 0; i < postMeal.length; i++){
                        postMeal[i].setId(i);
                    }
                    Sport[] postSport = sport.toArray(new Sport[sport.size()]);
                    for(int i = 0; i < postSport.length; i++){
                        postSport[i].setId(i);
                    }


                    Event postEvent = new Event(0, user.getId(), datum, uhrzeit, Float.valueOf(nBZ.getText().toString()), Float.valueOf(nKIE.getText().toString()),
                            postMeal, Float.valueOf(nBE.getText().toString()), Float.valueOf(nIE.getText().toString()), postSport);

                    JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

                    Call<List<Event>> call = jsonPlaceHolderApi.createEvents(postEvent);

                    call.enqueue(new Callback<List<Event>>() {
                        @Override
                        public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                        }

                        @Override
                        public void onFailure(Call<List<Event>> call, Throwable t) {

                        }
                    });
                    user.setScore(user.getScore()+150);
                    changeUser(user);


                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    intent.putExtras(extra);
                    startActivity(intent);

                }
            }
        });
        return v;
    }

    public void changeUser(User nuser) {
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<User> call = jsonPlaceHolderApi.putUser(nuser.getId(), nuser);

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
