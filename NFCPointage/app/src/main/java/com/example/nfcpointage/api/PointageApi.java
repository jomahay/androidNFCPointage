package com.example.nfcpointage.api;

import com.example.nfcpointage.modeles.Reponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PointageApi {

    @GET("pointage/{tag}")
    Call<Reponse> getPointageByTag(@Path("tag")String tag);
}
