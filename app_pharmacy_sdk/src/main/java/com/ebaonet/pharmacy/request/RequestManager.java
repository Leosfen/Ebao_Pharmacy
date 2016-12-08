package com.ebaonet.pharmacy.request;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 *
 */
public class RequestManager {

    private final static RequestQueue mRequestQueue = CustomVolley.newRequestQueue();

    private RequestManager() {
        // no instances
    }

    /**
     * @param url
     * @param tag
     * @param listener
     */
    public static void get(String url, Object tag, RequestListener listener) {
        get(url, tag, null, listener);
    }

    /**
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void get(String url, Object tag, RequestParams params, RequestListener listener) {
        Logger.d("request.tag=========" + tag.toString() + "............" + "request.url=====" + url);
        ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET, url, params, responseListener(
                listener, tag, null), responseError(listener, tag, null));
        addRequest(request, tag);
    }

    /**
     * @param url
     * @param tag
     * @param listener
     */
    public static void post(String url, Object tag, RequestListener listener) {
        post(url, tag, null, listener, null);
    }

    /**
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void post(String url, Object tag, RequestParams params, RequestListener listener,
                            Class<?> cl, String... keys) {
        Logger.d("request.tag=========" + tag.toString() + "............" + "request.url=====" + url);
        ByteArrayRequest request = new ByteArrayRequest(Method.POST, url, params, responseListener(
                listener, tag, cl, keys), responseError(listener, tag, cl, keys));
        request.setShouldCache(false);
        addRequest(request, tag);//将请求对象添加到请求队列里
    }

    /**
     * @param url
     * @param tag
     * @param params
     * @param listener
     * @param cl
     * @param isAddHeader  是否增加头部信息
     * @param isSaveCookie 是否保存cookie信息
     */
    public static void post(String url, Object tag, RequestParams params, RequestListener listener,
                            Class<?> cl, boolean isAddHeader, boolean isSaveCookie, String... keys) {
        Logger.d("request.tag=========" + tag.toString() + "............" + "request.url=====" + url);
        ByteArrayRequest request = new ByteArrayRequest(Method.POST, url, params, responseListener(
                listener, tag, cl, keys), responseError(listener, tag, cl, keys), isAddHeader,
                isSaveCookie);
        request.setShouldCache(false);
        addRequest(request, tag);

    }

    public static void postNrRequest(String url, Object tag, RequestParams params, RequestListener listener,
                                     Class<?> cl, boolean isAddHeader, boolean isSaveCookie, String... keys) {
        Logger.d("request.tag=========" + tag.toString() + "............" + "request.url=====" + url);
        NetworkResponseRequest request = new NetworkResponseRequest(Method.POST, url, params, networkResponseListener(
                listener, tag, cl, keys), responseError(listener, tag, cl, keys), isAddHeader,
                isSaveCookie);
        request.setShouldCache(false);
        addRequest(request, tag);
    }


    public static void postNrRequest(String url, Object tag, RequestParams params, RequestListener listener,
                                     Class<?> cl, String... keys) {
        Logger.d("request.tag=========" + tag.toString() + "............" + "request.url=====" + url);
        NetworkResponseRequest request = new NetworkResponseRequest(Method.POST, url, params, networkResponseListener(
                listener, tag, cl, keys), responseError(listener, tag, cl, keys));
        request.setShouldCache(false);
        addRequest(request, tag);
    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    /*响应成功方法
         如下俩方法就是在定义的post请求中，new ByteArrayRequest时被调用，返回值传递给ByteArrayRequest对象*/
    /*该方法中传入参数  RequestListener l，因为要调用子类的requestSuccess方法*/

    //重要：子类（DefaultXX）发送post请求，子类再重写requestSuccess方法

    /**
     * 成功消息监听
     *
     * @param l
     * @return
     */

    protected static Response.Listener<byte[]> responseListener(final RequestListener l,
                                                                final Object tag, final Class<?> cl, final String... keys) {
        return new Response.Listener<byte[]>() {
            //服务器返回成功，需要
            @Override
            public void onResponse(byte[] arg0) {
                l.requestSuccess(arg0, tag, cl, keys);
            }
        };
    }

    protected static Response.Listener<NetworkResponse> networkResponseListener(final RequestListener l,
                                                                                final Object tag, final Class<?> cl, final String... keys) {
        return new Response.Listener<NetworkResponse>() {
            //服务器返回成功，需要
            @Override
            public void onResponse(NetworkResponse arg0) {
                l.requestSuccess(arg0, tag, cl, keys);
            }
        };
    }

    /**
     * 返回错误监听
     *
     * @param l
     * @return
     */
    protected static Response.ErrorListener responseError(final RequestListener l,
                                                          final Object tag, final Class<?> cl, final String... keys) {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError e) {
                l.requestError(e, tag, cl, keys);
            }
        };
    }
}
