package com.example.datadeal;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datadeal.data.Sqlite;

public class AddWord extends AppCompatActivity {

    private Sqlite dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newword_main);

        dbManager = new Sqlite(this, "first.db", null, 1);

    }
}
