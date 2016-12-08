package com.ebaonet.pharmacy.manager;

import com.ebaonet.pharmacy.entity.BaseEntity;
import com.ebaonet.pharmacy.entity.shoppingcar.ShoppingCarData;
import com.ebaonet.pharmacy.manager.abs.AbsShoppingCar;
import com.ebaonet.pharmacy.manager.config.ShoppingCartConfig;
import com.ebaonet.pharmacy.request.PharmacyUrlConst;
import com.ebaonet.pharmacy.request.RequestManager;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by yao.feng on 2016/8/19.
 */
public class ShoppingCarManager extends AbsShoppingCar {

    private ShoppingCarManager() {
    }

    private static ShoppingCarManager mInstance;

    public static ShoppingCarManager getInstance() {
        if (mInstance == null) {
            mInstance = new ShoppingCarManager();
        }
        return mInstance;
    }

    @Override
    public void getShoppingCarList(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.GET_CART, ShoppingCartConfig.GET_CART, mParams,
                this, ShoppingCarData.class);

    }

    @Override
    public void ChangeShoppingCarItem(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.CHANGE_CART_ITEM, ShoppingCartConfig.CHANGE_CART_ITEM, mParams,
                this, BaseEntity.class);
    }

    //提交和刪除都访问删除的接口
    @Override
    public void DeleteShoppingCarItem(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DELETE_CART_ITEM, ShoppingCartConfig.DELETE_CART_ITEM, mParams,
                this, BaseEntity.class);
    }

    //提交和刪除都访问删除的接口
    @Override
    public void CommitShoppingCarItem(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DELETE_CART_ITEM, ShoppingCartConfig.COMMIT_CART, mParams,
                this, BaseEntity.class);
    }
}
