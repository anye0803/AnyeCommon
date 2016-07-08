package com.anye.anyecommon.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.anye.anyecommon.R;
import com.anye.anyecommon.ui.fragment.DiscoveryFragment;
import com.anye.anyecommon.ui.fragment.HomeFragment;
import com.anye.anyecommon.ui.fragment.PersonFragment;
import com.anye.base.ui.activity.AbsBaseActivity;
import com.anye.base.ui.widget.TabStripView;

import butterknife.Bind;


/**
 *
 * Created by lwz on 2016/6/27.
 */
public class TabMainActivity extends AbsBaseActivity{
    @Bind(R.id.navigateTabBar)
    TabStripView navigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对应xml中的containerId
        navigateTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.abc_tab_text_normal));
        //对应xml中的navigateTabSelectedTextColor
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.colorPrimary));

        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(HomeFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_home, R.mipmap.ic_tab_bar_home_selected, R.string.abc_tab_text_home));
        navigateTabBar.addTab(DiscoveryFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_find, R.mipmap.ic_tab_bar_find_selected, R.string.abc_tab_text_find));
        navigateTabBar.addTab(PersonFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_person, R.mipmap.ic_tab_bar_person_selected, R.string.abc_tab_text_person));

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.act_tab_main;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        navigateTabBar.onSaveInstanceState(outState);
    }
}
