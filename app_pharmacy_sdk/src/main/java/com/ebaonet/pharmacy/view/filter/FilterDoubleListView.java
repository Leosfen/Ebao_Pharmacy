package com.ebaonet.pharmacy.view.filter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.view.filter.adapter.FilterDoubleViewAdapter;
import com.ebaonet.pharmacy.view.filter.adapter.FilterDoubleViewChildAdapter;
import com.ebaonet.pharmacy.view.filter.inter.OnClickFilterDoubleItem;
import com.ebaonet.pharmacy.view.filter.obj.DoubleFilterObj;
import com.ebaonet.pharmacy.view.filter.obj.FilterParams;

import java.util.ArrayList;


/**
 * 混合的过滤项作为下拉菜单
 *
 * @author yao.feng
 *         <p/>
 *         2016年1月12日
 */
public class FilterDoubleListView extends LinearLayout implements View.OnClickListener {

    private OnClickFilterDoubleItem mListener;
    private Context mContext;
    private ListView mListView;
    private FilterDoubleViewAdapter mDoubleViewAdapter;
    private ArrayList<DoubleFilterObj> mDoubObjs = new ArrayList<DoubleFilterObj>();
    private TextView mReset, mComplete;
    private View mFootView;
    private EditText mMinEdit, mMaxEdit;
    private LinearLayout contentLayout;
    private LinearLayout mBottomLayout;
    private int initHeight;
    private CharSequence temp;
    private final int charMaxNumCompany = 5;
    public void setLayHeight(int layHeight) {
        if (layHeight != 0) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, layHeight);
            contentLayout.setLayoutParams(llp);
        } else {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, initHeight);
            contentLayout.setLayoutParams(llp);
        }
    }

    public void setInitHeight(int initHeight) {
        this.initHeight = initHeight;
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, initHeight);
        contentLayout.setLayoutParams(llp);
    }

    public FilterDoubleListView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FilterDoubleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void setOnClickFilterDoubleListItem(OnClickFilterDoubleItem l) {// 设置点击回调监听
        mListener = l;
    }

    public void setIndex(int index) {// 设置当前下拉菜单的下标
        mDoubleViewAdapter.setIndex(index);
    }

    public void setDoubleFilterData(ArrayList<DoubleFilterObj> filters) {
        mDoubObjs.clear();
        mDoubObjs.addAll(filters);
        mDoubleViewAdapter.setData(filters);
        mDoubleViewAdapter.notifyDataSetChanged();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pharmacy_view_filter_double, this, true);

        contentLayout = (LinearLayout) findViewById(R.id.view_filter_double_content);
        mListView = (ListView) findViewById(R.id.filter_listView_double);

        mFootView = inflater.inflate(R.layout.pharmacy_double_filter_footer, null);
        mMinEdit = (EditText) mFootView.findViewById(R.id.price_min_num);
        mMaxEdit = (EditText) mFootView.findViewById(R.id.price_max_num);
        mMaxEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int start = mMaxEdit.getSelectionStart();
                int end = mMaxEdit.getSelectionEnd();
                if (temp.length() > charMaxNumCompany) {
                    Toast.makeText(mContext, "最高只能输入五位数字！", Toast.LENGTH_LONG).show();
                    s.delete(start - 1, end);
                    mMaxEdit.setText(s);
                    mMaxEdit.setSelection(temp.length());
                }
            }
        });
        mListView.addFooterView(mFootView);

        mDoubleViewAdapter = new FilterDoubleViewAdapter(mContext);
        mDoubleViewAdapter.setOnClickFilterDoubleListItem(mListener);
        mListView.setAdapter(mDoubleViewAdapter);

        mReset = (TextView) findViewById(R.id.filter_double_resetTv);
        mReset.setOnClickListener(this);
        mComplete = (TextView) findViewById(R.id.filter_double_completeTv);
        mComplete.setOnClickListener(this);

        findViewById(R.id.blank_layout2).setOnClickListener(this);

        mBottomLayout = (LinearLayout) findViewById(R.id.bottom_linear_layout);
        mBottomLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mBottomLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mBottomLayout.getHeight();
                if (mListener != null) {
                    int footerBottomHei = (int) getResources().getDimension(R.dimen.filter_list_footer_bottom);
                    mListener.countBottomHeight(mBottomLayout.getHeight() + footerBottomHei);
                }
            }
        });
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int MaxHeight = (int) (((Activity) mContext).getWindowManager().getDefaultDisplay()
//                .getHeight() * 3 / 3);
//        super.onMeasure(widthMeasureSpec,
//                MeasureSpec.makeMeasureSpec(MaxHeight, MeasureSpec.AT_MOST));
//    }

    @Override
    public void onClick(View v) {
        if (mReset.getId() == v.getId()) {
            clickReset();
            if (mListener != null) {
                mListener.onClickReset();
            }
        } else if (mComplete.getId() == v.getId()) {
            if (mListener != null) {
                mListener.onClickComplete();
            }
        } else if (v.getId() == R.id.blank_layout2) {
            if (mListener != null) {
                mListener.onDismissDoubleView();
            }
        }
    }

    // 点击重置后，所有的子gridView都要默认选择的是第一项
    public void clickReset() {
        for (FilterDoubleViewChildAdapter adapter : mDoubleViewAdapter.getAllChildAdapters()) {
            adapter.setSelectPosition(0);
        }
        mMinEdit.setText("");
        mMaxEdit.setText("");
    }

    public void setFilterParamsValue(FilterParams params) {
        params.lowPrice = mMinEdit.getText().toString().trim();
        params.highPrice = mMaxEdit.getText().toString().trim();
        mDoubleViewAdapter.setFilterParamsValue(params);
    }

}
