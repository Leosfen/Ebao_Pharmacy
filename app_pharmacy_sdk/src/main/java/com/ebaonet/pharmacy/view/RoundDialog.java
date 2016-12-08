package com.ebaonet.pharmacy.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ebaonet.pharmacy.sdk.R;
import com.ebaonet.pharmacy.util.UIUtils;

/**
 * 圆角方框
 * Created by yao.feng on 2016/4/21.
 */
public class RoundDialog extends Dialog {

    private int dialogWidth = 0;
    private int dialogHeight = 0;

    private Context mContext;

    public RoundDialog(Context context) {
        super(context, R.style.MyDialogStyleBottom);
    }

    public RoundDialog(Context context, View mView) {
        super(context, R.style.MyDialogStyleBottom);
        this.mContext = context;
        initView(mView);
    }

    public void setView(View mView) {
        initView(mView);
    }

    public void setWidth(int width) {
        this.dialogWidth = width;
    }

    public void setHeight(int height) {
        this.dialogHeight = height;
    }


    private void initView(View mView) {
        setContentView(mView);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        if (dialogWidth == 0) {
            dialogWidth = UIUtils.getScreenWidth(mContext) * 2 / 3;
        }
        if (dialogHeight == 0) {
            dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        params.width = dialogWidth;
        params.height = dialogHeight;
        window.setAttributes(params);
    }

}
