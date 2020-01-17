//JsonPlaceHolderApi.java

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

    @POST("users")
    Call<User> createUser(@Body User user);

    @GET("users")
    Call<List<User>> getUsers();

    @PUT("users/{id}")
    Call<User> putUser(@Path("id") int id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

    @POST("posts")
    Call<Post> createPost(@Body Post post);
    
    @GET("posts")
    Call<List<Post>> getPosts();

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    @GET("food")
    Call<Food> getFood();
    
    @GET("sport")
    Call<Sport> getFood();
}
