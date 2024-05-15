package com.sagiri.pojo;


public class Product {

  private long pid;
  private String pName;
  private double pPrice;
  private String pImg;
  private long pSales;
  private long tid;
  private String pIntroduce;


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public String getPName() {
    return pName;
  }

  public void setPName(String pName) {
    this.pName = pName;
  }


  public double getPPrice() {
    return pPrice;
  }

  public void setPPrice(double pPrice) {
    this.pPrice = pPrice;
  }


  public String getPImg() {
    return pImg;
  }

  public void setPImg(String pImg) {
    this.pImg = pImg;
  }


  public long getPSales() {
    return pSales;
  }

  public void setPSales(long pSales) {
    this.pSales = pSales;
  }


  public long getTid() {
    return tid;
  }

  public void setTid(long tid) {
    this.tid = tid;
  }


  public String getPIntroduce() {
    return pIntroduce;
  }

  public void setPIntroduce(String pIntroduce) {
    this.pIntroduce = pIntroduce;
  }

}
