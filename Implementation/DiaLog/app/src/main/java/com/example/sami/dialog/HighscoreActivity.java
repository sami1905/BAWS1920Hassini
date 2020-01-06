package com.example.sami.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HighscoreActivity extends AppCompatActivity {
    private User user;

    private TextView[] textsHighscore = new TextView[12];
    private List<User> userList;
    private TextView textBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");

        textsHighscore[0] = findViewById(R.id.highscore_1);
        textsHighscore[1] = findViewById(R.id.highscore_2);
        textsHighscore[2] = findViewById(R.id.highscore_3);
        textsHighscore[3] = findViewById(R.id.highscore_4);
        textsHighscore[4] = findViewById(R.id.highscore_5);
        textsHighscore[5] = findViewById(R.id.highscore_6);
        textsHighscore[6] = findViewById(R.id.highscore_7);
        textsHighscore[7] = findViewById(R.id.highscore_8);
        textsHighscore[8] = findViewById(R.id.highscore_9);
        textsHighscore[9] = findViewById(R.id.highscore_10);
        textsHighscore[10] = findViewById(R.id.highscore_11);
        textsHighscore[11] = findViewById(R.id.highscore_12);

        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList = response.body();

                User temp;
                for(int i = 1; i < userList.size(); i++) {
                    for(int j = 0; j < userList.size()-i; j++) {
                        if(userList.get(j).getScore() < userList.get(j+1).getScore()) {
                            temp = userList.get(j);
                            userList.set(j, userList.get(j+1));
                            userList.set(j+1, temp);
                        }

                    }
                }

                for(int i = 0; i < userList.size() ; i++){
                    textsHighscore[i].setText(i+1 + ".: " + userList.get(i).getNickname() + " " + userList.get(i).getScore());
                    if(i == 9) break;



                }


                for(int i = 0; i < userList.size(); i++){
                    if(user.getId() == userList.get(i).getId() && i <10 ){
                        textsHighscore[i].setBackgroundColor(Color.rgb(2, 86,166));
                    }
                    else if (user.getId() == userList.get(i).getId() && i >= 10 ){
                        textsHighscore[10].setText("- - -");
                        textsHighscore[11].setText(i+1 + ".: " + userList.get(i).getNickname() + " " + userList.get(i).getScore());
                        textsHighscore[11].setBackgroundColor(Color.rgb(2, 86,166));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


        textBack = findViewById(R.id.button_back);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighscoreActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 4);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });







        }

    public static List<User> sort(List<User> list) {
        User temp;
        for(int i = 1; i < list.size(); i++) {
            for(int j = 0; j < list.size()-i; j++) {
                if(list.get(j).getScore() > list.get(j+1).getScore()) {
                    temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }

            }
        }
        return list;
    }
}
