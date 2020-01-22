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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private User user;
    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<Sport> sport = new ArrayList<>();
    private Fragment selectedFragment = null;

    private TextView score;
    private int whichFragment;
    private TextView textÜberschrift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        meals = intent.getParcelableArrayListExtra("Meals");
        sport = intent.getParcelableArrayListExtra("Sport");
        whichFragment = intent.getIntExtra("Fragment", 0);
        score = findViewById(R.id.score_text);

        score.setText("" + user.getScore());

        textÜberschrift = findViewById(R.id.text_überschrift);


        Bundle args = new Bundle();

        args.putParcelable("User", user);
        args.putParcelableArrayList("Meals", meals);
        args.putParcelableArrayList("Sport", sport);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if(whichFragment == 0) {
            selectedFragment = new TagebuchFragment();
            args.remove("Meals");
            args.remove("Sport");
            textÜberschrift.setText("Tagebuch");
            bottomNav.setSelectedItemId(R.id.nav_tagebuch);
        }
        else if(whichFragment == 1) {
            selectedFragment = new IchFragment();
            args.remove("Meals");
            args.remove("Sport");
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
            args.remove("Meals");
            args.remove("Sport");
            textÜberschrift.setText("Wartezimmer");
            bottomNav.setSelectedItemId(R.id.nav_wartezimmer);
        }
        else if(whichFragment == 4) {
            selectedFragment = new MehrFragment();
            args.remove("Meals");
            args.remove("Sport");
            textÜberschrift.setText("Mehr");
            bottomNav.setSelectedItemId(R.id.nav_mehr);
        }


        selectedFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectedFragment = null;

                    Bundle args = new Bundle();

                    args.putParcelable("User", user);
                    args.putParcelableArrayList("Meals", meals);
                    args.putParcelableArrayList("Sport", sport);

                    switch (item.getItemId()){
                        case R.id.nav_tagebuch:
                            selectedFragment = new TagebuchFragment();
                            args.remove("Meals");
                            args.remove("Sport");
                            textÜberschrift.setText("Tagebuch");
                            break;
                        case R.id.nav_ich:
                            selectedFragment = new IchFragment();
                            args.remove("Meals");
                            args.remove("Sport");
                            textÜberschrift.setText("Ich");
                            break;
                        case R.id.nav_add_event:
                            selectedFragment = new AddEventFragment();
                            args.remove("Meals");
                            args.remove("Sport");
                            textÜberschrift.setText("Ereignis hinzufügen");
                            break;
                        case R.id.nav_wartezimmer:
                            selectedFragment = new WartezimmerFragment();
                            args.remove("Meals");
                            args.remove("Sport");
                            textÜberschrift.setText("Wartezimmer");
                            break;
                        case R.id.nav_mehr:
                            selectedFragment = new MehrFragment();
                            args.remove("Meals");
                            args.remove("Sport");
                            textÜberschrift.setText("Mehr");
                            break;
                    }


                    selectedFragment.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


}
