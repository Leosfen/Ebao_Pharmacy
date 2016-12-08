package com.ebaonet.pharmacy.entity.address;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.ArrayList;

/**
 * Created by yao.feng on 2016/8/19.
 */
public class AddressList extends BaseEntity{
    
    private ArrayList<Address> data;

    public ArrayList<Address> getData() {
        return data;
    }

    public void setData(ArrayList<Address> data) {
        this.data = data;
    }
}
