package com.example.admin.week4weekendhwamazonpablo;


import com.example.admin.week4weekendhwamazonpablo.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 9/18/2017.
 */

public class RetrofitHelper {
    public static final String BASE_URL = "http://de-coding-test.s3.amazonaws.com/";

    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    //create a static method to use the interface verbs
    public static Call<List<Response>> createCallWeather(){
        Retrofit retrofit = create();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService.getCityForecast();
    }

    //create an interface to have all the paths and verbs for the REST api to use
    interface ApiService {
        @GET("books.json")// id={id}APPID={key}")//{user} or {abc} will make it {abc} dynamically
        Call<List<Response>> getCityForecast();
    }

}

