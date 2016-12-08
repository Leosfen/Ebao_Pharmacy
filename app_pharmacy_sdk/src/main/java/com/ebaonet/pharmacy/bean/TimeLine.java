package com.ebaonet.pharmacy.bean;

/**
 * 物流订单列表数据
 * Created by zhaojun.gao on 2016/9/26.
 */
public class TimeLine {
    private String  maddress;
    private String  mtime;

    public TimeLine(String maddress, String mtime) {
        this.maddress = maddress;
        this.mtime = mtime;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}
