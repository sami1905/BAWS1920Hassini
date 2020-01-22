package com.example.sami.dialog;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private User user;
    private Fragment selectedFragment = null;
    private Bundle userBdl = new Bundle();
    private TextView score;
    private int whichFragment;
    private TextView textÜberschrift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");
        whichFragment = intent.getIntExtra("Fragment", 0);
        score = findViewById(R.id.score_text);

        score.setText("" + user.getScore());

        userBdl.putParcelable("User", user);

        textÜberschrift = findViewById(R.id.text_überschrift);



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if(whichFragment == 0) {
            selectedFragment = new TagebuchFragment();
            textÜberschrift.setText("Tagebuch");
            bottomNav.setSelectedItemId(R.id.nav_tagebuch);
        }
        else if(whichFragment == 1) {
            selectedFragment = new IchFragment();
            textÜberschrift.setText("Ich");
            bottomNav.setSelectedItemId(R.id.nav_ich);
        }
        else if(whichFragment == 2){
            selectedFragment = new AddEventFragment();
            textÜberschrift.setText("Ereignis hinzufügen");
            bottomNav.setSelectedItemId(R.id.nav_add_event);
        }
        else if(whichFragment == 3) {
            selectedFragment = new WartezimmerFragment();
            textÜberschrift.setText("Wartezimmer");
            bottomNav.setSelectedItemId(R.id.nav_wartezimmer);
        }
        else if(whichFragment == 4) {
            selectedFragment = new MehrFragment();
            textÜberschrift.setText("Mehr");
            bottomNav.setSelectedItemId(R.id.nav_mehr);
        }




        selectedFragment.setArguments(userBdl);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectedFragment = null;


                    switch (item.getItemId()){
                        case R.id.nav_tagebuch:
                            selectedFragment = new TagebuchFragment();
                            textÜberschrift.setText("Tagebuch");
                            break;
                        case R.id.nav_ich:
                            selectedFragment = new IchFragment();
                            textÜberschrift.setText("Ich");
                            break;
                        case R.id.nav_add_event:
                            selectedFragment = new AddEventFragment();
                            textÜberschrift.setText("Ereignis hinzufügen");
                            break;
                        case R.id.nav_wartezimmer:
                            selectedFragment = new WartezimmerFragment();
                            textÜberschrift.setText("Wartezimmer");
                            break;
                        case R.id.nav_mehr:
                            selectedFragment = new MehrFragment();
                            textÜberschrift.setText("Mehr");
                            break;
                    }

                    selectedFragment.setArguments(userBdl);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


}
