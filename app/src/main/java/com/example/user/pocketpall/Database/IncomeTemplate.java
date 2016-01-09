package com.example.user.pocketpall.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.pocketpall.Classes.ExIn;
import com.example.user.pocketpall.Classes.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/1/2016.
 */
public class IncomeTemplate extends Template {

    public IncomeTemplate(Context context) {
        super(context);
    }

    @Override
    public ContentValues setValues(ExIn come) {
        ContentValues values = new ContentValues();
        values.put(ColumnNames.Income.COLUMN_INCOME_TITLE, come.getTitle()); // get title
        values.put(ColumnNames.Income.COLUMN_INCOME_COMMENT, come.getComment()); // get author
        values.put(ColumnNames.Income.COLUMN_INCOME_AMOUNT, come.getAmount()); // get amount
        values.put(ColumnNames.Income.COLUMN_INCOME_CATEGORY, come.getCategory()); // get category
        values.put(ColumnNames.Income.COLUMN_INCOME_DATE, come.getDate()); // get date
        return values;
    }

    @Override
    public void insertToDb(SQLiteDatabase db, ContentValues values) {
        db.insert(ColumnNames.Income.TABLE_NAME, // table
                null,
                values);
    }


    @Override
    public String buildQuery() {
        return "SELECT  * FROM " + ColumnNames.Income.TABLE_NAME;
    }

    @Override
    public Cursor createCursor(SQLiteDatabase db, String query) {
        return db.rawQuery(query, null);
    }

    @Override
    public List getAllData(Cursor cursor, SQLiteDatabase db) {
        List <Income> list = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do
            {
                Income income = new Income(cursor.getString(0), cursor.getString(1), cursor.getString(5),
                        Double.parseDouble(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
                list.add(income);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    @Override
    public void delete(SQLiteDatabase db, ExIn come) {
        db.delete(ColumnNames.Income.TABLE_NAME, //table name
                " title = ? and date = ?",  // selections
                new String[] {come.getTitle(), come.getDate() }); //selections args
    }
}