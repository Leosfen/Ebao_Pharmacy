package com.ebaonet.pharmacy.sdk.adapt;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhaojun.gao on 2016/8/31.
 */
public class DrugDetailPagerAdapter extends PagerAdapter {
   
    private ArrayList<View> mViews = new ArrayList<View>();
    public DrugDetailPagerAdapter() {
    }
    
    public void setViews(ArrayList<View> mViews) {
        this.mViews.clear();
        this.mViews.addAll(mViews);
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }
}
