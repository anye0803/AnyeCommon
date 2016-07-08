package com.anye.anyecommon.domain;

import android.support.annotation.Nullable;

import com.anye.base.presenter.BasePresenter;
import com.anye.base.ui.BaseView;

import java.util.List;

/**
 * 数据展示控制器
 * Created by anye on 2016/5/7.
 */
public interface BaseRefreshContract {

    interface View extends BaseView<Presenter> {

        //展示数据
        public void showMore(@Nullable List list);

        //停止刷新
        public void finishRefresh();

        //展示提示信息
        public void showSnackBar();

    }

    interface Presenter extends BasePresenter {
        void loadMore(int page);
    }
}
