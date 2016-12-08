package com.ebaonet.pharmacy.manager.abs;

import com.ebaonet.pharmacy.base.manager.ManagerBean;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * 药品相关操作
 * Created by zhaojun.gao on 2016/8/19.
 */
public abstract class AbsOrder extends ManagerBean {

    /**
     * 订单配送方式修改，重新生成订单信息
     *
     * @param params
     */
    public abstract void changeDelivery(RequestParams params);

    /**
     * 删除订单
     *
     * @param params
     */
    public abstract void deleteOrder(RequestParams params);

    /**
     * 订单提交
     *
     * @param params
     */
    public abstract void submitOrder(RequestParams params);

    /**
     * 生成订单
     *
     * @param params
     */
    public abstract void createOrder(RequestParams params);


    /**
     * 订单进度
     *
     * @param params
     */
    public abstract void queryOrderProgress(RequestParams params);


    /**
     * 获取我的订单列表
     *
     * @param params
     */
    public abstract void getOrderManagerList(RequestParams params);

    /**
     * 获取正在进行中的订单数量
     *
     * @param params
     */
    public abstract void getOnDoingOrderNum(RequestParams params);
}
