package com.ebaonet.pharmacy.request;

import android.content.Context;

import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.ebaonet.pharmacy.util.ResourceUitls;

public class VolleyErrorHelper {

    /**
     * 一般性错误
     */
    public static final int VOLLEY_ERR_GENERIC = -10000;

    /**
     * 网络请求超时
     */
    public static final int VOLLEY_ERR_TIME_OUT = -10001;

    /**
     * 网络不可用
     */
    public static final int VOLLEY_ERR_NETWORK_NOT_AVAILABLE = -10002;

    public static String getMessage(VolleyError error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(ResourceUitls.getStringId(context, "volley_request_timeout"));
        } else if (error instanceof NetworkError) {
            return context.getResources().getString(ResourceUitls.getStringId(context, "volley_network_not_available"));
        }
        return context.getResources().getString(ResourceUitls.getStringId(context, "volley_generic_error"));
    }


    public static int getCode(VolleyError error) {
        if (error instanceof TimeoutError) {
            return VOLLEY_ERR_TIME_OUT;
        } else if (error instanceof NetworkError) {
            return VOLLEY_ERR_NETWORK_NOT_AVAILABLE;
        }
        return VOLLEY_ERR_GENERIC;
    }

    public static boolean isVolleyError(int code) {
        if (code == VolleyErrorHelper.VOLLEY_ERR_GENERIC ||
                code == VolleyErrorHelper.VOLLEY_ERR_TIME_OUT ||
                code == VolleyErrorHelper.VOLLEY_ERR_NETWORK_NOT_AVAILABLE) {
            return true;
        }
        return false;
    }

}
