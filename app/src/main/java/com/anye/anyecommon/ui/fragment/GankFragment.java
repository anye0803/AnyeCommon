package com.anye.anyecommon.ui.fragment;

import android.view.View;

import com.anye.anyecommon.R;
import com.anye.base.ui.fragment.AbsBaseFragment;

/**
 * Created by lwz on 2016/6/28.
 */

public class GankFragment extends AbsBaseFragment {

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents(View rootView) {

    }



    @Override
    protected int getContentViewID() {
        return R.layout.act_login;
    }
}
