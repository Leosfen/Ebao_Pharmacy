package com.ebaonet.pharmacy.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author yao.feng
 * 
 *         2015-8-4
 */
public class SoftInputUtils {

	/**
	 * 切换键盘状态
	 * 
	 * @param v
	 */
	public static void switchInput(Context mContext, View v) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 打开键盘
	 * 
	 * @param v
	 */
	public static void openInput(Context mContext, View v) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * 关闭键盘
	 * 
	 * @param v
	 */
	public static void closeInput(Context mContext, View v) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(),0); // 强制隐藏键盘
	}

}
