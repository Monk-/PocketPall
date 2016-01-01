package com.example.user.pocketpall.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 1/1/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BudgetDB";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      /*  String CREATE_USERS_TABLE = "CREATE TABLE " + ColumnNames.Users.TABLE_NAME + " ( " +
                ColumnNames.Users.COLUMN_NAME_NAME + " TEXT PRIMARY KEY, " +
                ColumnNames.Users.COLUMN_NAME_AGE + " INTEGER, " +
                ColumnNames.Users.COLUMN_NAME_STATUS + " TEXT )" ;
        db.execSQL(CREATE_USERS_TABLE);*/

        String CREATE_INCOME_TABLE = "CREATE TABLE " + ColumnNames.Income.TABLE_NAME + " ( " +
                ColumnNames.Income.COLUMN_INCOME_TITLE + " TEXT, " +
                ColumnNames.Income.COLUMN_INCOME_COMMENT + " TEXT, " +
                ColumnNames.Income.COLUMN_INCOME_AMOUNT + " INTEGER, " +
                ColumnNames.Income.COLUMN_INCOME_DATE + " TEXT )";
        db.execSQL(CREATE_INCOME_TABLE);

        String CREATE_Expence_TABLE = "CREATE TABLE " + ColumnNames.Expence.TABLE_NAME + " ( " +
                ColumnNames.Expence.COLUMN_EXPENCE_TITLE+ " TEXT, " +
                ColumnNames.Expence.COLUMN_EXPENCE_COMMENT + " TEXT, " +
                ColumnNames.Expence.COLUMN_EXPENCE_AMOUNT + " INTEGER, " +
                ColumnNames.Expence.COLUMN_EXPENCE_DATE + " TEXT )";
        db.execSQL(CREATE_Expence_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
