package com.ebaonet.pharmacy.sdk.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.fragment.adapter.PhaFraPageAdapter;
import com.ebaonet.pharmacy.sdk.fragment.orders.OrdersFragment;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 我的订单
 * Created by yao.feng on 2016/9/22.
 */
public class MyOrdersListActivity extends BaseActivity {

    private TabLayout mOrderTabs;
    private ViewPager mOrderVp;
    private PhaFraPageAdapter mPfpAdapter;
    private ArrayList<Fragment> mFrgList = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();
    private RightTopActionPopWin popupWindow;
    public final static String WHICH_CURRENT_POS = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_my_orders_list);
        initFragments();
        initView();
        setCurrentPage();
    }
    
    private void setCurrentPage() {
        int mCurPagePos;
        if (getIntent() != null) {
            mCurPagePos = getIntent().getIntExtra(WHICH_CURRENT_POS, 0);
            mOrderVp.setCurrentItem(mCurPagePos);
        }
    }

    private void initView() {
        
        tvTitle.setText("我的订单");
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        mOrderTabs = (TabLayout) findViewById(R.id.my_orders_tabs);
        mOrderVp = (ViewPager) findViewById(R.id.orders_view_pager);

        mOrderTabs.addTab(mOrderTabs.newTab().setText(mTitles.get(0)));
        mOrderTabs.addTab(mOrderTabs.newTab().setText(mTitles.get(1)));
        mOrderTabs.addTab(mOrderTabs.newTab().setText(mTitles.get(2)));
        mOrderTabs.addTab(mOrderTabs.newTab().setText(mTitles.get(3)));

        mPfpAdapter = new PhaFraPageAdapter(getSupportFragmentManager());
        mPfpAdapter.setFragments(mFrgList);
        mPfpAdapter.setTitles(mTitles);
        mOrderVp.setAdapter(mPfpAdapter);

        mOrderTabs.setTabMode(TabLayout.MODE_FIXED);
        mOrderTabs.setupWithViewPager(mOrderVp);
        mOrderTabs.setTabsFromPagerAdapter(mPfpAdapter);

        setIndicatorWidth();
        
    }

    private void initFragments() {
        OrdersFragment mAllFrg = new OrdersFragment();
        Bundle mAllBun = new Bundle();
        mAllBun.putInt(OrdersFragment.ORDER_TYPE, OrdersFragment.ALL_ORDERS);
        mAllFrg.setArguments(mAllBun);

        OrdersFragment mDoingFrg = new OrdersFragment();
        Bundle mDoingBun = new Bundle();
        mDoingBun.putInt(OrdersFragment.ORDER_TYPE, OrdersFragment.DOING_ORDERS);
        mDoingFrg.setArguments(mDoingBun);

        OrdersFragment mCompleteFrg = new OrdersFragment();
        Bundle mCompBun = new Bundle();
        mCompBun.putInt(OrdersFragment.ORDER_TYPE, OrdersFragment.COMPLETE_ORDERS);
        mCompleteFrg.setArguments(mCompBun);

        OrdersFragment mCancelFrg = new OrdersFragment();
        Bundle mCancelBun = new Bundle();
        mCancelBun.putInt(OrdersFragment.ORDER_TYPE, OrdersFragment.CANCEL_ORDERS);
        mCancelFrg.setArguments(mCancelBun);

        mFrgList.add(mAllFrg);
        mFrgList.add(mDoingFrg);
        mFrgList.add(mCompleteFrg);
        mFrgList.add(mCancelFrg);

        mTitles.add("全部");
        mTitles.add("进行中");
        mTitles.add("已完成");
        mTitles.add("已取消");
    }

    private void setIndicatorWidth() {
        int leftMar = (int) getResources().getDimension(R.dimen.orders_width_per);
        try {
            Class<?> tablayout = mOrderTabs.getClass();
            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(mOrderTabs);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = leftMar;
                params.rightMargin = params.leftMargin;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (Exception e) {
        }
    }

    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, btnRight.getWidth() / 3, -(btnRight.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }
}
