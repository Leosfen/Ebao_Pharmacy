package com.ebaonet.pharmacy.view.filter.inter;

import com.ebaonet.pharmacy.view.filter.obj.SingleFilterObj;

/**
 * Created by yao.feng on 2016/8/31.
 */
public interface OnCheckFilterDoubleItem {

    /**
     * 大目录位置，小目录的位置,以及对应的实体类
     */
    public void onCheckFilterDoubleItem(int index, int parentPos, int childPos, 
                                        SingleFilterObj obj, boolean isChecked);
}
