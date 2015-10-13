package com.example.emergency;

import java.util.Date;
import java.text.SimpleDateFormat;
import android.location.Location;

public class Quake {
 
  private String title;
  private String name;
  private String address;
  private String link;
  private String description;
  private String tel;
  
  public String getTitle() { return title; }
  public String getName() { return name; }
  public String getAddress() { return address; }
  public String getLink() { return link; }
  public String getDescription() { return description; }
  public String getTel() { return tel; }



  public Quake(String name) {   this.name = name;}
  
  public Quake(String name, String address, String tel, String description) 
  {   this.name = name;
      this.address = address;
      this.tel = tel;
      this.description = description;
  }
  
  @Override
  public String toString() {
  
    return  "Hospital : " + name;
  }

}