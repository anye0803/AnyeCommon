package com.anye.okhttplibrary.core;

import android.content.Context;
import android.widget.Toast;

import com.anye.okhttplibrary.R;
import com.anye.okhttplibrary.http.HttpUtil;
import com.anye.okhttplibrary.http.callback.StringHttpCallback;
import com.anye.okhttplibrary.http.request.HttpRequest;
import com.anye.okhttplibrary.util.NetUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by lwz on 2016/5/25.
 */
public class CommonRequest {
    /**
     * 抽取公共的请求方法
     * @param urlStr
     * @param map
     * @param callback
     */
    public static void setNetRequest(final Context context, String urlStr, HashMap<String,Object> map, final NetModelCallback callback){
        HttpRequest httpRequest = new HttpRequest.Builder()
                .url(urlStr)
                .params(map)
                .build();
        HttpUtil.getInstance().loadString(httpRequest, new StringHttpCallback() {

            @Override
            public void onResponse(String response) {
               callback.onSuccess(response);
            }

            @Override
            public void onError(String error) {
                if (!NetUtils.isNetworkAvailable(context)) {
                    Toast.makeText(context, context.getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onEnd() {
                super.onEnd();
            }
        });
    }

    /**
     * 抽取公共的请求方法
     * 上传图片
     * @param urlStr
     * @param map
     * @param callback
     */
    public static void setNetPicRequest(final Context context, String urlStr, HashMap<String,Object> map, HashMap<String,File> fileMap, final NetModelCallback callback){
        HttpRequest httpRequest = new HttpRequest.Builder()
                .url(urlStr)
                .params(map)
                .files(fileMap)
                .build();
        HttpUtil.getInstance().loadString(httpRequest, new StringHttpCallback() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onEnd() {
                super.onEnd();
            }
        });
    }

}
