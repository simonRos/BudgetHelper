package com.example.admin.budgetHelper

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bhDatabase extends SQLiteOpenHelper {
    public static final String dbName = "BHDB.db";
    //Person Table
    public static final String personTable = "person";
    public static final String username = "username";
    public static final String password = "password";
    //IOU table
    public static final String iouTable = "IOU";
    public static final String iouUser = "IOU_Username";
    public static final String iouID = "iou_ID";
    public static final String description = "description";
    public static final String toFrom = "To_From";
    public static final String iouAmount = "amount";
    public static final String iouDate = "date";
    //Reminder
    public static final String reminderTable = "reminder";
    public static final String reminderIOU = "reminder_iouID";
    public static final String message = "message";
    public static final String remDate = "date";
    //Spendings
    public static final String spendingsTable = "spendings";
    public static final String spendUser = "Spend_Username";
    public static final String spendingsID = "spendings_ID";
    public static final String spendingAmount = "spending_amount";
    public static final String spendingDate = "spending_date";
    //WeeklyBudget
    public static final String budgetTable = "weekly_budget";
    public static final String budgetUser = "budget_user";
    public static final String weeklyBudget = "budget_amount";

    //Table Creation Strings
    String createPersonTable=("create table " + personTable + " (USERNAME TEXT PRIMARY KEY, USERNAME TEXT)");
    String createIOUTable=("create table " + iouTable + " (IOUID TEXT PRIMARY KEY, DESCRIPTION TEXT, TOFROM TEXT, IOUAMOUNT REAL, IOUDATE TEXT, IOUUSER TEXT, FOREIGN kEY(IOUUSER) REFERENCES PERSONTABLE(USERNAME)");
    String createReminderTable=("create table " + reminderTable + " (MESSAGE TEXT, REMDATE TEXT, REMINDIOU INT, FOREIGN kEY(REMINDIOU) REFERENCES IOUTABLE(IOUID))");
    String createSpendingsTable=("create table " + spendingsTable + " (SPENDINGSID INT PRIMARY KEY, SPENDINGSAMOUNT REAL, SPENDINGSDATE TEXT, SPENDUSER TEXT, FOREIGN kEY(SPENDUSER) REFERENCES PERSONTABLE(USERNAME))");
    String createBudgetTable=("create table " + budgetTable + " (WEEKLYBUDGET REAL, BUDGETUSER, FOREIGN kEY(BUDGETUSER) REFERENCES PERSONTABLE(USERNAME))");


    //Constructor
    public bhDatabase(Context context){
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(createPersonTable);
        db.execSQL(createIOUTable);
        db.execSQL(createReminderTable);
        db.execSQL(createSpendingsTable);
        db.execSQL(createBudgetTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + personTable);
        db.execSQL("DROP TABLE IF EXISTS " + createIOUTable);
        db.execSQL("DROP TABLE IF EXISTS " + createReminderTable);
        db.execSQL("DROP TABLE IF EXISTS " + createSpendingsTable);
        db.execSQL("DROP TABLE IF EXISTS " + createBudgetTable);


        onCreate(db);
    }

    public void createUser(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(username, person.getName());
        contentValue.put(password, person.getPass());
        db.insert(personTable, null, contentValue);
    }

    public void createIOU(IOU iou){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(iouUser, iou.getName());
        contentValue.put(iouID, iou.getID());
        contentValue.put(description, iou.getDescription());
        contentValue.put(toFrom, iou.getToFrom());
        contentValue.put(iouAmount, iou.getAmount());
        contentValue.put(iouDate, iou.getDate());
        db.insert(personTable, null, contentValue);
    }

    public void createReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(reminderIOU, reminder.getID());
        contentValue.put(message, reminder.getMessage());
        contentValue.put(remDate, reminder.getDate());
    }
    
    public void createSpendings(Spendings spendings){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(spendUser, spendings.getUser());
        contentValue.put(spendingsID, spendings.getID());
        contentValue.put(spendingAmount, spendings.getAmount());
        contentValue.put(spendingDate, spendings.getDate());
    }

    public void createBudget(Budget budget){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(budgetUser ,budget.getUsername());
        contentValue.put(weeklyBudget ,budget.getBudget());
    }
}
