package com.anye.anyecommon.ui.fragment;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anye.anyecommon.R;
import com.anye.anyecommon.domain.showgril.GirlAdapter;
import com.anye.anyecommon.domain.showgril.GirlContract;
import com.anye.anyecommon.domain.showgril.GirlPresenter;
import com.anye.anyecommon.model.entily.Girl;
import com.anye.anyecommon.ui.BaseRecyclerFragment;
import com.anye.anyecommon.util.SnackBarUtil;
import com.anye.base.ui.adapter.CustomViewPageAdapter;
import com.anye.base.ui.widget.CustomViewpager;
import com.anye.base.util.LogUtil;
import com.anye.base.util.ToastUtils;
import com.anye.swiperefreshlibrary.widget.KRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lwz on 2016/6/28.
 */

public class GirlFragment extends BaseRecyclerFragment implements GirlContract.View{
    @Bind(R.id.news_list)
    KRecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private CustomViewpager mViewpage;
    private int pageNum = 1;
    private List<Girl> mGirls = new ArrayList<>();
    private GirlAdapter girlAdapter;
    private GirlContract.Presenter mPresenter = new GirlPresenter(this);

    private boolean mIsFirstTouchedBottom = true;
    private boolean mIsFirstCreated = true;

    @Override
    protected int getCenterViewID() {
        return R.layout.common_recyclerview;
    }

    @Override
    protected int getTopBarViewID() {
        return 0;
    }

    @Override
    protected View getLoadingTargetView() {
//        centerLayout = (FrameLayout) mRootView.findViewById(R.id.fragment_content);
        return null;
    }

    @Override
    protected void initViewsAndEvents(View rootView) {
        setRecyclerView();
        setSwipeRefreshLayout();
    }

    // 广告数据
    public static List<Integer> getAdData() {
        List<Integer> adList = new ArrayList<>();
        adList.add(R.mipmap.abc_adv_1);
        adList.add(R.mipmap.abc_adv_2);
        adList.add(R.mipmap.abc_adv_3);
        return adList;
    }

    @Override
    public void setRecyclerView() {
        girlAdapter = new GirlAdapter(getActivity(), mGirls,R.layout.gril_layout);
        ToastUtils.getInstance().showToast(mGirls.size()+"");
        recyclerView.setAdapter(girlAdapter,2, LinearLayout.VERTICAL);
        recyclerView.setLoadDataLintener(new KRecyclerView.LoadDataListener() {
            @Override
            public void loadData(int page) {
                showToast("加载更多");
                mPresenter.loadMore(pageNum);
                LogUtil.e("loadData===>" + pageNum);
            }
        });
        recyclerView.setItemCount(5);
        recyclerView.enableLoadMore();
        View header = View.inflate(getActivity(), R.layout.anye_viewpager_view, null);
        mViewpage = (CustomViewpager) header.findViewById(R.id.viewpager);
        recyclerView.addHeaderView(header);
        CustomViewPageAdapter adapter = new CustomViewPageAdapter(getActivity(), getAdData());
        mViewpage.updateIndicatorView(getAdData().size(), 0);
        mViewpage.setAdapter(adapter);
        mViewpage.startScorll();
    }

    @Override
    public void setSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_process1, R.color.refresh_process2, R.color.refresh_process3);

        // 进入之后先加载，故refresh
        swipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        if (mIsFirstCreated){
            onRefresh();
        }
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showMore(@Nullable List list) {
        int size = mGirls.size();
        LogUtil.e("size===>" + size);
        for (Object girl : list) {
            mGirls.add((Girl) girl);
        }
        recyclerView.requestLayout();
        if (mGirls.size()-size==10){
            pageNum++;
        }
        girlAdapter.notifyDataSetChanged();
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
        pageNum = 1;
        mGirls.clear();
        swipeRefreshLayout.setRefreshing(true);
        LogUtil.e("onRefresh===>" + pageNum);
        mPresenter.loadMore(pageNum);
        mIsFirstCreated = false;
    }

    @Override
    public void setPresenter(GirlContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading(String msg) {
//        toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading() {
//        toggleShowLoading(false, "");
    }
}
