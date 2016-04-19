package com.exampleandroid.testthing;

public class Reminder{
 private int iouID;
 private String message;
 private String date;
 
 public Reminder(int iouID, String message, String date){
   this.iouID = iouID;
   this.message = message;
   this.date = date;
 }
 
 public void setID(int id){
   iouID = id;
 }
 public void setMessage(String message){
   this.message = message;
 }
 public void setDate(String date){
   this.date = date;
 }
 
 public int getID(){
   return iouID;
 }
 public String getMessage(){
  return message; 
 }
 public String getDate(){
  return date; 
 }
 
}