package com.ebaonet.pharmacy.sdk.activity;

import android.os.Bundle;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.fragment.ShoppingCarFragment;

/**
 * Created by zhaojun.gao on 2016/11/14.
 */
public class ShoppCarActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_shoppcar_activity);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new ShoppingCarFragment(), "Other").commit();
    }
}
