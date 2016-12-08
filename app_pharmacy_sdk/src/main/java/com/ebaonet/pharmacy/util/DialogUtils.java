package com.ebaonet.pharmacy.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;

public class DialogUtils {

	public static Dialog createDialog(Context context, int msgId) {
		Builder builder = new Builder(context);
		builder.setMessage(msgId);
		builder.setPositiveButton("知道了", null);
		return builder.create();
	}

	public static Dialog createDialog(Context context, String msg) {
		Builder builder = new Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton("知道了", null);
		return builder.create();
	}

	public static void alertMsg(Context context, int msgId) {
		createDialog(context, msgId).show();
	}

	public static void alertMsg(Context context, String msg) {
		createDialog(context, msg).show();
	}
	
	/**
	 * 更新网络提醒
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog updateNetDialog(Context context, String msg,OnClickListener listener) {
		Builder builder = new Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton("取消", null);
		builder.setNegativeButton("继续", listener);
		builder.setCancelable(false);
		return builder.create();
	}
	/**
	 * 版本更新提醒
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog updateDialog(Context context, String msg,OnClickListener listener) {
		Builder builder = new Builder(context);
		builder.setTitle("发现新版本");
		builder.setMessage(msg);
		builder.setPositiveButton("稍后再试", null);
		builder.setNegativeButton("立即更新", listener);
		builder.setCancelable(false);
		return builder.create();
	}
	
	/**
	 * 两个按钮
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param listener
	 */
	public static void showDialogToLogin(Context context, String title,
			String msg, final OnClickListener listener) {
		Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(msg)) {
			builder.setMessage(msg);
		}
		builder.setPositiveButton("直接登录", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onClick(dialog, which);
				}
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
	
}
