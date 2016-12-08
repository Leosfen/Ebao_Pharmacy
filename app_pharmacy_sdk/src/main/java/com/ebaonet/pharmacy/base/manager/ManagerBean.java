package com.ebaonet.pharmacy.base.manager;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.ebaonet.pharmacy.base.callback.CallBackManager;
import com.ebaonet.pharmacy.entity.BaseEntity;
import com.ebaonet.pharmacy.logger.Logger;
import com.ebaonet.pharmacy.request.RequestListener;
import com.ebaonet.pharmacy.request.VolleyErrorHelper;
import com.ebaonet.pharmacy.util.JsonUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by yao.feng on 2016/8/10.
 */
public class ManagerBean implements RequestListener {

    private CallBackManager mCallBackManager = CallBackManager.getInstance();


    @Override
    public void requestSuccess(Object response, Object tag, Class<?> cl, String... keys) {
        if (mCallBackManager == null) {
            Logger.e("CallBackManager has been destroyed");
        } else {
            if (response != null) {
                if (response instanceof byte[]) {
                    byte[] rBytes = (byte[]) response;
                    String json = null;
                    try {
                        json = new String(rBytes, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                    if (json != null) {
                        if (tag != null) {
                            Logger.d("==============" + tag.toString() + "============");
                            Logger.d(json);
                            //Logger.json(json);
                            if (cl != null) {
//                                if(tag.toString().contains(DrugConfig.GET_DRUG_DISP)){
//                                    String tempJson = json.replace("\\", "");
//                                    json = tempJson;
//                                    Logger.d("==============" + tag.toString() + "============");
//                                    Logger.d(json);
//                                    BaseEntity mBaseEntity = (BaseEntity) JsonUtil.toBean(json, cl);
//                                    if (mBaseEntity != null) {
//                                        mCallBackManager.finishCallBack(tag.toString(), mBaseEntity.getCode(), mBaseEntity, keys);
//                                    } else {
//                                        Logger.e("JsonUtil has an exception");
//                                    }
//                                }else {
                                BaseEntity mBaseEntity = (BaseEntity) JsonUtil.toBean(json, cl);
                                if (mBaseEntity != null) {
                                    mCallBackManager.finishCallBack(tag.toString(), mBaseEntity.getCode(), mBaseEntity, keys);
                                } else {
                                    Logger.e("JsonUtil has an exception");
                                }
//                                }

                            } else {
                                Logger.e("Class of reflect can not be null");
                            }
                        } else {
                            Logger.e("Http request have not tag");
                            Logger.d(json);
                        }
                    } else {
                        Logger.e("response jsonString can not be null");
                    }
                } else if (response instanceof NetworkResponse) {

                } else {
                    Logger.e("response entity can not be definition");
                }
            } else {
                Logger.e("response entity can not be null");
            }
        }
    }

    @Override
    public void requestError(VolleyError e, Object tag, Class<?> cl, String... keys) {
        if (mCallBackManager == null) {
            Logger.e("CallBackManager has been destroyed");
        } else {
            if (tag != null) {
                Logger.e("==============" + tag.toString() + "============");
                Logger.e("==============Http request occurs an exception============");
                mCallBackManager.finishCallBack(tag.toString(), VolleyErrorHelper.getCode(e),
                        e, keys);
            } else {
                Logger.e("Http request have not tag");
            }
        }

    }

    public void startCallBack(String... keys) {
        mCallBackManager.startCallBack(keys);
    }


}
