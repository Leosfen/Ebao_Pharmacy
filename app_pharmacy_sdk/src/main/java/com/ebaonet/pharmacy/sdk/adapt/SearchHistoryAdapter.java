package com.ebaonet.pharmacy.sdk.adapt;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.fragment.search.SearchFragment;
import com.ebaonet.pharmacy.view.model.SearchAutoData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojun.gao on 2016/9/8.
 */
//
public class SearchHistoryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SearchAutoData> mOriginalValues;// 所有的Item
    private List<SearchAutoData> mObjects = new ArrayList<SearchAutoData>();// 过滤后的item，没用
    private final Object mLock = new Object();//??????????????????????
    private int mMaxMatch ;// 最多显示多少个选项,负数表示全部
    public SearchHistoryAdapter(Context context,int maxMatch) {
        this.context = context;
        this.mMaxMatch = maxMatch;
        initSearchHistory();
//        mObjects = mOriginalValues;
    }
    public void setData(){
        initSearchHistory();
        notifyDataSetChanged();
    }
    public int getSize() {
        return mObjects.size();
//          return mOriginalValues.size();
    }

    @Override
    public int getCount() {
        return null == mObjects ? 0 : mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        if (position == mObjects.size()) {
            clear();
        }
        return null == mObjects ? 0 : mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AutoHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.pharmacy_seach_list_item, parent, false);
            holder = new AutoHolder();
            holder.content = (TextView) convertView
                    .findViewById(R.id.auto_content);
            holder.item_divider = convertView.findViewById(R.id.item_divider);
            holder.item_divider2 = convertView.findViewById(R.id.item_divider2);
            if(position == mObjects.size()-1){
                holder.item_divider.setVisibility(View.VISIBLE);
            }else{
                holder.item_divider2.setVisibility(View.VISIBLE);
            }
            convertView.setTag(holder);
        } else {
            holder = (AutoHolder) convertView.getTag();
        }
        
        SearchAutoData data = mObjects.get(position);
        holder.content.setText(data.getContent());
        return convertView;
    }
    
    
    /**
     * 读取历史搜索记录
     */
    public void initSearchHistory() {
        SharedPreferences sp = context.getSharedPreferences(
                SearchFragment.SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SearchFragment.SEARCH_HISTORY, "");
        String[] hisArrays = longhistory.split(",");//字符串数组
        mOriginalValues = new ArrayList<SearchAutoData>();//new出一个list集合，承载字符串数组中的每一个元素
        if (longhistory.equals("")) {
            return;
        }
        //将元素添加到集合中
        for (int i = 0; i < hisArrays.length; i++) {
            mOriginalValues.add(new SearchAutoData().setContent(hisArrays[i]));
        }
        mObjects.clear();
        mObjects.addAll(mOriginalValues);
        Logger.d("===size====" + mObjects.size());
        notifyDataSetChanged();
    }

    /**
     * 清空历史
     */
    public void clear() {
        SharedPreferences sp = context.getSharedPreferences(
                SearchFragment.SEARCH_HISTORY, 0);
        sp.edit().putString(SearchFragment.SEARCH_HISTORY, "").commit();
        mObjects = null;
        mObjects = new ArrayList<SearchAutoData>();
        mOriginalValues = null;
        mOriginalValues = new ArrayList<SearchAutoData>();
        notifyDataSetChanged();
    }

    /**
     * 匹配过滤搜索内容
     *
     * @param prefix
     *            输入框中输入的内容
     */
    public void performFiltering(CharSequence prefix) {
        if (prefix == null || prefix.length() == 0) {// 搜索框内容为空的时候显示所有历史记录
            synchronized (mLock) {
                mObjects = mOriginalValues;
            }
        } else {
            String prefixString = prefix.toString().toLowerCase();
            int count = mOriginalValues.size();
            ArrayList<SearchAutoData> newValues = new ArrayList<SearchAutoData>(
                    count);
            for (int i = 0; i < count; i++) {
                final String value = mOriginalValues.get(i).getContent();
                final String valueText = value.toLowerCase();
                if (valueText.contains(prefixString)) {

                }
                if (valueText.startsWith(prefixString)) {
                    newValues.add(new SearchAutoData().setContent(valueText));
                } else {
                    final String[] words = valueText.split(" ");
                    final int wordCount = words.length;
                    for (int k = 0; k < wordCount; k++) {
                        if (words[k].startsWith(prefixString)) {
                            newValues.add(new SearchAutoData()
                                    .setContent(value));
                            break;
                        }
                    }
                }
                if (mMaxMatch > 0) {
                    if (newValues.size() > mMaxMatch - 1) {
                        break;
                    }
                }
            }
            mObjects = newValues;
        }
        notifyDataSetChanged();
    }

    private class AutoHolder {
        TextView content;
        View item_divider,item_divider2;
    }
}
