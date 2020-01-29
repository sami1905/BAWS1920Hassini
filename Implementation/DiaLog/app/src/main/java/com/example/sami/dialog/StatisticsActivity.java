package com.example.sami.dialog;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsActivity extends AppCompatActivity {
    private User user;
    private int whichFragment;
    private TextView score;
    private TextView bzUnit;
    private TextView anzahlHypos;
    private TextView anzahlHypers;
    private TextView averageBZ;
    private TextView bePerMealUnit;
    private TextView bePerMeal;
    private TextView hba1c;
    private TextView iesPerDay;
    private TextView niedrig;
    private TextView ziel;
    private TextView hoch;

    private TextView textBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        whichFragment = intent.getIntExtra("Fragment", 0);
        score = findViewById(R.id.score_text);
        score.setText("" + user.getScore());
        bzUnit = findViewById(R.id.input_bz_unit);
        anzahlHypos = findViewById(R.id.input_anzahl_hypos);
        anzahlHypers = findViewById(R.id.input_anzahl_hypers);
        averageBZ = findViewById(R.id.input_bz);
        bePerMealUnit = findViewById(R.id.input_bePerMeal_Unit);
        bePerMeal = findViewById(R.id.input_bePerMeal);
        hba1c = findViewById(R.id.input_hba1c);
        iesPerDay = findViewById(R.id.input_iesperday);
        niedrig = findViewById(R.id.input_niedrig);
        ziel = findViewById(R.id.input_ziel);
        hoch = findViewById(R.id.input_hoch);
        if(user.getUnitKH().equals("BE")){
            bePerMealUnit.setText("BE/MAHLZEIT");
        }
        if(user.getUnitKH().equals("KE")){
            bePerMealUnit.setText("KE/MAHLZEIT");
        }
        if(user.getUnitBZ().equals("mg/dL")){
            bzUnit.setText("MG/DL");

        }
        if(user.getUnitBZ().equals("mmol/L")){
            bzUnit.setText("MMOL/L");

        }

        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<Statistik> call = jsonPlaceHolderApi.getStats(user.getId());

        call.enqueue(new Callback<Statistik>() {
            @Override
            public void onResponse(Call<Statistik> call, Response<Statistik> response) {
                Statistik stats = response.body();

                anzahlHypos.setText("" + stats.getCountLow());
                anzahlHypers.setText("" + stats.getCountHigh());
                if(user.getUnitBZ().equals("mmol/L")){
                    float bz = stats.getAverageSugar() * 10;
                    bz = Math.round(bz);
                    bz = bz/10;
                    averageBZ.setText("" + bz);
                }
                if(user.getUnitBZ().equals("mg/dL")){
                    int bz = Math.round(stats.getAverageSugar());
                    averageBZ.setText("" + bz);
                }
                float be = stats.getBePerMeal()*10;
                be = Math.round(be);
                be = be/10;
                bePerMeal.setText("" + be);

                float hb = stats.getHba1c()*10;
                hb = Math.round(hb);
                hb = hb/10;
                hba1c.setText("" + hb + "%");
                float ies = stats.getIesPerDay()*10;
                ies = Math.round(ies);
                ies = ies/10;
                iesPerDay.setText("" + ies);

                float n = (float)stats.getCountLow()/(float)stats.getCountBZ();
                n = n*1000;
                n = Math.round(n);
                n = n/10;
                niedrig.setText(" "+n +"%");

                float z = (float)stats.getCountInRange()/(float)stats.getCountBZ();
                z = z*1000;
                z = Math.round(z);
                z = z/10;
                ziel.setText(" "+z +"%");

                float h = (float)stats.getCountHigh()/(float)stats.getCountBZ();
                h = h*1000;
                h = Math.round(h);
                h = h/10;
                hoch.setText(" "+h +"%");



            }

            @Override
            public void onFailure(Call<Statistik> call, Throwable t) {

            }
        });

        textBack = findViewById(R.id.button_back);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 1);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });





    }
}
