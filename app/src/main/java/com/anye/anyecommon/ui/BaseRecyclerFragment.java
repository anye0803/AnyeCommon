package com.anye.anyecommon.ui;

import android.support.v4.widget.SwipeRefreshLayout;

import com.anye.base.ui.activity.AbsBaseActivity;
import com.anye.base.ui.fragment.AbsBaseFragment;
import com.anye.base.ui.fragment.AbsTitleFragment;

/**
 * Created by lwz on 2016/6/28.
 */

public abstract class BaseRecyclerFragment extends AbsTitleFragment implements SwipeRefreshLayout.OnRefreshListener{
    public abstract void setRecyclerView();
    public abstract void setSwipeRefreshLayout();
}
