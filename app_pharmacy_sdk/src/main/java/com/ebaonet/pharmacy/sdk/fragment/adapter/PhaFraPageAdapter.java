package com.ebaonet.pharmacy.sdk.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yao.feng on 2016/8/12.
 */
public class PhaFraPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private ArrayList<String> mTitles = new ArrayList<>();

    public PhaFraPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments.clear();
        mFragments.addAll(fragments);
    }

    public void setTitles(ArrayList<String> mTitles) {
        this.mTitles.clear();
        this.mTitles.addAll(mTitles);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles.size() > position) {
            return mTitles.get(position);
        }
        return super.getPageTitle(position);
    }
}
