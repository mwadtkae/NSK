package com.naukri.naukrisuchakkendra;

public class DataModel
{
  private String catid;
  private String crid;
  private String desc;
  private String id;
  private String imgURL;
  private String message;
  private String name;
  private String price;
  private String prodid;
  private String quantity;
  private String weight;
  private String expe;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTaluka() {
    return taluka;
  }

  public void setTaluka(String taluka) {
    this.taluka = taluka;
  }

  private String job;
  private String date;
  private String taluka;

  public String getExpe() {
    return expe;
  }

  public void setExpe(String expe) {
    this.expe = expe;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String jobt) {
    this.job = jobt;
  }

  public DataModel() {}
  
  public DataModel(String paramString1, String paramString2, String paramString3)
  {
    id = paramString1;
    name = paramString2;
    imgURL = paramString3;
  }
  
  public String getCatid()
  {
    return catid;
  }
  
  public String getCrid()
  {
    return crid;
  }
  
  public String getDesc()
  {
    return desc;
  }
  
  public String getId()
  {
    return id;
  }
  
  public String getImgURL()
  {
    return imgURL;
  }
  
  public String getMessage()
  {
    return message;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getPrice()
  {
    return price;
  }
  
  public String getProdid()
  {
    return prodid;
  }
  
  public String getQuantity()
  {
    return quantity;
  }
  
  public String getWeight()
  {
    return weight;
  }
  
  public void setCatid(String paramString)
  {
    catid = paramString;
  }
  
  public void setCrid(String paramString)
  {
    crid = paramString;
  }
  
  public void setDesc(String paramString)
  {
    desc = paramString;
  }
  
  public void setId(String paramString)
  {
    id = paramString;
  }
  
  public void setImgURL(String paramString)
  {
    imgURL = paramString;
  }
  
  public void setMessage(String paramString)
  {
    message = paramString;
  }
  
  public void setName(String paramString)
  {
    name = paramString;
  }
  
  public void setPrice(String paramString)
  {
    price = paramString;
  }
  
  public void setProdid(String paramString)
  {
    prodid = paramString;
  }
  
  public void setQuantity(String paramString)
  {
    quantity = paramString;
  }
  
  public void setWeight(String paramString)
  {
    weight = paramString;
  }
}
