package com.ebaonet.pharmacy.entity.shoppingcar;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.ArrayList;

/**
 * Created by peng.dong on 2016/8/30.
 */
public class ShoppingCarData extends BaseEntity{
    private ArrayList<DrugStoreInfo> data =new ArrayList<DrugStoreInfo>();

    public ArrayList<DrugStoreInfo> getData() {
        return data;
    }

    public void setData(ArrayList<DrugStoreInfo> data) {
        this.data = data;
    }
}
