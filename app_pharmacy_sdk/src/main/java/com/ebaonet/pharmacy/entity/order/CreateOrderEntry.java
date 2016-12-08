package com.ebaonet.pharmacy.entity.order;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Created by peng.dong on 2016/10/11.
 */
public class CreateOrderEntry extends BaseEntity{


    /**
     * address : {"addr":"Wwww","addrId":"2a4ee5f32237477a957778ff33e90f00","biotopeId":"","biotopeName":"新源里西11号楼","cityId":"","cityName":"开封市","creTime":1479896687000,"districtId":"","districtName":"","latitude":"39.95538155496928","longitude":"116.45731442978307","pGendId":false,"provId":"","provName":"河南省","receiveName":"rrr","receivePhone":"13840012345","statusDefault":true,"tmUserId":"T","updTime":1479896687000,"zoneId":"","zoneName":""}
     * creTime : 
     * creUser : 
     * csPhone : 
     * delFlag : 
     * deliveryDesc : 
     * deliveryType : 1
     * districtId : 
     * drug : [{"creTime":"2016-10-20 17:00:03","creUser":"ljq","dimImage":{"creTime":1478835918000,"creUser":"ljq1111","fileSize":0,"fileType":"","hPixel":0,"imageId":"BD0000046-1","imagePath":"http://ebao-zsyd.ebaonet.cn/drugImages/BD0000046-1.jpg","origFileName":"","remark":"","thumbPath":"http://ebao-zsyd.ebaonet.cn/drugImages/BD0000046-1.jpg","updTime":1478835918000,"updUser":"","vPixel":0,"validFlag":true},"displayName":"神苗 小儿氨酚黄那敏颗粒 20袋/盒","drDrugBase":{"approved":"国药准字H13023707","barcode":"6938007001444","brand":"神苗","brandId":"B1155","creTime":1476951624000,"creUser":"ljq","domesticImports":"国产","drImageCarouselList":[],"drImeageManualList":[],"drugCategory":"","drugForm":"颗粒剂","drugFormId":"F1013","generalName":"","indications":"适用于缓解儿童普通感冒及流行性感冒引起的发热、头痛、四肢酸痛、打喷嚏、流鼻涕、鼻塞、咽痛等症状。","insuranceId":"","listImageId":"BD0000046-1","manufacturer":"神威药业集团有限公司","norms":"20袋/盒","otcId":1,"otcName":"甲","packMaterial":"","skuId":"ljq110746","specId":"SMS01051F","standardName":"小儿氨酚黄那敏颗粒","updTime":1476951624000,"updUser":"","validFlag":true},"drugCode":"110746","drugDsId":"ljq110746","drugId":"G092193","drugNum":2,"drugStoreId":"1","drugStoreName":"百氏康药店","handler":{},"images":[],"manufacturer":"","normPrice":4.7,"otcId":"","otcName":"","rate":0,"remark":"","salesName":"","skuId":"ljq110746","statusStore":"1","statusUp":"1","upPrice":4.7,"updTime":"2016-10-20 17:00:03","updUser":""},{"creTime":"2016-10-20 17:00:03"," reUser":"ljq","dimImage":{"creTime":1478835918000,"creUser":"ljq1111","fileSize":0,"fileType":"","hPixel":0,"imageId":"BD0000897-1","imagePath":"http://ebao-zsyd.ebaonet.cn/drugImages/BD0000897-1.jpg","origFileName":"","remark":"","thumbPath":"http://ebao-zsyd.ebaonet.cn/drugImages/BD0000897-1.jpg","updTime":1478835918000,"updUser":"","vPixel":0,"validFlag":true},"displayName":"芬必得 布洛芬咀嚼片 0.2g*10片/盒","drDrugBase":{"approved":"国药准字H20140018","barcode":"6913991301411","brand":"芬必得","brandId":"B1196","creTime":1476951624000,"creUser":"ljq","domesticImports":"国产","drImageCarouselList":[],"drImeageManualList":[],"drugCategory":"","drugForm":"咀嚼片","drugFormId":"F1031","generalName":"","indications":"用于儿童减轻中度疼痛，如关节痛、神经痛、肌肉痛、偏头痛、头痛、牙痛，也可用于减轻普通感冒或流行性感冒引起的发热。","insuranceId":"","listImageId":"BD0000897-1","manufacturer":"中美天津史克制药有限公司","norms":"0.2g*10片/盒","otcId":1,"otcName":"甲","packMaterial":"","skuId":"ljq140065","specId":"SMS002810","standardName":"布洛芬咀嚼片","updTime":1476951624000,"updUser":"","validFlag":true},"drugCode":"140065","drugDsId":"ljq140065","drugId":"G145545","drugNum":1,"drugStoreId":"1","drugStoreName":"百氏康药店","handler":{},"images":[],"manufacturer":"","normPrice":25,"otcId":"","otcName":"","rate":0,"remark":"","salesName":"","skuId":"ljq140065","statusStore":"1","statusUp":"1","upPrice":25,"updTime":"2016-10-20 17:00:03","updUser":""}]
     * drugStoreId : 1
     * drugSubStoreId : 
     * drugsPrice : 34.4
     * invoice : null
     * invoiceFlag : 
     * invoiceId : 
     * latitude : 
     * logs : []
     * longitude : 
     * normalPrice : 34.4
     * orderCode : 120000003366
     * orderDrug : []
     * orderFreight : 0
     * orderId : 
     * orderPrice : 34.4
     * orderStatus : 
     * orderStatusDesc : 
     * preferentialPrice : 0
     * reason : 
     * receiveAddr : 
     * receiveName : 
     * receivePhone : 
     * remark : []
     * sort : 0
     * updTime : 
     * updUser : 
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * addr : Wwww
         * addrId : 2a4ee5f32237477a957778ff33e90f00
         * biotopeId : 
         * biotopeName : 新源里西11号楼
         * cityId : 
         * cityName : 开封市
         * creTime : 1479896687000
         * districtId : 
         * districtName : 
         * latitude : 39.95538155496928
         * longitude : 116.45731442978307
         * pGendId : false
         * provId : 
         * provName : 河南省
         * receiveName : rrr
         * receivePhone : 13840012345
         * statusDefault : true
         * tmUserId : T
         * updTime : 1479896687000
         * zoneId : 
         * zoneName : 
         */

