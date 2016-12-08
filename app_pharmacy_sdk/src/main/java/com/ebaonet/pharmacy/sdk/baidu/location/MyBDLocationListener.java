package com.ebaonet.pharmacy.sdk.baidu.location;

import com.baidu.location.BDLocation;

/**
 * Created by yao.feng on 2016/8/12.
 */
public interface MyBDLocationListener {

    void onReceiveLocation(BDLocation bdLocation);
}
