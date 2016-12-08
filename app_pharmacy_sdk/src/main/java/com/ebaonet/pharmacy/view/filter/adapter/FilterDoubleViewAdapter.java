package com.ebaonet.pharmacy.view.filter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.view.filter.obj.FilterParams;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.view.MyGridview;
import com.ebaonet.pharmacy.view.filter.inter.OnCheckFilterDoubleItem;
import com.ebaonet.pharmacy.view.filter.inter.OnClickFilterDoubleItem;
import com.ebaonet.pharmacy.view.filter.obj.DoubleFilterObj;
import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


/**
 * @author yao.feng
 *         <p/>
 *         2016年1月12日
 */
public class FilterDoubleViewAdapter extends BaseAdapter implements OnCheckFilterDoubleItem {

    private HashMap<Integer, Boolean> mArrowMaps = new HashMap<Integer, Boolean>();
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<DoubleFilterObj> mData = new ArrayList<DoubleFilterObj>();
    private ArrayList<FilterDoubleViewChildAdapter> mChildAdapters = new ArrayList<FilterDoubleViewChildAdapter>();
    private OnClickFilterDoubleItem mListener;
    private int index;// 对于横向下拉菜单有多个的时候，index用于区分是哪一个下拉菜单

    private int blueCol, greyCol;

    private HashMap<Integer, HashMap<Integer, SingleFilterObj>> mCheckedObj = new HashMap<Integer, HashMap<Integer, SingleFilterObj>>();