        private AddressBean address;
        private String creTime;
        private String creUser;
        private String csPhone;
        private String delFlag;
        private String deliveryDesc;
        private String deliveryType;
        private String districtId;
        private String drugStoreId;
        private String drugSubStoreId;
        private double drugsPrice;
        private Object invoice;
        private String invoiceFlag;
        private String invoiceId;
        private String latitude;
        private String longitude;
        private double normalPrice;
        private String orderCode;
        private int orderFreight;
        private String orderId;
        private double orderPrice;
        private String orderStatus;
        private String orderStatusDesc;
        private int preferentialPrice;
        private String reason;
        private String receiveAddr;
        private String receiveName;
        private String receivePhone;
        private int sort;
        private String updTime;
        private String updUser;
        /**
         * creTime : 2016-10-20 17:00:03
         * creUser : ljq
         * dimImage : {"creTime":1478835918000,"creUser":"ljq1111","fileSize":0,"fileType":"","hPixel":0,"imageId":"BD0000046-1","imagePath":"http://ebao-zsyd.ebaonet.cn/drugImages/BD0000046-1.jpg","origFileName":"","remark":"","thumbPath":"http://ebao-zsyd.ebaonet.cn/drugImages/BD0000046-1.jpg","updTime":1478835918000,"updUser":"","vPixel":0,"validFlag":true}
         * displayName : 神苗 小儿氨酚黄那敏颗粒 20袋/盒
         * drDrugBase : {"approved":"国药准字H13023707","barcode":"6938007001444","brand":"神苗","brandId":"B1155","creTime":1476951624000,"creUser":"ljq","domesticImports":"国产","drImageCarouselList":[],"drImeageManualList":[],"drugCategory":"","drugForm":"颗粒剂","drugFormId":"F1013","generalName":"","indications":"适用于缓解儿童普通感冒及流行性感冒引起的发热、头痛、四肢酸痛、打喷嚏、流鼻涕、鼻塞、咽痛等症状。","insuranceId":"","listImageId":"BD0000046-1","manufacturer":"神威药业集团有限公司","norms":"20袋/盒","otcId":1,"otcName":"甲","packMaterial":"","skuId":"ljq110746","specId":"SMS01051F","standardName":"小儿氨酚黄那敏颗粒","updTime":1476951624000,"updUser":"","validFlag":true}
         * drugCode : 110746
         * drugDsId : ljq110746
         * drugId : G092193
         * drugNum : 2
         * drugStoreId : 1
         * drugStoreName : 百氏康药店
         * handler : {}
         * images : []
         * manufacturer : 
         * normPrice : 4.7
         * otcId : 
         * otcName : 
         * rate : 0
         * remark : 
         * salesName : 
         * skuId : ljq110746
         * statusStore : 1
         * statusUp : 1
         * upPrice : 4.7
         * updTime : 2016-10-20 17:00:03
         * updUser : 
         */

