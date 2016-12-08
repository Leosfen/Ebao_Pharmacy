package com.ebaonet.pharmacy.sdk.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.drug.sort.level2.CateleveltwoInfo;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.view.MyGridview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojun.gao on 2016/8/12.
 */
public class MyRightlistviewAdapter extends BaseAdapter {
    private Context mContext;
    private List<CateleveltwoInfo> mList = new ArrayList<CateleveltwoInfo>();
    private MyGridviewAdapter mMyGridviewAdapter;

    public MyRightlistviewAdapter(Context context) {
        mContext = context;
    }

    private List<CateleveltwoInfo.SonCateInfos> cateInfos;

    public void setDefault(List<CateleveltwoInfo> list) {
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.pharmacy_right_item_listview, null);
            convertView.setTag(viewHolder);
            viewHolder.tv_right = (TextView) convertView.findViewById(R.id.pharamcy_right_textview);
            viewHolder.gv = (MyGridview) convertView.findViewById(R.id.pharmcy_gridview);//gridview
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_right.setText(mList.get(position).getCateName());//二级分类名称
        mMyGridviewAdapter = new MyGridviewAdapter(mContext);
        viewHolder.gv.setAdapter(mMyGridviewAdapter);
        cateInfos = mList.get(position).getSonCateInfos();
        if (cateInfos != null && cateInfos.size() > 0) {//因为有的三级列表可能为空  目前是第三个为空
            mMyGridviewAdapter.setDefault(cateInfos);
        }
        // mMyGridviewAdapter.setDefault(cateInfos);
        return convertView;
    }

    public class ViewHolder {
        TextView tv_right;
        MyGridview gv;
    }
}

