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

import java.util.Calendar;

import static java.util.Calendar.YEAR;

public class ShowMealActifity extends AppCompatActivity {
    private User user;
    private Food meal;

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
        setContentView(R.layout.activity_show_meal_actifity);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        meal = intent.getParcelableExtra("Food");

        textMeal = findViewById(R.id.meal);
        textMeal.setText(meal.getName());

        editMenge = findViewById(R.id.input_menge);
        editMenge.setText(String.valueOf(meal.getAmount()));

        textkcal = findViewById(R.id.kcal);

        textKh = findViewById(R.id.kohlenhydrate);
        textE = findViewById(R.id.eiweiss);
        textF = findViewById(R.id.fett);

        float kcal = (Float.valueOf(editMenge.getText().toString()) * meal.getKcal()) / meal.getAmount();
        float kj = (Float.valueOf(editMenge.getText().toString()) * meal.getKj()) / meal.getAmount();
        float kh =  (Float.valueOf(editMenge.getText().toString()) * meal.getKh()) / meal.getAmount();
        float e =  (Float.valueOf(editMenge.getText().toString()) * meal.getE()) / meal.getAmount();
        float f =  (Float.valueOf(editMenge.getText().toString()) * meal.getF()) / meal.getAmount();

        textkcal.setText("kcal: " + String.valueOf(kcal));
        textKh.setText("Kohlenhydrate: " + String.valueOf(kh) + "g");
        textE.setText("Eiweiß: " + String.valueOf(e) + "g");
        textF.setText("Fett: " + String.valueOf(f) + "g");

        editMenge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!editMenge.getText().toString().isEmpty()){
                    float kcal = (Float.valueOf(editMenge.getText().toString()) * meal.getKcal()) / meal.getAmount();
                    float kj = (Float.valueOf(editMenge.getText().toString()) * meal.getKj()) / meal.getAmount();
                    float kh =  (Float.valueOf(editMenge.getText().toString()) * meal.getKh()) / meal.getAmount();
                    float e =  (Float.valueOf(editMenge.getText().toString()) * meal.getE()) / meal.getAmount();
                    float f =  (Float.valueOf(editMenge.getText().toString()) * meal.getF()) / meal.getAmount();

                    textkcal.setText("kcal: " + String.valueOf(kcal));
                    textKh.setText("Kohlenhydrate: " + String.valueOf(kh) + "g");
                    textE.setText("Eiweiß: " + String.valueOf(e) + "g");
                    textF.setText("Fett: " + String.valueOf(f) + "g");
                }
                else{
                    textkcal.setText("kcal: 0");
                    textKh.setText("Kohlenhydrate: 0g");
                    textE.setText("Eiweiß: 0g");
                    textF.setText("Fett: 0g");
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
                if(editMenge.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getBaseContext(), " Bitte gib die Menge an! ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                }
                else{
                    float kcal = (Float.valueOf(editMenge.getText().toString()) * meal.getKcal()) / meal.getAmount();
                    float kj = (Float.valueOf(editMenge.getText().toString()) * meal.getKj()) / meal.getAmount();
                    float kh =  (Float.valueOf(editMenge.getText().toString()) * meal.getKh()) / meal.getAmount();
                    float e =  (Float.valueOf(editMenge.getText().toString()) * meal.getE()) / meal.getAmount();
                    float f =  (Float.valueOf(editMenge.getText().toString()) * meal.getF()) / meal.getAmount();

                    int id = 0;
                    if(!user.getMeals().isEmpty()){

                        for(int i = 0; i < user.getMeals().size(); i++){
                            if(id <= user.getMeals().get(i).getId() ){
                                id = user.getMeals().get(i).getId()+1;
                            }
                        }

                    }

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

                    Meal nMeal = new Meal(id,date, time, meal.getName(), Float.valueOf(editMenge.getText().toString()), kcal, e, kh, f, meal.getUnit() );

                    Intent intent = new Intent(ShowMealActifity.this, AddEventFragment.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    extra.putParcelable("Meal", nMeal);
                    intent.putExtras(extra);
                    startActivity(intent);


                }
            }
        });



        back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowMealActifity.this, SearchItem.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


    }
}
