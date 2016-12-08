package com.ebaonet.pharmacy.request;

public interface RequestCallBack<T> {

	/**
	 * 成功
	 * 
	 * @param flag
	 *            请求标示
	 * @param json
	 */
	public void requestSuccess(final int flag, T json);
}
