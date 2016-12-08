package com.ebaonet.pharmacy.sdk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.fragment.BaseFragment;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderDoingNum;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.manager.OrderListManager;
import com.ebaonet.pharmacy.manager.config.OrderConfig;
import com.ebaonet.pharmacy.manager.params.OrderParamsHelper;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.activity.ManageAddrActivity;
import com.ebaonet.pharmacy.sdk.activity.MyOrdersListActivity;
import com.ebaonet.pharmacy.sdk.activity.StartActivity;
import com.ebaonet.pharmacy.util.ResourceUitls;

/**
 * Created by yao.feng on 2016/8/11.
 * 购药订单
 */
public class ShoppingListFragment extends BaseFragment implements View.OnClickListener {

    private TextView mOrderDoingNumTv;
    private ImageButton mPharmacy_leftBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(ResourceUitls.getLayoutId(mContext, "pharmacy_fragment_list"), container, false);
            initView();
        }
        return mView;
    }

    @Override
    protected void lazyLoad() {
        getOrderDoingNum();
    }

    private void getOrderDoingNum() {
        PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
        OrderListManager.getInstance().getOnDoingOrderNum(OrderParamsHelper.getOnDoingOrderNum(
                userInfo == null ? "" : userInfo.getUserId()
                , "23", "33"
        ));
    }

    private void initView() {
        mPharmacy_leftBtn = (ImageButton) mView.findViewById(R.id.pharmacy_leftBtn);
        mPharmacy_leftBtn.setVisibility(View.GONE);
        TextView title = (TextView) mView.findViewById(ResourceUitls.getId(mContext, "tv_title"));
        title.setText("订单管理");
        mView.findViewById(R.id.layout_manage_addr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ManageAddrActivity.class);
                startActivity(intent);
            }
        });
        mView.findViewById(R.id.pharmacy_leftBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StartActivity) mContext).pageToFragmentByPosition(0);
            }
        });
        mView.findViewById(R.id.all_orders_layout).setOnClickListener(this);
        mView.findViewById(R.id.shopping_doing).setOnClickListener(this);
        mView.findViewById(R.id.shoppingsort_finished).setOnClickListener(this);
        mView.findViewById(R.id.shoppingsort_cancal).setOnClickListener(this);
        mOrderDoingNumTv = (TextView) mView.findViewById(R.id.order_doing_num);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.all_orders_layout) {
            startActivity(new Intent(mContext, MyOrdersListActivity.class));
        } else if (i == R.id.shopping_doing) {
            Intent mIntent = new Intent(mContext, MyOrdersListActivity.class);
            mIntent.putExtra(MyOrdersListActivity.WHICH_CURRENT_POS, 1);
            startActivity(mIntent);
        } else if (i == R.id.shoppingsort_finished) {
            Intent mIntent = new Intent(mContext, MyOrdersListActivity.class);
            mIntent.putExtra(MyOrdersListActivity.WHICH_CURRENT_POS, 2);
            startActivity(mIntent);
        } else if (i == R.id.shoppingsort_cancal) {
            Intent mIntent = new Intent(mContext, MyOrdersListActivity.class);
            mIntent.putExtra(MyOrdersListActivity.WHICH_CURRENT_POS, 3);
            startActivity(mIntent);
        }
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (OrderConfig.GET_ON_DOING_ORDER_NUM.equals(tag)) {
            if (code == 1) {
                OrderDoingNum orderDoingNum = (OrderDoingNum) obj;
                Logger.d("datadata:" + orderDoingNum.getData());
                if (orderDoingNum != null && !TextUtils.isEmpty(orderDoingNum.getData())) {
                    int orderDoingCount = 0;
                    try {
                        orderDoingCount = Integer.parseInt(orderDoingNum.getData());
                    } catch (Exception e) {
                    }
                    if (orderDoingCount > 0) {
                        mOrderDoingNumTv.setVisibility(View.VISIBLE);
                        if (orderDoingCount < 100) {
                            mOrderDoingNumTv.setText(orderDoingNum.getData());
                        } else {
                            mOrderDoingNumTv.setText("99+");
                        }
                    } else {
                        mOrderDoingNumTv.setVisibility(View.INVISIBLE);
                    }
                } else {
                    mOrderDoingNumTv.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public boolean isNetDataTransmission() {
        return true;
    }
}
