package com.anye.anyecommon.model;

import java.util.List;

/**
 * Created by lwz on 2016/6/27.
 */

public class BeautyModel {

    /**
     * status : true
     * total : 54
     * tngou : [{"count":5184,"fcount":0,"galleryclass":2,"id":687,"img":"/ext/160310/92093505d160f1a8056ec777cbb8d783.jpg","rcount":0,"size":41,"time":1457573079000,"title":"美女米妮mini大秀巨乳乳沟显清纯 "}]
     */

    private boolean status;
    private int total;
    /**
     * count : 5184
     * fcount : 0
     * galleryclass : 2
     * id : 687
     * img : /ext/160310/92093505d160f1a8056ec777cbb8d783.jpg
     * rcount : 0
     * size : 41
     * time : 1457573079000
     * title : 美女米妮mini
     */

    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
