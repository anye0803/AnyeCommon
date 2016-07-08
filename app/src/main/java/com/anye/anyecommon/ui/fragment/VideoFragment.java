package com.anye.anyecommon.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.android.tedcoder.wkvideoplayer.model.Video;
import com.anye.anyecommon.R;
import com.anye.anyecommon.domain.BaseRefreshContract;
import com.anye.anyecommon.domain.showvideo.VideoAdapter;
import com.anye.anyecommon.domain.showvideo.VideoPresenter;
import com.anye.anyecommon.ui.BaseRecyclerFragment;
import com.anye.anyecommon.ui.activity.VideoPlayerActivity;
import com.anye.anyecommon.util.SnackBarUtil;
import com.anye.base.util.LogUtil;
import com.anye.swiperefreshlibrary.adapter.call.MyItemClickListener;
import com.anye.swiperefreshlibrary.widget.KRecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lwz on 2016/6/28.
 */

public class VideoFragment extends BaseRecyclerFragment implements BaseRefreshContract.View {

    @Bind(R.id.news_list)
    KRecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    private List<Video> mVideos = new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    private BaseRefreshContract.Presenter mPresenter = new VideoPresenter(this);

    private boolean mIsFirstTouchedBottom = true;
    private boolean mIsFirstCreated = true;

    @Override
    protected void initViewsAndEvents(View rootView) {

        setRecyclerView();
        setSwipeRefreshLayout();
    }

    @Override
    protected int getCenterViewID() {
        return R.layout.common_recyclerview;
    }

    @Override
    protected int getTopBarViewID() {
        return 0;
    }

    @Override
    public void setRecyclerView() {
        mVideoAdapter = new VideoAdapter(getActivity(), mVideos);
        recyclerView.setAdapter(mVideoAdapter, 1, LinearLayout.VERTICAL);
        mVideoAdapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("video", mVideos.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setLoadDataLintener(new KRecyclerView.LoadDataListener() {
            @Override
            public void loadData(int page) {
                showToast("加载更多");
                LogUtil.e("page==>" + pageNum);
                mPresenter.loadMore(pageNum);
            }
        });
        recyclerView.setItemCount(5);
        recyclerView.enableLoadMore();
    }

    @Override
    public void setSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_process1, R.color.refresh_process2, R.color.refresh_process3);
        // 进入之后先加载，故refresh
        swipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        if (mIsFirstCreated) {
            onRefresh();
        }
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void onTopicRecommendReceived() {

        if (mVideos != null && mVideos.size() > 0) {
            if (pageNum == 1) { // 第一页
                mVideoAdapter.updateNewsListAndNotify(mVideos);
            } else {
                mVideoAdapter.addNewsListAndNofity(mVideos);
            }
            if (mVideos.size() < pageSize) {
                recyclerView.cantLoadMore();
            } else {
                recyclerView.enableLoadMore();
//                pageNum++;
            }
        } else {
            if (pageNum == 1) {
                recyclerView.cantLoadMore();
            } else {
                recyclerView.enableLoadMore();
            }
//            mTvLoadEmpty.setVisibility(View.VISIBLE);
//            mTvLoadError.setVisibility(View.GONE);
        }
    }


    @Override
    public void showMore(@Nullable List list) {
        int size = mVideos.size();
        for (Object video : list) {
            mVideos.add((Video) video);
        }
        recyclerView.requestLayout();
        if (mVideos.size() - size == 10) {
            pageNum++;
        }
        onTopicRecommendReceived();
    }

    @Override
    public void finishRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showSnackBar() {
        SnackBarUtil.showSnackBar(recyclerView, this);
    }

    @Override
    public void onRefresh() {
        mVideos.clear();
        swipeRefreshLayout.setRefreshing(true);
        mPresenter.loadMore(pageNum);
        mIsFirstCreated = false;
    }

    @Override
    public void setPresenter(BaseRefreshContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, "");
    }

}
