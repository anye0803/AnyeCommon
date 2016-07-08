package com.anye.anyecommon.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.anye.anyecommon.R;
import com.anye.anyecommon.domain.showgril.GirlAdapter;
import com.anye.anyecommon.model.entily.Girl;
import com.anye.base.ui.adapter.CustomViewPageAdapter;
import com.anye.base.ui.fragment.AbsBaseFragment;
import com.anye.base.ui.widget.CustomViewpager;
import com.anye.swiperefreshlibrary.widget.KRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lwz on 2016/6/27.
 */

public class HomeFragment extends Fragment {

    @Bind(R.id.news_list)
    KRecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private CustomViewpager mViewpage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_recyclerview, container, false);
        ButterKnife.bind(this, view);
        initViewsAndEvents();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View header = View.inflate(getActivity(), R.layout.anye_viewpager_view, null);
        mViewpage = (CustomViewpager) header.findViewById(R.id.viewpager);
        mRecyclerView.addHeaderView(header);
        CustomViewPageAdapter adapter = new CustomViewPageAdapter(getActivity(), getAdData());
        mViewpage.updateIndicatorView(getAdData().size(), 0);
        mViewpage.setAdapter(adapter);
        mViewpage.startScorll();
    }

    protected void initViewsAndEvents() {

    }


    // 广告数据
    public List<Integer> getAdData() {
        List<Integer> adList = new ArrayList<>();
        adList.add(R.mipmap.abc_adv_1);
        adList.add(R.mipmap.abc_adv_2);
        adList.add(R.mipmap.abc_adv_3);
        return adList;
    }
}
