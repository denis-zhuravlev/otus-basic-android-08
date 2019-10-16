package com.otus.otusbasicnetwork.api;

import com.otus.otusbasicnetwork.api.reponse.MovieJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {
    @GET("movies")
    Call<List<MovieJson>> getMovies();
}
