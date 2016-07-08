package com.anye.anyecommon.ui;

import android.content.Intent;
import android.view.View;

import com.anye.anyecommon.R;
import com.anye.anyecommon.ui.activity.MainActivity;
import com.anye.anyecommon.ui.activity.TabMainActivity;
import com.anye.base.ui.activity.AbsBaseActivity;

import butterknife.OnClick;

/**
 * 登录
 * Created by lwz on 2016/5/17.
 */
public class LoginActivity extends AbsBaseActivity{

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.button,R.id.button2})
    void onclick(View view){
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, MainActivity.class));
                finish();
            break;
            case R.id.button2:
                startActivity(new Intent(this, TabMainActivity.class));
                finish();
            break;
        }
    }
    @Override
    protected int getContentViewID() {
        return R.layout.act_login;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
}
