package com.ebaonet.pharmacy.sdk.fragment.index;

import com.ebaonet.pharmacy.entity.BaseEntity;

/**后期根据接口修改
 * Created by zhaojun.gao on 2016/10/11.
 */
public class MyShowBean extends BaseEntity {
    private String url;
    private String title;
    private String des;
    private String des2;

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
