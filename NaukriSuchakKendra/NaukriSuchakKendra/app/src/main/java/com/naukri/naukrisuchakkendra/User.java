package com.naukri.naukrisuchakkendra;

public class User
{
  private String address;
  private String email;
  private String id;
  private String name;
  private String phone;
  private String username;
  private String zip;

  public User(String paramString1, String paramString2)
  {
    id = paramString1;
    username = paramString2;
  }

  public String getAddress()
  {
    return address;
  }
  
  public String getEmail()
  {
    return email;
  }
  
  public String getId()
  {
    return id;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getPhone()
  {
    return phone;
  }
  
  public String getUsername()
  {
    return username;
  }
  
  public String getZip()
  {
    return zip;
  }
  
  public void setAddress(String paramString)
  {
    address = paramString;
  }
  
  public void setEmail(String paramString)
  {
    email = paramString;
  }
  
  public void setId(String paramString)
  {
    id = paramString;
  }
  
  public void setName(String paramString)
  {
    name = paramString;
  }
  
  public void setPhone(String paramString)
  {
    phone = paramString;
  }
  
  public void setUsername(String paramString)
  {
    username = paramString;
  }
  
  public void setZip(String paramString)
  {
    zip = paramString;
  }
}
