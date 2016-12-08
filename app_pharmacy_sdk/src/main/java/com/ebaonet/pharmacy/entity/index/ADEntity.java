package com.ebaonet.pharmacy.entity.index;

/**滚动文字实体类
 * Created by zhaojun.gao on 2016/10/22.
 */
public class ADEntity {
    private String mFront ; //前面的文字
    private String mBack ; //后面的文字
    private String mUrl ;//包含的链接

    public ADEntity(String mFront, String mBack,String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmFront() {
        return mFront;
    }

    public void setmFront(String mFront) {
        this.mFront = mFront;
    }

    public String getmBack() {
        return mBack;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }
}
