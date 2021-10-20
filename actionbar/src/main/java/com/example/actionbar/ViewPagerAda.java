package com.example.actionbar;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAda extends PagerAdapter {

    List<View> viewList;

    public ViewPagerAda(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        List<String> listTitle = new ArrayList<>();
        listTitle.add("tb1");
        listTitle.add("tb2");
        listTitle.add("tb3");
        return listTitle.get(position);
    }
}
