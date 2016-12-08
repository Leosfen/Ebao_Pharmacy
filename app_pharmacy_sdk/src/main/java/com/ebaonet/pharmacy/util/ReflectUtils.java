package com.ebaonet.pharmacy.util;

import java.lang.reflect.Method;

/**
 * 反射相关的工具类。
 */
public class ReflectUtils {
	
	/**
	 * 根据类名获取类。
	 * @param s
	 * @return
	 */
	public static Class<?> getClass(String s) {
		Class<?> c = null;
		try {
			if (s != null && !s.trim().equals(""))
				c = Class.forName(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * 根据类名创建类对象。
	 * @param s
	 * @return
	 */
	public static Object getNewInstance(String s) {
		Class<?> c = getClass(s);
		Object o = null;
		try {
			if (c != null)
				o = c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	/**
	 * 判断是否实现了某个接口。
	 * @param sub
	 * @param parent
	 * @return
	 */
	public static boolean subOfInterface(Class sub, String interfaceName) {
		boolean bool = false;
		Class[] clazz = sub.getInterfaces();
		for(int i=0;i<clazz.length;i++){
			if(clazz[i].getName().equals(interfaceName)) {
				bool = true;
			}
		}
		return bool;
	}
	
	/**
	 * 查看某个类中是否有给定的方法。
	 * @param methodName
	 * @return
	 */
	public static boolean containMethod(Class clazz, String methodName) {
		boolean bool = false;
		Method[] methods = clazz.getMethods();
		for(Method method : methods) {
			if(method.getName().equals(methodName)) {
				bool = true;
				break;
			}
		}
		return bool;
	}
}
