package com.ebaonet.pharmacy.entity.order.orderlist;

import java.io.Serializable;

/**
 * Created by yao.feng on 2016/10/13.
 */
public class OrderAddress implements Serializable {
    private String addr;
    private String addrId;
    private String biotopeId;
    private String biotopeName;
    private String cityId;
    private String cityName;
    private long creTime;
    private String districtId;
    private String districtName;
    private String latitude;
    private String longitude;
    private boolean pGendId;
    private String provId;
    private String provName;
    private String receiveName;
    private String receivePhone;
    private boolean statusDefault;
    private String tmUserId;
    private long updTime;
    private String zoneId;
    private String zoneName;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getBiotopeId() {
        return biotopeId;
    }

    public void setBiotopeId(String biotopeId) {
        this.biotopeId = biotopeId;
    }

    public String getBiotopeName() {
        return biotopeName;
    }

    public void setBiotopeName(String biotopeName) {
        this.biotopeName = biotopeName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCreTime() {
        return creTime;
    }

    public void setCreTime(long creTime) {
        this.creTime = creTime;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isPGendId() {
        return pGendId;
    }

    public void setPGendId(boolean pGendId) {
        this.pGendId = pGendId;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public boolean isStatusDefault() {
        return statusDefault;
    }

    public void setStatusDefault(boolean statusDefault) {
        this.statusDefault = statusDefault;
    }

    public String getTmUserId() {
        return tmUserId;
    }

    public void setTmUserId(String tmUserId) {
        this.tmUserId = tmUserId;
    }

    public long getUpdTime() {
        return updTime;
    }

    public void setUpdTime(long updTime) {
        this.updTime = updTime;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
