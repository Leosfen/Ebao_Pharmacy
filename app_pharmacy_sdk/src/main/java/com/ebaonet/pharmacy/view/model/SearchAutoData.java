package com.ebaonet.pharmacy.view.model;

/**
 * 历史记录条目实体类
 * Created by zhaojun.gao on 2016/6/25.
 */
public class SearchAutoData {
    private String id ="";
    private String content = "";
    public String getId() {
        return id;
    }
    public SearchAutoData setId(String id) {
        this.id = id;
        return this;
    }
    public String getContent() {
        return content;
    }
    public SearchAutoData setContent(String content) {
        this.content = content;
        return this;
    }
}
