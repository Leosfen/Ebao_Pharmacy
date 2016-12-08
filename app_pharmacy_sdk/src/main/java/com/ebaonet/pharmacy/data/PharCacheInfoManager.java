package com.ebaonet.pharmacy.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ebaonet.pharmacy.data.user.PharmcyUserInfo;
import com.ebaonet.pharmacy.util.JsonUtil;

/**
 * Created by yao.feng on 2016/11/9.
 */
public class PharCacheInfoManager {

    private static final String PHAR_USER_SP_NAME = "pharUserSpName";

    private static final String PHAR_USER_INFO = "pharUserInfo";

    private static PharmcyUserInfo mUserInfo;

    private PharCacheInfoManager() {
    }

    public static PharmcyUserInfo getUserInfo(Context mContext) {
        SharedPreferences mSharedPre = mContext.getSharedPreferences(PHAR_USER_SP_NAME,
                Context.MODE_PRIVATE);
        if (mUserInfo != null) {
            return mUserInfo;
        } else {
            String userStr = mSharedPre.getString(PHAR_USER_INFO, "");
            if (!TextUtils.isEmpty(userStr)) {
                try {
                    mUserInfo = JsonUtil.toBean(userStr, PharmcyUserInfo.class);
                    return mUserInfo;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }


    public static void setPharmcyUserInfo(Context mContext, PharmcyUserInfo mInfo) {
        SharedPreferences mSharedPre = mContext.getSharedPreferences(PHAR_USER_SP_NAME,
                Context.MODE_PRIVATE);
        mUserInfo = mInfo;
        String userJson;
        if (mInfo == null) {
            userJson = "";
        } else {
            userJson = JsonUtil.toJSONString(mUserInfo);
        }
        mSharedPre.edit().putString(PHAR_USER_INFO, userJson).commit();
    }

    public void removePharmcyUserInfo(Context mContext) {
        setPharmcyUserInfo(mContext, null);
    }


}
