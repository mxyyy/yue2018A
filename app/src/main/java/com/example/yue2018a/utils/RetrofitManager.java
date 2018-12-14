package com.example.yue2018a.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class RetrofitManager {

    private static final String BASE_URL = "https://api.douban.com/v2/";
    private final Retrofit retrofit;

    private static final class SINGLE_INSTANCE {
        private static final RetrofitManager _INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return SINGLE_INSTANCE._INSTANCE;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public <T> T create(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
