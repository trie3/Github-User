package com.suwarso.tri.githubuser.utils.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.suwarso.tri.githubuser.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URLAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
