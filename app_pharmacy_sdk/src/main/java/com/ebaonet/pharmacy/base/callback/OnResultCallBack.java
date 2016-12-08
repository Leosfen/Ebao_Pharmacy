package com.ebaonet.pharmacy.base.callback;

/**
 * @author yao.feng
 *         <p/>
 *         2015-7-14 网络请求生命周期方法
 * @tag 判定是哪个接口返回的数据
 * @code 请求响应的业务code，0是正确，非零是错误
 * @obj 请求成功后，返回的业务实体
 * @keys 补充的业务标记
 */

public interface OnResultCallBack {

    /* 网络请求启动 */
    public void onStartCallBack(String... keys);

    /* 网络请求运行中 */
    public void onResumeCallBack();

    /* 网络请求结束 */
    public void onFinishCallBack(String tag, int code, Object obj, String... keys);

}
