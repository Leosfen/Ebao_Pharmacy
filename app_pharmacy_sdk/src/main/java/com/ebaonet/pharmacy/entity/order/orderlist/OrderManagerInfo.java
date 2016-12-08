package com.ebaonet.pharmacy.entity.order.orderlist;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yao.feng on 2016/10/13.
 */
public class OrderManagerInfo implements Serializable {
    private String addrId;
    /**
     * addr : 交道口南15号院3层
     * addrId : fc18b40822774a29acf259b07b67c529
     * biotopeId :
     * biotopeName : 交道口东街15号
     * cityId :
     * cityName : 开封市
     * creTime : 1476170192000
     * districtId : 410288
     * districtName : 龙亭区
     * latitude : 114.752410
     * longitude : 34.751236
     * pGendId : false
     * provId :
     * provName : 河南省
     * receiveName : sdghs
     * receivePhone : 18752366698
     * statusDefault : false
     * tmUserId : 123
     * updTime : 1476170192000
     * zoneId :
     * zoneName :
     */

    private OrderAddress address;
    private String creTime;
    private String creUser;
    private String delFlag;
    private String deliveryDesc;
    private String deliveryType;
    private String drugStoreId;
    private String drugSubStoreId;
    private String csPhone;
    /**
     * delFlag : 0
     * invoiceContent :
     * invoiceHead : 刘燕燕
     * invoiceId : 1
     * invoiceType : 1
     * remark :
     * sort : 1
     */

    private OrderInvoice invoice;
    private String invoiceFlag;
    private String invoiceId;
    private double normalPrice;
    private String orderCode;
    private double orderFreight;
    private String orderId;
    private double orderPrice;
    private String orderStatus;
    private String orderStatusDesc;
    private int preferentialPrice;
    private String reason;
    private int sort;
    private String updTime;
    private String updUser;
    private String qrCode;
    /**
     * creTime : 2016-09-27 10:24:47.0
     * creUser : 1
     * dimImage : {"creTime":1473822221000,"creUser":"超级管理员","fileSize":780831,"fileType":"image/pjpeg","hPixel":0,"imageId":"f4f0e55d8c7a4d649a51309fe0df784b","imagePath":"http://ebao-zsyd.ebaonet.cn/uploadimages/Koala.jpg","origFileName":"Koala.jpg","remark":"","thumbPath":"http://ebao-zsyd.ebaonet.cn/uploadimages/Koala.jpg","updTime":1473822221000,"updUser":"超级管理员","vPixel":0,"validFlag":true}
     * displayName : 天津天士力 丹参滴丸 60粒/瓶
     * drDrugBase : {"approved":"国药准字H41021856","barcode":"69**********9","brand":"天士力","brandId":"BRAND_KEY27","creTime":1474942000000,"creUser":"","domesticImports":"国产","drImageCarouselList":[],"drImeageManualList":[],"drugCategory":"","drugForm":"水丸","drugFormId":"FORM_KEY4","generalName":"天士力","indications":"用于治疗胸闷气短理气化瘀","insuranceId":"甲","listImageId":"f4f0e55d8c7a4d649a51309fe0df784b","manufacturer":"天津天士力现代中药集团","norms":"60粒/瓶","otcId":1,"otcName":"甲","packMaterial":"空","skuId":"186325","specId":"","standardName":"丹参滴丸","updTime":1474942000000,"updUser":"1","validFlag":true}
     * drugCode : 186325
     * drugDsId : 18
     * drugId : G695286
     * drugNum : 1
     * drugStoreId : 1
     * drugStoreName : 开封百氏康大药店
     * handler : {}
     * images : []
     * normPrice : 45.6
     * otcId :
     * otcName :
     * rate : 0
     * remark :
     * salesName :
     * skuId : 186325
     * statusStore :
     * statusUp : 1
     * upPrice : 45.6
     * updTime : 2016-09-27 15:43:26.0
     * updUser : 13
     */

    private List<OrderDrugInfo> drug;
    private List<?> logs;
    private List<?> remark;
    private String receiveAddr;
    private String receiveName;
    private String receivePhone;

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
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

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public OrderAddress getAddress() {
        return address;
    }

    public void setAddress(OrderAddress address) {
        this.address = address;
    }

    public String getCreTime() {
        return creTime;
    }

    public void setCreTime(String creTime) {
        this.creTime = creTime;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDrugStoreId() {
        return drugStoreId;
    }

    public void setDrugStoreId(String drugStoreId) {
        this.drugStoreId = drugStoreId;
    }

    public String getDrugSubStoreId() {
        return drugSubStoreId;
    }

    public void setDrugSubStoreId(String drugSubStoreId) {
        this.drugSubStoreId = drugSubStoreId;
    }

    public OrderInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderInvoice invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getOrderFreight() {
        return orderFreight;
    }

    public void setOrderFreight(double orderFreight) {
        this.orderFreight = orderFreight;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public int getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(int preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public List<OrderDrugInfo> getDrug() {
        return drug;
    }

    public void setDrug(List<OrderDrugInfo> drug) {
        this.drug = drug;
    }

    public List<?> getLogs() {
        return logs;
    }

    public void setLogs(List<?> logs) {
        this.logs = logs;
    }

    public List<?> getRemark() {
        return remark;
    }

    public void setRemark(List<?> remark) {
        this.remark = remark;
    }

    public String getCsPhone() {
        return csPhone;
    }

    public void setCsPhone(String csPhone) {
        this.csPhone = csPhone;
    }
}
