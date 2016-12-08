package com.ebaonet.pharmacy.manager.abs;

import com.ebaonet.pharmacy.base.manager.ManagerBean;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/10/11.
 */
public abstract class AbsConfigInfo extends ManagerBean {
    /** 配置信息查询*/
    public abstract void getConfigList(RequestParams mParams);
}
