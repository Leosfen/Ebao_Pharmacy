package com.ebaonet.pharmacy.sdk.file;

/**
 * Created by zhaojun.gao on 2016/9/29.
 */
public class ChaseInfo {
    
    private String chaseType;
    
    private String personInfo;
    private String companyInfo;

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getChaseType() {
        return chaseType;
    }

    public void setChaseType(String chaseType) {
        this.chaseType = chaseType;
    }

}
