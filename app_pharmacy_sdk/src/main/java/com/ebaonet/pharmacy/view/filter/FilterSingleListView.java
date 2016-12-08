package com.ebaonet.pharmacy.view.filter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.view.filter.adapter.FilterSingleListAdapter;
import com.ebaonet.pharmacy.view.filter.inter.OnClickFilterSingleListItem;
import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

import java.util.ArrayList;


/**
 * 布局中仅含有一个listview,作为下拉菜单
 *
 * @author yao.feng
 *         <p/>
 *         2016年1月11日
 */
public class FilterSingleListView extends LinearLayout implements View.OnClickListener {

    private ListView mListView;
    private ArrayList<SingleFilterObj> filterObjs = new ArrayList<SingleFilterObj>();
    private FilterSingleListAdapter mAdapter;
    private Context mContext;
    private int selectPos = 0;// 当前点击的位置，默认为第一个
    private int MaxHeight;
    private OnClickFilterSingleListItem mListener;
    private int index;// 对于横向下拉菜单有多个的时候，index用于区分是哪一个下拉菜单

    private LinearLayout mContentLayout;

    public FilterSingleListView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FilterSingleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public void setLayHeight(int layHeight) {
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, layHeight);
        mContentLayout.setLayoutParams(llp);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pharmacy_view_filter_single_list, this, true);

        mContentLayout = (LinearLayout) findViewById(R.id.view_filter_single_list_content);
        mListView = (ListView) findViewById(R.id.filter_listView);
        mAdapter = new FilterSingleListAdapter(mContext);
        mListView.setAdapter(mAdapter);

        findViewById(R.id.blank_layout1).setOnClickListener(this);
        findViewById(R.id.blank_layout2).setOnClickListener(this);
        // mListView.setOnItemClickListener(new OnItemClickListener() {
        //
        // @Override
        // public void onItemClick(AdapterView<?> parent, View view, int
        // position, long id) {
        // selectPos = position;
        // mAdapter.setSelectPosition(position);
        // if (mListener != null) {
        // mListener.onClickFilterSingeItem(index, position,
        // filterObjs.get(position));
        // }
        // }
        // });
    }

    public void setFilterData(ArrayList<SingleFilterObj> filters) {// 装载要显示的菜单列表
        this.filterObjs.clear();
        this.filterObjs.addAll(filters);
        mAdapter.setData(filters);
        mAdapter.setSelectPosition(selectPos);// 默认选择的是第一个
        mAdapter.notifyDataSetChanged();
        // mearsureHeight(mContext, mListView);
    }

    public void setOnClickFilterSingleListItem(OnClickFilterSingleListItem l) {// 设置点击回调监听
        this.mListener = l;
        if (mAdapter != null) {
            mAdapter.setOnClickFilterSingleListItem(mListener);//把监听对象传给adapter
        }
    }

    public void setIndex(int index) {// 设置当前下拉菜单的下标
        this.index = index;
        mAdapter.setIndex(index);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec,
//                MeasureSpec.makeMeasureSpec(layHeight, MeasureSpec.AT_MOST));
//    }

    public void clickReset() {// 重置
        mAdapter.setSelectPosition(0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.blank_layout1 || v.getId() == R.id.blank_layout2) {
            if (mListener != null) {
                mListener.onDismissSingleView();
            }
        }
    }
}
