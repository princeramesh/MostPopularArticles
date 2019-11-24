package com.ramesh.app;

import com.ramesh.app.data.entities.ArticlesResponse;
import com.ramesh.app.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestUtils {

    private String getJson(String path) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    ArticlesResponse getArticles() {
        return provideJson().fromJson(getJson("articles.json"), ArticlesResponse.class);
    }

    private Gson provideJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideJson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.URL)
                .client(okHttpClient())
                .build();
    }

}
