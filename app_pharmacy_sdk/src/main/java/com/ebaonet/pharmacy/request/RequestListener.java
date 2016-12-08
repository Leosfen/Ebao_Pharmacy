package com.ebaonet.pharmacy.request;


import com.android.volley.VolleyError;

public interface RequestListener {

    /**
     * 成功
     */
    public void requestSuccess(Object response, Object tag, Class<?> cl, String... keys);

    /**
     * 错误
     */
    public void requestError(VolleyError e, Object tag, Class<?> cl, String... keys);

}
