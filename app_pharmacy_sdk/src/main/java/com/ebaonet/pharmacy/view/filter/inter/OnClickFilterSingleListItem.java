package com.ebaonet.pharmacy.view.filter.inter;


import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

/**
 * @author yao.feng
 *         <p>
 *         2016年1月12日
 */
public interface OnClickFilterSingleListItem {
    /**
     * 下拉菜单的下标，点击菜单的位置，以及要用到的filter实体类
     */
    public void onClickFilterSingeItem(int index, int position, SingleFilterObj mObj);

    /**
     * 隐藏
     */
    public void onDismissSingleView();
}
