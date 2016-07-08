package com.anye.okhttplibrary.http.provider;



import com.anye.okhttplibrary.base.AbsApplication;
import com.anye.okhttplibrary.http.callback.FileDownloadHttpCallback;
import com.anye.okhttplibrary.http.callback.FileUploadHttpCallback;
import com.anye.okhttplibrary.http.callback.StringHttpCallback;
import com.anye.okhttplibrary.http.request.HttpRequest;
import com.anye.okhttplibrary.util.FileUtil;

import java.io.IOException;


/**
 * Created by Anthony on 2016/3/1.
 * Class Note:
 * provide base operation of http
 * include load String, upload file,download file and load local String
 * 提供http常用操作，包含加载字符串（本地或者网络），下载文件，上传文件
 */
public abstract class BaseHttpProvider {
    public abstract void loadString(HttpRequest request, StringHttpCallback callback);

    public abstract void uploadFile(HttpRequest request, FileUploadHttpCallback callback);

    public abstract void downloadFile(HttpRequest request, FileDownloadHttpCallback callback);

    protected void loadLocalString(String path, final StringHttpCallback callback) {

        try {
            String result = FileUtil.getString(AbsApplication.app(), path);
            callback.onResponse(result);
        } catch (IOException e) {
//            e.printStackTrace();
            callback.onError(e.getMessage());
        }


    }
}
