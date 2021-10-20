package com.example.datadeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datadeal.data.Sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hsibin";
    private Sqlite dbManager;

    private EditText edit;
    private TextView text;
    private Button bt1;
    private Button bt2;

    protected void initView() {
        EditText edit = findViewById(R.id.word);
        TextView text = findViewById(R.id.show);
        Button bt1 = findViewById(R.id.btn1);
        Button bt2 = findViewById(R.id.btn2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new Sqlite(this, "first.db", null, 1);
        initView();


    }

    protected void onClick(int id) {
        switch (id) {
            case R.id.btn1:
                String key = edit.getText().toString();
                Cursor cursor;
                cursor = dbManager.getReadableDatabase().query(
                        "tb.dict", null, "word=?", new String[]{key},
                        null, null, null);
                ArrayList<Map<String, String>> res = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("word", cursor.getString(1));
                    map.put("interpret", cursor.getString(2));
                    res.add(map);
                }
                if (res == null || res.size() == 0)
                    Toast.makeText(this, "没有记录", Toast.LENGTH_SHORT).show();
                else {
                    text.setText(res.get(0).get("interpret"));
                }
                break;
            case R.id.btn2:
                Intent intent = new Intent(MainActivity.this, AppCompatActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbManager != null) {
            dbManager.close();
        }
    }
}
