package com.anye.test.mvp.goods;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 处理业务需要哪些方法
 */
public interface GoodsView<T> {

    void getDataSuccess(T model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
