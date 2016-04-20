package com.example.admin.budgethelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 4/20/16.
 */
public class SpendingsDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="budget.db";
    private static final String TEXT_TYPE=" TEXT";
    private static final String COMMA_SEP=",";

    private static final String SQL_CREATE_SPENDINGS = "CREATE TABLE " + SpendingsContract.SpendingsEntry.TABLE_NAME + " ("+
            SpendingsContract.SpendingsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            SpendingsContract.SpendingsEntry.COLUMN_AMOUNT + TEXT_TYPE + COMMA_SEP +
            SpendingsContract.SpendingsEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
            SpendingsContract.SpendingsEntry.COLUMN_STORE + TEXT_TYPE + " )";

    private static final String DELETE_SPENDINGS=
            "DROP TABLE IF EXISTS "+SpendingsContract.SpendingsEntry.TABLE_NAME;

    public SpendingsDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //need constructor with just context...
    public SpendingsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SPENDINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_SPENDINGS);
        onCreate(db);
    }
}
