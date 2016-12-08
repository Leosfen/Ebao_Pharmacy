package com.ebaonet.pharmacy.sdk.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.entity.config.ConfigInfo;
import com.ebaonet.pharmacy.manager.ConfigInfoManager;
import com.ebaonet.pharmacy.manager.config.ConfigInfoConfig;
import com.ebaonet.pharmacy.manager.params.ConfigParamsHelper;
import com.ebaonet.pharmacy.sdk.fragment.ShoppingCarFragment;
import com.ebaonet.pharmacy.sdk.fragment.ShoppingIndexFragment;
import com.ebaonet.pharmacy.sdk.fragment.ShoppingListFragment;
import com.ebaonet.pharmacy.sdk.fragment.ShoppingSortFragment;
import com.ebaonet.pharmacy.sdk.fragment.adapter.PhaFraPageAdapter;
import com.ebaonet.pharmacy.ui.support.SharedHelper;
import com.ebaonet.pharmacy.util.JsonUtil;
import com.ebaonet.pharmacy.util.ResourceUitls;
import com.ebaonet.pharmacy.util.permission.MPermissions;
import com.ebaonet.pharmacy.view.FixedSpeedScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by yao.feng on 2016/8/8.
 */
public class StartActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    private LinearLayout mToolbar;

    private LinearLayout mCurLl;

    private int selCor, unSelCor, selFirst, unSelFirst, selSort, unSelSort, selCar, unSelCar, selMine, unSelMine;

    private ViewPager mPharmacyVp;

    private PhaFraPageAdapter mVpAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ResourceUitls.getLayoutId(this, "pharmacy_activity_start"));
        initResourceId();
        initFragment();
        initView();
        initData();
        //请求权限
        requestPermission();
    }

    private void requestPermission() {
        MPermissions.requestStoragePermission(this, this);
        MPermissions.requestPhonePermission(this, this);
    }

    private void initData() {
        ConfigInfoManager.getInstance().getConfigList(ConfigParamsHelper.configList("10", "1"));
    }

    private void initFragment() {
        mFragments.add(new ShoppingIndexFragment());
        mFragments.add(new ShoppingSortFragment());
        mFragments.add(new ShoppingCarFragment());
        mFragments.add(new ShoppingListFragment());

        mVpAdapter = new PhaFraPageAdapter(getSupportFragmentManager());
        mVpAdapter.setFragments(mFragments);
    }


    private void initResourceId() {
        selCor = getResources().getColor(ResourceUitls.getColorId(this, "pharmacy_select_color"));
        unSelCor = getResources().getColor(ResourceUitls.getColorId(this, "pharmacy_unselect_color"));
        selFirst = ResourceUitls.getDrawableId(this, "pharmacy_pocket_pharmacies_selected");
        unSelFirst = ResourceUitls.getDrawableId(this, "pharmacy_pocket_pharmacies");
        selSort = ResourceUitls.getDrawableId(this, "pharmacy_classification_selected");
        unSelSort = ResourceUitls.getDrawableId(this, "pharmacy_classification");
        selCar = ResourceUitls.getDrawableId(this, "pharmacy_list_selected");
        unSelCar = ResourceUitls.getDrawableId(this, "pharmacy_list");
        selMine = ResourceUitls.getDrawableId(this, "pharmacy_order_selected");
        unSelMine = ResourceUitls.getDrawableId(this, "pharmacy_order");
    }

    private void initView() {

        mPharmacyVp = (ViewPager) findViewById(ResourceUitls.getId(this, "pharmacy_four_viewpager"));
        mPharmacyVp.setAdapter(mVpAdapter);
        mPharmacyVp.setOnPageChangeListener(this);
        setViewPagerScrollSpeed();

        mToolbar = (LinearLayout) findViewById(ResourceUitls.getId(this, "pharmacy_bottom_toolbar"));
        int mSize = mToolbar.getChildCount();
        for (int i = 0; i < mSize; i++) {
            LinearLayout mChildLl = (LinearLayout) mToolbar.getChildAt(i);
            mChildLl.setTag(i);
            mChildLl.setOnClickListener(new MyChildClickListener());
        }
        //首次进入药店显示第一栏
        setCurSelectView((LinearLayout) mToolbar.getChildAt(0), true);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        onActionSelectItem((LinearLayout) mToolbar.getChildAt(i));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    class MyChildClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPharmacyVp.setCurrentItem((Integer) v.getTag());
            onActionSelectItem((LinearLayout) v);
        }
    }

    private void onActionSelectItem(LinearLayout mCheck) {
        if (mCurLl != null) {
            //置为未选择
            setCurSelectView(mCurLl, false);
        }
        setCurSelectView(mCheck, true);
    }

    private void setCurSelectView(LinearLayout mCheckLl, boolean isCheck) {
        //设为当前选择
        if (isCheck) {
            mCurLl = (LinearLayout) mCheckLl;
        }
        ImageView mChildImg = (ImageView) mCurLl.getChildAt(0);
        TextView mChildTv = (TextView) mCurLl.getChildAt(1);
        Integer mCurIndex = (Integer) mCurLl.getTag();
        if (mCurIndex == 0) {
            if (isCheck) {
                mChildImg.setImageResource(selFirst);
            } else {
                mChildImg.setImageResource(unSelFirst);
            }
        } else if (mCurIndex == 1) {
            if (isCheck) {
                mChildImg.setImageResource(selSort);
            } else {
                mChildImg.setImageResource(unSelSort);
            }
        } else if (mCurIndex == 2) {
            if (isCheck) {
                mChildImg.setImageResource(selCar);
            } else {
                mChildImg.setImageResource(unSelCar);
            }
        } else if (mCurIndex == 3) {
            if (isCheck) {
                mChildImg.setImageResource(selMine);
            } else {
                mChildImg.setImageResource(unSelMine);
            }
        }

        if (isCheck) {
            mChildTv.setTextColor(selCor);
        } else {
            mChildTv.setTextColor(unSelCor);
        }
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private void setViewPagerScrollSpeed() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mPharmacyVp.getContext());
            mScroller.set(mPharmacyVp, scroller);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }

    public void pageToFragmentByPosition(int position) {
        if (mPharmacyVp != null) {
            mPharmacyVp.setCurrentItem(position);
        }
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (tag == ConfigInfoConfig.CONFIGLISTINFO) {
            if (code == 1) {
                ConfigInfo.DataBean data = ((ConfigInfo) obj).getData();
                String s = JsonUtil.toJSONString(data);
                SharedHelper.saveConfigInfo(mContext, s);
            }
        }
    }
}
