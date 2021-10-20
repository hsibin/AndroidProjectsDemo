package com.example.stage.imagescroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.stage.R;

public class PagerAda extends PagerAdapter {

    private int[] imgColl = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6};

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View item = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_litm, container, false);
        ImageView iv = item.findViewById(R.id.cover);

        iv.setImageResource(imgColl[position % imgColl.length]);
        if (iv.getParent() instanceof ViewGroup) {
            ((ViewGroup) iv.getParent()).removeView(iv);
        }
        container.addView(iv);

        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
