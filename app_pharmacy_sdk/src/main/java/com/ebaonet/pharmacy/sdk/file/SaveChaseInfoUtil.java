package com.ebaonet.pharmacy.sdk.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ebaonet.pharmacy.util.JsonUtil;

/**
 * 保存选中发票信息工具类
 * Created by zhaojun.gao on 2016/9/29.
 */
public class SaveChaseInfoUtil {

    private static final String CHASE = "chase";

    private static final String CHASE_INFO = "info";

    public static void setChaseInfo(Context mContext, ChaseInfo info) {
        String msg = "";
        if(info!=null){
           msg = JsonUtil.toJSONString(info);
        }
        SharedPreferences sp = mContext.getSharedPreferences(CHASE, Context.MODE_PRIVATE);
        sp.edit().putString(CHASE_INFO, msg).commit();
    }

    public static ChaseInfo getChaseInfo(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(CHASE, Context.MODE_PRIVATE);
        String info = sp.getString(CHASE_INFO, "");
        if (!TextUtils.isEmpty(info)) {
            ChaseInfo mCha = JsonUtil.toBean(info, ChaseInfo.class);
            return mCha;
        }
        return null;
    }

}
