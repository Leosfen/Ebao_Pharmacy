package com.ebaonet.pharmacy.data.user;

/**
 * Created by yao.feng on 2016/11/9.
 */
public class PharmcyUserInfo {

    private String userId;//姓名
    private String name;//用户名
    private String idCard;//身份证号

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
