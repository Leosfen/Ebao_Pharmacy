package com.ebaonet.pharmacy.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ebaonet.pharmacy.base.callback.CallBackManager;
import com.ebaonet.pharmacy.base.callback.OnResultCallBack;
import com.ebaonet.pharmacy.request.VolleyErrorHelper;
import com.ebaonet.pharmacy.view.CustomProgressDialog;


/**
 * fragment 基类
 *
 * @author geely
 */
public class BaseFragment extends Fragment implements OnResultCallBack {

    protected View mView;

    protected Context mContext;
    protected CustomProgressDialog mProgressDialog;
    private boolean mResumed = true;

    //是否已经加载过
    private boolean isHaveLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        //增加回调接口
        CallBackManager.getInstance().addListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mResumed = true;
        isCanLoadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        mResumed = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //移除回调接口
        CallBackManager.getInstance().removeListener(this);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(getActivity());
            mProgressDialog.setMessage("请稍候...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing() && !getActivity().isFinishing()) {
            mProgressDialog.show();
        }
    }


    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (mView == null) {
            return;
        }
        if (getUserVisibleHint()) {
            if (isOftenLazyLoad()) {
                lazyLoad();
            } else {
                if (!isHaveLoaded) {
                    lazyLoad();
                }
            }
        } else {
            stopLoad();
        }
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected void lazyLoad() {
    }

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }

    /**
     * 默认只会请求一次网络
     * 是否视图每次可见都要加载数据
     */
    protected boolean isOftenLazyLoad() {
        return false;
    }


    @Override
    public void onStartCallBack(String... keys) {
    }

    @Override
    public void onResumeCallBack() {
        // TODO Auto-generated method stub

    }

    @Override
    public final void onFinishCallBack(String tag, int code, Object obj, String... keys) {
        if (isNetDataTransmission()) {
            onCallBack(tag, code, obj, keys);
            //真正的加载完成应该是布局控件被赋值之后,且没有网络请求报的错误,且可见
            if (!VolleyErrorHelper.isVolleyError(code) && getUserVisibleHint()) {
                isHaveLoaded = true;
            }
        }
    }

    public void onCallBack(String tag, int code, Object obj, String... keys) {
        // TODO Auto-generated method stub

    }

    /**
     * 位于栈顶的Activity请求的数据是否透传到当前的Fragment
     *
     * @return
     */
    public boolean isNetDataTransmission() {
        return mResumed;
    }

}
