package com.ebaonet.pharmacy.manager.params;

import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by yao.feng on 2016/8/19.
 */
public class AddressParamsHelper {


    /**
     * 地址管理页面的列表查询
     *
     * @param userId 用户ID
     * @return
     */
    public static RequestParams getAddressListParams(String userId) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        return params;
    }


    /**
     * 用户新增地址、编辑地址保存
     *
     * @param addrId       地址ID,若为空则为新增地址，若不为空，则为编辑地址
     * @param userId       用户ID
     * @param biotopeName  地区名称
     * @param addr         收货地址
     * @param jd           经度
     * @param wd           纬度
     * @param receiveName  收货人姓名
     * @param receivePhone 收货人手机号
     * @return
     */
    public static RequestParams getSaveAddressParams(String addrId, String userId, String biotopeName,
                                                     String addr, String jd, String wd,
                                                     String receiveName, String receivePhone) {
        RequestParams params = new RequestParams();
        params.put("addrId", addrId);
        params.put("userId", userId);
        params.put("biotopeName", biotopeName);
        params.put("addr", addr);
        params.put("jd", jd);
        params.put("wd", wd);
        params.put("receiveName", receiveName);
        params.put("receivePhone", receivePhone);
        return params;

    }

    /**
     * 设置默认地址
     *
     * @param addrId 地址ID
     * @return
     */
    public static RequestParams getSaveDefaultAddressParams(String addrId,String userId) {
        RequestParams params = new RequestParams();
        params.put("addrId", addrId);
        params.put("userId", userId);
        return params;
    }

    /**
     * 删除地址
     *
     * @param addrId 地址ID
     * @return
     */
    public static RequestParams getDeleteAddressParams(String addrId) {
        RequestParams params = new RequestParams();
        params.put("addrId", addrId);
        return params;
    }

}
