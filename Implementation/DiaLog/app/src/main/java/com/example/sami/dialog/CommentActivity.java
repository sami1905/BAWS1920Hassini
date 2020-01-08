package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private User user;
    private Bundle userBdl = new Bundle();
    private int postID;
    private int whichFragment;
    private int postOrComment;
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
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");
        postID = intent.getIntExtra("ID", 0);
        whichFragment = intent.getIntExtra("Fragment", 0);
        postOrComment = intent.getIntExtra("PostOrComment", 0);

        score = findViewById(R.id.score_text);

        score.setText("" + user.getScore());

        postList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PostAdapter(postList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Aus Fragment "Ich"; Meine Posts

        if(whichFragment == 1 && postOrComment == 0){
            getPosts();
            textBack = findViewById(R.id.button_back);
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CommentActivity.this, HomeActivity.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    extra.putInt("Fragment", 1);
                    intent.putExtras(extra);
                    startActivity(intent);
                }
            });

            addCommentButton = findViewById(R.id.add_comment_button);
            addCommentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CommentActivity.this, NewPostActivity.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    extra.putInt("ID", postID);
                    extra.putInt("Fragment", 1);

                    intent.putExtras(extra);
                    startActivity(intent);
                }
            });

            mAdapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    int clickedPostID = postList.get(position).getmID();
                    Intent intent = new Intent(CommentActivity.this, CommentActivity.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    extra.putInt("ID", clickedPostID);
                    extra.putInt("Fragment", 3);
                    extra.putInt("PostOrComment", 1);
                    intent.putExtras(extra);
                    startActivity(intent);


                }
            });

        }




        //Aus Fragment "Wartezimmer; Kommentare zu einem Post
        if(whichFragment == 3){
            getPost(postID);

            textBack = findViewById(R.id.button_back);
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(postOrComment == 1){
                        Intent intent = new Intent(CommentActivity.this, CommentActivity.class);
                        Bundle extra = new Bundle();
                        extra.putParcelable("User", user);
                        extra.putInt("ID", postID);
                        extra.putInt("Fragment", 1);
                        extra.putInt("PostOrComment", 0);
                        intent.putExtras(extra);
                        startActivity(intent);

                    }
                    else{
                        Intent intent = new Intent(CommentActivity.this, HomeActivity.class);
                        Bundle extra = new Bundle();
                        extra.putParcelable("User", user);
                        extra.putInt("ID", postID);
                        extra.putInt("Fragment", 3);
                        intent.putExtras(extra);
                        startActivity(intent);

                    }

                }
            });

            addCommentButton = findViewById(R.id.add_comment_button);
            addCommentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CommentActivity.this, NewCommentActivity.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    extra.putInt("ID", postID);

                    if(postOrComment == 1) extra.putInt("Fragment", 1);
                    intent.putExtras(extra);
                    startActivity(intent);
                }
            });
        }



    }

    public void getPost(int id){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.getPost(id);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();

                String uhrzeit = post.getTime();
                if(uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 2){
                    uhrzeit = uhrzeit.substring(0, 3) + "0" + uhrzeit.substring(3,4);
                }
                if(uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 1){
                    uhrzeit =  "0" + uhrzeit;
                }
                if(uhrzeit.length() == 3 && uhrzeit.indexOf(":") == 1){
                    uhrzeit = "0" + uhrzeit.substring(0,2) + "0" + uhrzeit.substring(2,3);
                }
                String text1 = post.getUserNickname() + " | " + post.getDate() + " " + uhrzeit + " Uhr";

                String text3 = String.valueOf(post.getComments().length);
                int resource = R.drawable.ic_comment_blue;
                if(text3.equals("0")){
                    text3 = " ";
                    resource = 0;
                }
                if(post.getUserID() == user.getId()) postList.add(new PostItem(text1, post.getText(), text3,resource, post.getId(), 0, R.color.colorPrimaryDark));
                else postList.add(new PostItem(text1, post.getText(), text3,resource, post.getId(), 0, R.color.colorPrimaryDark));
                mAdapter.notifyDataSetChanged();
                Comment[] comment = post.getComments();
                for(int i = 0; i < comment.length; i++){
                    String uhrzeit2 = comment[i].getTime();
                    if(uhrzeit2.length() == 4 && uhrzeit2.indexOf(":") == 2){
                        uhrzeit2 = uhrzeit2.substring(0, 3) + "0" + uhrzeit2.substring(3,4);
                    }
                    if(uhrzeit2.length() == 4 && uhrzeit2.indexOf(":") == 1){
                        uhrzeit2 =  "0" + uhrzeit2;
                    }
                    if(uhrzeit2.length() == 3 && uhrzeit2.indexOf(":") == 1){
                        uhrzeit2 = "0" + uhrzeit2.substring(0,2) + "0" + uhrzeit2.substring(2,3);
                    }
                    String text4 = "Antwort von: \n" + comment[i].getUserNickname() + " | " + comment[i].getDate() + " " + uhrzeit2 + " Uhr";

                    String text6 = String.valueOf(0);
                    int resource2 = R.drawable.ic_comment_blue;
                    if(text6.equals("0")){
                        text6 = " ";
                        resource2 = 0;
                    }
                    if(comment[i].getUserID() == user.getId()) postList.add(new PostItem(text4, comment[i].getText(), text6,resource2, comment[i].getId(), R.drawable.ic_delete, R.color.colorAccent));
                    else postList.add(new PostItem(text4, comment[i].getText(), text6,resource2, comment[i].getId(), 0, R.color.colorAccent));

                    mAdapter.notifyDataSetChanged();


                }


                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


    }

    public void getPosts(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> posts = response.body();
                    for(int i = posts.size()-1; i >= 0; i--){
                        if(user.getId() == posts.get(i).getUserID()) {
                            String uhrzeit = posts.get(i).getTime();
                            if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 2) {
                                uhrzeit = uhrzeit.substring(0, 3) + "0" + uhrzeit.substring(3, 4);
                            }
                            if (uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 1) {
                                uhrzeit = "0" + uhrzeit;
                            }
                            if (uhrzeit.length() == 3 && uhrzeit.indexOf(":") == 1) {
                                uhrzeit = "0" + uhrzeit.substring(0, 2) + "0" + uhrzeit.substring(2, 3);
                            }
                            String text1 = posts.get(i).getUserNickname() + " | " + posts.get(i).getDate() + " " + uhrzeit + " Uhr";

                            String text3 = String.valueOf(posts.get(i).getComments().length);
                            int resource = R.drawable.ic_comment_blue;
                            if (text3.equals("0")) {
                                text3 = " ";
                                resource = 0;
                            }
                            postList.add(new PostItem(text1, posts.get(i).getText(), text3, resource, posts.get(i).getId(), R.drawable.ic_delete, R.color.colorPrimaryDark));


                            mAdapter.notifyDataSetChanged();
                        }


                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
    }


}
