package com.ebaonet.pharmacy.view.filter.obj;

import java.util.ArrayList;

/**
 * @author yao.feng
 *         <p/>
 *         2016年1月12日
 */
public class DoubleFilterObj {

    public DoubleFilterObj() {
    }

    private String id;

    /**
     * 一级大标题
     */
    private String name;

    /**
     * 二级小标题
     */
    private ArrayList<SingleFilterObj> mChild = new ArrayList<SingleFilterObj>();

    public DoubleFilterObj(String id, String name, ArrayList<SingleFilterObj> mChild) {
        this.id = id;
        this.name = name;
        setChild(mChild);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SingleFilterObj> getmChild() {
        return mChild;
    }

    public void setChild(ArrayList<SingleFilterObj> mChild) {
        this.mChild.addAll(mChild);
    }

}