    public FilterDoubleViewAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);

        blueCol = mContext.getResources().getColor(R.color.select_filter_color);
        greyCol = mContext.getResources().getColor(R.color.color_gray_9c9c9c);
    }

    public void setData(ArrayList<DoubleFilterObj> data) {
        mData.clear();
        mData.addAll(data);
        int len = data.size();
        for (int i = 0; i < len; i++) {
            mChildAdapters.add(new FilterDoubleViewChildAdapter(mContext, data.get(i).getmChild()));
        }
    }

    public ArrayList<FilterDoubleViewChildAdapter> getAllChildAdapters() {// 获得所有的子gridview中的adapter
        return mChildAdapters;
    }

    public void setOnClickFilterDoubleListItem(OnClickFilterDoubleItem l) {// 设置点击回调监听
        this.mListener = l;
    }

    public void setIndex(int index) {// 设置当前下拉菜单的下标
        this.index = index;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.pharmacy_item_filter_double, null);
            mHolder.mDoubleTitle = (TextView) convertView.findViewById(R.id.double_filter_title);
            mHolder.mNsGridView = (MyGridview) convertView
                    .findViewById(R.id.double_filter_gridview);
            mHolder.mArrow = (ImageView) convertView.findViewById(R.id.action_arrow_down);
            mHolder.mCheckText = (TextView) convertView.findViewById(R.id.double_filter_check_data);
            mHolder.mActionDown = (LinearLayout) convertView.findViewById(R.id.action_arrow_down_ll);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.mDoubleTitle.setText(mData.get(pos).getName());
        setCheckString(pos, mHolder.mCheckText);

        final FilterDoubleViewChildAdapter mAdapter = mChildAdapters.get(pos);
        mAdapter.setIndex(index);
        mAdapter.setParentPosition(pos);
        mAdapter.setOnCheckFilterDoubleListItem(this);
        mHolder.mNsGridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mHolder.mActionDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArrowMaps.get(pos) != null && mArrowMaps.get(pos)) {
                    //转换箭头，关闭，隐藏8后面的数据
                    mArrowMaps.put(pos, false);
                    mHolder.mArrow.setImageResource(R.drawable.pharmacy_screen_icon_arrow_down);
                    if (mAdapter.getAllData().size() > 8) {
                        mAdapter.closeEightPlusData();
                    }
                } else {
                    //转换箭头，打开全部数据
                    mArrowMaps.put(pos, true);
                    mHolder.mArrow.setImageResource(R.drawable.pharmacy_screen_icon_arrow_upward);
                    if (mAdapter.getAllData().size() > 8) {
                        mAdapter.openAllData();
                    }
                }
            }
        });


        // mHolder.mNsGridView.setOnItemClickListener(new OnItemClickListener()
        // {
        //
        // @Override
        // public void onItemClick(AdapterView<?> parent, View view, int
        // position, long id) {
        // mChildAdapters.get(pos).setSelectPosition(position);
        // if (mListener != null) {
        // mListener.onClickFilterDoubleItem(index, pos, position,
        // (SingleFilterObj) (mChildAdapters.get(pos).getItem(position)));
        // }
        // }
        // });
        return convertView;
    }

    @Override
    public void onCheckFilterDoubleItem(int index, int parentPos, int childPos,
                                        SingleFilterObj obj, boolean isChecked) {
        Logger.d("===double==filter===parentPos==" + parentPos + ",,childPos=="
                + childPos + ",,,index==" + index + ",,,"
                + obj.getName() + "check=" + isChecked);
        if (mCheckedObj.get(parentPos) == null) {
            mCheckedObj.put(parentPos, new HashMap<Integer, SingleFilterObj>());
        }
        if (childPos == 0) {
            if (isChecked) {
                mCheckedObj.get(parentPos).clear();
            } else {
                mCheckedObj.get(parentPos).remove(childPos);
            }
        } else {
            if (isChecked) {
                mCheckedObj.get(parentPos).put(childPos, obj);
            } else {
                mCheckedObj.get(parentPos).remove(childPos);
            }
        }

        notifyDataSetChanged();
    }


    class ViewHolder {
        TextView mDoubleTitle;
        MyGridview mNsGridView;
        ImageView mArrow;
        TextView mCheckText;
        LinearLayout mActionDown;
    }

    private void setCheckString(int parPos, TextView mTextview) {
        HashMap<Integer, SingleFilterObj> map = mCheckedObj.get(parPos);
        if (map == null) {
            mTextview.setText("全部");
            mTextview.setTextColor(greyCol);
        } else {
            StringBuilder sb = new StringBuilder();
            //增加排序
            ArrayList<HashMap.Entry<Integer, SingleFilterObj>> mListhe =
                    new ArrayList<HashMap.Entry<Integer, SingleFilterObj>>(map.entrySet());
            Collections.sort(mListhe, new Comparator<HashMap.Entry<Integer, SingleFilterObj>>() {
                @Override
                public int compare(HashMap.Entry<Integer, SingleFilterObj> lhs, HashMap.Entry<Integer, SingleFilterObj> rhs) {
                    return lhs.getKey() - rhs.getKey();
                }
            });
            for (HashMap.Entry<Integer, SingleFilterObj> hes : mListhe) {
                sb.append(hes.getValue().getName() + "、");
            }
            int len = sb.length();
            if (len == 0) {
                mTextview.setText("全部");
                mTextview.setTextColor(greyCol);
            } else {
                String showText = sb.deleteCharAt(len - 1).toString();
                if (showText.length() > 16) {
                    mTextview.setText(showText.substring(0, 16) + "...");
                } else {
                    mTextview.setText(showText);
                }
                mTextview.setTextColor(blueCol);
            }
        }
    }

    public void setFilterParamsValue(FilterParams params) {
        params.typeId.delete(0, params.typeId.length());
        params.brandId.delete(0, params.brandId.length());
        params.drugFormId.delete(0, params.drugFormId.length());

        HashMap<Integer, SingleFilterObj> map0 = mCheckedObj.get(0);
        if (map0 != null) {
            for (HashMap.Entry<Integer, SingleFilterObj> heis : map0.entrySet()) {
                if (!heis.getValue().getId().equals(SingleFilterObj.NOT_LIMIT_ID)) {
                    params.typeId.append(heis.getValue().getId()).append(",");
                    Logger.d("SingleFilterObj" + heis.getValue().toString());
                }
            }
            if (params.typeId.length() > 0) {
                params.typeId.deleteCharAt(params.typeId.length() - 1);
            }
            Logger.d("setFilterParamsValue0===" + params.typeId.toString());
        }
        HashMap<Integer, SingleFilterObj> map1 = mCheckedObj.get(1);
        if (map1 != null) {
            for (HashMap.Entry<Integer, SingleFilterObj> heis : map1.entrySet()) {
                if (!heis.getValue().getId().equals(SingleFilterObj.NOT_LIMIT_ID)) {
                    params.brandId.append(heis.getValue().getId()).append(",");
                    Logger.d("SingleFilterObj" + heis.getValue().toString());
                }
            }
            if (params.brandId.length() > 0) {
                params.brandId.deleteCharAt(params.brandId.length() - 1);
            }
            Logger.d("setFilterParamsValue1===" + params.brandId.toString());
        }
        HashMap<Integer, SingleFilterObj> map2 = mCheckedObj.get(2);
        if (map2 != null) {
            for (HashMap.Entry<Integer, SingleFilterObj> heis : map2.entrySet()) {
                if (!heis.getValue().getId().equals(SingleFilterObj.NOT_LIMIT_ID)) {
                    params.drugFormId.append(heis.getValue().getId()).append(",");
                    Logger.d("SingleFilterObj" + heis.getValue().toString());
                }
            }
            if (params.drugFormId.length() > 0) {
                params.drugFormId.deleteCharAt(params.drugFormId.length() - 1);
            }
            Logger.d("setFilterParamsValue2====" + params.drugFormId.toString());
        }

    }


}
