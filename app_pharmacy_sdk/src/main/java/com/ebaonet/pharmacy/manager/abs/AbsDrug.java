package com.ebaonet.pharmacy.manager.abs;

import com.ebaonet.pharmacy.base.manager.ManagerBean;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * 药品相关操作
 * Created by zhaojun.gao on 2016/8/19.
 */
public abstract class AbsDrug extends ManagerBean {

    public abstract void getCatelevelone();

    public abstract void getCateleveltwo(RequestParams params);

    public abstract void getDruglevelthree(RequestParams params);

    public abstract void getDrugFilterConditions(RequestParams params);

    public abstract void addCartitem(RequestParams params);

    public abstract void getDrugInfo(RequestParams params);

    public abstract void getDrugDetail(RequestParams params);

    public abstract void getDrugAcvityList(RequestParams params);

    public abstract void getDrugDisp(RequestParams params);

    public abstract void getQuantity(RequestParams params);
}
