/*
CS 300
BudgetHelper App
May 1, 2016

DataBaseHelper is nescessary to use the SQLite database.
 */

package com.example.admin.budgethelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 3/1/16.
 */


public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=3;
    public static final String DATABASE_NAME="budgetdb.db";
    private static final String TEXT_TYPE=" TEXT";
    private static final String COMMA_SEP=",";

    //Table Creation Strings----------------------------------------------------------------
    //Budget Table
    private static final String SQL_CREATE_BUDGET = "CREATE TABLE " + DBContract.BudgetEntry.TABLE_NAME + " (" +
            DBContract.BudgetEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.BudgetEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.BudgetEntry.COLUMN_DATE + TEXT_TYPE +  " )";
    //Spendings Table
    private static final String SQL_CREATE_SPENDINGS = "CREATE TABLE " + DBContract.SpendingsEntry.TABLE_NAME + " (" +
            DBContract.SpendingsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_STORE + TEXT_TYPE + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_DATE + TEXT_TYPE +  " )";
    //IOU Table
    private static final String SQL_CREATE_IOU="CREATE TABLE " + DBContract.IOUEntry.TABLE_NAME + " ("+
            DBContract.IOUEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_DESC + TEXT_TYPE + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_FROM + TEXT_TYPE + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_DATE + TEXT_TYPE + " )";
    //UO Table
    private static final String SQL_CREATE_UO="CREATE TABLE " + DBContract.UOEntry.TABLE_NAME + " ("+
            DBContract.UOEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.UOEntry.COLUMN_DESC + TEXT_TYPE + COMMA_SEP +
            DBContract.UOEntry.COLUMN_TO + TEXT_TYPE + COMMA_SEP +
            DBContract.UOEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.UOEntry.COLUMN_DATE + TEXT_TYPE + " )";
    //Reminder Table
    private static final String SQL_CREATE_REMINDER="CREATE TABLE " + DBContract.ReminderEntry.TABLE_NAME + " ("+
            DBContract.ReminderEntry.COLUMN_MESSAGE + TEXT_TYPE + COMMA_SEP +
            DBContract.ReminderEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
            DBContract.ReminderEntry.COLUMN_IOUID +" INTEGER )";
            
    //END OF TABLE CREATIONS--------------------------------------------------------------------

    //Delete tables-----------------------------------------------------------------------------
    private static final String DELETE_IOU=
            "DROP TABLE IF EXISTS "+DBContract.IOUEntry.TABLE_NAME;
    private static final String DELETE_UO=
            "DROP TABLE IF EXISTS "+DBContract.UOEntry.TABLE_NAME;
    private static final String DELETE_REMINDER=
            "DROP TABLE IF EXISTS "+DBContract.ReminderEntry.TABLE_NAME;
    private static final String DELETE_BUDGET=
            "DROP TABLE IF EXISTS "+DBContract.BudgetEntry.TABLE_NAME;
    private static final String DELETE_SPENDINGS=
            "DROP TABLE IF EXISTS "+DBContract.SpendingsEntry.TABLE_NAME;
    //END DELETES-------------------------------------------------------------------------------

    //full constructor
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //constructor with just context
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //create db

        db.execSQL(SQL_CREATE_IOU);
        db.execSQL(SQL_CREATE_UO);
        db.execSQL(SQL_CREATE_REMINDER);
        db.execSQL(SQL_CREATE_SPENDINGS);
        db.execSQL(SQL_CREATE_BUDGET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //upgrade db
        db.execSQL(DELETE_IOU);
        db.execSQL(DELETE_UO);
        db.execSQL(DELETE_REMINDER);
        db.execSQL(DELETE_SPENDINGS);
        db.execSQL(DELETE_BUDGET);
        onCreate(db);
    }
}
