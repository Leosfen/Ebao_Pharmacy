package com.ebaonet.pharmacy.util;

import android.content.Context;
import android.text.format.Formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DigitalUtil {

	/**
	 * 保留1位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String keepOneDecimal(String d) {
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(Double.valueOf(d));
	}
	/**
	 * 保留1位小数
	 * 
	 * @param d
	 * @return
	 */
	public static double keepOneDecimal(double d) {
		DecimalFormat df = new DecimalFormat("0.0");
		return Double.valueOf(df.format(d));
	}
	/**
	 * 保留两位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String keepTwoDecimal(String d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(Double.valueOf(d));
	}

	/**
	 * 保留两位小数
	 * 
	 * @param d
	 * @return
	 */
	public static double keepTwoDecimal(double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(d));
	}

	/**
	 * 转换为百分数格式，保留两位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String toPercentDigital(double d) {
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(2);
		return nt.format(d);
	}

	/**
	 * 格式化数字，每隔3位打一个逗号
	 * 
	 * @param value
	 * @return
	 */
	public static String toNumberFormat(long value) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(0);
		return nf.format(value);
	}

	/**
	 * 格式化数字，转换为文件大小格式
	 * 
	 * @param context
	 * @param number
	 * @return
	 */
	public static String toFileSizeFormat(Context context, long number) {
		return Formatter.formatFileSize(context, number);
	}
}
