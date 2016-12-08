package com.ebaonet.pharmacy.view.tabview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebaonet.pharmacy.base.fragment.BaseFragment;

/**
 * Created by yao.feng on 2016/9/6.
 */
public class ExpandViewFragment extends BaseFragment {


    public ExpandViewFragment(View mCreateView) {
        this.mView = mCreateView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mView;
    }
}
