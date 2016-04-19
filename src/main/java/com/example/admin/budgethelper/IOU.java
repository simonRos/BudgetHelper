package com.exampleandroid.testthing;

public class IOU{
 
  private String username;
  private int iouID;
  private String description;
  private int toFrom;
  private double amount;
  private String date;
  
  public IOU(String username, int iouID, String description, int toFrom, double amount, String date){
    this.username = username;
    this.iouID = iouID;
    this.description = description;
    this.toFrom = toFrom;
    this.amount = amount;
    this.date = date;
  }
  
  public void setName(String name){
    username = name;
  }
  public void setID(int id){
    iouID = id;
  }
  public void setDescription(String desc){
    description = desc;
  }
  public void setToFrom(int x){
    toFrom = x;
  }
  public void setAmount(double y){
    amount = y;
  }
  public void setDate(String d){
    date = d;
  }
  
  
  public String getName(){
    return username;
  }
  public int getID(){
    return iouID;
  }
  public String getDescription(){
    return description;
  }
  public int getToFrom(){
    return toFrom;
  }
  public double getAmount(){
   return amount; 
  }
  public String getDate(){
   return date; 
  }
  
}