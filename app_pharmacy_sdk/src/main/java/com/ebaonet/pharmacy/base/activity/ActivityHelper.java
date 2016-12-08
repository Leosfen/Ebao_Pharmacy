package com.ebaonet.pharmacy.base.activity;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * activity管理类
 * 
 */
public class ActivityHelper {
	// activity栈
	private static Stack<Activity> activityStack;
	private static ActivityHelper instance;

	private ActivityHelper() {
	}

	public static ActivityHelper getInstance() {
		if (instance == null) {
			instance = new ActivityHelper();
		}
		return instance;
	}

	/**
	 * 将指定activity压入栈中
	 * 
	 * @param activity
	 */
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		// 防止重复入栈
		if (!activityStack.contains(activity)) {
			activityStack.add(activity);
		}
	}

	/**
	 * 销毁最顶层的activity
	 */
	public void popActivity() {
		Activity activity = activityStack.lastElement();
		popActivity(activity);
	}

	/**
	 * 销毁指定actiivty
	 * 
	 * @param activity
	 */
	public void popActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			if (!activity.isFinishing()) {
				activity.finish();
			}
			activity = null;
		}
	}

	/**
	 * 返回最顶层activity实例
	 * 
	 * @return
	 */
	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack != null && !activityStack.isEmpty()) {
			activity = activityStack.lastElement();
		}
		return activity;
	}

	/**
	 * 判断栈中是否有某个Activity
	 * 
	 * @param activity
	 * @return
	 */
	public boolean hasActivity(Class<Activity> cls) {
		for (Activity act : activityStack) {
			if (act.getComponentName().getClassName().equals(cls.getName())) {
				Log.i("ScreenManager", cls.getName() + " is found.");
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回栈中某个Activity
	 * 
	 * @param activity
	 * @return
	 */
	public Activity getActivity(Class<Activity> cls) {
		for (Activity act : activityStack) {
			if (act.getComponentName().getClassName().equals(cls.getName())) {
				Log.i("ScreenManager", cls.getName() + " is found.");
				return act;
			}
		}
		return null;
	}

	/**
	 * 除了指定activity其余的全部销毁
	 * 
	 * @param cls
	 */
	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (cls != null
					&& activity.getClass().getName().equals(cls.getName())) {
				break;
			}
			popActivity(activity);
		}
	}
	/**
	 * activity全部销毁
	 *
	 *
	 */
	public void popAllActivity() {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			
			popActivity(activity);
		}
	}

	public void popActivitys(Class... clss) {
		int len = clss.length;
		for (int i = 0; i < len; i++) {
			Activity activity = getActivity(clss[i]);
			if (activity != null) {
				popActivity(activity);
			}
		}
	}
}
