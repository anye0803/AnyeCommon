package com.anye.anyecommon.domain.showvideo;

import com.android.tedcoder.wkvideoplayer.model.Video;
import com.anye.anyecommon.api.ApiConfigs;
import com.anye.anyecommon.api.HttpClient;
import com.anye.anyecommon.domain.BaseRefreshContract;
import com.anye.anyecommon.model.VideoModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 视频
 * Created by lwz on 2016/6/28.
 */

public class VideoPresenter implements BaseRefreshContract.Presenter{

    private VideoModel mVideoModel = null;
    private BaseRefreshContract.View mView;
//    private Retrofit mRetrofit;
    private ApiConfigs mApiConfigs;

    public VideoPresenter(BaseRefreshContract.View baseView) {
        this.mView = baseView;
        init();
    }

    private void init() {
//        mRetrofit = AppClient.retrofit("https://api.bmob.cn/1/classes/");
        mApiConfigs = HttpClient.getIns("https://api.bmob.cn/1/classes/").createService(ApiConfigs.class);
//        mApiConfigs = mRetrofit.create(ApiConfigs.class);
        mView.setPresenter(this);
    }

    @Override
    public void loadMore(int page) {
        mView.showLoading("加载中。。。");
        Observable observable = mApiConfigs.getVideoList()
                .subscribeOn(Schedulers.io())
                .observeOn( AndroidSchedulers.mainThread());
        observable.subscribe(new Subscriber<VideoModel>() {
            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.finishRefresh();
                mView.showSnackBar();
            }

            @Override
            public void onNext(VideoModel videoData) {
                mView.finishRefresh();
                List<Video> newVideo = videoData.getVideoList();
                mView.showMore(newVideo);
            }
        });
    }

    @Override
    public void start() {

    }
}
