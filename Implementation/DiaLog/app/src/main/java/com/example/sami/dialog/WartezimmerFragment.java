package com.example.sami.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WartezimmerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private User user;
    private TextView text;
    private ArrayList<PostItem> postList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wartezimmer, container, false);

        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }



        postList = new ArrayList<>();
        mRecyclerView = v.findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new PostAdapter(postList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        getPosts();

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int clickedPostID = postList.get(position).getmID();
                Intent intent = new Intent(getContext(), CommentActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("ID", clickedPostID);
                extra.putInt("Fragment", 3);
                intent.putExtras(extra);
                startActivity(intent);


            }
        });



        return v;
    }
    public void getPosts(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for(int i = posts.size()-1; i >= 0; i--){
                    String uhrzeit = posts.get(i).getTime();
                    if(uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 2){
                        uhrzeit = uhrzeit.substring(0, 3) + "0" + uhrzeit.substring(3,4);
                    }
                    if(uhrzeit.length() == 4 && uhrzeit.indexOf(":") == 1){
                        uhrzeit =  "0" + uhrzeit;
                    }
                    if(uhrzeit.length() == 3 && uhrzeit.indexOf(":") == 1){
                        uhrzeit = "0" + uhrzeit.substring(0,2) + "0" + uhrzeit.substring(2,3);
                    }
                    String text1 = posts.get(i).getUserNickname() + " | " + posts.get(i).getDate() + " " + uhrzeit + " Uhr";

                    String text3 = String.valueOf(posts.get(i).getComments().length);
                    int resource = R.drawable.ic_comment_blue;
                    if(text3.equals("0")){
                        text3 = " ";
                        resource = 0;
                    }
                    if(posts.get(i).getUserID() == user.getId())
                    postList.add(new PostItem(text1, posts.get(i).getText(), text3,resource, posts.get(i).getId(), R.drawable.ic_delete,R.color.colorPrimaryDark));
                    else postList.add(new PostItem(text1, posts.get(i).getText(), text3,resource, posts.get(i).getId(), 0,R.color.colorPrimaryDark));


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
