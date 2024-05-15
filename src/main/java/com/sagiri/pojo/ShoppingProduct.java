package com.sagiri.pojo;

public class ShoppingProduct {
    private long sid;
    private long uid;
    private long pid;
    private long sNum;
    private String pName;
    private double pPrice;
    private String pImg;
    private long pSales;
    private long tid;

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public long getpSales() {
        return pSales;
    }

    public void setpSales(long pSales) {
        this.pSales = pSales;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getsNum() {
        return sNum;
    }

    public void setsNum(long sNum) {
        this.sNum = sNum;
    }
}
