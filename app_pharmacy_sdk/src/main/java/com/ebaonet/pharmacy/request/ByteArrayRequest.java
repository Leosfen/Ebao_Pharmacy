package com.ebaonet.pharmacy.request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.request.params.RequestParams;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ByteArrayRequest override getBody() and getParams()
 */
class ByteArrayRequest extends Request<byte[]> {

    private Response.Listener<byte[]> mListener;

    private Object mPostBody = null;

    private HttpEntity httpEntity = null;

    private boolean isAddHeader = true;// http请求默认需要添加header信息

    private boolean isSaveCookie = false;// 对于response默认不保存cookie信息

    public ByteArrayRequest(int method, String url, Object postBody, Listener<byte[]> listener,
                            ErrorListener errorListener) {
        super(method, url, errorListener);
        initRequest(postBody, listener);
    }

    /*第四个参数是服务器响应成功的回调，第五个参数是服务器响应失败的回调。*/
    public ByteArrayRequest(int method, String url, Object postBody, Listener<byte[]> listener,
                            ErrorListener errorListener, boolean isAddHeader, boolean isSaveCookie) {
        super(method, url, errorListener);
        initRequest(postBody, listener);
        this.isAddHeader = isAddHeader;
        this.isSaveCookie = isSaveCookie;
    }

    private void initRequest(Object postBody, Listener<byte[]> listener) {
        this.mPostBody = postBody;
        this.mListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(RequestConfig.TIMEOUT, 1, 1.0F));
        if (this.mPostBody != null && this.mPostBody instanceof RequestParams) {// contains
            // file
            this.httpEntity = ((RequestParams) this.mPostBody).getEntity();
        }
    }

    /**
     * mPostBody is null or Map<String, String>, then execute this method
     */
    @SuppressWarnings("unchecked")
    protected Map<String, String> getParams() throws AuthFailureError {
        if (this.httpEntity == null && this.mPostBody != null
                && this.mPostBody instanceof Map<?, ?>) {
            return ((Map<String, String>) this.mPostBody);// common Map<String,
            // String>
        }
        return null;// process as json, xml or MultipartRequestParams
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (null == headers || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        // cookie
//        String cookie = AndroidApplication.getCookie();
//        if (!TextUtils.isEmpty(cookie) && isAddHeader) {
//            Logger.e("=========================================Request.Cookie===========================" + cookie);
//            headers.put("Cookie", cookie);
//        }
        return headers;
    }

    @Override
    public String getBodyContentType() {
        if (httpEntity != null) {
            return httpEntity.getContentType().getValue();
        }
        return null;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (this.mPostBody != null && this.mPostBody instanceof String) {// process
            // as
            // json
            // or
            // xml
            String postString = (String) mPostBody;
            if (postString.length() != 0) {
                try {
                    return postString.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                return null;
            }
        }
        if (this.httpEntity != null) {// process as MultipartRequestParams
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                httpEntity.writeTo(baos);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            try {
                Logger.e("==============================Request.Params==========================" + new String(baos.toByteArray(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return baos.toByteArray();
        }
        return super.getBody();// mPostBody is null or Map<String, String>
    }

    //parseNetworkResponse()方法是对服务器响应的数据进行解析
    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        // 获取kookie
        String cookie = response.headers.get("Set-Cookie");//获取服务器返回的cookie信息
        if (cookie != null && cookie.length() > 0 && isSaveCookie) {//默认是false，这样保证只有在特定环境下比如登录才会走if这段代码
            // AndroidApplication.setCookie(cookie);
            Logger.e("=====================================Response.Cookie================================" + cookie);
        }

        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        this.mListener.onResponse(response);
    }

}