package com.ebaonet.pharmacy.entity.drug.sort.filter;

import java.util.List;

/**
 * Created by yao.feng on 2016/9/2.
 */
public class FilterCondition {

    private List<BrandInfo> brandList;

    private List<DrugFormInfo> formList;

    private List<TypeInfo> typeList;

    public List<BrandInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandInfo> brandList) {
        this.brandList = brandList;
    }

    public List<DrugFormInfo> getFormList() {
        return formList;
    }

    public void setFormList(List<DrugFormInfo> formList) {
        this.formList = formList;
    }

    public List<TypeInfo> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeInfo> typeList) {
        this.typeList = typeList;
    }
}
