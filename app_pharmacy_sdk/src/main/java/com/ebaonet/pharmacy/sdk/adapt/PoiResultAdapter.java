package com.ebaonet.pharmacy.sdk.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.ebaonet.pharmacy.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao.feng on 2016/8/16.
 */
public class PoiResultAdapter extends BaseAdapter {

    private List<PoiInfo> mPoiInfos = new ArrayList<PoiInfo>();

    private LayoutInflater mInflater;
    private Context mContext;

    public PoiResultAdapter(Context mContext) {
        this.mContext=mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setPoiInfos(List<PoiInfo> mLps) {
        mPoiInfos.clear();
        mPoiInfos.addAll(mLps);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPoiInfos.size();
    }

    @Override
    public PoiInfo getItem(int position) {
        return mPoiInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.pharmacy_item_adapter_poi_result, null);
            mHolder.mName = (TextView) convertView.findViewById(R.id.poi_result_name);
            mHolder.mAddress = (TextView) convertView.findViewById(R.id.poi_result_address);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mName.setText(mPoiInfos.get(position).name);
        mHolder.mAddress.setText(mPoiInfos.get(position).address);
        if (position == 0) {
            mHolder.mName.setTextColor(mContext.getResources().getColor(R.color.color_blue_00a5f9));
            mHolder.mAddress.setTextColor(mContext.getResources().getColor(R.color.color_black_666666));
        } else {
            mHolder.mName.setTextColor(mContext.getResources().getColor(R.color.color_black_484747));
            mHolder.mAddress.setTextColor(mContext.getResources().getColor(R.color.color_gray_9c9c9c));
        }
        return convertView;
    }

    class ViewHolder {
        TextView mName, mAddress;
    }
}
