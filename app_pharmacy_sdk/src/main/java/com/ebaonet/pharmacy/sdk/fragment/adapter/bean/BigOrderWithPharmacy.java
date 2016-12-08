package com.ebaonet.pharmacy.sdk.fragment.adapter.bean;

import java.util.ArrayList;

/**
 * Created by yao.feng on 2016/9/23.
 */
public class BigOrderWithPharmacy {
    
    private String pharmacyName;
    
    private String orderType;
    
    private String goodsCount;
    
    private String totalRMB;
    
    private ArrayList<DrugGoods> mDrugList = new ArrayList<>();

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getTotalRMB() {
        return totalRMB;
    }

    public void setTotalRMB(String totalRMB) {
        this.totalRMB = totalRMB;
    }

    public ArrayList<DrugGoods> getDrugList() {
        return mDrugList;
    }

    public void setDrugList(ArrayList<DrugGoods> drugList) {
        mDrugList = drugList;
    }
}
