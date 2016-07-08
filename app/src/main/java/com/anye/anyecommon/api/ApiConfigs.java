package com.anye.anyecommon.api;

import com.anye.anyecommon.model.GirlModel;
import com.anye.anyecommon.model.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lwz on 2016/6/28.
 */

public interface ApiConfigs {

    @GET("data/福利/10/{page}")
    Observable<GirlModel> getGirls(@Path("page") int page);

    @Headers({
            "X-Bmob-Application-Id: 82c75d3435a1a68a58df1574ba7e235a",
            "X-Bmob-REST-API-Key: f72a1c058aee798da27a3e39085e4155",
            "Content-Type: application/json"
    })
    @GET("Video")
    Observable<VideoModel> getVideoList();

}
