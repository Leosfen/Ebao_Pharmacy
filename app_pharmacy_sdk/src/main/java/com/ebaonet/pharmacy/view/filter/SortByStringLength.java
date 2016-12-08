package com.ebaonet.pharmacy.view.filter;


import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

import java.util.Comparator;

/**
 * @author yao.feng
 * 
 *         2016年2月23日
 */
public class SortByStringLength implements Comparator<SingleFilterObj> {

	@Override
	public int compare(SingleFilterObj arg0, SingleFilterObj arg1) {

		if (arg0.getName().length() > arg1.getName().length()) {
			return 1;
		} else if (arg0.getName().length() == arg1.getName().length()) {
			return 0;
		}
		return -1;
	}

}
