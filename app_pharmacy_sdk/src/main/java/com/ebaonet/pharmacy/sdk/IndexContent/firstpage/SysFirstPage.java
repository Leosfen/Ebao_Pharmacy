package com.ebaonet.pharmacy.sdk.IndexContent.firstpage;

import com.ebaonet.pharmacy.util.StringUtils;

/**
 *  
 *
 * @author ning.yang
 */
public class SysFirstPage {

    public String getPage_num() {
        return StringUtils.formatString(page_num);
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }

    public String getImage_id() {
        return StringUtils.formatString(image_id);
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return StringUtils.formatString(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String page_num; // 页面次序
    private String image_id; // 图片id
    private String url; // url

}
