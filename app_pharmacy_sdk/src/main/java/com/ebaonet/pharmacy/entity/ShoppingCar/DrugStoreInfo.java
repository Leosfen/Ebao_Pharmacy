package com.ebaonet.pharmacy.entity.shoppingcar;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.ArrayList;

/**
 * Created by peng.dong on 2016/9/3.
 */
public class DrugStoreInfo extends BaseEntity{
    private String drugStoreName;
    private String drugStoreId;
    private ArrayList<DrugInfo> drugList =new ArrayList<DrugInfo>();

    public String getDrugStoreName() {
        return drugStoreName;
    }

    public void setDrugStoreName(String drugStoreName) {
        this.drugStoreName = drugStoreName;
    }

    public String getDrugStoreId() {
        return drugStoreId;
    }

    public void setDrugStoreId(String drugStoreId) {
        this.drugStoreId = drugStoreId;
    }

    public ArrayList<DrugInfo> getDrugList() {
        return drugList;
    }

    public void setDrugList(ArrayList<DrugInfo> drugList) {
        this.drugList = drugList;
    }
}
