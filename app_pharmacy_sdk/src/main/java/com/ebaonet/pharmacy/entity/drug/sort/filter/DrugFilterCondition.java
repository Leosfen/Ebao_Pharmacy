package com.ebaonet.pharmacy.entity.drug.sort.filter;

import com.ebaonet.pharmacy.entity.BaseEntity;

/**
 * Created by yao.feng on 2016/9/2.
 */
public class DrugFilterCondition extends BaseEntity {

    private FilterCondition data;

    public FilterCondition getData() {
        return data;
    }

    public void setData(FilterCondition data) {
        this.data = data;
    }
}
