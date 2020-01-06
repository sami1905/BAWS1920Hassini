package com.example.sami.dialog;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.YEAR;

public class NewCommentActivity extends AppCompatActivity {
    private User user;
    private int postID;

    private User updateUser;
    private EditText textEingabe;
    private Button buttonPosten;
    private TextView textAbbrechen;
    private int currentPostId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");
        postID = intent.getIntExtra("ID", 0);


        textEingabe = findViewById(R.id.text_eingabe);
        buttonPosten = findViewById(R.id.posten_button);
        textAbbrechen = findViewById(R.id.text_back);
        getPost(postID);








        buttonPosten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(YEAR);
                int currentMonth = cal.get(Calendar.MONTH);
                int currentDay = cal.get(Calendar.DAY_OF_MONTH);
                int currentHour = cal.get(Calendar.HOUR_OF_DAY);
                int currentMin = cal.get(Calendar.MINUTE);

                String currentDate = currentMonth+1 + "/" + currentDay + "/" + currentYear;
                String currentTime = currentHour + ":" + currentMin;
                Comment newComment = new Comment(currentPostId, user.getId(), user.getNickname(), textEingabe.getText().toString(), currentDate, currentTime );
                createComment(postID, newComment);

                user.setScore(user.getScore()+20);
                updateUser();

                Intent intent = new Intent(NewCommentActivity.this, CommentActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("ID", postID);
                intent.putExtras(extra);
                startActivity(intent);


            }
        });


    }
    public void getPost(int id) {
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.getPost(id);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                textEingabe.setHint("Antworte auf den Post von " + post.getUserNickname() + ".");
                if(post.getComments().length == 0) currentPostId = 0;
                else currentPostId = post.getComments()[post.getComments().length-1].getId();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    public void createComment(int id, Comment comment){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<Comment> call = jsonPlaceHolderApi.createComment(id, comment);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {

            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }
    public void updateUser(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<User> call = jsonPlaceHolderApi.putUser(user.getId(), user);

        call.enqueue(new Callback<com.example.sami.dialog.User>() {
            @Override
            public void onResponse(Call<com.example.sami.dialog.User> call, Response<com.example.sami.dialog.User> response) {

            }

            @Override
            public void onFailure(Call<com.example.sami.dialog.User> call, Throwable t) {

            }
        });
    }


}
