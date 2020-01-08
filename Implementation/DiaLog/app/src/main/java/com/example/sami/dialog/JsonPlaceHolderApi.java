package com.example.sami.dialog;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("helloWorld")
    Call<ResponseBody> getConnection();

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);

    @POST("users")
    Call<User> createUser(@Body User user);

    @PUT("users/{id}")
    Call<User> putUser(@Path("id") int id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @DELETE("post/{id}")
    Call<Void> deletePost(@Path("id") int id);

    @POST("comment/{id}")
    Call<Comment> createComment(@Path("id")  int id, @Body Comment comment);

    @DELETE("comment/{id}")
    Call<Void> deleteComment(@Path("id") int id);
}
