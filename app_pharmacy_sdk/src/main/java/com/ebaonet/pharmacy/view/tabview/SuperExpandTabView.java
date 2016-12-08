package com.ebaonet.pharmacy.view.tabview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.ebaonet.pharmacy.sdk.R;

import java.util.ArrayList;

/**
 * Created by yao.feng on 2016/9/6.
 */
public class SuperExpandTabView extends LinearLayout {

    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    private Context mContext;
    private int contentViewId;

    private ToggleButton mCurTb;
    private Fragment mCurFragment;

    public SuperExpandTabView(Context context) {
        super(context);
        this.mContext = context;
    }

    public SuperExpandTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void setExpandContentId(int contentViewId) {
        this.contentViewId = contentViewId;
    }

    public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
        removeAllViews();
        mFragments.clear();
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(llp);
        setBackgroundColor(Color.WHITE);

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        int textCount = textArray.size();
        int childCount = viewArray.size();
        for (int i = 0; i < childCount; i++) {
            RelativeLayout mToggleView = (RelativeLayout) mInflater.inflate(R.layout.pharmacy_toggle_button, null);
            ToggleButton mTb = (ToggleButton) mToggleView.findViewById(R.id.tBtn);
            mTb.setOnClickListener(new OnTabClickListener(i));
            if (i < textCount) {
                mTb.setText(textArray.get(i));
            }

            LinearLayout.LayoutParams childLlp = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            childLlp.weight = 1;
            addView(mToggleView, childLlp);

            //添加竖线
            if (i != childCount) {
                ImageView line = new ImageView(mContext);
                line.setImageResource(R.drawable.pharmacy_choosebar_line);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                mToggleView.addView(line, lp);
            }

            mFragments.add(new ExpandViewFragment(viewArray.get(i)));
        }

    }


    public void onPressBack() {
        if (mCurFragment != null) {
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().
                    beginTransaction();
            ft.hide(mCurFragment);
            ft.commit();
        }

        if (mCurTb != null) {
            mCurTb.setChecked(false);
        }
    }


    class OnTabClickListener implements View.OnClickListener {

        private int position;

        public OnTabClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            ToggleButton mTob = (ToggleButton) v;
            if (mCurTb != null && mCurTb == mTob) {
                if (mTob.isChecked()) {
                    showExpandViewByPosition(position);
                } else {
                    hideExpandViewByPosition(position);
                }
            } else {
                if (mCurTb != null) {
                    mCurTb.setChecked(false);
                }
                showExpandViewByPosition(position);
            }
            mCurTb = mTob;
        }
    }

    private synchronized void showExpandViewByPosition(int position) {
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().
                beginTransaction();
        if (mCurFragment != null) {
            if (!mCurFragment.isHidden()) {
                ft.hide(mCurFragment);
            }
        }
        mCurFragment = mFragments.get(position);
        if (!mCurFragment.isAdded()) {
            ft.add(contentViewId, mFragments.get(position));
        }
        if (mCurFragment.isHidden()) {
            ft.show(mCurFragment);
        }
        ft.commit();
    }

    private synchronized void hideExpandViewByPosition(int position) {
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().
                beginTransaction();
        if (!mFragments.get(position).isAdded()) {
            ft.add(contentViewId, mFragments.get(position));
        }
        if (!mFragments.get(position).isHidden()) {
            ft.hide(mCurFragment);
        }
        ft.commit();
    }

    private void setText(String text) {

    }

}
