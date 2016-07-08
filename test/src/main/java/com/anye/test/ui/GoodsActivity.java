package com.anye.test.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anye.swiperefreshlibrary.adapter.CommonRecyclerViewAdapter;
import com.anye.swiperefreshlibrary.adapter.RecyclerViewHolder;
import com.anye.swiperefreshlibrary.widget.KRecyclerView;
import com.anye.test.R;
import com.anye.test.adapter.GoodsInfoAdapter;
import com.anye.test.mvp.MvpActivity;
import com.anye.test.mvp.goods.GoodsPresenter;
import com.anye.test.mvp.goods.GoodsView;
import com.anye.test.mvp.model.GoodsBean;
import com.anye.test.mvp.model.GoodsInfoBean;
import com.wuxiaolong.androidutils.library.LogUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lwz on 2016/6/23.
 */

public class GoodsActivity extends MvpActivity<GoodsPresenter> implements GoodsView<GoodsBean>, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.news_list)
    KRecyclerView krecyclerView;
    @Bind(R.id.tv_load_empty)
    TextView mTvLoadEmpty;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;

    private GoodsInfoAdapter mAdapter;
    private List<GoodsInfoBean> mGoodsList = new ArrayList<>();

    private boolean isRefreshed = false;
    private int maxScore = 0;
    private int pageSize = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);
        initUi();
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter(this);
    }

    @Override
    public void getDataSuccess(GoodsBean model) {
        onTopicRecommendReceived(model);
    }

    @Override
    public void getDataFail(String msg) {
        onTopicRecommendError();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        onTopicRecommendCompleted();
    }


    private void initUi() {
        // 设置Adapter
        mAdapter = new GoodsInfoAdapter(GoodsActivity.this, mGoodsList, R.layout.item_goods);
        krecyclerView.setAdapter(mAdapter,1, LinearLayout.VERTICAL);
        krecyclerView.setLoadDataLintener(new KRecyclerView.LoadDataListener() {
            @Override
            public void loadData(int page) {
                GoodsActivity.this.loadData();
            }
        });
        //make KRecyclerView know how many items in a page
        krecyclerView.setItemCount(10);
        krecyclerView.enableLoadMore();
        // 设置手势滑动监听器
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // 设置刷新时动画的颜色
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_primary);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshIf(!isRefreshed);
    }

    private void refreshIf(boolean prerequisite) {
        if (prerequisite) {
            loadData();
        }
    }

    private void loadData() {
        if (maxScore == 0) {
            mSwipeRefreshLayout.setRefreshing(true);
            isRefreshed = false;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("libraryID", "12");
        map.put("pageSize", pageSize);
        map.put("page", maxScore);
        mvpPresenter.loadData(map);

    }

    private void onTopicRecommendReceived(GoodsBean model) {
        mTvLoadEmpty.setVisibility(View.GONE);
        mTvLoadError.setVisibility(View.GONE);

        mGoodsList.addAll(model.getData());
        if (mGoodsList != null && mGoodsList.size() > 0) {
//            if (maxScore == 0) { // 第一页
//                mAdapter.notifyDataSetChanged();
//            } else {
//                mAdapter.addNewsListAndNofity(mGoodsList);
//            }
            mAdapter.notifyDataSetChanged();
            if (mGoodsList.size() < pageSize) {
                krecyclerView.cantLoadMore();
            } else {
                krecyclerView.enableLoadMore();
                maxScore++;
            }
        } else {
            if (maxScore == 0) {
                krecyclerView.cantLoadMore();
            } else {
                krecyclerView.enableLoadMore();
            }

            mTvLoadEmpty.setVisibility(View.VISIBLE);
            mTvLoadError.setVisibility(View.GONE);
        }
    }

    private void onTopicRecommendError() {
        isRefreshed = true;
        mSwipeRefreshLayout.setRefreshing(false);
        toastShow(getString(R.string.network_error));
        krecyclerView.enableLoadMore();
        if (maxScore == 0) {
            mTvLoadEmpty.setVisibility(View.GONE);
            mTvLoadError.setVisibility(View.VISIBLE);
        }
    }

    private void onTopicRecommendCompleted() {
        isRefreshed = true;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        maxScore = 0;
        mGoodsList.clear();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(GoodsActivity.this);
    }

}
