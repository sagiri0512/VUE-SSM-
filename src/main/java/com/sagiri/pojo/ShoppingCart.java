package com.sagiri.pojo;


public class ShoppingCart {

  private long sid;
  private long uid;
  private long pid;
  private long sNum;

  public ShoppingCart(long uid, long pid, long sNum) {
    this.uid = uid;
    this.pid = pid;
    this.sNum = sNum;
  }

  public ShoppingCart(){

  }
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


  public long getSNum() {
    return sNum;
  }

  public void setSNum(long sNum) {
    this.sNum = sNum;
  }

}
