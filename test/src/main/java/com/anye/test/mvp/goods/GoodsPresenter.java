package com.anye.test.mvp.goods;

import com.anye.test.mvp.BasePresenter;
import com.anye.test.mvp.main.MainModel;
import com.anye.test.mvp.main.MainView;
import com.anye.test.mvp.model.GoodsBean;
import com.anye.test.mvp.model.GoodsRequest;
import com.anye.test.rxjava.ApiCallback;
import com.anye.test.rxjava.SubscriberCallBack;
import com.google.gson.Gson;
import com.wuxiaolong.androidutils.library.LogUtil;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by WuXiaolong
 * on 2015/9/23.
 */
public class GoodsPresenter extends BasePresenter<GoodsView<GoodsBean>> {

    public GoodsPresenter(GoodsView<GoodsBean> view) {
        attachView(view);
    }


    public void loadData(HashMap<String,Object> params) {
        mvpView.showLoading();
        addSubscription(apiStores.loadGoods(params),
                new SubscriberCallBack<>(new ApiCallback<GoodsBean>() {
                    @Override
                    public void onSuccess(GoodsBean model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }

}
