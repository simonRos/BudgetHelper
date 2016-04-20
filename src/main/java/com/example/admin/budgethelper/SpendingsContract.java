package com.example.admin.budgethelper;

/**
 * Created by admin on 4/20/16.
 */

import android.provider.BaseColumns;

public final class SpendingsContract {

    public SpendingsContract(){}

    public static abstract class SpendingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "spendings";
        public static final String COLUMN_STORE = "spendstore";
        public static final String COLUMN_AMOUNT = "spendingamount";
        public static final String COLUMN_DATE = "spendingdate";
    }
}
