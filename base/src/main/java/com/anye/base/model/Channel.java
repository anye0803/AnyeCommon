package com.anye.base.model;

import com.anye.base.model.config.Constants;
import com.anye.base.util.JSONObjectHelper;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Anthony on 2016/5/4.
 * Class Note:
 */
public class Channel {
    private String type;
    private String title;
    private String url;
    private String img;
    private String extra;


    public Channel(){

    }

    public Channel(String objStr) throws JSONException {
        this(new JSONObject(objStr));
    }

    public Channel(JSONObject obj) {
        JSONObjectHelper helper = new JSONObjectHelper(obj);
        setType(helper.getString(Constants.TYPE_NAMES, null));
        setImg(helper.getString(Constants.IMAGE_URL_NAMES, null));
        setUrl(helper.getString(Constants.URL_NAMES, null));
        setTitle(helper.getString(Constants.TITLE_NAMES, null));
        setExtra(helper.getString(Constants.EXTRA_NAMES, null));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != ((Object) this).getClass()) {
            return false;
        }

        Channel c = (Channel) o;
        if (!c.getImg().equals(getImg())) {
            return false;
        }

        if (!c.getUrl().equals(getUrl())) {
            return false;
        }

        if (!c.getType().equals(getType())) {
            return false;
        }

        if (!c.getTitle().equals(getTitle())) {
            return false;
        }

        return true;
    }
}
