package com.naukri.naukrisuchakkendra;

import android.graphics.drawable.Drawable;

public class Imagemodel
{
  public String brief;
  public Integer counter = null;
  public String date;
  String image;
  public Drawable imageDrw;
  private String imageUrl;
  public String name;
  
  Imagemodel() {}
  
  public String getImageUrl()
  {
    return imageUrl;
  }
  
  public void setImageUrl(String paramString)
  {
    imageUrl = paramString;
  }
}
