package com.ebaonet.pharmacy.view.filter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.view.filter.inter.OnClickFilterSingleListItem;
import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

import java.util.ArrayList;


/**
 * @author yao.feng
 * 
 *         2016-1-11
 */
public class FilterSingleListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<SingleFilterObj> mDataAl = new ArrayList<SingleFilterObj>();
	private int selectPos = 0;// 默认当前选择的是第一项

	private OnClickFilterSingleListItem mListener;
	private int index;// 对于横向下拉菜单有多个的时候，index用于区分是哪一个下拉菜单

	private int defalutColor, selectColor;

	public FilterSingleListAdapter(Context mContext) {
		this.mInflater = LayoutInflater.from(mContext);
		defalutColor = mContext.getResources().getColor(R.color.default_filter_color);
		selectColor = mContext.getResources().getColor(R.color.select_filter_color);
	}

	public void setOnClickFilterSingleListItem(OnClickFilterSingleListItem l) {// 设置点击回调监听
		this.mListener = l;
	}

	public void setIndex(int index) {// 设置当前下拉菜单的下标
		this.index = index;
	}

	public void setData(ArrayList<SingleFilterObj> mData) {// 加载数据
		this.mDataAl.clear();
		this.mDataAl.addAll(mData);
	}

	public void setSelectPosition(int selectPos) {// 设置当前选择的position
		this.selectPos = selectPos;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mDataAl.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataAl.get(position);
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
			convertView = mInflater.inflate(R.layout.pharmacy_item_single_list_filter, null);
			mHolder.mNameText = (TextView) convertView.findViewById(R.id.filter_value);
			mHolder.mSelctFlagimg = (ImageView) convertView.findViewById(R.id.filter_select_flag);
			convertView.setTag(mHolder);
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					System.out.println("====filter=====onclick=====position=" + position
							+ ",,,index =" + index);
					setSelectPosition(position);
					if (mListener != null) {
						mListener.onClickFilterSingeItem(index, position, mDataAl.get(position));
					} 
				}
			});
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.mNameText.setText(mDataAl.get(position).getName());
		if (position == selectPos) {
			mHolder.mNameText.setTextColor(selectColor);
			mHolder.mSelctFlagimg.setVisibility(View.VISIBLE);
		} else {
			mHolder.mNameText.setTextColor(defalutColor);
			mHolder.mSelctFlagimg.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	class ViewHolder {
		TextView mNameText;
		ImageView mSelctFlagimg;
	}

}
