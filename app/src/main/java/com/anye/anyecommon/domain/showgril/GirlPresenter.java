package com.anye.anyecommon.domain.showgril;

import com.android.tedcoder.wkvideoplayer.model.Video;
import com.anye.anyecommon.api.ApiConfigs;
import com.anye.anyecommon.api.HttpClient;
import com.anye.anyecommon.model.GirlModel;
import com.anye.anyecommon.model.VideoModel;
import com.anye.anyecommon.model.entily.Girl;
import com.anye.base.presenter.BasePresenter;
import com.anye.base.util.LogUtil;
import com.anye.base.util.ToastUtils;
import com.anye.rxretrofitlibrary.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 美女的控制器
 * Created by lwz on 2016/6/28.
 */

public class GirlPresenter implements GirlContract.Presenter{

    private GirlModel mGirlData = null;
    private GirlContract.View mView;
    private ApiConfigs mService;

    public GirlPresenter(GirlContract.View baseView) {
        this.mView = baseView;
        init();
    }

    private void init() {
//        mRetrofit = new Retrofit.Builder()
//                .baseUrl("http://gank.io/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        mService = mRetrofit.create(ApiConfigs.class);
        mService = HttpClient.getIns("http://gank.io/api/").createService(ApiConfigs.class);
        mView.setPresenter(this);
    }

    @Override
    public void loadMore(int page) {
        LogUtil.e("page==>" + page);
//        final Call<GirlModel> girlDataCall = mService.getGirls(page);
//        girlDataCall.enqueue(new Callback<GirlModel>() {
//            @Override
//            public void onResponse(Call<GirlModel> call, Response<GirlModel> response) {
//                mGirlData = response.body();
//                List<Girl> newGirls = mGirlData.getGirls();
//                mView.showMore(newGirls);
//                mView.finishRefresh();
//            }
//
//            @Override
//            public void onFailure(Call<GirlModel> call, Throwable t) {
//                mView.finishRefresh();
//                mView.showSnackBar();
//            }
//        });
        Observable observable = mService.getGirls(page)
                .subscribeOn(Schedulers.io())
                .observeOn( AndroidSchedulers.mainThread());
        observable.subscribe(new Subscriber<GirlModel>() {
            @Override
            public void onCompleted() {
//                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.finishRefresh();
                mView.showSnackBar();
            }

            @Override
            public void onNext(GirlModel girlModel) {
                mView.finishRefresh();
                List<Girl> girls = girlModel.getGirls();
                mView.showMore(girls);
            }
        });
    }

    @Override
    public void start() {

    }
}
