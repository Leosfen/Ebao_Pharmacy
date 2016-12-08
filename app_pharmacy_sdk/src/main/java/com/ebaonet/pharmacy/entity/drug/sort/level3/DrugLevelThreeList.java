package com.ebaonet.pharmacy.entity.drug.sort.level3;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 药品三级分类--列表实体类
 * Created by zhaojun.gao on 2016/9/1.
 */
public class DrugLevelThreeList extends BaseEntity {

    private List<DrugThreeListInfo> data;

    public List<DrugThreeListInfo> getData() {
        return data;
    }

    public void setData(List<DrugThreeListInfo> data) {
        this.data = data;
    }
}
