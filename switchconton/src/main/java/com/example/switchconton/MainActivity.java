package com.example.switchconton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout li = findViewById(R.id.li);
        ImageView iv = findViewById(R.id.img);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getBackground();
        li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    animationDrawable.start();
                    flag = false;
                } else {
                    animationDrawable.stop();
                    flag = true;
                }
            }
        });

        final ImageView img = findViewById(R.id.alpha);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                img.startAnimation(animation);
            }
        });

    }


}