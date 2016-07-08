package com.anye.anyecommon.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.BuildConfig;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WuXiaolong
 * on 2016/3/24.
 */
public class AppClient {
    public static Retrofit mRetrofit;
    private static final int DEFAULT_TIMEOUT = 10;
    public static Retrofit retrofit(String baseUrl) {
        if (mRetrofit == null) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(builder.build())
                    .build();
        }
        return mRetrofit;
    }
}
