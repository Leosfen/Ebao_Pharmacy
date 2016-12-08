package com.ebaonet.pharmacy.view.filter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.view.filter.inter.OnCheckFilterDoubleItem;
import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author yao.feng
 *         <p/>
 *         2016年1月12日
 */
public class FilterDoubleViewChildAdapter extends BaseAdapter {

    private HashMap<Integer, Boolean> mArrowMaps = new HashMap<Integer, Boolean>();
    private OnCheckFilterDoubleItem mListener;
    private int index;// 对于横向下拉菜单有多个的时候，index用于区分是哪一个下拉菜单
    private int parentPos;

    private Context mContext;

    private ArrayList<SingleFilterObj> mAllData = new ArrayList<SingleFilterObj>();
    private ArrayList<SingleFilterObj> mShowData = new ArrayList<SingleFilterObj>();

    private int curSelPos = 0;

    private int defalutColor, selectColor, transColor;
    private int textHeight, textWidth;

    private static final int MIN_TXT_LENGTH = 5;

    private boolean isSingleCheck = false;//是否是单选

    public void setIsSingleCheck(boolean isSingleCheck) {
        this.isSingleCheck = isSingleCheck;
    }

    public FilterDoubleViewChildAdapter(Context mContext, ArrayList<SingleFilterObj> mArrayList) {
        this.mContext = mContext;

        defalutColor = mContext.getResources().getColor(R.color.default_filter_color);
        selectColor = mContext.getResources().getColor(R.color.select_filter_color);
        transColor = mContext.getResources().getColor(R.color.pharmacy_transparentcolor);
        textHeight = (int) mContext.getResources().getDimension(R.dimen.filter_text_height);
        initItemWidth();

        mAllData.clear();
        mAllData.addAll(mArrayList);

        mArrowMaps.put(0, true);//默认选择第一个位置

        closeEightPlusData();
    }

    public void openAllData() {
        mShowData.clear();
        mShowData.addAll(mAllData);
        notifyDataSetChanged();
    }

    public void closeEightPlusData() {
        mShowData.clear();
        int size = mAllData.size();
        for (int i = 0; i < size; i++) {
            if (i < 8) {
                mShowData.add(mAllData.get(i));
            }
        }
        notifyDataSetChanged();
    }

    private void initItemWidth() {
        int padding = (int) mContext.getResources().getDimension(R.dimen.filter_grid_view_padding);
        textWidth = (UIUtils.getScreenWidth(mContext) - 5 * padding) / 4;
    }

    public ArrayList<SingleFilterObj> getAllData() {
        return mAllData;
    }

    public void setAllData(ArrayList<SingleFilterObj> mArrayList) {
        mAllData.clear();
        mAllData.addAll(mArrayList);
    }

    public void setSelectPosition(int curPos) {// 设置菜单选择位置
        this.curSelPos = curPos;

        if (curPos == 0) {//为重置
            mArrowMaps.clear();
            mArrowMaps.put(0, true);
            if (mListener != null) {
                mListener.onCheckFilterDoubleItem(index, parentPos, 0,
                        getItem(0), true);
            }
        }
        this.notifyDataSetChanged();
    }

    public void setOnCheckFilterDoubleListItem(OnCheckFilterDoubleItem l) {// 设置点击回调监听
        this.mListener = l;
    }

    public void setIndex(int index) {// 设置当前下拉菜单的下标
        this.index = index;
    }

    public void setParentPosition(int pos) {// 当前adapter所属相对于主容器的位置
        this.parentPos = pos;
        if (parentPos == 0) {
            setIsSingleCheck(true);
        } else {
            setIsSingleCheck(false);
        }
    }

    @Override
    public int getCount() {
        return mShowData.size();
    }

