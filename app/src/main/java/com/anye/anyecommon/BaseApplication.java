package com.anye.anyecommon;

import android.content.Context;
import android.content.res.Resources;

import com.anye.base.AbsApplication;

/**
 * Created by lwz on 2016/5/17.
 */
public class BaseApplication extends AbsApplication {

    private static BaseApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }
}
