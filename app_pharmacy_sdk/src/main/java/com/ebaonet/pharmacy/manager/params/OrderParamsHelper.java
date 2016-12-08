package com.ebaonet.pharmacy.manager.params;

import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * 涉及药品操作  参数封装类
 * Created by zhaojun.gao on 2016/8/19.
 */
public class OrderParamsHelper {

    /**
     * 生成订单
     *
     * @param deviceCode 设备id
     * @param version    版本号
     * @param userId     用户ID
     * @param storeId    药店ID
     * @return
     */
    public static RequestParams createOrder(String deviceCode, String version, String userId, String storeId, String drugList) {
        RequestParams mParams = new RequestParams();
        mParams.put("deviceCode", deviceCode);
        mParams.put("version", version);
        mParams.put("userId", userId);
        mParams.put("drugStoreId", storeId);
        mParams.put("drug", drugList);
        return mParams;
    }

    /**
     * 确认订单
     *
     * @param deviceCode  设备id
     * @param version     版本号
     * @param orderCode   订单号
     *                    地址id
     * @param invoiceFlag 是否需要发票  1.要  0.不要
     * @param invoiceType 发票类型  
     * @param invoiceHead 发票抬头
     * @return
     */
    public static RequestParams submitOrders(String name,String idCard,
            String deviceCode, String version, String orderCode, String addrId, String invoiceFlag, String invoiceType, String invoiceHead) {
        RequestParams mParams = new RequestParams();
        mParams.put("name", name);
        mParams.put("idCard", idCard);
        mParams.put("deviceCode", deviceCode);
        mParams.put("version", version);
        mParams.put("orderCode", orderCode);
        mParams.put("addrId", addrId);
        mParams.put("invoiceFlag", invoiceFlag);
        mParams.put("invoiceType", invoiceType);
        mParams.put("invoiceHead", invoiceHead);
        return mParams;
    }

    /**
     * 订单进度查询（物流查询）
     *
     * @param orderId    订单id
     * @param version    版本
     * @param deviceCode 设备id
     * @return
     */
    public static RequestParams queryOrderProgress(String orderId, String version, String deviceCode) {
        RequestParams mParams = new RequestParams();
        mParams.put("orderId", orderId);
        mParams.put("version", version);
        mParams.put("deviceCode", deviceCode);
        return mParams;
    }

    /**
     * 6.6订单管理（包含详情）
     *
     * @param userId      用户ID
     * @param orderStatus 订单状态  （不传时，查询全部订单；3查询所有进行中订单；5查询已完成；6查询已取消）
     *                    1待分配
     *                    2待确认
     *                    3进行中
     *                    4待收货
     *                    5已完成
     *                    6已取消
     * @param version     版本
     * @param deviceCode  设备id
     * @param pageSize    每页显示的总数
     * @param pageNo      当前页码
     * @return
     */
    public static RequestParams getOrderManagerListParams(String userId, String orderStatus, String version,
                                                          String deviceCode, String pageSize, String pageNo) {
        RequestParams mParams = new RequestParams();
        mParams.put("userId", userId);
        mParams.put("orderStatus", orderStatus);
        mParams.put("version", version);
        mParams.put("deviceCode", deviceCode);
        mParams.put("pageSize", pageSize);
        mParams.put("pageNo", pageNo);
        return mParams;
    }


    /**
     * 删除订单
     *
     * @param orderId    订单ID
     * @param version    版本
     * @param deviceCode 设备id
     * @return
     */
    public static RequestParams getDeleteOrderParams(String orderId, String version, String deviceCode) {
        RequestParams mParams = new RequestParams();
        mParams.put("orderId", orderId);
        mParams.put("version", version);
        mParams.put("deviceCode", deviceCode);
        return mParams;
    }

    /**
     * 修改订单配送方式
     *
     * @param deviceCode   设备号
     * @param version      版本号
     * @param orderCode    订单号
     * @param deliveryType 配送方式  1.药店快递   2.上门自提
     * @return
     */
    public static RequestParams changeDelivery(String deviceCode, String version, String orderCode, String deliveryType) {
        RequestParams mParams = new RequestParams();
        mParams.put("deviceCode", deviceCode);
        mParams.put("version", version);
        mParams.put("orderCode", orderCode);
        mParams.put("deliveryType", deliveryType);
        return mParams;
    }

    /**
     * 查询正在进行中的订单数量
     *
     * @param userId     用户ID
     * @param version    版本
     * @param deviceCode 设备id
     * @return
     */
    public static RequestParams getOnDoingOrderNum(String userId, String version, String deviceCode) {
        RequestParams mParams = new RequestParams();
        mParams.put("userId", userId);
        mParams.put("version", version);
        mParams.put("deviceCode", deviceCode);
        return mParams;
    }
}
