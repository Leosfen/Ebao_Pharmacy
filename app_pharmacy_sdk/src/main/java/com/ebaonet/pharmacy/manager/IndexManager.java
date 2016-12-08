package com.ebaonet.pharmacy.manager;

import com.ebaonet.pharmacy.entity.index.IndexInfoEntity;
import com.ebaonet.pharmacy.manager.abs.AbsShoppingIndex;
import com.ebaonet.pharmacy.manager.config.IndexConfig;
import com.ebaonet.pharmacy.request.PharmacyUrlConst;
import com.ebaonet.pharmacy.request.RequestManager;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/10/24.
 */
public class IndexManager extends AbsShoppingIndex {

    private IndexManager() {
    }

    private static IndexManager mInstance;

    public static IndexManager getInstance() {
        if (mInstance == null) {
            mInstance = new IndexManager();
        }
        return mInstance;
    }
    
    @Override
    public void getShoppingIndexInfo(RequestParams mParams) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.INDEXINFO, IndexConfig.GETINDEXINFO,
                mParams, this, IndexInfoEntity.class);
    }
}
