package com.anye.base.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.anye.base.ui.widget.SystemBarTintManager;
import com.anye.base.ui.widget.loading.VaryViewHelperController;
import com.anye.base.ui.widget.netstatus.NetChangeObserver;
import com.anye.base.ui.widget.netstatus.NetStateReceiver;
import com.anye.base.ui.widget.netstatus.NetUtils;
import com.anye.base.util.AppUtils;
import com.anye.base.util.BaseAppManager;
import com.anye.base.util.NightModeHelper;

import butterknife.ButterKnife;

/**
 * Created by lwz on 2016/7/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG_LOG = null;// Log tag
    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    protected Context mContext = null;//context

    private NightModeHelper mNightModeHelper;//day night mode change

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = this;
        TAG_LOG = this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        mNightModeHelper = new NightModeHelper(this);

        if (getContentViewID() != 0) {
            setContentView(getContentViewID());
        } else {
            //support not to set content view
//            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        ButterKnife.bind(this);
        initViewsAndEvents();
    }


    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }


    /**
     * initialize views and events here
     */
    protected abstract void initViewsAndEvents();

    /**
     * bind layout resource file
     */
    protected abstract int getContentViewID();


    /**
     * change to day/night mode if needed
     */
    protected void changeDayNightMode() {
        mNightModeHelper.toggle();
    }
    /**
     * show toast
     */
    protected void showToast(String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !AppUtils.isEmpty(msg)) {
//            Snackbar.make(getLoadingTargetView(), msg, Snackbar.LENGTH_SHORT).show();
        }
    }
}
