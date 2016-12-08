package com.ebaonet.pharmacy.manager.config;

/**
 * 关于药品相关操作的配置信息（包含每个操作的标记）
 * Created by zhaojun.gao on 2016/8/19.
 */
public class DrugConfig {
    public static final String GETCATELEVELONE = "getCatelevelone";//分类页面：一级分类
    public static final String GETCATELEVELTWO = "getCateleveltwo";//二级分类
    public static final String GETDRUGLEVELTHREE = "getDruglevelthree";//三级分类进入药品列表
    public static final String GETDRUGSEARCH = "getDrugSearch";//药品关键字搜索
    public static final String ADDCARTITEM = "addcartitem";//三级分类进入药品列表
    public static final String GET_DRUG_FILTER_CONDITIONS = "getDrugFilterConditions";//获取药品筛选项
    public static final String GET_DRUG_INFO = "getDrugInfo";//获取药品筛选项
    public static final String GET_DRUG_DETAIL = "getDrugDetail";//获取药品详情
    public static final String GET_DRUG_ACTIVITY_LIST = "getDrugActivityList";//获取药品详情
    public static final String GET_DRUG_DISP = "getDrugDisp";//获取药品说明书
    public static final String GET_QUANTITY = "getQuantity";//获取购物车内药品条目数量
}
