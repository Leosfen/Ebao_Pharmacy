package com.ebaonet.pharmacy.base;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.ebaonet.pharmacy.entity.BaseEntity;
import com.ebaonet.pharmacy.request.VolleyErrorHelper;
import com.ebaonet.pharmacy.util.UIUtils;


/**
 * Created by yao.feng on 2016/4/19.
 */
public class ResponseCodeAgent {

    public static final int RESPONSE_SUCCESS = 1;

    public static void handleCode(Context mContext, String tag, int code, Object obj, String... keys) {
        if (obj instanceof VolleyError) {
            UIUtils.showToast(mContext, VolleyErrorHelper.getMessage((VolleyError) obj, mContext));
        } else {
            if (code != RESPONSE_SUCCESS) {
                BaseEntity mBe = (BaseEntity) obj;
                if (!TextUtils.isEmpty(mBe.getMessage())) {
                    UIUtils.showToast(mContext, mBe.getMessage());
                }
            }
        }


    }

}
