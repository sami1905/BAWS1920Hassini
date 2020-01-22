package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.YEAR;

public class ShowMealActivity extends AppCompatActivity {
    private User user;
    private ArrayList<Meal> meals = new ArrayList<>();

    private TextView back;
    private TextView textMeal;
    private EditText editMenge;
    private TextView textkcal;
    private TextView textKh;
    private TextView textE;
    private TextView textF;

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meal_activity);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        meals = intent.getParcelableArrayListExtra("Meals");

        final Meal meal = meals.get(meals.size()-1);

        textMeal = findViewById(R.id.meal);
        textMeal.setText(meal.getDescription());

        editMenge = findViewById(R.id.input_menge);
        editMenge.setText(String.valueOf(meal.getAmount()));

        textkcal = findViewById(R.id.kcal);

        textKh = findViewById(R.id.kohlenhydrate);
        textE = findViewById(R.id.eiweiss);
        textF = findViewById(R.id.fett);

        float kcal = (Float.valueOf(editMenge.getText().toString()) * meal.getKcal()) / meal.getAmount();
        float kh =  (Float.valueOf(editMenge.getText().toString()) * meal.getKh()) / meal.getAmount();
        float e =  (Float.valueOf(editMenge.getText().toString()) * meal.getE()) / meal.getAmount();
        float f =  (Float.valueOf(editMenge.getText().toString()) * meal.getF()) / meal.getAmount();

        textkcal.setText(String.valueOf(kcal));
        textKh.setText(String.valueOf(kh));
        textE.setText(String.valueOf(e));
        textF.setText(String.valueOf(f));

        editMenge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!editMenge.getText().toString().equals("")){
                    float kcal = (Float.valueOf(editMenge.getText().toString()) * meals.get(meals.size()-1).getKcal() / meals.get(meals.size()-1).getAmount());
                    float kh =  (Float.valueOf(editMenge.getText().toString()) * meals.get(meals.size()-1).getKh() / meals.get(meals.size()-1).getAmount());
                    float e =  (Float.valueOf(editMenge.getText().toString()) * meals.get(meals.size()-1).getE() / meals.get(meals.size()-1).getAmount());
                    float f =  (Float.valueOf(editMenge.getText().toString()) * meals.get(meals.size()-1).getF() / meals.get(meals.size()-1).getAmount());

                    textkcal.setText(String.valueOf(kcal));
                    textKh.setText(String.valueOf(kh));
                    textE.setText(String.valueOf(e));
                    textF.setText(String.valueOf(f));

                }
                else{
                    textkcal.setText("0");
                    textKh.setText("0");
                    textE.setText("0");
                    textF.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        addButton = findViewById(R.id.hinzufuegen_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editMenge.getText().toString() == null){
                    Toast toast = Toast.makeText(getBaseContext(), " Bitte gib die Menge an! ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                }
                else{
                    float nkcal = (Float.valueOf(editMenge.getText().toString()) * Float.valueOf(textkcal.getText().toString())) / meals.get(meals.size()-1).getAmount();
                    float nkh =  (Float.valueOf(editMenge.getText().toString()) * Float.valueOf(textKh.getText().toString())) / meals.get(meals.size()-1).getAmount();
                    float ne =  (Float.valueOf(editMenge.getText().toString()) * Float.valueOf(textE.getText().toString())) / meals.get(meals.size()-1).getAmount();
                    float nf =  (Float.valueOf(editMenge.getText().toString()) * Float.valueOf(textF.getText().toString())) / meals.get(meals.size()-1).getAmount();

                    int id = 0;


                    Calendar cal = Calendar.getInstance();
                    int currentYear = cal.get(YEAR);
                    int currentMonth = cal.get(Calendar.MONTH);
                    int currentDay = cal.get(Calendar.DAY_OF_MONTH);
                    currentMonth = currentMonth + 1;
                    String date = currentDay + "." + currentMonth + "." + currentYear;


                    int currentHour = cal.get(Calendar.HOUR_OF_DAY);
                    int currentMin = cal.get(Calendar.MINUTE);

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
                    int index = meals.size();
                    meals.set(index-1, new Meal(id, 0, textMeal.getText().toString(), Float.valueOf(editMenge.getText().toString()), nkcal, ne, nkh, nf, meals.get(meals.size()-1).getUnit()));

                    Intent intent = new Intent(ShowMealActivity.this, HomeActivity.class);
                    intent.putExtra("User", user);
                    intent.putExtra("Meals", meals);
                    intent.putExtra("Fragment", 2);
                    startActivity(intent);



                }
            }
        });



        back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowMealActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 2);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


    }
}
