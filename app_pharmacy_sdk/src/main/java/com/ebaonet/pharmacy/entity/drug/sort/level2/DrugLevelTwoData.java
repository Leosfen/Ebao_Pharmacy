package com.ebaonet.pharmacy.entity.drug.sort.level2;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * data若对应的是集合（list）,即后面是[],采用注销的代码
 * Created by zhaojun.gao on 2016/8/19.
 */
public class DrugLevelTwoData extends BaseEntity{
    
    /*private CateleveltwoInfo data;

    public CateleveltwoInfo getData() {
        return data;
    }

    public void setData(CateleveltwoInfo data) {
        this.data = data;
    }*/

    public List<CateleveltwoInfo> getData() {
        return data;
    }

    /**后期修改*/
    private List<CateleveltwoInfo> data;

    public void setData(List<CateleveltwoInfo> data) {
        this.data = data;
    }
}
