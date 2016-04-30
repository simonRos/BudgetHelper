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


    private static final String SQL_CREATE_BUDGET = "CREATE TABLE " + DBContract.BudgetEntry.TABLE_NAME + " (" +
            DBContract.BudgetEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.BudgetEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.BudgetEntry.COLUMN_DATE + TEXT_TYPE +  " )";

    private static final String SQL_CREATE_SPENDINGS = "CREATE TABLE " + DBContract.SpendingsEntry.TABLE_NAME + " (" +
            DBContract.SpendingsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_STORE + TEXT_TYPE + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_DATE + TEXT_TYPE +  " )";

    //Table Creation Strings----------------------------------------------------------------
    private static final String SQL_CREATE_PERSON="CREATE TABLE " + DBContract.PersonEntry.TABLE_NAME + " ("+
            DBContract.PersonEntry.COLUMN_USERNAME + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
            DBContract.PersonEntry.COLUMN_PASSWORD +" )";

    private static final String SQL_CREATE_IOU="CREATE TABLE " + DBContract.IOUEntry.TABLE_NAME + " ("+
            DBContract.IOUEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_DESC + TEXT_TYPE + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_FROM + TEXT_TYPE + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.IOUEntry.COLUMN_DATE + TEXT_TYPE + " )";
            //DBContract.IOUEntry.COLUMN_USER + TEXT_TYPE + " )";
    //FOREIGN kEY(" + DBContract.IOUEntry.iouUser + ") REFERENCES PERSONTABLE(USERNAME)";

    private static final String SQL_CREATE_UO="CREATE TABLE " + DBContract.UOEntry.TABLE_NAME + " ("+
            DBContract.UOEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.UOEntry.COLUMN_DESC + TEXT_TYPE + COMMA_SEP +
            DBContract.UOEntry.COLUMN_TO + TEXT_TYPE + COMMA_SEP +
            DBContract.UOEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.UOEntry.COLUMN_DATE + TEXT_TYPE + " )";
    //DBContract.IOUEntry.COLUMN_USER + TEXT_TYPE + " )";
    //FOREIGN kEY(" + DBContract.IOUEntry.iouUser + ") REFERENCES PERSONTABLE(USERNAME)";

    private static final String SQL_CREATE_REMINDER="CREATE TABLE " + DBContract.ReminderEntry.TABLE_NAME + " ("+
            DBContract.ReminderEntry.COLUMN_MESSAGE + TEXT_TYPE + COMMA_SEP +
            DBContract.ReminderEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
            DBContract.ReminderEntry.COLUMN_IOUID +" INTEGER )";
            //FOREIGN kEY(REMINDIOU) REFERENCES IOUTABLE(IOUID))");
/*
    private static final String SQL_CREATE_SPENDINGS = "CREATE TABLE " + DBContract.SpendingsEntry.TABLE_NAME + " ("+
            DBContract.SpendingsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
            DBContract.SpendingsEntry.COLUMN_STORE + TEXT_TYPE + " )";
            // FOREIGN kEY(SPENDUSER) REFERENCES PERSONTABLE(USERNAME))");

    /*
    private static final String createBudgetTable="create table " + DBContract.BudgetEntry.budgetTable + " ("+
            DBContract.BudgetEntry.weeklyBudget + TEXT_TYPE + COMMA_SEP +
            DBContract.BudgetEntry.startDate + TEXT_TYPE +" )";
            //DBContract.BudgetEntry.budgetUser + TEXT_TYPE + COMMA_SEP +
            //FOREIGN kEY(BUDGETUSER) REFERENCES PERSONTABLE(USERNAME))");
*/
    //END OF TABLE CREATIONS--------------------------------------------------------------------


    //Delete tables-----------------------------------------------------------------------------
    private static final String DELETE_PERSON=
            "DROP TABLE IF EXISTS "+DBContract.PersonEntry.TABLE_NAME;
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
    //private static final String deleteBudget=
      //      "DROP TABLE IF EXISTS "+DBContract.BudgetEntry.budgetTable;
    //END DELETES-------------------------------------------------------------------------------

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //need constructor with just context...
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PERSON);
        db.execSQL(SQL_CREATE_IOU);
        db.execSQL(SQL_CREATE_UO);
        db.execSQL(SQL_CREATE_REMINDER);
        db.execSQL(SQL_CREATE_SPENDINGS);
        db.execSQL(SQL_CREATE_BUDGET);
        //db.execSQL(createBudgetTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_PERSON);
        db.execSQL(DELETE_IOU);
        db.execSQL(DELETE_UO);
        db.execSQL(DELETE_REMINDER);
        db.execSQL(DELETE_SPENDINGS);
        db.execSQL(DELETE_BUDGET);
        //db.execSQL(deleteBudget);
        onCreate(db);
    }
}
