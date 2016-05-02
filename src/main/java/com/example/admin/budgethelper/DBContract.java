/*
CS 300
BudgetHelper App
May 1, 2016

Database contract.
Contains all variable names for database tables, rows, etc.
This file is essentially a referance page. No real work is done here.
 */

package com.example.admin.budgethelper;

/**
 * Created by Alex Stroud on 3/1/16.
 */

import android.provider.BaseColumns;

public final class DBContract {

    public DBContract(){}

    public static abstract class BudgetEntry implements BaseColumns {   //Budget Table
        public static final String TABLE_NAME = "budget";
        public static final String COLUMN_AMOUNT = "budgetamount";  //amount col
        public static final String COLUMN_DATE = "startdate";   //date col
    }

    public static abstract class SpendingsEntry implements BaseColumns {    //Spendings Table
        public static final String TABLE_NAME = "spendings";
        public static final String COLUMN_AMOUNT = "spendingamount";    //amount col
        public static final String COLUMN_STORE = "spendingstore";  //'store' col. store refers to the attached string used as a note by user
        public static final String COLUMN_DATE = "spendingdate";    //date col
    }

    public static abstract class PersonEntry implements BaseColumns {   //person table
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static abstract class IOUEntry implements BaseColumns { //IOU table
        public static final String TABLE_NAME = "IOU";
        public static final String COLUMN_DESC = "description"; //description col. User note
        public static final String COLUMN_FROM = "From_Name";   //From col
        public static final String COLUMN_AMOUNT = "amount";    //amount col
        public static final String COLUMN_DATE = "date";    //date col
    }

    public static abstract class UOEntry implements BaseColumns {   //IOU table
        public static final String TABLE_NAME = "UO";
        public static final String COLUMN_DESC = "description"; //Description col. User note
        public static final String COLUMN_TO = "To_Name";   //To col
        public static final String COLUMN_AMOUNT = "amount";    //amount col
        public static final String COLUMN_DATE = "date";    //date col
    }

    //Note: reminders were not implemented but we left the table in the database
    //If we ever decide to revisit the app and add a reminder feature, we would need this table
    public static abstract class ReminderEntry implements BaseColumns { //Reminder table
        public static final String TABLE_NAME = "reminder";
        public static final String COLUMN_IOUID = "reminder_iouID"; //Reminders need unique IDs
        public static final String COLUMN_MESSAGE = "message";  //Message col. User note
        public static final String COLUMN_DATE = "date";    //date col
    }
}

