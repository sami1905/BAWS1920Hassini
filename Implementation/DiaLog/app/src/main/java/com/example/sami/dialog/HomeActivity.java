package com.example.sami.dialog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private User user;
    private Fragment selectedFragment = null;
    private Bundle userBdl = new Bundle();
    private TextView score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        score = findViewById(R.id.score_text);

        score.setText("" + user.getScore());

        userBdl.putParcelable("User", user);



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        selectedFragment = new TagebuchFragment();
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

                            break;
                        case R.id.nav_ich:
                            selectedFragment = new IchFragment();
                            break;
                        case R.id.nav_add_event:
                            selectedFragment = new AddEventFragment();
                            break;
                        case R.id.nav_wartezimmer:
                            selectedFragment = new WartezimmerFragment();
                            break;
                        case R.id.nav_mehr:
                            selectedFragment = new MehrFragment();
                            break;
                    }

                    selectedFragment.setArguments(userBdl);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


}
