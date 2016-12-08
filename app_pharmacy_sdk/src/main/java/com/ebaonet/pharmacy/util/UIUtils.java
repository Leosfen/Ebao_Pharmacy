package com.ebaonet.pharmacy.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ebaonet.pharmacy.sdk.R;


public class UIUtils {

    /**
     * 设置右侧小图标。
     *
     * @param txtView
     * @param resId
     */
    public static void setDrawableRight(Context context, TextView txtView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        txtView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 设置左侧小图标。
     *
     * @param txtView
     * @param resId
     */
    public static void setDrawableLeft(Context context, TextView txtView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        txtView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 显示Toast提示。
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, int resId) {

        String msg = context.getResources().getString(resId);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        
    }

    /**
     * 显示Toast提示。
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * volleyError时显示的toast
     *
     * @param context
     * @param msg
     */
    public static void showErrorToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 设置图片的吐司提示
     *
     * @param context
     * @param view
     */
    public static void showImageToast(Context context, View view) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * 设置View的背景。
     *
     * @param view
     * @param background
     */
    @SuppressLint("NewApi")
    public static void setViewBackground(View view, Drawable background) {

        if (ReflectUtils.containMethod(TextView.class, "setBackground")) {
            view.setBackground(background);
        } else if (ReflectUtils.containMethod(TextView.class, "setBackgroundDrawable")) {
            view.setBackgroundDrawable(background);
        }
    }

    /**
     * 设置View的背景。
     *
     * @param view
     * @param background
     */
    @SuppressLint("NewApi")
    public static void setViewBackground(Context context, View view, int resId) {

        if (ReflectUtils.containMethod(TextView.class, "setBackground")) {
            view.setBackground(context.getResources().getDrawable(resId));
        } else if (ReflectUtils.containMethod(TextView.class, "setBackgroundDrawable")) {
            view.setBackgroundDrawable(context.getResources().getDrawable(resId));
        }
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得屏幕Dpi
     *
     * @param context
     * @return
     */
    public static int getScreenDpi(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.densityDpi;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object)
                    .toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

    public static int dip2px(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;
        return (int) (dp * density + 0.5f);
    }

    public static void showDialog(Context context, int res) {
        showDialog(context, context.getResources().getString(res));
    }

    public static void showDialog(Context context, String msg) {
        showDialog(context, msg, null);
    }

    public static void showDialog(Context context, int res, final OnClickListener listener) {
        showDialog(context, null, context.getResources().getString(res), listener);
    }

    public static void showDialog(Context context, String msg, final OnClickListener listener) {
        showDialog(context, null, msg, listener);
    }

    /**
     * 一个确定按钮
     *
     * @param context
     * @param title
     * @param msg
     */
    public static void showDialog(Context context, String title, String msg,
                                  final OnClickListener listener) {
        showDialog(context, title, msg, "确定", listener);
    }

    /**
     * 一个确定按钮
     *
     * @param context
     * @param title
     * @param msg
     */
    public static void showDialog(Context context, String title, String msg, String confirm,
                                  final OnClickListener listener) {
        Builder builder = new Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(msg)) {
            builder.setMessage(msg);
        }
        builder.setPositiveButton(confirm, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
            }
        });
        builder.show();
    }

    /**
     * 两个按钮
     *
     * @param context
     * @param title
     * @param msg
     * @param listener
     */
    public static void showDialogWithCancel(Context context, String title, String msg,
                                            final OnClickListener listener) {
        Builder builder = new Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(msg)) {
            builder.setMessage(msg);
        }
        builder.setPositiveButton("确定", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
            }
        });
        builder.setNegativeButton("取消", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
            }
        });
        builder.show();
    }
    /**
     * 保存成功toast
     *
     * @param context
     *
     */
    public static void showSaveSuccessToast(Context context,String str) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.pharmacy_save_sucess_toast,
                (ViewGroup) ((Activity) context).findViewById(R.id.llToast));
        TextView tvTextToast = (TextView)layout.findViewById(R.id.tvTextToast);
        tvTextToast.setText(str);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    /**
     * 保存成功toast
     *
     * @param context
     *
     */
    public static void showSuccessToast(Context context) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.pharmacy_success_toast,
                (ViewGroup) ((Activity) context).findViewById(R.id.llToast));
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    
    /**
     * 保存失败toast
     *
     * @param context
     *
     */
    public static void showSaveFailToast(Context context) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.pharmacy_save_error_toast,
                (ViewGroup) ((Activity) context).findViewById(R.id.llToast));
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    /**
     * 解决控件中显示中文内容不对齐
     * @param str
     * @return
     */
    public static String toDBC(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }

        }
        return new String(c);
    }
}
