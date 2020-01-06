package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.YEAR;

public class NewPostActivity extends AppCompatActivity {

    private User user;
    private User updateUser;
    private EditText textEingabe;
    private Button buttonPosten;
    private TextView textAbbrechen;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");

        textEingabe = findViewById(R.id.text_eingabe);
        buttonPosten = findViewById(R.id.posten_button);
        textAbbrechen = findViewById(R.id.text_back);

        buttonPosten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastPostID();

                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(YEAR);
                int currentMonth = cal.get(Calendar.MONTH);
                int currentDay = cal.get(Calendar.DAY_OF_MONTH);
                int currentHour = cal.get(Calendar.HOUR_OF_DAY);
                int currentMin = cal.get(Calendar.MINUTE);

                String currentDate = currentMonth+1 + "/" + currentDay + "/" + currentYear;
                String currentTime = currentHour + ":" + currentMin;

                Post newPost = new Post(id+1, user.getId(), user.getNickname(), textEingabe.getText().toString(), currentDate, currentTime, 0, new Comment[]{});
                createPost(newPost);

                Intent intent = new Intent(NewPostActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 3);
                intent.putExtras(extra);
                startActivity(intent);


            }
        });




        textAbbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPostActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 2);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


    }

    public void createPost(Post post) {
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    public void getLastPostID(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() || response.code() == 200) {
                    List<Post> posts = response.body();
                    for (Post post : posts) {
                        id = post.getId();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    public void updateUser(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();

                for(User matchUser : users) {
                    if (matchUser.getId() == user.getId()) {
                        updateUser = matchUser;

                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
