package com.ebaonet.pharmacy.manager.params;

import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * 涉及药品操作  参数封装类
 * Created by zhaojun.gao on 2016/8/19.
 */
public class DrugParamsHelper {


    /**
     * 分类模块：获取二级分类
     *
     * @param cateLevelOneId 一级分类id
     * @param start          起始位置
     * @param length         二级分类长度
     * @return
     */
    public static RequestParams getCateleveltwo(String cateLevelOneId, String start, String length) {
        RequestParams mParams = new RequestParams();
        mParams.put("cateLevelOneId", cateLevelOneId);
        mParams.put("start", start);
        mParams.put("length", length); 
        return mParams;
    }

    /**
     * 分类模块：获取三级分类---药品列表界面
     *
     * @param cateId     三级分类ID
     * @param typeId     类型ID
     * @param brandId    品牌ID
     * @param drugFormId 剂型ID
     * @param lowPrice   低价
     * @param highPrice  高价
     * @param totalSort  综合排序
     * @param startNum   当前页码
     * @param endNum     每页显示的总数
     * @return
     */
    public static RequestParams getDruglevelthree(String cateId, String typeId, String brandId,
                                                  String drugFormId, String lowPrice, String highPrice,
                                                  String totalSort, String startNum, String endNum) {
        RequestParams mParams = new RequestParams();
        mParams.put("cateId", cateId);
        mParams.put("typeId", typeId);
        mParams.put("brandId", brandId);
        mParams.put("drugFormId", drugFormId);
        mParams.put("lowPrice", lowPrice);
        mParams.put("highPrice", highPrice);
        mParams.put("totalSort", totalSort);
        mParams.put("startNum", startNum);
        mParams.put("endNum", endNum);
        return mParams;
    }

    /**
     * 
     * @param keywords  关键词
     * @param otcId   OTCID（多个以逗号分隔）
     * @param brandId  品牌ID（多个以逗号分隔）
     * @param drugFormId  剂型ID（多个以逗号分隔）
     * @param lowPrice  低价
     * @param highPrice  高价
     * @param priceSort  价格排序 1：升序;其他：降序
     * @param pageNO  当前页码
     * @param pageSize  每页显示的总数
     * @return
     */
    public static RequestParams getDrugSearch(String keywords, String otcId, String brandId,
                                                  String drugFormId, String lowPrice, String highPrice,
                                                  String priceSort, String pageNO, String pageSize){
        RequestParams mParams = new RequestParams();
        mParams.put("keywords", keywords);
        mParams.put("otcId", otcId);
        mParams.put("brandId", brandId);
        mParams.put("drugFormId", drugFormId);
        mParams.put("lowPrice", lowPrice);
        mParams.put("highPrice", highPrice);
        mParams.put("priceSort", priceSort);
        mParams.put("pageNO", pageNO);
        mParams.put("pageSize", pageSize);
        return mParams;
    }
    
    
    /**
     * 
     * @param userId  用户id
     * @param drugId  药品ID
     * @param quantity  药品数量
     * @param price  药品价格
     * @return
     */
    public static RequestParams addCartitem(String userId,String drugId,String quantity,String price){
        RequestParams mParams = new RequestParams();
        mParams.put("userId",userId);
        mParams.put("drugId",drugId);
        mParams.put("quantity",quantity);
        mParams.put("price",price);
        return mParams;
    }

    /**
     *
     * @param deviceCode  设备ID
     * @param version  版本号
     * @param barcode  条形码
     * @param drugDsId  药品ID
     * @param drugStoreId  所属药店ID
     * @return
     */
    public static RequestParams getDrugDetail(String deviceCode,String version,String barcode,String drugDsId,String drugStoreId){
        RequestParams mParams = new RequestParams();
        mParams.put("deviceCode",deviceCode);
        mParams.put("version",version);
        mParams.put("barcode",barcode);
        mParams.put("drugDsId",drugDsId);
        mParams.put("drugStoreId",drugStoreId);
        return mParams;
    }

    /**
     *
     * @param configId  配置ID
     * @param configId  促销ID
     * @param pageNo  当前页面
     * @param pageSize  每页显示的总数
     * @return
     */
    public static RequestParams getDrugActivityList(String configId,String promotionId,String keywords,String otcId,String brandId,String drugFormId,
                                                    String lowPrice,String highPrice,String priceSort,String pageNo,String pageSize){
        RequestParams mParams = new RequestParams();
        mParams.put("configId",configId);
        mParams.put("promotionId",promotionId);
        mParams.put("keywords",keywords);
        mParams.put("otcId", otcId);
        mParams.put("brandId", brandId);
        mParams.put("drugFormId", drugFormId);
        mParams.put("lowPrice", lowPrice);
        mParams.put("highPrice", highPrice);
        mParams.put("priceSort", priceSort);
        mParams.put("pageNo", pageNo);
        mParams.put("pageSize", pageSize);

        return mParams;
    }

    /**
     * @param userId 用户ID
     * @return
     */
    public static RequestParams getQuantity(String userId) {
        RequestParams mParams = new RequestParams();
        if (userId != null) {
            mParams.put("userId", userId);
        }
        return mParams;
    }
 
    /**
     * @param drugDsId 药品ID
     * @return
     */
    public static RequestParams getDrugDisp(String drugDsId) {
        RequestParams mParams = new RequestParams();
        mParams.put("drugDsId", drugDsId);
        return mParams;
    }
 
}
