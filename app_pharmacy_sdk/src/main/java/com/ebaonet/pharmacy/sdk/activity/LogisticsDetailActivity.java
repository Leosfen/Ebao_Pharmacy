package com.ebaonet.pharmacy.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.activity.BaseActivity;
import com.ebaonet.pharmacy.entity.order.OrderProgressEntity;
import com.ebaonet.pharmacy.manager.OrderListManager;
import com.ebaonet.pharmacy.manager.config.OrderConfig;
import com.ebaonet.pharmacy.manager.params.OrderParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.adapt.MyLogisticsAdapter;
import com.ebaonet.pharmacy.view.RightTopActionPopWin;

import java.util.List;

/**
 * 物流信息
 * Created by zhaojun.gao on 2016/9/26.
 */
public class LogisticsDetailActivity extends BaseActivity {

    private TextView mTv_logistics_state;
    private TextView mTv_logistics_number;
    private TextView mTv_logistics_time;
    private ListView mLl_logistics;
    private MyLogisticsAdapter mMyLogisticsAdapter;
    private List<OrderProgressEntity.DataBean.LogsBean> mLogs;
    private RightTopActionPopWin popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_activity_logistics_detail);
        initView();
        initData();
    }

    private void initData() {
        final String orderCode = getIntent().getStringExtra(OrderDetailActivity.ORDER_NUM);
        OrderListManager.getInstance().queryOrderProgress(OrderParamsHelper.queryOrderProgress(orderCode, "10", "1"));
    }

    private void initView() {
        tvTitle.setText("订单进度");
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setImageResource(R.drawable.pharmacy_titlebar_icon_point_normal);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        mTv_logistics_state = (TextView) findViewById(R.id.tv_logistics_state);
        mTv_logistics_number = (TextView) findViewById(R.id.tv_logistics_number);
        mTv_logistics_time = (TextView) findViewById(R.id.tv_logistics_time);
        mLl_logistics = (ListView) findViewById(R.id.ll_logistics);
        mMyLogisticsAdapter = new MyLogisticsAdapter(mContext);
        mLl_logistics.setAdapter(mMyLogisticsAdapter);
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (tag.equals(OrderConfig.ORDER_PROGRESS)) {
            if (code == 1) {
                OrderProgressEntity.DataBean data = ((OrderProgressEntity)obj).getData();
               if (data!=null){
                String orderStatus = data.getOrderStatus();//订单状态
                String orderCode = data.getOrderCode();//订单编码
                String creTime = data.getCreTime();//订单创建时间
                mLogs = data.getLogs();
                switch (orderStatus){
                    case "1":
                        mTv_logistics_state.setText("进行中");
                        break;
                    case "2":
                        mTv_logistics_state.setText("进行中");
                        break;
                    case "3":
                        mTv_logistics_state.setText("进行中");
                        break;
                    case "4":
                        mTv_logistics_state.setText("待收货");
                        break;
                    case "5":
                        mTv_logistics_state.setText("已完成");
                        break;
                    case "6":
                        mTv_logistics_state.setText("已取消");
                        break;
                }
                mTv_logistics_number.setText(orderCode);
                mTv_logistics_time.setText(creTime);
                mMyLogisticsAdapter.setOrderProgressList(mLogs);
               }
            }
        }
    }
    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            popupWindow = new RightTopActionPopWin(mContext, true);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(v, btnRight.getWidth() / 3, -(btnRight.getHeight() / 3));
            popupWindow.update();
        } else {
            popupWindow.dismiss();
        }
    }
}
