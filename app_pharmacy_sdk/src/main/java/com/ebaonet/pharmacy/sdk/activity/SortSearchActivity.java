package com.ebaonet.pharmacy.sdk.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.fragment.search.SearchFragment;

/**
 * 搜索历史记录界面
 * Created by zhaojun.gao on 201MAX/8/6.
 */
public class SortSearchActivity extends BaseActivity {

    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_sortsearch_activity);
        showSearchFragment();
    }

    private void showSearchFragment() {
        FragmentTransaction mFt = getSupportFragmentManager().beginTransaction();
        if (mSearchFragment == null) {
            mSearchFragment = new SearchFragment();
        }
        if (!mSearchFragment.isAdded()) {
            mFt.add(R.id.search_content_fragment, mSearchFragment);
        }
        mFt.show(mSearchFragment);
        mFt.commit();
    }
    
}