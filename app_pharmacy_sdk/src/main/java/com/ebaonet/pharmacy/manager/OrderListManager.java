package com.ebaonet.pharmacy.manager;

import com.ebaonet.pharmacy.entity.BaseEntity;
import com.ebaonet.pharmacy.entity.order.CreateOrderEntry;
import com.ebaonet.pharmacy.entity.order.DeliveryEntity;
import com.ebaonet.pharmacy.entity.order.OrderProgressEntity;
import com.ebaonet.pharmacy.entity.order.SubmitOrderEntry;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderDoingNum;
import com.ebaonet.pharmacy.entity.order.orderlist.OrderManagerListInfo;
import com.ebaonet.pharmacy.manager.abs.AbsOrder;
import com.ebaonet.pharmacy.manager.config.OrderConfig;
import com.ebaonet.pharmacy.request.PharmacyUrlConst;
import com.ebaonet.pharmacy.request.RequestManager;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by peng.dong on 2016/10/10.
 */
public class OrderListManager extends AbsOrder {

    private OrderListManager() {
    }

    private static OrderListManager mInstance;

    public static OrderListManager getInstance() {
        if (mInstance == null) {
            mInstance = new OrderListManager();
        }
        return mInstance;
    }


    @Override
    public void changeDelivery(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.CHANGE_DELIVERY, OrderConfig.CHANGE_DELIVERY, params,
                this, DeliveryEntity.class);
    }

    @Override
    public void deleteOrder(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DELETE_ORDER, OrderConfig.DELETE_ORDER, params,
                this, BaseEntity.class);
    }

    @Override
    public void submitOrder(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.SUBMIT_ORDER, OrderConfig.SUBMIT_ORDER, params,
                this, SubmitOrderEntry.class);
    }

    @Override
    public void createOrder(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.CREATE_ORDER, OrderConfig.CREATE_ORDER, params,
                this, CreateOrderEntry.class);
    }

    //物流查询
    @Override
    public void queryOrderProgress(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.ORDER_PROGRESS, OrderConfig.ORDER_PROGRESS, params,
                this, OrderProgressEntity.class);
    }

    @Override
    public void getOrderManagerList(RequestParams params) {
        startCallBack();
        String key = null;
        if (params != null) {
            key = params.getValue("key");
        }
        RequestManager.post(PharmacyUrlConst.GET_ORDER_MANAGER_LIST, OrderConfig.GET_ORDER_MANAGER_LIST, params,
                this, OrderManagerListInfo.class, key);
    }

    @Override
    public void getOnDoingOrderNum(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.GET_ORDER_DOING_COUNT, OrderConfig.GET_ON_DOING_ORDER_NUM, params,
                this, OrderDoingNum.class);
    }

}
