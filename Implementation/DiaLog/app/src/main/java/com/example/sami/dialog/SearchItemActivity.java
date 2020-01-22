package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerviewSearch;
    private SearchAdapter mAdapterSearch;
    private RecyclerView.LayoutManager mLayoutManagerSearch;
    ArrayList<SearchItem> searchList;
    ArrayList<SearchItem> filteredList;
    private EditText nMeal;
    private TextView back;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);


        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        searchList = new ArrayList<>();

        back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchItemActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 2);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

        nMeal = findViewById(R.id.text_eingabe);
        nMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });







        mRecyclerviewSearch = findViewById(R.id.recycleview_search_items);
        mLayoutManagerSearch = new LinearLayoutManager(this);
        mAdapterSearch = new SearchAdapter(searchList);

        mRecyclerviewSearch.setLayoutManager(mLayoutManagerSearch);
        mRecyclerviewSearch.setAdapter(mAdapterSearch);

        mAdapterSearch.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                searchList.get(position);

                JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

                Call<List<Food>> call = jsonPlaceHolderApi.getFood();

                call.enqueue(new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                        List<Food> food = response.body();
                        for(int i = 0; i < food.size(); i++) {
                            if(searchList.get(position).getId() == food.get(i).getId()){
                                Food meal = food.get(i);

                                Intent intent = new Intent(SearchItemActivity.this, ShowMealActifity.class);
                                Bundle extra = new Bundle();
                                extra.putParcelable("User", user);
                                extra.putParcelable("Food", meal);
                                intent.putExtras(extra);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Food>> call, Throwable t) {

                    }
                });

            }
        });

        getFood();

        mAdapterSearch.notifyDataSetChanged();
    }

    public void getFood(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<Food>> call = jsonPlaceHolderApi.getFood();

        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> food = response.body();
                for(int i = 0; i < food.size(); i++) {
                    searchList.add(new SearchItem(food.get(i).getId(), food.get(i).getName() + ", " + String.valueOf(food.get(i).getAmount()) + " " + food.get(i).getUnit(),
                            String.valueOf(food.get(i).getKcal()) + " kcal - " + String.valueOf(food.get(i).getKh())  + "g Kohlenhydrate - " +
                            String.valueOf(food.get(i).getE()) + "g Eiweiß - " + String.valueOf(food.get(i).getF()) + "g Fett"));
                    mAdapterSearch.notifyDataSetChanged();
                }
                mAdapterSearch.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

    }

    private void filter(String text){
        filteredList = new ArrayList<>();
        for(SearchItem item : searchList){
            if(item.getText().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        mAdapterSearch.filterList(filteredList);
    }

}
