package com.ebaonet.pharmacy.sdk.fragment.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.fragment.BaseFragment;
import com.ebaonet.pharmacy.data.PharCacheInfoManager;
import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderManagerListInfo;
import com.ebaonet.pharmacy.manager.OrderListManager;
import com.ebaonet.pharmacy.manager.config.OrderConfig;
import com.ebaonet.pharmacy.manager.params.OrderParamsHelper;
import com.ebaonet.pharmacy.request.params.RequestParams;
import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.sdk.fragment.adapter.MyOrderListAdapter;
import com.ebaonet.pharmacy.view.ActionEndToast;
import com.ebaonet.pharmacy.view.AutoListView;

/**
 * Created by yao.feng on 2016/9/22.
 */
public class OrdersFragment extends BaseFragment implements MyOrderListAdapter.OnDeleteOrderListener,
        AutoListView.OnLoadListener, AutoListView.OnRefreshListener {

    private static final int PAGE_SIZE = 10;
    public static int ALL_ORDERS = 1;//全部
    public static int DOING_ORDERS = 2;//进行中
    public static int COMPLETE_ORDERS = 3;//已完成
    public static int CANCEL_ORDERS = 4;//已取消

    public static String ORDER_TYPE = "type";
    private int ordersType = -1;

    private AutoListView mOrderListView;
    private MyOrderListAdapter mOrderListAdapter;
    private View mEmptyView;
    private int mCurRefreshState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ordersType = getArguments().getInt(ORDER_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.pharmacy_fragment_orders, null);
            initView();
//            getOrderList(1, AutoListView.REFRESH);
        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrderList(1, AutoListView.REFRESH);
    }

    private void getOrderList(int pageNum, int refreshState) {
        this.mCurRefreshState = refreshState;
        String orderState = "";
        if (ordersType == ALL_ORDERS) {
        } else if (ordersType == DOING_ORDERS) {
            orderState = "3";
        } else if (ordersType == COMPLETE_ORDERS) {
            orderState = "5";
        } else if (ordersType == CANCEL_ORDERS) {
            orderState = "6";
        }
        PharmcyUserInfo userInfo = PharCacheInfoManager.getUserInfo(mContext);
        RequestParams params = OrderParamsHelper.getOrderManagerListParams(
                userInfo==null?"":userInfo.getUserId()
                , orderState, "1", "2", PAGE_SIZE + "", pageNum + "");
        params.put("key", ordersType + "");
        OrderListManager.getInstance().getOrderManagerList(params);
    }

    private void initView() {
        mOrderListView = (AutoListView) mView.findViewById(R.id.my_orders_list_view);
        mOrderListView.setPageSize(PAGE_SIZE);
        mOrderListView.setOnLoadListener(this);
        mOrderListView.setOnRefreshListener(this);
        mOrderListAdapter = new MyOrderListAdapter(mContext);
        mOrderListAdapter.setOnDeleteListener(this);
        mOrderListView.setAdapter(mOrderListAdapter);

        mEmptyView = mView.findViewById(R.id.order_list_empty_view);
        mEmptyView.setVisibility(View.GONE);
        ((TextView) mView.findViewById(R.id.my_empty_text)).setText("您还没有相关的订单");
    }

    @Override
    public void onCallBack(String tag, int code, Object obj, String... keys) {
        super.onCallBack(tag, code, obj, keys);
        if (OrderConfig.GET_ORDER_MANAGER_LIST.equals(tag)) {
            if (keys != null && keys.length > 0) {
                if (keys[0].equals(ordersType + "")) {
                    if (mCurRefreshState == AutoListView.REFRESH) {
                        if (code == 1) {
                            OrderManagerListInfo mOmli = (OrderManagerListInfo) obj;
                            if (mOmli != null && mOmli.getData() != null && mOmli.getData().size() > 0) {
                                mOrderListView.setVisibility(View.VISIBLE);
                                mEmptyView.setVisibility(View.GONE);

                                mOrderListView.onRefreshComplete();
                                mOrderListView.setResultSize(mOmli.getData().size());
                                mOrderListAdapter.setOrderData(mOmli.getData());
                            } else {
                                mOrderListView.setVisibility(View.GONE);
                                mEmptyView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mOrderListView.setVisibility(View.GONE);
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    } else if (mCurRefreshState == AutoListView.LOAD) {
                        if (code == 1) {
                            OrderManagerListInfo mOmli = (OrderManagerListInfo) obj;
                            if (mOmli != null && mOmli.getData() != null && mOmli.getData().size() > 0) {
                                mOrderListView.setVisibility(View.VISIBLE);
                                mEmptyView.setVisibility(View.GONE);

                                mOrderListView.onLoadComplete();
                                mOrderListView.setResultSize(mOmli.getData().size());
                                mOrderListAdapter.addOrderData(mOmli.getData());
                            } else {
                                mOrderListView.onLoadComplete();
                                mOrderListView.setResultSize(0);
                            }
                        }
                    }

                }
            }
        } else if (OrderConfig.DELETE_ORDER.equals(tag)) {
            if (code == 1) {
                if (getUserVisibleHint()) {
                    ActionEndToast.showSmallImgToast(mContext, "删除订单成功", true);
                }
                getOrderList(1, AutoListView.REFRESH);
            }
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        OrderListManager.getInstance().deleteOrder(OrderParamsHelper.getDeleteOrderParams(orderId, "23", "345"));
    }

    @Override
    public void onRefresh() {
        if (mOrderListView != null) {
            mOrderListView.onRefreshComplete();
        }
    }

    @Override
    public void onLoad() {
        if (mOrderListView != null) {
            if (mOrderListAdapter != null) {
                getOrderList(mOrderListAdapter.getCount() / PAGE_SIZE + 1, AutoListView.LOAD);
            } else {
                mOrderListView.onLoadComplete();
            }
        }
    }

    @Override
    public boolean isNetDataTransmission() {
        return true;
    }
}
