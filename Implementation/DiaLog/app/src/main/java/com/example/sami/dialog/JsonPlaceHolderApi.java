package com.example.sami.dialog;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("helloWorld")
    Call<ResponseBody> getConnection();

    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<User> createUser(@Body User user);
}
