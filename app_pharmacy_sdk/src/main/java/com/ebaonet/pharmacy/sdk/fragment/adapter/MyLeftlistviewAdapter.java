package com.ebaonet.pharmacy.sdk.fragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.drug.sort.level1.DrugLevelOneData;
import com.ebaonet.pharmacy.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojun.gao on 2016/8/12.
 */
public class MyLeftlistviewAdapter extends BaseAdapter {

    private Context mContext;
    private  List<DrugLevelOneData.SonCateInfos> mList = new ArrayList<DrugLevelOneData.SonCateInfos>();
    public MyLeftlistviewAdapter(Context context, List<DrugLevelOneData.SonCateInfos> list) {
        mContext = context;
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
    }
    public MyLeftlistviewAdapter(Context context) {
        mContext = context;
    }
    /**给adapter设置数据*/
    public void setDefault( List<DrugLevelOneData.SonCateInfos> list){
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.pharmacy_left_item_listview,null);
            convertView.setTag(viewHolder);
            viewHolder.tv_left = (TextView) convertView.findViewById(R.id.pharamcy_left_textview);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mList.get(position).isChecked()){
            viewHolder.tv_left.setBackgroundColor(Color.WHITE);
            viewHolder.tv_left.setTextColor(mContext.getResources().getColor(R.color.color_blue_00a5f9));
            /*convertView.setBackgroundColor(Color.WHITE);*/
        }else{
            viewHolder.tv_left.setBackgroundColor(00000000);
            viewHolder.tv_left.setTextColor(mContext.getResources().getColor(R.color.module_title_black));

        }
        viewHolder.tv_left.setText(mList.get(position).getCateName());
        return  convertView;
    }

    public class ViewHolder {
        TextView tv_left;
    }
}

