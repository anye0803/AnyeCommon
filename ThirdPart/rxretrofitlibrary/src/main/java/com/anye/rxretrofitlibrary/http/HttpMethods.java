package com.anye.rxretrofitlibrary.http;

import com.anye.rxretrofitlibrary.api.ApiService;
import com.anye.rxretrofitlibrary.bean.HttpResult;
import com.anye.rxretrofitlibrary.bean.HttpResults;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;
/**
 * Created by lwz on 2016/5/4.
 */
public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    public static final String BASE_URL2 = "http://115.47.6.138";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private ApiService movieService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL2)
                .build();

        movieService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

//    /**
//     * 用于获取豆瓣电影Top250的数据
//     * @param subscriber  由调用者传过来的观察者对象
//     * @param start 起始位置
//     * @param count 获取长度
//     */
//    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count){
//
////        movieService.getTopMovie(start, count)
////                .map(new HttpResultFunc<List<Subject>>())
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
//
//        Observable observable = movieService.getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>());
//
//        toSubscribe(observable, subscriber);
//    }

//    public void getLoginInfo(Subscriber<HttpResults> result, User user){
//        Observable observable = movieService.setLogin(user);
//        toSubscribe(observable, result);
//    }
//
//    public void getFirendList(Subscriber<HttpResults> result, HashMap<String,String> user){
//        Observable observable = movieService.setFirendList(user);
//        toSubscribe(observable, result);
//    }



    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }

}