        private List<DrugBean> drug;
        private List<?> logs;
        private List<?> orderDrug;
        private List<?> remark;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
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

        public String getCsPhone() {
            return csPhone;
        }

        public void setCsPhone(String csPhone) {
            this.csPhone = csPhone;
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

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
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

        public double getDrugsPrice() {
            return drugsPrice;
        }

        public void setDrugsPrice(double drugsPrice) {
            this.drugsPrice = drugsPrice;
        }

        public Object getInvoice() {
            return invoice;
        }

        public void setInvoice(Object invoice) {
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

        public int getOrderFreight() {
            return orderFreight;
        }

        public void setOrderFreight(int orderFreight) {
            this.orderFreight = orderFreight;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public List<DrugBean> getDrug() {
            return drug;
        }

        public void setDrug(List<DrugBean> drug) {
            this.drug = drug;
        }

        public List<?> getLogs() {
            return logs;
        }

        public void setLogs(List<?> logs) {
            this.logs = logs;
        }

        public List<?> getOrderDrug() {
            return orderDrug;
        }

        public void setOrderDrug(List<?> orderDrug) {
            this.orderDrug = orderDrug;
        }

        public List<?> getRemark() {
            return remark;
        }

        public void setRemark(List<?> remark) {
            this.remark = remark;
        }

        public static class AddressBean  implements Serializable {
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

        public static class DrugBean  implements Serializable {
            private String medicalInsuranceCode;

            public String getMedicalInsuranceCode() {
                return medicalInsuranceCode;
            }

            public void setMedicalInsuranceCode(String medicalInsuranceCode) {
                this.medicalInsuranceCode = medicalInsuranceCode;
            }

            private String creTime;
            private String creUser;
            /**
             * creTime : 1478835918000
             * creUser : ljq1111
             * fileSize : 0
             * fileType : 
             * hPixel : 0
             * imageId : BD0000046-1
             * imagePath : http://ebao-zsyd.ebaonet.cn/drugImages/BD0000046-1.jpg
             * origFileName : 
             * remark : 
             * thumbPath : http://ebao-zsyd.ebaonet.cn/drugImages/BD0000046-1.jpg
             * updTime : 1478835918000
             * updUser : 
             * vPixel : 0
             * validFlag : true
             */

            private DimImageBean dimImage;
            private String displayName;
            /**
             * approved : 国药准字H13023707
             * barcode : 6938007001444
             * brand : 神苗
             * brandId : B1155
             * creTime : 1476951624000
             * creUser : ljq
             * domesticImports : 国产
             * drImageCarouselList : []
             * drImeageManualList : []
             * drugCategory : 
             * drugForm : 颗粒剂
             * drugFormId : F1013
             * generalName : 
             * indications : 适用于缓解儿童普通感冒及流行性感冒引起的发热、头痛、四肢酸痛、打喷嚏、流鼻涕、鼻塞、咽痛等症状。
             * insuranceId : 
             * listImageId : BD0000046-1
             * manufacturer : 神威药业集团有限公司
             * norms : 20袋/盒
             * otcId : 1
             * otcName : 甲
             * packMaterial : 
             * skuId : ljq110746
             * specId : SMS01051F
             * standardName : 小儿氨酚黄那敏颗粒
             * updTime : 1476951624000
             * updUser : 
             * validFlag : true
             */

            private DrDrugBaseBean drDrugBase;
            private String drugCode;
            private String drugDsId;
            private String drugId;
            private int drugNum;
            private String drugStoreId;
            private String drugStoreName;
            private HandlerBean handler;
            private String manufacturer;
            private double normPrice;
            private String otcId;
            private String otcName;
            private int rate;
            private String remark;
            private String salesName;
            private String skuId;
            private String statusStore;
            private String statusUp;
            private double upPrice;
            private String updTime;
            private String updUser;
            private List<?> images;

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

            public DimImageBean getDimImage() {
                return dimImage;
            }

            public void setDimImage(DimImageBean dimImage) {
                this.dimImage = dimImage;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public DrDrugBaseBean getDrDrugBase() {
                return drDrugBase;
            }

            public void setDrDrugBase(DrDrugBaseBean drDrugBase) {
                this.drDrugBase = drDrugBase;
            }

            public String getDrugCode() {
                return drugCode;
            }

            public void setDrugCode(String drugCode) {
                this.drugCode = drugCode;
            }

            public String getDrugDsId() {
                return drugDsId;
            }

            public void setDrugDsId(String drugDsId) {
                this.drugDsId = drugDsId;
            }

            public String getDrugId() {
                return drugId;
            }

            public void setDrugId(String drugId) {
                this.drugId = drugId;
            }

            public int getDrugNum() {
                return drugNum;
            }

            public void setDrugNum(int drugNum) {
                this.drugNum = drugNum;
            }

            public String getDrugStoreId() {
                return drugStoreId;
            }

            public void setDrugStoreId(String drugStoreId) {
                this.drugStoreId = drugStoreId;
            }

            public String getDrugStoreName() {
                return drugStoreName;
            }

            public void setDrugStoreName(String drugStoreName) {
                this.drugStoreName = drugStoreName;
            }

            public HandlerBean getHandler() {
                return handler;
            }

            public void setHandler(HandlerBean handler) {
                this.handler = handler;
            }

            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }

            public double getNormPrice() {
                return normPrice;
            }

            public void setNormPrice(double normPrice) {
                this.normPrice = normPrice;
            }

            public String getOtcId() {
                return otcId;
            }

            public void setOtcId(String otcId) {
                this.otcId = otcId;
            }

            public String getOtcName() {
                return otcName;
            }

            public void setOtcName(String otcName) {
                this.otcName = otcName;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSalesName() {
                return salesName;
            }

            public void setSalesName(String salesName) {
                this.salesName = salesName;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getStatusStore() {
                return statusStore;
            }

            public void setStatusStore(String statusStore) {
                this.statusStore = statusStore;
            }

            public String getStatusUp() {
                return statusUp;
            }

            public void setStatusUp(String statusUp) {
                this.statusUp = statusUp;
            }

            public double getUpPrice() {
                return upPrice;
            }

            public void setUpPrice(double upPrice) {
                this.upPrice = upPrice;
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

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public static class DimImageBean  implements Serializable{
                private long creTime;
                private String creUser;
                private int fileSize;
                private String fileType;
                private int hPixel;
                private String imageId;
                private String imagePath;
                private String origFileName;
                private String remark;
                private String thumbPath;
                private long updTime;
                private String updUser;
                private int vPixel;
                private boolean validFlag;

                public long getCreTime() {
                    return creTime;
                }

                public void setCreTime(long creTime) {
                    this.creTime = creTime;
                }

                public String getCreUser() {
                    return creUser;
                }

                public void setCreUser(String creUser) {
                    this.creUser = creUser;
                }

                public int getFileSize() {
                    return fileSize;
                }

                public void setFileSize(int fileSize) {
                    this.fileSize = fileSize;
                }

                public String getFileType() {
                    return fileType;
                }

                public void setFileType(String fileType) {
                    this.fileType = fileType;
                }

                public int getHPixel() {
                    return hPixel;
                }

                public void setHPixel(int hPixel) {
                    this.hPixel = hPixel;
                }

                public String getImageId() {
                    return imageId;
                }

                public void setImageId(String imageId) {
                    this.imageId = imageId;
                }

                public String getImagePath() {
                    return imagePath;
                }

                public void setImagePath(String imagePath) {
                    this.imagePath = imagePath;
                }

                public String getOrigFileName() {
                    return origFileName;
                }

                public void setOrigFileName(String origFileName) {
                    this.origFileName = origFileName;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getThumbPath() {
                    return thumbPath;
                }

                public void setThumbPath(String thumbPath) {
                    this.thumbPath = thumbPath;
                }

                public long getUpdTime() {
                    return updTime;
                }

                public void setUpdTime(long updTime) {
                    this.updTime = updTime;
                }

                public String getUpdUser() {
                    return updUser;
                }

                public void setUpdUser(String updUser) {
                    this.updUser = updUser;
                }

                public int getVPixel() {
                    return vPixel;
                }

                public void setVPixel(int vPixel) {
                    this.vPixel = vPixel;
                }

                public boolean isValidFlag() {
                    return validFlag;
                }

                public void setValidFlag(boolean validFlag) {
                    this.validFlag = validFlag;
                }
            }

            public static class DrDrugBaseBean  implements Serializable {
                private String approved;
                private String barcode;
                private String brand;
                private String brandId;
                private long creTime;
                private String creUser;
                private String domesticImports;
                private String drugCategory;
                private String drugForm;
                private String drugFormId;
                private String generalName;
                private String indications;
                private String insuranceId;
                private String listImageId;
                private String manufacturer;
                private String norms;
                private int otcId;
                private String otcName;
                private String packMaterial;
                private String skuId;
                private String specId;
                private String standardName;
                private long updTime;
                private String updUser;
                private boolean validFlag;
                private List<?> drImageCarouselList;
                private List<?> drImeageManualList;

                public String getApproved() {
                    return approved;
                }

                public void setApproved(String approved) {
                    this.approved = approved;
                }

                public String getBarcode() {
                    return barcode;
                }

                public void setBarcode(String barcode) {
                    this.barcode = barcode;
                }

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getBrandId() {
                    return brandId;
                }

                public void setBrandId(String brandId) {
                    this.brandId = brandId;
                }

                public long getCreTime() {
                    return creTime;
                }

                public void setCreTime(long creTime) {
                    this.creTime = creTime;
                }

                public String getCreUser() {
                    return creUser;
                }

                public void setCreUser(String creUser) {
                    this.creUser = creUser;
                }

                public String getDomesticImports() {
                    return domesticImports;
                }

                public void setDomesticImports(String domesticImports) {
                    this.domesticImports = domesticImports;
                }

                public String getDrugCategory() {
                    return drugCategory;
                }

                public void setDrugCategory(String drugCategory) {
                    this.drugCategory = drugCategory;
                }

                public String getDrugForm() {
                    return drugForm;
                }

                public void setDrugForm(String drugForm) {
                    this.drugForm = drugForm;
                }

                public String getDrugFormId() {
                    return drugFormId;
                }

                public void setDrugFormId(String drugFormId) {
                    this.drugFormId = drugFormId;
                }

                public String getGeneralName() {
                    return generalName;
                }

                public void setGeneralName(String generalName) {
                    this.generalName = generalName;
                }

                public String getIndications() {
                    return indications;
                }

                public void setIndications(String indications) {
                    this.indications = indications;
                }

                public String getInsuranceId() {
                    return insuranceId;
                }

                public void setInsuranceId(String insuranceId) {
                    this.insuranceId = insuranceId;
                }

                public String getListImageId() {
                    return listImageId;
                }

                public void setListImageId(String listImageId) {
                    this.listImageId = listImageId;
                }

                public String getManufacturer() {
                    return manufacturer;
                }

                public void setManufacturer(String manufacturer) {
                    this.manufacturer = manufacturer;
                }

                public String getNorms() {
                    return norms;
                }

                public void setNorms(String norms) {
                    this.norms = norms;
                }

                public int getOtcId() {
                    return otcId;
                }

                public void setOtcId(int otcId) {
                    this.otcId = otcId;
                }

                public String getOtcName() {
                    return otcName;
                }

                public void setOtcName(String otcName) {
                    this.otcName = otcName;
                }

                public String getPackMaterial() {
                    return packMaterial;
                }

                public void setPackMaterial(String packMaterial) {
                    this.packMaterial = packMaterial;
                }

                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public String getSpecId() {
                    return specId;
                }

                public void setSpecId(String specId) {
                    this.specId = specId;
                }

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }

                public long getUpdTime() {
                    return updTime;
                }

                public void setUpdTime(long updTime) {
                    this.updTime = updTime;
                }

                public String getUpdUser() {
                    return updUser;
                }

                public void setUpdUser(String updUser) {
                    this.updUser = updUser;
                }

                public boolean isValidFlag() {
                    return validFlag;
                }

                public void setValidFlag(boolean validFlag) {
                    this.validFlag = validFlag;
                }

                public List<?> getDrImageCarouselList() {
                    return drImageCarouselList;
                }

                public void setDrImageCarouselList(List<?> drImageCarouselList) {
                    this.drImageCarouselList = drImageCarouselList;
                }

                public List<?> getDrImeageManualList() {
                    return drImeageManualList;
                }

                public void setDrImeageManualList(List<?> drImeageManualList) {
                    this.drImeageManualList = drImeageManualList;
                }
            }

            public static class HandlerBean  implements Serializable {
            }
        }
    }
}

