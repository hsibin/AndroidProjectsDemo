package com.example.actionbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subacr);
        if (NavUtils.getParentActivityName(SubActivity.this) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
