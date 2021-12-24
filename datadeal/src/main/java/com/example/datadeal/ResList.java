package com.example.datadeal;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResList extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, String>> mapList;

    @SuppressLint("Range")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res);

        init();

        SQLiteDatabase db = MainActivity.dbHelper.getReadableDatabase();
        Cursor cursor = db.query("student", new String[]{"snum", "sname", "sage"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, String> item = new HashMap<>();
            item.put("snum", cursor.getString(cursor.getColumnIndex("snum")));
            item.put("sname", cursor.getString(cursor.getColumnIndex("sname")));
            item.put("sage", cursor.getString(cursor.getColumnIndex("sage")));
            mapList.add(item);
        }

        adapter = new SimpleAdapter(this, mapList, R.layout.list_item, new String[]{"snum", "sname", "sage"}, new int[]{R.id.snum_text, R.id.sname_text, R.id.sage_text});
        listView.setAdapter(adapter);


    }

    protected void init() {
        listView = findViewById(R.id.reslist);
        mapList = new ArrayList<>();
    }
}
