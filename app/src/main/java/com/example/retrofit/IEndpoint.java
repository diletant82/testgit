package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IEndpoint {
    @GET("/photos")
    Call<List<Album>> getAll();

    @GET("/photos/{id}")
    Call <Album>getAlbum(@Path("id") int i);

    @GET("/photos")
    Call<List<Album>> getAllById(@Query("albumId") int albumId);

}