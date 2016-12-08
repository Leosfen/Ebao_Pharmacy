package com.ebaonet.pharmacy.sdk.adapt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebaonet.pharmacy.sdk.fragment.drug.LazyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojun.gao on 2016/8/31.
 */
public class DrugDetailFragPagerAdapter extends FragmentPagerAdapter {
    //fragment 集合
    private List<LazyFragment> mFragmentList = new ArrayList<LazyFragment>();
    public DrugDetailFragPagerAdapter(FragmentManager fm, List<LazyFragment> list) {
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
