package com.ebaonet.pharmacy.manager.abs;

import com.ebaonet.pharmacy.base.manager.ManagerBean;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by yao.feng on 2016/8/19.
 */
public abstract class AbsAddress extends ManagerBean {

    /**
     * 地址管理页面的列表查询
     *
     * @param mParams
     */
    public abstract void getAddressList(RequestParams mParams);


    /**
     * 用户新增地址、编辑地址保存
     *
     * @param mParams
     */
    public abstract void saveAddress(RequestParams mParams);


    /**
     * 设置默认地址
     *
     * @param mParams
     */
    public abstract void saveDefaultAddress(RequestParams mParams);

    /**
     * 删除地址
     *
     * @param mParams
     */
    public abstract void delAddress(RequestParams mParams);
}
