package com.example.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TabLayout tb = findViewById(R.id.tbl);

        ViewPager viewPager = findViewById(R.id.vp);
        List<View> list = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();
        list.add(li.inflate(R.layout.vp_item1, null));
        list.add(li.inflate(R.layout.vp_item2, null));
        list.add(li.inflate(R.layout.vp_item3, null));
        ViewPagerAda pagerAda = new ViewPagerAda(list);
        viewPager.setAdapter(pagerAda);
        tb.setupWithViewPager(viewPager);

//        Button button=findViewById(R.id.vp2btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                button.setText("lailg");
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); //实例化MenuInflater；

        inflater.inflate(R.menu.menu, menu); //解析菜单文件
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}