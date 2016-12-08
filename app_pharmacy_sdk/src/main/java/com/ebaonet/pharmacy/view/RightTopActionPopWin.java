package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ebaonet.pharmacy.base.activity.ActivityHelper;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.StartActivity;

/**
 * Created by yao.feng on 2016/9/28.
 */
public class RightTopActionPopWin extends PopupWindow {

    private View tvIndex;//首页
    private View tvOnline;//掌上药店
    private View tvThird;//需求清单

    private Context mContext;

    public RightTopActionPopWin(Context mContext, boolean isShowThird) {
        this.mContext = mContext;
        initView(isShowThird);
    }

    private void initView(boolean isShowThird) {
        View mRightTopView = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_layout_popupwindow, null);
        tvIndex = mRightTopView.findViewById(R.id.pop_index);
        tvOnline = mRightTopView.findViewById(R.id.pop_online);
        tvThird = mRightTopView.findViewById(R.id.tv_requirementslist);
        if (isShowThird) {
            tvThird.setVisibility(View.VISIBLE);
        } else {
            tvThird.setVisibility(View.GONE);
        }
        mRightTopView.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Logger.d("===popview:" + mRightTopView.getMeasuredWidth() + "=:" + mRightTopView.getMeasuredHeight());
        setContentView(mRightTopView);
        setHeight(mRightTopView.getMeasuredHeight());
        setWidth(mRightTopView.getMeasuredWidth());
        setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.pharmacy_more_bc));
        setOutsideTouchable(true);
        setFocusable(true);//
        setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        tvIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.getInstance().popAllActivityExceptOne(null);
            }
        });
        tvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity activity = (StartActivity) ActivityHelper.getInstance().getActivity((Class) StartActivity.class);
                if (activity != null) {
                    activity.pageToFragmentByPosition(0);
                    ActivityHelper.getInstance().popAllActivityExceptOne(StartActivity.class);
                }
            }
        });
        tvThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity activity = (StartActivity) ActivityHelper.getInstance().getActivity((Class) StartActivity.class);
                if (activity != null) {
                    activity.pageToFragmentByPosition(2);
                    ActivityHelper.getInstance().popAllActivityExceptOne(StartActivity.class);
                }
            }
        });

    }


}


