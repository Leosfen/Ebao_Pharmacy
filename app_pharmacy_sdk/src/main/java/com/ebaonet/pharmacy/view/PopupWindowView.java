package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.DrugInfoActivity;

/**
 * Created by peng.dong on 2016/9/18.
 */
public class PopupWindowView extends LinearLayout {
    View tvIndex;//首页
    View tvOnline;//掌上药店
    DrugInfoActivity.MyClickListener listener;

    public PopupWindowView(Context context, boolean itemThree) {
        super(context);
        init(context, itemThree);
    }

    public PopupWindowView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init(context);
    }


    private void init(Context context, boolean itemThree) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.pharmacy_layout_popupwindow, this, true);
        TextView tv_requirementslist = (TextView) inflate.findViewById(R.id.tv_requirementslist);
        if (itemThree == true) {
            tv_requirementslist.setVisibility(VISIBLE);
        } else {
            tv_requirementslist.setVisibility(GONE);
        }
        tvIndex = inflate.findViewById(R.id.pop_index);
        tvOnline = inflate.findViewById(R.id.pop_online);
        tvIndex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvOnline.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.PageToOnLine();
                }

            }
        });
    }

    public void setMyClickListener(DrugInfoActivity.MyClickListener mlistener) {
        listener = mlistener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
