/*
CS 300
BudgetHelper App
May 1, 2016
 */

package com.example.admin.budgethelper;

/**
 * Created by Alex Stroud on 3/1/16.
 */

import android.provider.BaseColumns;

public final class DBContract {

    public DBContract(){}


    public static abstract class BudgetEntry implements BaseColumns {
        public static final String TABLE_NAME = "budget";
        public static final String COLUMN_AMOUNT = "budgetamount";
        public static final String COLUMN_DATE = "startdate";
    }

    public static abstract class SpendingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "spendings";
        public static final String COLUMN_AMOUNT = "spendingamount";
        public static final String COLUMN_STORE = "spendingstore";
        public static final String COLUMN_DATE = "spendingdate";
    }

    public static abstract class PersonEntry implements BaseColumns {
        //Person Table
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static abstract class IOUEntry implements BaseColumns {
        //IOU table
        public static final String TABLE_NAME = "IOU";
        //public static final String COLUMN_USER = "IOU_Username";
        //public static final String ID = "iou_ID";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_FROM = "From_Name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_DATE = "date";
    }

    public static abstract class UOEntry implements BaseColumns {
        //IOU table
        public static final String TABLE_NAME = "UO";
        //public static final String COLUMN_USER = "IOU_Username";
        //public static final String ID = "iou_ID";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_TO = "To_Name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_DATE = "date";
    }

    public static abstract class ReminderEntry implements BaseColumns {
        //Reminder
        public static final String TABLE_NAME = "reminder";
        public static final String COLUMN_IOUID = "reminder_iouID";
        public static final String COLUMN_MESSAGE = "message";
        public static final String COLUMN_DATE = "date";
    }
/*
    public static abstract class SpendingsEntry implements BaseColumns {
        //Spendings
        public static final String TABLE_NAME = "spendings";
        public static final String COLUMN_STORE = "spendstore";
        //public static final String SPENDINGS_ID = "spendings_ID";
        public static final String COLUMN_AMOUNT = "spendingamount";
        public static final String COLUMN_DATE = "spendingdate";
    }
/*
    public static abstract class BudgetEntry implements BaseColumns {
        //WeeklyBudget
        public static final String budgetTable = "weekly_budget";
        //public static final String budgetUser = "budget_user";
        public static final String weeklyBudget = "budget_amount";
        public static final String startDate = "budget_date";
    }
*/
}

