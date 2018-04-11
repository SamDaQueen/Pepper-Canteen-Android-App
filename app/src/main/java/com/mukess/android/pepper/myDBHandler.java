package com.mukess.android.pepper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class myDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "order.db";
    private static final String TABLE_ORDER = "orderTable";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_QUANTITY = "Quantity";
    private static final String COLUMN_TIME = "Time";

    myDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_ORDER + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " VARCHAR(20), " + COLUMN_PRICE + " DECIMAL(3,2), " + COLUMN_QUANTITY + " INTEGER, " + COLUMN_TIME + " DATE);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(sqLiteDatabase);
    }

    //add new row
    public void addProduct(MenuItem menuItem) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, menuItem.getName());
        contentValues.put(COLUMN_PRICE, menuItem.getPrice());
        contentValues.put(COLUMN_QUANTITY, menuItem.getQuantity());
        DateFormat df = new SimpleDateFormat("EEE, d M yyyy HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        contentValues.put(COLUMN_TIME, date);
        sqLiteDatabase.insert(TABLE_ORDER, null, contentValues);
        sqLiteDatabase.close();
    }

    public StringBuffer databaseToString() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ORDER + " ORDER BY " + COLUMN_ID + " DESC";

        //Cursor pointer to a location in your result
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        StringBuffer ex = new StringBuffer();
        while (!cursor.isAfterLast()) {
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            ex.append(".");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            ex.append("\nPrice: ");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)));
            ex.append("\nQuantity: ");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY)));
            ex.append("\nOrdered On: ");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            cursor.moveToNext();
            ex.append("\n\n");
        }
        cursor.close();
        sqLiteDatabase.close();
        return ex;
    }

    public StringBuffer databaseOnDate(String date) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_TIME + " LIKE '%" + date + "%';";

        //Cursor pointer to a location in your result
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        StringBuffer ex = new StringBuffer();
        while (!cursor.isAfterLast()) {
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            ex.append(".");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            ex.append("\nPrice: ");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)));
            ex.append("\nQuantity: ");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY)));
            ex.append("\nOrdered On: ");
            ex.append(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            cursor.moveToNext();
            ex.append("\n\n");
        }
        cursor.close();
        sqLiteDatabase.close();
        return ex;
    }

    public void deleteAll() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_ORDER);
    }
}
