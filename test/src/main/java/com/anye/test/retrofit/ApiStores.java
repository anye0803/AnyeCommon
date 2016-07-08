package com.anye.test.retrofit;


import com.anye.test.mvp.main.MainModel;
import com.anye.test.mvp.model.GoodsBean;
import com.anye.test.mvp.model.GoodsRequest;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by WuXiaolong
 * on 2016/3/24.
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";
    String BASE = "http://123.57.13.118/warehouse/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadData(@Path("cityId") String cityId);

    @FormUrlEncoded
    @POST("queryWareGoods.action")
    Observable<GoodsBean> loadGoods(@FieldMap HashMap<String, Object> map);

//    @POST("queryWareGoods.action")
//    Observable<GoodsBean> loadGoods(@Body GoodsRequest jsonstr);

//    @Headers("Content-Type: application/json")
//    @POST("queryWareGoods.action")
//    Observable<GoodsBean> loadGoods(@Body HashMap<String, Object> map);

//    @FormUrlEncoded
//    @POST("queryWareGoods.action")
//    Observable<GoodsBean> loadGoods(@Field("libraryID") String libraryID,@Field("pageSize") int pageSize,@Field("page") int page);
}
