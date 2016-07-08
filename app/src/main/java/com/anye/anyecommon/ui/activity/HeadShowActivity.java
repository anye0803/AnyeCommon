package com.anye.anyecommon.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.anye.anyecommon.R;
import com.anye.base.ui.activity.BaseActivity;
import com.anye.base.ui.widget.AnyeTitleBar;

import butterknife.Bind;

/**
 * 各种形状的头像展示
 * Created by lwz on 2016/7/7.
 */

public class HeadShowActivity extends BaseActivity {

    @Bind(R.id.title_bar)
    AnyeTitleBar titleBar;

    @Override
    protected void initViewsAndEvents() {
        titleBar.setTitle("头像展示");
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.act_head_show;
    }

}
