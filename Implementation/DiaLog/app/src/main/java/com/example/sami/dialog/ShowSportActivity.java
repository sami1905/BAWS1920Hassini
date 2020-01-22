package com.example.sami.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ShowSportActivity extends AppCompatActivity {
    private User user;
    private ArrayList<Sport> sport = new ArrayList<>();

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
        setContentView(R.layout.activity_show_sport_activity);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        sport = intent.getParcelableArrayListExtra("Sport");

        final Sport nSport = sport.get(sport.size()-1);

        textMeal = findViewById(R.id.meal);
        textMeal.setText(nSport.getDescription());

        editMenge = findViewById(R.id.input_menge);
        editMenge.setText("1");

        textkcal = findViewById(R.id.kcal);

        float kcal = nSport.getMet() * user.getWeight() * Float.valueOf(editMenge.getText().toString());

        textkcal.setText(String.valueOf(kcal));

        editMenge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!editMenge.getText().toString().equals("")){
                    float kcal = nSport.getMet() * user.getWeight() * Float.valueOf(editMenge.getText().toString());
                    textkcal.setText(String.valueOf(kcal));

                }
                else{
                    textkcal.setText("0");
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
                    Toast toast = Toast.makeText(getBaseContext(), " Bitte gib die Dauer an! ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                }
                else{
                    float nkcal = nSport.getMet() * user.getWeight() * Float.valueOf(editMenge.getText().toString());

                    int id = 0;

                    int index = sport.size();
                    sport.set(index-1, new Sport(id, 0, textMeal.getText().toString(), Float.valueOf(editMenge.getText().toString()), nSport.getMet(), Math.round(nkcal)));

                    Intent intent = new Intent(ShowSportActivity.this, HomeActivity.class);
                    intent.putExtra("User", user);
                    intent.putExtra("Sport", sport);
                    intent.putExtra("Fragment", 2);
                    startActivity(intent);



                }
            }
        });



        back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSportActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 2);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


    }
}
