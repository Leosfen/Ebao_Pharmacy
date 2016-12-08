package com.ebaonet.pharmacy.manager.abs;

import com.ebaonet.pharmacy.base.manager.ManagerBean;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/10/24.
 */
public abstract class AbsShoppingIndex extends ManagerBean {
    /**
     * 首页数据查询
     * @param mParams
     */
    public abstract void getShoppingIndexInfo(RequestParams mParams);
}
