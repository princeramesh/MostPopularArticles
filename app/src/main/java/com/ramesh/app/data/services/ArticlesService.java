package com.ramesh.app.data.services;

import com.ramesh.app.data.entities.ArticlesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticlesService {

    @GET("v2/mostviewed/all-sections/{days}.json")
    Observable<ArticlesResponse> getArticles(@Path("days") int days,@Query("api-key") String apiKey);

}
