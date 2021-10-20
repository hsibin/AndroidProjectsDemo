package com.example.datadeal.data;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datadeal.MainActivity;


public class Sqlite extends SQLiteOpenHelper {

    final String CREATE_TABLE_SQL = "create table td_dict (_id integer primary key autoincrement,word,detail)";
    private static final String TAG = "hsibin";


    //创建数据表语句
    public Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "--版本更新" + oldVersion + "-->" + newVersion);
    }
}
