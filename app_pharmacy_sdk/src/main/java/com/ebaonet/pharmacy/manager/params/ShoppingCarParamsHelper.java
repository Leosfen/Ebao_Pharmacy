package com.ebaonet.pharmacy.manager.params;

import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by yao.feng on 2016/8/19.
 */
public class ShoppingCarParamsHelper {

    /**
     * 购物车商品查询
     *
     * @param userId 用户ID
     * @return
     */
    public static RequestParams getCartInfoParams(String userId) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        return params;

    }

    /**
     * 购物车商品数量修改
     *
     * @param drugId   药品ID
     * @param quantity 数量
     * @return
     */
    public static RequestParams getChangeCartParams(String userId, String drugId, String quantity) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("drugId", drugId);
        params.put("quantity", quantity);
        return params;

    }

    /**
     * 删除商品
     *
     * @param drugIds 药品ID
     * @return
     */
    public static RequestParams getDeleteItemParams(String userId, String drugIds) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("drugIds", drugIds);
        return params;
    }

    /**
     * 提交购物车
     *
     * @param drugIds ID
     * @return
     */
    public static RequestParams getCommitItemParams(String userId, String drugIds) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("drugIds", drugIds);
        return params;
    }

}
