package com.example.sami.dialog;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private String url = "http://192.168.0.17:3000/";
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.0.22:3000/";

    public static Retrofit getRestService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public String getUrl() {
        return url;
    }
}
