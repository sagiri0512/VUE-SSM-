package com.sagiri.pojo;


public class User {

  private long uid;
  private String uName;
  private String upwd;
  private String uPhoneNmber;
  private long usex;
  private long uage;
  private String uHeadImage;
  private String uMailBox;
  private String uNickName;

  public User(String userName, String userPWD) {
    this.uName = userName;
    this.upwd = userPWD;
  }

  public User() {

  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getUName() {
    return uName;
  }

  public void setUName(String uName) {
    this.uName = uName;
  }


  public String getUpwd() {
    return upwd;
  }

  public void setUpwd(String upwd) {
    this.upwd = upwd;
  }


  public String getUPhoneNmber() {
    return uPhoneNmber;
  }

  public void setUPhoneNmber(String uPhoneNmber) {
    this.uPhoneNmber = uPhoneNmber;
  }


  public long getUsex() {
    return usex;
  }

  public void setUsex(long usex) {
    this.usex = usex;
  }


  public long getUage() {
    return uage;
  }

  public void setUage(long uage) {
    this.uage = uage;
  }


  public String getUHeadImage() {
    return uHeadImage;
  }

  public void setUHeadImage(String uHeadImage) {
    this.uHeadImage = uHeadImage;
  }


  public String getUMailBox() {
    return uMailBox;
  }

  public void setUMailBox(String uMailBox) {
    this.uMailBox = uMailBox;
  }


  public String getUNickName() {
    return uNickName;
  }

  public void setUNickName(String uNickName) {
    this.uNickName = uNickName;
  }

}
