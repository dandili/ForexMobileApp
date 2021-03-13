package com.example.apppiptips.newsapi;

import com.example.apppiptips.models.News;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("everything") // Fetches everything in domain sorted by recent first
    Call<News> getNews(
            @Query("domains") String domain, // Allows the domain to be changed
            @Query("apiKey") String apiKey
    );
}
