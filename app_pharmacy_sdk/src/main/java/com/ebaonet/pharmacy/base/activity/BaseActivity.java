package com.ebaonet.pharmacy.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ebaonet.pharmacy.base.ResponseCodeAgent;
import com.ebaonet.pharmacy.base.callback.CallBackManager;
import com.ebaonet.pharmacy.base.callback.OnResultCallBack;
import com.ebaonet.pharmacy.request.VolleyErrorHelper;
import com.ebaonet.pharmacy.util.ResourceUitls;
import com.ebaonet.pharmacy.util.UIUtils;
import com.ebaonet.pharmacy.util.permission.MPermissions;
import com.ebaonet.pharmacy.util.permission.PermissionProxy;
import com.ebaonet.pharmacy.view.CustomProgressDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by yao.feng on 2016/8/9.
 */
public class BaseActivity extends FragmentActivity implements OnResultCallBack, PermissionProxy {


    protected boolean mResumed = true;
    private ViewGroup mAllContentView;
    private View mToolBarView;
    protected Context mContext;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    protected ImageButton btnLeft;
    protected ImageButton btnRight;
    protected TextView tvRight;
    protected TextView tvTitle;
    protected TextView tvLeft;
    protected CustomProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(BaseActivity.this);
            mProgressDialog.setMessage("请稍候...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing() && !isFinishing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //入栈
        ActivityHelper.getInstance().pushActivity(this);
        // 增加数据回调接口
        CallBackManager.getInstance().addListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mResumed = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //出栈
        ActivityHelper.getInstance().popActivity(this);
        // 移除数据回调接口
        CallBackManager.getInstance().removeListener(this);
    }


    protected void initTopbar() {
        btnLeft = (ImageButton) findViewById(ResourceUitls.getId(this, "pharmacy_leftBtn"));
        if (btnLeft != null) {
            btnLeft.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onClickLeftBack();
                }
            });
        }
        btnRight = (ImageButton) findViewById(ResourceUitls.getId(this, "pharmacy_rightBtn"));
        tvTitle = (TextView) findViewById(ResourceUitls.getId(this, "pharmacy_tv_title"));
        tvRight = (TextView) findViewById(ResourceUitls.getId(this, "pharmacy_rightTv"));
        tvLeft = (TextView) findViewById(ResourceUitls.getId(this, "pharmacy_leftTv"));
    }

    /**
     * 点击左上方的返回箭头
     */
    public void onClickLeftBack() {
        finish();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (getContentView(layoutResID) != null) {
            super.setContentView(mAllContentView);
        } else {
            super.setContentView(layoutResID);
        }
        initTopbar();
    }

    //暂存contentVIew，为以后网络连接失败，填充另一个布局做准备,子类可以重写此方法
    public View getContentView(int layoutResID) {
        mAllContentView = (ViewGroup) LayoutInflater.from(mContext).inflate(layoutResID, null);
        if (mAllContentView.getChildCount() > 0) {
            if (mAllContentView.getChildAt(0) instanceof ViewGroup) {
                mToolBarView = mAllContentView.getChildAt(0);
            }
        }
        return mAllContentView;
    }


    @Override
    public void onStartCallBack(String... keys) {
        if (mResumed) {
            showProgressDialog();
        }
    }

    @Override
    public void onResumeCallBack() {
        // TODO Auto-generated method stub

    }

    @Override
    public final void onFinishCallBack(String tag, int code, Object obj, String... keys) {
        dismissProgressDialog();
        if (mResumed) {
            //错误的Message,Toast显示
            ResponseCodeAgent.handleCode(mContext, tag, code, obj, keys);
            //加载网络连接失败图片
            if (code == VolleyErrorHelper.VOLLEY_ERR_NETWORK_NOT_AVAILABLE) {
                showNetworkProView();
            }
        }

        if (isNetDataTransmission()) {
            onCallBack(tag, code, obj, keys);
        }
    }

    public void onCallBack(String tag, int code, Object obj, String... keys) {
        // TODO Auto-generated method stub

    }

    public void showNetworkProView() {

    }


    /**
     * 位于栈顶的Activity请求的数据是否透传到当前的activity
     *
     * @return
     */
    public boolean isNetDataTransmission() {
        return mResumed;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void OnPermissionProxyResult(int grantResult, int requestCode) {
        if (requestCode == MPermissions.REQUEST_LOCATION_CODE) {
            if (grantResult == MPermissions.DENIED) {
                UIUtils.showToast(this, "很抱歉，定位权限被禁止，请在权限管理中打开！");
            }
        } else if (requestCode == MPermissions.REQUEST_CAMERA_CODE) {
            if (grantResult == MPermissions.DENIED) {
                UIUtils.showToast(this, "很抱歉，相机权限被禁止，请在权限管理中打开！");
            }
        } else if (requestCode == MPermissions.REQUEST_STORAGE_CODE) {
            if (grantResult == MPermissions.DENIED) {
                UIUtils.showToast(this, "很抱歉，读写内存卡权限被禁止，请在权限管理中打开！");
            }
        } else if (requestCode == MPermissions.REQUEST_PHONE_CODE) {
            if (grantResult == MPermissions.DENIED) {
                UIUtils.showToast(this, "很抱歉，读取手机信息权限被禁止，请在权限管理中打开！");
            }
        }
    }
}