    @Override
    public SingleFilterObj getItem(int position) {
        return mShowData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_item_filter_double_view_child, null);
            mHolder.mChildText = (TextView) convertView.findViewById(R.id.filter_double_child_name);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mChildText.setText(mShowData.get(position).getName());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(textWidth, textHeight);
        mHolder.mChildText.setLayoutParams(params);
        mHolder.mChildText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isSingleCheck) {
                    if (mArrowMaps.get(position) != null && mArrowMaps.get(position)) {
                        mArrowMaps.clear();
                        mArrowMaps.put(position, false);
                    } else {
                        mArrowMaps.clear();
                        mArrowMaps.put(position, true);
                    }
                    if (mListener != null) {
                        mListener.onCheckFilterDoubleItem(index, parentPos, 0,
                                getItem(position), true);
                        mListener.onCheckFilterDoubleItem(index, parentPos, position,
                                getItem(position), mArrowMaps.get(position));
                    }
                } else {
                    //转换选择状态
                    if (position == 0) {
                        if (mArrowMaps.get(position) != null && mArrowMaps.get(position)) {
                            mArrowMaps.put(position, false);
                        } else {
                            mArrowMaps.clear();
                            mArrowMaps.put(position, true);
                        }
                        if (mListener != null) {
                            mListener.onCheckFilterDoubleItem(index, parentPos, position,
                                    getItem(position), mArrowMaps.get(position));
                        }
                    } else {
                        if (mArrowMaps.get(position) != null && mArrowMaps.get(position)) {
                            mArrowMaps.put(position, false);
                        } else {
                            mArrowMaps.put(position, true);
                        }
                        //如果选择项大于5个，则提示文案
                        mArrowMaps.put(0, false);
                        if (checkItemCount() > 5) {
                            mArrowMaps.put(position, false);
                            UIUtils.showToast(mContext, "最多选择5个哦");
                        } else {
                            if (mListener != null) {
                                mListener.onCheckFilterDoubleItem(index, parentPos, position,
                                        getItem(position), mArrowMaps.get(position));
                            }
                        }
                        //如果选择了所有的选项，则把“不限”置为选择，其他的都置为未选择状态
//                    if (mListener != null) {
//                        mListener.onCheckFilterDoubleItem(index, parentPos, position,
//                                getItem(position), mArrowMaps.get(position));
//                    }
//                    if (checkItemCount() == mAllData.size() - 1) {//是否为全选
//                        mArrowMaps.put(0, true);
//                        if (mListener != null) {
//                            mListener.onCheckFilterDoubleItem(index, parentPos, 0,
//                                    getItem(position), true);
//                        }
//                    } else {
//                        mArrowMaps.put(0, false);
//                        if (mListener != null) {
//                            mListener.onCheckFilterDoubleItem(index, parentPos, 0,
//                                    getItem(position), false);
//                        }
//                    }
                    }
                }
                notifyDataSetChanged();

            }
        });

        //设置初始为选择状态
        if (mArrowMaps.get(0) != null && mArrowMaps.get(0)) {
            if (position != 0) {
                mArrowMaps.put(position, false);
                mHolder.mChildText.setTextColor(defalutColor);
                mHolder.mChildText.setBackgroundResource(R.drawable.pharmacy_filter_uncheck_button);
            } else {
                mArrowMaps.put(position, true);
                mHolder.mChildText.setTextColor(selectColor);
                mHolder.mChildText.setBackgroundResource(R.drawable.pharmacy_filter_check_button);
            }
        } else {
            if (position != 0) {
                if (mArrowMaps.get(position) != null && mArrowMaps.get(position)) {
                    mArrowMaps.put(position, true);
                    mHolder.mChildText.setTextColor(selectColor);
                    mHolder.mChildText.setBackgroundResource(R.drawable.pharmacy_filter_check_button);
                } else {
                    mArrowMaps.put(position, false);
                    mHolder.mChildText.setTextColor(defalutColor);
                    mHolder.mChildText.setBackgroundResource(R.drawable.pharmacy_filter_uncheck_button);
                }
            } else {
                mArrowMaps.put(position, false);
                mHolder.mChildText.setTextColor(defalutColor);
                mHolder.mChildText.setBackgroundResource(R.drawable.pharmacy_filter_uncheck_button);
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView mChildText;
    }

    private int checkItemCount() {
        int mAllSize = mAllData.size();
        int count = 0;
        for (int i = 1; i < mAllSize; i++) {
            if (mArrowMaps.get(i) != null && mArrowMaps.get(i)) {
                count++;
            }
        }
        return count;
    }


}
