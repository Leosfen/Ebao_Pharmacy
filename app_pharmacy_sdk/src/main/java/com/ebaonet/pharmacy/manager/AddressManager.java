package com.ebaonet.pharmacy.manager;

import com.ebaonet.pharmacy.entity.BaseEntity;
import com.ebaonet.pharmacy.entity.address.AddressList;
import com.ebaonet.pharmacy.manager.abs.AbsAddress;
import com.ebaonet.pharmacy.manager.config.AddressConfig;
import com.ebaonet.pharmacy.request.PharmacyUrlConst;
import com.ebaonet.pharmacy.request.RequestManager;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by yao.feng on 2016/8/19.
 */
public class AddressManager extends AbsAddress {

    private AddressManager() {
    }

    private static AddressManager mInstance;

    public static AddressManager getInstance() {
        if (mInstance == null) {
            mInstance = new AddressManager();
        }
        return mInstance;
    }

    @Override
    public void getAddressList(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.ADDRESS_LIST, AddressConfig.GET_ADDRESS_LIST, mParams,
                this, AddressList.class);
    }

    @Override
    public void saveAddress(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.SAVE_ADDRESS, AddressConfig.SAVE_ADDRESS, mParams,
                this, BaseEntity.class);
    }

    @Override
    public void saveDefaultAddress(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.SAVE_DEFAULT_ADDRESS, AddressConfig.SAVE_DEFAULT_ADDRESS, mParams,
                this, BaseEntity.class);
    }

    @Override
    public void delAddress(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DELETE_ADDRESS, AddressConfig.DEL_ADDRESS, mParams,
                this, BaseEntity.class);
    }
}
