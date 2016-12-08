package com.ebaonet.pharmacy.view.filter.obj;

/**
 * Created by yao.feng on 2016/9/2.
 */
public class FilterParams {

    public StringBuilder typeId = new StringBuilder();
    public StringBuilder brandId = new StringBuilder();
    public StringBuilder drugFormId = new StringBuilder();

    public String lowPrice;
    public String highPrice;
    public String totalSort;


    public void setReset() {
        typeId.delete(0, typeId.length());
        brandId.delete(0, brandId.length());
        drugFormId.delete(0, drugFormId.length());

        lowPrice = "";
        highPrice = "";
        totalSort = "";
    }

}
