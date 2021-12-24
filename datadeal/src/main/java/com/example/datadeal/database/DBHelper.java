package com.example.datadeal.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "hsibin";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i(TAG, "DBDeal: 使用了");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: 使用了");
        db.execSQL("create table student (snum integer primary key autoincrement,sname varchar(10),sage int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE student ADD phone VARCHAR(12)");
    }

//    public void dbInsert(SQLiteDatabase db, String snum, String sname, int sage, String table) {
//        ContentValues values = new ContentValues();
//        values.put("snum", snum);
//        values.put("sname", sname);
//        values.put("sage", sage);
//        db.insert(table, "snum", values);
//        values.clear();
//    }

}
