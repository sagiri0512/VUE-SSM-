package com.sagiri.pojo;


public class Address {

  private long aid;
  private long uid;
  private String aText;
  private String aPhonenNmber;
  private String aName;
  private long did;


  public Address() {}
  public Address(long aid, String aText, String aPhonenNmber, String aName){
    this.aid = aid;
    this.aText = aText;
    this.aPhonenNmber = aPhonenNmber;
    this.aName = aName;
  }
  public long getAid() {
    return aid;
  }

  public void setAid(long aid) {
    this.aid = aid;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getAText() {
    return aText;
  }

  public void setAText(String aText) {
    this.aText = aText;
  }


  public String getAPhonenNmber() {
    return aPhonenNmber;
  }

  public void setAPhonenNmber(String aPhonenNmber) {
    this.aPhonenNmber = aPhonenNmber;
  }


  public String getAName() {
    return aName;
  }

  public void setAName(String aName) {
    this.aName = aName;
  }


  public long getDid() {
    return did;
  }

  public void setDid(long did) {
    this.did = did;
  }

}
