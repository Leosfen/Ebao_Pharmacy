package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by peng.dong on 2016/9/8.
 * 自定义viewpager解决viewpager里嵌套viewpager滑动冲突
 */
public class MyViewPager extends ViewPager {
    Context mContext;
    public MyViewPager(Context context) {
        super(context);
        mContext=context;
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }
    //作为子viewpager，拦截滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
