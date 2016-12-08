package com.ebaonet.pharmacy.bean;

import com.ebaonet.pharmacy.util.StringUtils;

/**
 * Created by zhaojun.gao on 2016/8/16.
 */
public class LeftInfoBean {
    private boolean isChecked;//代表是否被选中
    private String labelName;
    public boolean isChecked() {
        return isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    public String getLabelName() {
        return StringUtils.formatString(labelName);
    }
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
