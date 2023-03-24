package com.example.MiniProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "SIGNUP_DATABASE";
    private static final String TABLE_NAME = "SIGNUP_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "EMAIL";
    private static final String COL_3 = "USER_NAME";
    private static final String COL_4 = "PASSWORD";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "EMAIL TEXT," + "USER_NAME TEXT, " + "PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertRecord(String email, String userName, String password) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, userName);
        contentValues.put(COL_4, password);

        long flag = database.insert(TABLE_NAME, null, contentValues);

        return flag != -1;
    }

    public boolean getRecord(String userName, String password) {

        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COL_1};
        String selection = COL_3 + "=?" + " AND " + COL_4 + "=?";
        String[] selectionARGS = {userName, password};

        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionARGS, null, null, null);

        int count = cursor.getCount();

        database.close();
        cursor.close();

        return count > 0;
    }
}
