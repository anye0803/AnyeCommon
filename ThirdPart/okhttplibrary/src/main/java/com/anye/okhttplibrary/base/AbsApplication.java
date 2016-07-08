package com.anye.okhttplibrary.base;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.anye.okhttplibrary.util.LocalFileUncaughtExceptionHandler;

import java.io.File;


/**
 * Create By Anthony on 2016/1/15
 * 当前类注释：Application的父类，本项目中的Application将继承本类。
 * 当前功能：单例模式，异常捕获，由子类实现的获取url
 */
public abstract class AbsApplication extends Application {
    private static AbsApplication sInstance;
//    private Menu mFirstLevelMenu;

    public static AbsApplication app() {

        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileUncaughtExceptionHandler(this,
                Thread.getDefaultUncaughtExceptionHandler()));
    }


    @Override
    public File getCacheDir() {
        Log.i("getCacheDir", "cache sdcard state: " + Environment.getExternalStorageState());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());
                return cacheDir;
            }
        }

        File cacheDir = super.getCacheDir();
        Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());

        return cacheDir;
    }

}