package com.ebaonet.pharmacy.view.filter.obj;

/**
 * 单个过滤实体类
 *
 * @author yao.feng
 *         <p/>
 *         2016-1-11
 */
public class SingleFilterObj {

    public static final String NOT_LIMIT = "不限";
    public static final String NOT_LIMIT_ID = "NOT_LIMIT";

    private String id;// 名字ID
    private String name;// 名字

    public SingleFilterObj(String id, String name) {
        this.id = id;
        this.name = name;
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


    @Override
    public String toString() {
        return "SingleFilterObj{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
