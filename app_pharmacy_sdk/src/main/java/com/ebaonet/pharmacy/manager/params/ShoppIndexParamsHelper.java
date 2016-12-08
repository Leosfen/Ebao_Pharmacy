package com.ebaonet.pharmacy.manager.params;

import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/10/24.
 */
public class ShoppIndexParamsHelper {
    /**
     * 
     * @param drugStoreId
     * @return
     */
    public static RequestParams getIndexInfoParams(String drugStoreId,String version) {
        RequestParams params = new RequestParams();
        params.put("drugStoreId", drugStoreId);
        params.put("version", version);
        return params;
    }
}
