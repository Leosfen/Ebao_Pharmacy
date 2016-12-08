package com.ebaonet.pharmacy.base.callback;


import java.util.LinkedList;

public class CallBackManager {

    private CallBackManager() {
    }

    private static CallBackManager mInstance;

    private LinkedList<OnResultCallBack> mCallBackListener;

    public static CallBackManager getInstance() {
        if (mInstance == null) {
            mInstance = new CallBackManager();
        }
        return mInstance;
    }

    public void addListener(OnResultCallBack lister) {
        if (mCallBackListener == null) {
            mCallBackListener = new LinkedList<OnResultCallBack>();
        }
        mCallBackListener.remove(lister);
        mCallBackListener.addFirst(lister);
    }

    public void removeListener(OnResultCallBack lister) {
        if (mCallBackListener != null) {
            mCallBackListener.remove(lister);
        }
    }

    public void stopCallback() {
        if (mCallBackListener != null) {
            mCallBackListener.clear();
            mCallBackListener = null;
        }
    }

    public void startCallBack(String... keys) {
        if (mCallBackListener != null) {
            for (int i = 0; i < mCallBackListener.size(); i++) {
                OnResultCallBack mListener = mCallBackListener.get(i);
                if (mListener != null) {
                    mListener.onStartCallBack(keys);
                }
            }
        }
    }

    public void finishCallBack(String tag, int code, Object obj, String... keys) {
        if (mCallBackListener != null) {
            for (int i = 0; i < mCallBackListener.size(); i++) {
                OnResultCallBack mListener = mCallBackListener.get(i);
                if (mListener != null) {
                    mListener.onFinishCallBack(tag, code, obj, keys);
                }
            }
        }
    }

}
