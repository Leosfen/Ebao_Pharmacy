package com.ebaonet.pharmacy.sdk.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebaonet.pharmacy.entity.order.orderlist.OrderDrugInfo;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderManagerInfo;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.LogisticsDetailActivity;
import com.ebaonet.pharmacy.sdk.activity.OrderDetailActivity;
import com.ebaonet.pharmacy.util.StringUtils;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.view.NoScrollListView;
import com.ebaonet.pharmacy.view.RoundDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao.feng on 2016/9/23.
 */
public class MyOrderListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private OnDeleteOrderListener mLitener;

    private ArrayList<OrderManagerInfo> mBigOrders = new ArrayList<>();
    private ArrayList<MyDrugChildAdapter> mChildAdapters = new ArrayList<>();

    public MyOrderListAdapter(Context mCon) {
        this.mContext = mCon;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setOnDeleteListener(OnDeleteOrderListener l) {
        this.mLitener = l;
    }

    public void setOrderData(List<OrderManagerInfo> mdata) {
        this.mBigOrders.clear();
        this.mBigOrders.addAll(mdata);

        this.mChildAdapters.clear();
        for (OrderManagerInfo bowp : mBigOrders) {
            mChildAdapters.add(new MyDrugChildAdapter(mContext, bowp.getDrug()));
        }

        notifyDataSetChanged();
    }

    public void addOrderData(List<OrderManagerInfo> list) {
        this.mBigOrders.addAll(list);
        for (OrderManagerInfo bowp : list) {
            mChildAdapters.add(new MyDrugChildAdapter(mContext, bowp.getDrug()));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBigOrders.size();
    }

    @Override
    public OrderManagerInfo getItem(int i) {
        return mBigOrders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (view == null) {
            mHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.pharmacy_adapter_big_order_list, null);
            mHolder.mPharNameTv = (TextView) view.findViewById(R.id.pharmacy_name);
            mHolder.mOrderTypeTv = (TextView) view.findViewById(R.id.order_type);
            mHolder.mCountsTv = (TextView) view.findViewById(R.id.total_count);
            mHolder.mTotalRMBTv = (TextView) view.findViewById(R.id.total_rmb);
            mHolder.mConnectShopTv = (TextView) view.findViewById(R.id.connect_shop);
            mHolder.mFindLogisticsTv = (TextView) view.findViewById(R.id.find_logistics);
            mHolder.mGoodsListLv = (NoScrollListView) view.findViewById(R.id.goods_drug_listview);
            mHolder.mDividerView = view.findViewById(R.id.my_order_list_divider);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        final OrderManagerInfo mBigOrder = getItem(i);
        final String mOrderId = mBigOrder.getOrderId();
        if (mBigOrder != null && mBigOrder.getDrug() != null && mBigOrder.getDrug().size() > 0) {
            mHolder.mPharNameTv.setText(mBigOrder.getDrug().get(0).getDrugStoreName());
            int drugCount = 0;
            for (OrderDrugInfo mDrugInfo : mBigOrder.getDrug()) {
                drugCount += mDrugInfo.getDrugNum();
            }
            mHolder.mCountsTv.setText("共" + drugCount + "件商品");
        } else {
            mHolder.mPharNameTv.setText("");
            mHolder.mCountsTv.setText("");
        }
        final String orderTypeString = getOrderTypeString(mBigOrder.getOrderStatus());
        if("已完成".equals(orderTypeString)||"已取消".equals(orderTypeString)){
            mHolder.mOrderTypeTv.setTextColor(mContext.getResources().getColor(R.color.textcolor_9c9c9c));
        }
        mHolder.mOrderTypeTv.setText(orderTypeString); 
        mHolder.mTotalRMBTv.setText("¥" + StringUtils.format(StringUtils.formatDouble(mBigOrder.getOrderPrice()))); 
        mHolder.mGoodsListLv.setAdapter(mChildAdapters.get(i));
        mHolder.mGoodsListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(mContext, OrderDetailActivity.class);
                mIntent.putExtra(OrderDetailActivity.ORDER_DETAIL_INFO, mBigOrder);
                mContext.startActivity(mIntent);
            }
        });
        if (isCanDelete(mBigOrder.getOrderStatus())) {
            mHolder.mConnectShopTv.setText("删除订单");
        } else {
            mHolder.mConnectShopTv.setText("联系药店");
        }
        mHolder.mConnectShopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanDelete(mBigOrder.getOrderStatus())) {
                    View mView = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_dialog_delete_order, null);
                    final RoundDialog mRd = new RoundDialog(mContext, mView);
                    mView.findViewById(R.id.click_cancel_delete_order).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRd.dismiss();
                        }
                    });
                    mView.findViewById(R.id.click_confirm_delete_order).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRd.dismiss();
                            if (mLitener != null) {
                                mLitener.deleteOrder(mBigOrder.getOrderId());
                            }
                        }
                    });
                    mRd.show();
                } else {
                    if (!TextUtils.isEmpty(mBigOrder.getCsPhone())) {
                        callPhone(mBigOrder.getCsPhone());
                    } else {
                        UIUtils.showToast(mContext, "暂无电话信息！");
                    }
                }
            }
        });
        mHolder.mFindLogisticsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LogisticsDetailActivity.class);
                intent.putExtra(OrderDetailActivity.ORDER_NUM, mOrderId);//?
                mContext.startActivity(intent);
            }
        });

        if (i == mBigOrders.size() - 1) {
            mHolder.mDividerView.setVisibility(View.GONE);
        } else {
            mHolder.mDividerView.setVisibility(View.VISIBLE);
        }
        return view;
    }

    class ViewHolder {
        TextView mPharNameTv, mOrderTypeTv, mCountsTv, mTotalRMBTv, mConnectShopTv, mFindLogisticsTv;
        NoScrollListView mGoodsListLv;
        View mDividerView;
    }

    public String getOrderTypeString(String orderStatus) {
        if (orderStatus.equals("3")) {
            return "进行中";
        } else if (orderStatus.equals("5")) {
            return "已完成";
        } else if (orderStatus.equals("6")) {
            return "已取消";
        }
        return "";
    }

    public boolean isCanDelete(String orderStatus) {
        if (orderStatus.equals("5") || orderStatus.equals("6")) {
            return true;
        }
        return false;
    }

    private void callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        mContext.startActivity(intent);
    }


    public interface OnDeleteOrderListener {
        void deleteOrder(String orderId);
    }
}
