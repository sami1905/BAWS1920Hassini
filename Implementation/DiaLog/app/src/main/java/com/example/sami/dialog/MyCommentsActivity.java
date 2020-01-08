package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCommentsActivity extends AppCompatActivity {

    private User user;
    private int whichFragment;
    private TextView score;
    private ImageView addCommentButton;
    private TextView textBack;

    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<PostItem> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comments);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");
        whichFragment = intent.getIntExtra("Fragment", 0);

        score = findViewById(R.id.score_text);

        score.setText("" + user.getScore());

        postList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PostAdapter(postList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        getComments();
        mAdapter.notifyDataSetChanged();

        textBack = findViewById(R.id.button_back);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCommentsActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 1);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
    }

    public void getComments(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();

                for(int i = 0; i < posts.size(); i++){
                    Comment comments[] = posts.get(i).getComments();
                    for(int j = comments.length-1 ; j>=0; j--){
                        if(user.getId() == comments[j].getUserID()){
                            String uhrzeit = comments[j].getTime();
                            if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 2) {
                                uhrzeit = uhrzeit.substring(0, 3) + "0" + uhrzeit.substring(3, 4);
                            }
                            if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 1) {
                                uhrzeit = "0" + uhrzeit;
                            }
                            if (uhrzeit.length() == 3 && uhrzeit.indexOf(":") == 1) {
                                uhrzeit = "0" + uhrzeit.substring(0, 2) + "0" + uhrzeit.substring(2, 3);
                            }

                            String text1 = "Antwort auf einen Post von: " + posts.get(i).getUserNickname() + "\n" +
                                    comments[j].getUserNickname() + " | " + comments[j].getDate() + " " + uhrzeit + " Uhr";
                            postList.add(new PostItem(text1, comments[j].getText(), " ", 0, comments[j].getId(), R.drawable.ic_delete, R.color.colorAccent));


                            mAdapter.notifyDataSetChanged();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    mAdapter.notifyDataSetChanged();
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
