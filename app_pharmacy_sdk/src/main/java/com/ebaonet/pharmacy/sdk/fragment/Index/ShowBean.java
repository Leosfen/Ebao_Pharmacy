package com.ebaonet.pharmacy.sdk.fragment.index;

/**
 * @author yao.feng
 *         <p/>
 *         2015-12-9
 */
public class ShowBean {

    private int id;
    private int imgPath;
    private String title;
    private String describe;

    public ShowBean(int imgPath, String title, int id) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
    }

    public ShowBean(int imgPath, String title, String des, int id) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.describe = des;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImgPath() {
        return imgPath;
    }

    public void setImgPath(int imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
