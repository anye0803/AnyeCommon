package com.anye.rxretrofitlibrary.api;



import com.anye.rxretrofitlibrary.bean.GirlData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/19.
 */
public interface ApiService {
    @GET("data/福利/10/{page}")
    Observable<GirlData> getGirls(@Path("page") int page);

//    @GET("data/Android/10/{page}")
//    Call<AndroidData> getAndroidData(@Path("page") int page);
}
