package com.example.nfcpointage.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {


    }

    public static synchronized Retrofit getInstance() {
        Retrofit  retrofit=new Retrofit.Builder()
                .baseUrl("https://backendstage.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;


    }
}
