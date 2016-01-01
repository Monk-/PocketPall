package com.example.user.pocketpall.Database;


import android.provider.BaseColumns;

public class ColumnNames {

    public ColumnNames() {
    }

    public static abstract class Income implements BaseColumns
    {
        public static final String TABLE_NAME = "income";
        public static final String COLUMN_INCOME_TITLE = "title";
        public static final String COLUMN_INCOME_COMMENT = "comment";
        public static final String COLUMN_INCOME_AMOUNT = "amount";
        public static final String COLUMN_INCOME_DATE = "date";
    }

    public static abstract class Expence implements BaseColumns
    {
        public static final String TABLE_NAME = "expence";
        public static final String COLUMN_EXPENCE_TITLE = "title";
        public static final String COLUMN_EXPENCE_COMMENT = "comment";
        public static final String COLUMN_EXPENCE_AMOUNT = "amount";
        public static final String COLUMN_EXPENCE_DATE = "date";
    }
}
