package com.anye.test.mvp.model;

import java.util.List;

/**
 * Created by lwz on 2016/6/24.
 */

public class GoodsInfoBean {
    private String sn;
    private String memberid;
    private String remark;
    private String wareHousename;
    private String wmembername;
    private String unit_id;
    private String wmemberid;
    private String autoid;
    private String date;
    private String type;
    private String isImport;
    private String Name;
    private String outnum;
    private String time;
    private String num;
    private String manufacturer;
    private String brand;
    private String membername;
    private String goodsID;
    private String bar;
    private String wareHouseid;
    /**
     * pic_url : http://123.57.13.118:80/warehouse/file/up/2016-06-22/20160622143851_70.jpg
     */

    private List<DataPicurlBean> data_picurl;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWareHousename() {
        return wareHousename;
    }

    public void setWareHousename(String wareHousename) {
        this.wareHousename = wareHousename;
    }

    public String getWmembername() {
        return wmembername;
    }

    public void setWmembername(String wmembername) {
        this.wmembername = wmembername;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getWmemberid() {
        return wmemberid;
    }

    public void setWmemberid(String wmemberid) {
        this.wmemberid = wmemberid;
    }

    public String getAutoid() {
        return autoid;
    }

    public void setAutoid(String autoid) {
        this.autoid = autoid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getOutnum() {
        return outnum;
    }

    public void setOutnum(String outnum) {
        this.outnum = outnum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getWareHouseid() {
        return wareHouseid;
    }

    public void setWareHouseid(String wareHouseid) {
        this.wareHouseid = wareHouseid;
    }

    public List<DataPicurlBean> getData_picurl() {
        return data_picurl;
    }

    public void setData_picurl(List<DataPicurlBean> data_picurl) {
        this.data_picurl = data_picurl;
    }

    public static class DataPicurlBean {
        private String pic_url;

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
