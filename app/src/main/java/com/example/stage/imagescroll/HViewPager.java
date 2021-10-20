package com.example.stage.imagescroll;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class HViewPager extends ViewPager {

    Handler handler;

    public HViewPager(@NonNull Context context) {
        super(context);
    }

    public HViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        handler = new Handler(Looper.getMainLooper());

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        starLoop();
    }

    private void starLoop() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                int currItem = getCurrentItem();
                currItem++;
                setCurrentItem(currItem);
                postDelayed(this,2000);
            }
        });
    }
}
