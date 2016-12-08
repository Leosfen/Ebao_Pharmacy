package com.ebaonet.pharmacy.request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
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
 * NetworkResponseRequest deliver data of cookie and result that is from server. It is multiple data.
 * It is different from ByteArrayRequest.
 * <p/>
 * Created by yao.feng on 2016/7/19.
 */
public class NetworkResponseRequest extends Request<NetworkResponse> {


    private Response.Listener<NetworkResponse> mListener;

    private Object mPostBody = null;

    private HttpEntity httpEntity = null;

    private boolean isAddHeader = true;// Request default adds header

    private boolean isSaveCookie = false;//Response default do not save cookie

    public NetworkResponseRequest(int method, String url, Object postBody, Response.Listener<NetworkResponse> listener,
                                  Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        initRequest(postBody, listener);
    }

    public NetworkResponseRequest(int method, String url, Object postBody, Response.Listener<NetworkResponse> listener,
                                  Response.ErrorListener errorListener, boolean isAddHeader, boolean isSaveCookie) {
        super(method, url, errorListener);
        initRequest(postBody, listener);
        this.isAddHeader = isAddHeader;
        this.isSaveCookie = isSaveCookie;
    }

    private void initRequest(Object postBody, Response.Listener<NetworkResponse> listener) {
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
        //String cookie = AndroidApplication.getCookie();
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

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        String cookie = response.headers.get("Set-Cookie");
        if (cookie != null && cookie.length() > 0 && isSaveCookie) {
           // AndroidApplication.setCookie(cookie);
            Logger.e("=====================================Response.Cookie================================" + cookie);
        }

        return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        this.mListener.onResponse(response);
    }
}
