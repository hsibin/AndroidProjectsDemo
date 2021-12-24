package com.example.datadeal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datadeal.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hsibin";
    public static DBHelper dbHelper;

    private EditText snum;
    private EditText sname;
    private EditText sage;
    private Button addBtn;
    private Button selBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dbHelper = new DBHelper(MainActivity.this, "student.db", null, 1);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Log.i(TAG, "onCreate: Main使用了");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbInsert(sqLiteDatabase);
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(MainActivity.this, "重复添加，检查输入", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "空数据，检查输入", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });

        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResList.class);
                startActivity(intent);
            }
        });

    }

    //初始化view
    protected void init() {
        snum = findViewById(R.id.snum);
        sname = findViewById(R.id.sname);
        sage = findViewById(R.id.sage);
        addBtn = findViewById(R.id.btn_add);
        selBtn = findViewById(R.id.btn_select);
    }

    //插入方法
    protected void dbInsert(SQLiteDatabase db) {
        Integer snumnew = null;
        try {
            snumnew = Integer.parseInt(snum.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "学号为数字，检查输入", Toast.LENGTH_SHORT).show();

        }
        String snamenew = sname.getText().toString();
        int sagenew = 0;
        try {
            sagenew = Integer.parseInt(sage.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "年龄为数字，检查输入", Toast.LENGTH_SHORT).show();

        }

        ContentValues values = new ContentValues();
        values.put("snum", snumnew);
        values.put("sname", snamenew);
        values.put("sage", sagenew);
        db.insert("student", "snum", values);

        values.clear();
    }

    //删除方法
    protected void dbDelete(SQLiteDatabase db) {
        db.delete("student", "snum=?", new String[]{"2021"});
    }

}
