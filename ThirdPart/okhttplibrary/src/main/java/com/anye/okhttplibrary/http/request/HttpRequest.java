package com.anye.okhttplibrary.http.request;



import com.anye.okhttplibrary.util.HttpUtil;

import java.io.File;
import java.util.HashMap;


/**
 * Created by Anthony on 2016/3/1.
 * Class Note:
 * http request
 */
public class HttpRequest {
    private int method;
    private String url;
    private boolean isNeedCache;
    private HashMap<String, String> headers;
    private HashMap<String, Object> params;
    private HashMap<String, File> files;
    private String tag;

    private HttpRequest(Builder builder){
        this.method=builder.method;
        this.url = builder.url;
        this.isNeedCache = builder.isNeedCache;
        this.headers = builder.headers;
        this.params = builder.params;
        this.files = builder.files;
        this.tag = builder.tag;
    }

    public int getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public boolean getIsNeedCache() {
        return isNeedCache;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public String getTag() {
        return tag;
    }

    public HashMap<String, File> getFiles(){
        return files;
    }


    public static class Builder {
        private int method;
        private String url;
        private boolean isNeedCache;
        private HashMap<String, String> headers;
        private HashMap<String, Object> params;
        private HashMap<String, File> files;
        private String tag;

        public Builder() {
            this.method = HttpUtil.POST;
            this.url = "";
            this.isNeedCache = true;
            this.headers = null;
            this.params = null;
            this.files = null;
            this.tag = null;
        }

        public Builder method(int method){
            this.method = method;
            return this;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder isNeedCache(boolean isNeedCache){
            this.isNeedCache = isNeedCache;
            return this;
        }

        public Builder headers(HashMap<String, String> headers){
            this.headers = headers;
            return this;
        }

        public Builder params(HashMap<String, Object> params){
            this.params = params;
            return this;
        }

        public Builder files(HashMap<String, File> files){
            this.files = files;
            return this;
        }

        public Builder tag(String tag){
            this.tag = tag;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }
}
