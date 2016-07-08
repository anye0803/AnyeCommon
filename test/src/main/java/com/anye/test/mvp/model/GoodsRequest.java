package com.anye.test.mvp.model;

/**
 * Created by lwz on 2016/6/23.
 */

public class GoodsRequest {
    private String libraryID;
    private int pageSize;
    private int page;

    public String getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(String libraryID) {
        this.libraryID = libraryID;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
