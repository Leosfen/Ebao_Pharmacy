package com.ebaonet.pharmacy.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebaonet.pharmacy.sdk.R;


/**
 * 设置结束的Toast提示
 * Created by yao.feng on 2016/5/25.
 */
public class ActionEndToast {

    /**
     * @param mContext
     * @param title    例如登录成功、绑定成功，登录失败
     * @param msg      如果为空，则不显示
     */
    public static void showImgToast(Context mContext, String title, String msg, boolean isSuccess) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_img_toast, null);
        ImageView mImage = (ImageView) view.findViewById(R.id.img_toast);
        TextView mTitle = (TextView) view.findViewById(R.id.img_toast_title);
        TextView mMsg = (TextView) view.findViewById(R.id.img_toast_message);
        if (isSuccess) {
            mImage.setImageResource(R.drawable.pharmacy_success_icon);
        } else {
            mImage.setImageResource(R.drawable.pharmacy_fail_icon);
        }
        if (!TextUtils.isEmpty(title)) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(title);
        } else {
            mTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(msg)) {
            mMsg.setVisibility(View.VISIBLE);
            mMsg.setText(msg);
        } else {
            showSmallImgToast(mContext, title, isSuccess);
            return;
        }
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }


    /**
     * @param mContext
     * @param title    例如登录成功、绑定成功，登录失败
     */
    public static void showSmallImgToast(Context mContext, String title, boolean isSuccess) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pharmacy_small_img_toast, null);
        ImageView mImage = (ImageView) view.findViewById(R.id.img_toast);
        TextView mTitle = (TextView) view.findViewById(R.id.img_toast_title);
        if (isSuccess) {
            mImage.setImageResource(R.drawable.pharmacy_success_icon);
        } else {
            mImage.setImageResource(R.drawable.pharmacy_fail_icon);
        }
        if (!TextUtils.isEmpty(title)) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(title);
        } else {
            mTitle.setVisibility(View.GONE);
        }
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

}
