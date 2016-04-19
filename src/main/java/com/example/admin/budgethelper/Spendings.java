package com.exampleandroid.testthing;

public class Spendings {
    //vars
    private String user;
    private int ID;
    private double amount;
    private String date;

    //constructor
    public Spendings(String user, int ID, double amount, String date){
        this.user = user;
        this.ID = ID;
        this.amount = amount;
        this.date = date;
    }
    //setters
    public void setUser(String u){
        user = u;
    }
    public void setID(int id){
        ID = id;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setDate(String date){
        this.date = date;
    }
    //getters
    public String getUser(){
        return user;
    }
    public String getDate(){
        return date;
    }
    public double getAmount(){
        return amount;
    }
    public int getID(){
        return ID;
    }
}
