package com.anye.okhttplibrary.http;


import com.anye.okhttplibrary.http.callback.FileDownloadHttpCallback;
import com.anye.okhttplibrary.http.callback.FileUploadHttpCallback;
import com.anye.okhttplibrary.http.callback.StringHttpCallback;
import com.anye.okhttplibrary.http.provider.BaseHttpProvider;
import com.anye.okhttplibrary.http.provider.okhttp.OkHttpProvider;
import com.anye.okhttplibrary.http.request.HttpRequest;

/**
 * Created by Anthony on 2016/5/6.
 * Class Note:
 */
public class HttpUtil {
    private static HttpUtil mInstance;
    private BaseHttpProvider mProvider;
    public static final int GET = 0;
    public static final int POST = 1;

    private HttpUtil() {
        mProvider = new OkHttpProvider();
    }

    public static HttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtil.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * load String data
     */
    public void loadString(HttpRequest request, StringHttpCallback callback) {
        mProvider.loadString(request, callback);
    }

    /**
     *post file to server
     */
    public void uploadFile(HttpRequest request, FileUploadHttpCallback callback) {
        mProvider.uploadFile(request, callback);
    }
    /**
     * download file from server
     */
    public void downloadFile(HttpRequest request,FileDownloadHttpCallback callback){
        mProvider.downloadFile(request,callback);
    }

/*
example:

 HttpRequest.Builder builder = new HttpRequest.Builder();
    HttpRequest request = builder.url("www.....").build();
    HttpUtil.getInstance().loadString(request, new StringHttpCallback() {
        @Override
        public void onResponse(String response) {
             ....
        }

        @Override
        public void onError(String error) {
            ...
        }
    });
    */
}
