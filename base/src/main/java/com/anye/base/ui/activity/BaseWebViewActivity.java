package com.anye.base.ui.activity;

import android.view.View;

/**
 * Created by Anthony on 2016/5/11.
 * Class Note:
 */
public class BaseWebViewActivity extends AbsSwipeBackActivity{
    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewID() {
        return 0;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
}
