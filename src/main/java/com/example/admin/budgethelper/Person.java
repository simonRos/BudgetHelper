package com.exampleandroid.testthing;

public class Person {
  //variables
  private String username;
  private String password;
  
  public Person(String username, String password){
    this.username = username;
    this.password = password;
  }
  
  public void setName(String newName){
    username = newName;
  }
  
  public void setPass(String newPass){
   password = newPass;
  }
  
  public String getName(){
   return username; 
  }
  
  public String getPass(){
    return password;
  }
  
}