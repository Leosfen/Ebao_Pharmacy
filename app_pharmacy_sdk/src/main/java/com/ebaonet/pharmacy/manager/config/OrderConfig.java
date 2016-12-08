package com.ebaonet.pharmacy.manager.config;

/**
 * 关于药品相关操作的配置信息（包含每个操作的标记）
 * Created by zhaojun.gao on 2016/8/19.
 */
public class OrderConfig {
    public static final String CREATE_ORDER = "createorder";//生成订单
    public static final String SUBMIT_ORDER = "submitorder";//提交订单
    public static final String DELETE_ORDER = "deleteorder";//删除订单
    public static final String CHANGE_DELIVERY = "changeDelivery";//改变配送方式，重新计算价格
    public static final String ORDER_PROGRESS = "order_progress";//订单进度查询
    public static final String GET_ORDER_MANAGER_LIST = "getOrderManagerList";//获取订单管理列表
    public static final String GET_ON_DOING_ORDER_NUM = "getOnDoingOrderNum";
}
