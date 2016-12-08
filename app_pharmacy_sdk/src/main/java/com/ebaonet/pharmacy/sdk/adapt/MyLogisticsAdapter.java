package com.ebaonet.pharmacy.sdk.adapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.order.OrderProgressEntity;
import com.ebaonet.pharmacy.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看物流适配器
 * Created by zhaojun.gao on 2016/9/26.
 */
public class MyLogisticsAdapter extends BaseAdapter {
    private Context context;
    private List<OrderProgressEntity.DataBean.LogsBean> list = new ArrayList<>();
    public MyLogisticsAdapter(Context context) {
        this.context = context;
    }

    public void setOrderProgressList(List<OrderProgressEntity.DataBean.LogsBean> list) {
        this.list.clear();
        this.list = list;
        this.notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView = View.inflate(context, R.layout.pharmacy_logigtics_item,null);
            viewHolder.logisticsTitle =(TextView)convertView.findViewById(R.id.logistics_item_title);
            viewHolder.logisticsDes =(TextView)convertView.findViewById(R.id.logistics_item_des);
            viewHolder.rl_left = (RelativeLayout)convertView.findViewById(R.id.rl_left);
            viewHolder.ll_left =(LinearLayout)convertView.findViewById(R.id.ll_left);
            convertView.setTag(viewHolder);
        }else{
            viewHolder  = (ViewHolder) convertView.getTag();
        }
        if(position == 0){
            viewHolder.logisticsTitle.setTextColor(context.getResources().getColor(R.color.color_green_92cb2d));
            viewHolder.logisticsDes.setTextColor(context.getResources().getColor(R.color.color_green_92cb2d));
            viewHolder.ll_left.setVisibility(View.VISIBLE);
            viewHolder.rl_left.setVisibility(View.GONE);
        }else{
            viewHolder.rl_left.setVisibility(View.VISIBLE);
            viewHolder.ll_left.setVisibility(View.GONE);
        }
        OrderProgressEntity.DataBean.LogsBean logsBean = list.get(position);
        viewHolder.logisticsTitle.setText(logsBean.getOrderPrompt());
        viewHolder.logisticsDes.setText(logsBean.getOperTime());
        return convertView;
    }
    
    class ViewHolder{
        TextView logisticsTitle,logisticsDes;
        RelativeLayout rl_left;
        LinearLayout ll_left;
        View item_line;
    }
}
