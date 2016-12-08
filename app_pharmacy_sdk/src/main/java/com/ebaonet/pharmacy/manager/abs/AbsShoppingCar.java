package com.ebaonet.pharmacy.manager.abs;

import com.ebaonet.pharmacy.base.manager.ManagerBean;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by peng.dong on 2016/8/19.
 */
public abstract class AbsShoppingCar extends ManagerBean {

    /**
     * 购物车列表查询
     *
     * @param mParams
     */
    public abstract void getShoppingCarList(RequestParams mParams);
    /**
     * 购物车商品数量修改
     *
     * @param mParams
     */
    public abstract void ChangeShoppingCarItem(RequestParams mParams);
    /**
     * 删除购物车商品修改
     *
     * @param mParams
     */
    public abstract void DeleteShoppingCarItem(RequestParams mParams);
    /**
     * 提交购物车商品
     *
     * @param mParams
     */
    public abstract void CommitShoppingCarItem(RequestParams mParams);
    
    
}
