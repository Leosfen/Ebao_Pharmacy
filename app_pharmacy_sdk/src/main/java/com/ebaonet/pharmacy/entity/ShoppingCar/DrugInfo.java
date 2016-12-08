package com.ebaonet.pharmacy.entity.shoppingcar;

import com.ebaonet.pharmacy.entity.BaseEntity;

/**
 * Created by peng.dong on 2016/8/30.
 */
public class DrugInfo extends BaseEntity{
    String drugDsId;
    String displayName;
    String drugStoreId;
    String skuId;
    String norms;
    String drugStoreName;
    String quantity;
    String picUrl;//
    String medicalInsuranceCode;
    String curPrice;

    public String getMedicalInsuranceCode() {
        return medicalInsuranceCode;
    }

    public void setMedicalInsuranceCode(String medicalInsuranceCode) {
        this.medicalInsuranceCode = medicalInsuranceCode;
    }

    public String getDrugDsId() {
        return drugDsId;
    }

    public void setDrugDsId(String drugDsId) {
        this.drugDsId = drugDsId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDrugStoreId() {
        return drugStoreId;
    }

    public void setDrugStoreId(String drugStoreId) {
        this.drugStoreId = drugStoreId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getDrugStoreName() {
        return drugStoreName;
    }

    public void setDrugStoreName(String drugStoreName) {
        this.drugStoreName = drugStoreName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(String curPrice) {
        this.curPrice = curPrice;
    }
}
