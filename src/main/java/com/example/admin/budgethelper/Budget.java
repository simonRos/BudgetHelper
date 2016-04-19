package com.exampleandroid.testthing;

/**
 * Created by Simon on 4/19/2016.
 */
public class Budget {
    //vars
    private String username;
    private double budget;
    //constructor
    public Budget(String owner, double amount){
        username = owner;
        budget = amount;
    }
    //setters
    public void setUsername(String newUser){
        username = newUser;
    }
    public void setBudget(double newBudget){
        budget = newBudget;
    }
    //getters
    public String getUsername(){
        return username;
    }
    public double getBudget(){
        return budget;
    }
}
