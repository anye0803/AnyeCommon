package com.anye.test.mvp.model;

import java.util.List;

/**
 * Created by lwz on 2016/6/23.
 */
public class GoodsBean {

    /**
     * count : 16
     * RespResult : 0
     * page : 0
     * data : [{"sn":"111","memberid":"15","remark":"111","wareHousename":"信息港仓库1","wmembername":"章宁","unit_id":"11","wmemberid":"15","data_picurl":[{"pic_url":"http://123.57.13.118:80/warehouse/file/up/2016-06-22/20160622143851_70.jpg"}],"autoid":"194","date":"2016-06-22","type":"0","isImport":"1","Name":"顶顶顶顶","outnum":"111","time":"15:27:04","num":"111","manufacturer":"111","brand":"111","membername":"章宁","goodsID":"62","bar":"111","wareHouseid":"12"}]
     * pageSize : 1
     * ErrorMsg :
     */

    private String count;
    private String RespResult;
    private int page;
    private int pageSize;
    private String ErrorMsg;
    /**
     * sn : 111
     * memberid : 15
     * remark : 111
     * wareHousename : 信息港仓库1
     * wmembername : 章宁
     * unit_id : 11
     * wmemberid : 15
     * data_picurl : [{"pic_url":"http://123.57.13.118:80/warehouse/file/up/2016-06-22/20160622143851_70.jpg"}]
     * autoid : 194
     * date : 2016-06-22
     * type : 0
     * isImport : 1
     * Name : 顶顶顶顶
     * outnum : 111
     * time : 15:27:04
     * num : 111
     * manufacturer : 111
     * brand : 111
     * membername : 章宁
     * goodsID : 62
     * bar : 111
     * wareHouseid : 12
     */

    private List<GoodsInfoBean> data;

    public List<GoodsInfoBean> getData() {
        return data;
    }

    public void setData(List<GoodsInfoBean> data) {
        this.data = data;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRespResult() {
        return RespResult;
    }

    public void setRespResult(String RespResult) {
        this.RespResult = RespResult;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "count='" + count + '\'' +
                ", RespResult='" + RespResult + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
