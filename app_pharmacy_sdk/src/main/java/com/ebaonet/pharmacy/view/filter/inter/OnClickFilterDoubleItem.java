package com.ebaonet.pharmacy.view.filter.inter;


import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

/**
 * @author yao.feng
 *         <p/>
 *         2016年1月12日
 */
public interface OnClickFilterDoubleItem {

    /**
     * 大目录位置，小目录的位置,以及对应的实体类
     */
    public void onClickFilterDoubleItem(int index, int parentPos, int childPos, SingleFilterObj obj);

    /**
     * 点击完成
     */
    public void onClickComplete();

    /**
     * 点击重置
     */
    public void onClickReset();

    /**
     * 隐藏
     */
    public void onDismissDoubleView();


    public void countBottomHeight(int hei);
}
