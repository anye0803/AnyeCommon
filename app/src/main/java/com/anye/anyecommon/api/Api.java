package com.anye.anyecommon.api;


import android.util.Log;

import com.anye.anyecommon.BaseApplication;
import com.anye.base.ui.widget.netstatus.NetUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by baixiaokang on 16/3/9.
 */
public class Api {

    public static final String X_LC_Id = "i7j2k7bm26g7csk7uuegxlvfyw79gkk4p200geei8jmaevmx";
    public static final String X_LC_Key = "n6elpebcs84yjeaj5ht7x0eii9z83iea8bec9szerejj7zy3";
    public static final String BASE_URL = "https://leancloud.cn:443/1.1/";

    public static final int DEFAULT_TIMEOUT = 5;

    public Retrofit retrofit;
    public ApiConfigs movieService;

    //构造方法私有
    private Api() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();
        movieService = retrofit.create(ApiConfigs.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

}