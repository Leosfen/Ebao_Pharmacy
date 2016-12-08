package com.ebaonet.pharmacy.manager;

import com.ebaonet.pharmacy.entity.config.ConfigInfo;
import com.ebaonet.pharmacy.manager.abs.AbsConfigInfo;
import com.ebaonet.pharmacy.manager.config.ConfigInfoConfig;
import com.ebaonet.pharmacy.request.PharmacyUrlConst;
import com.ebaonet.pharmacy.request.RequestManager;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/10/11.
 */
public class ConfigInfoManager extends AbsConfigInfo {

    private ConfigInfoManager() {
    }

    private static ConfigInfoManager mInstance;

    public static ConfigInfoManager getInstance() {
        if (mInstance == null) {
            mInstance = new ConfigInfoManager();
        }
        return mInstance;
    }


    @Override
    public void getConfigList(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.CONFIGINFO, ConfigInfoConfig.CONFIGLISTINFO,
                mParams,this, ConfigInfo.class);
    }
}
