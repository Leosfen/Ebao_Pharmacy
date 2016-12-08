package com.ebaonet.pharmacy.manager.params;

import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/10/13.
 */
public class ConfigParamsHelper {

    /**
     * 
     * @param version  版本
     * @param deviceCode  设备id
     * @return
     */
    public static RequestParams configList(String version, String deviceCode) {
        RequestParams mParams = new RequestParams();
        mParams.put("version", version);
        mParams.put("deviceCode", deviceCode);
        return mParams;
    }
}
