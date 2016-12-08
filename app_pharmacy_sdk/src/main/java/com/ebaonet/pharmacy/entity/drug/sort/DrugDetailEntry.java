package com.ebaonet.pharmacy.entity.drug.sort;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * Created by peng.dong on 2016/10/14.
 */
public class DrugDetailEntry extends BaseEntity{

    /**
     * creTime : 
     * creUser : 
     * dimImage : null
     * displayName : 欧姆龙 电子血压计 一台/盒
     * drDrugBase : null
     * drugCode : 
     * drugDsId : 27
     * drugId : 
     * drugNum : 0
     * drugStoreId : 
     * drugStoreName : 
     * handler : {}
     * images : [{"creTime":1473062320000,"creUser":"扎西德勒","fileSize":2780,"fileType":"image/jpeg","hPixel":0,"imageId":"1a39f66c3019496b878bc997a8c8ff78","imagePath":"http://ebao-zsyd.ebaonet.cn/uploadimages/4.jpg","origFileName":"4.jpg","remark":"","thumbPath":"http://ebao-zsyd.ebaonet.cn/uploadimages/4.jpg","updTime":1473062320000,"updUser":"扎西德勒","vPixel":0,"validFlag":true}]
     * normPrice : 258.0
     * otcId : 1
     * otcName : 甲
     * rate : 0
     * remark : 
     * salesName : 
     * skuId : 265324
     * statusStore : 
     * statusUp : 
     * upPrice : 258.0
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

    public static class DataBean {
        private String creTime;
        private String creUser;
        private Object dimImage;
        private String displayName;
        private Object drDrugBase;
        private String drugCode;
        private String drugDsId;
        private String drugId;
        private String manufacturer;
        private int drugNum;
        private String drugStoreId;
        private String drugStoreName;
        private String medicalInsuranceCode;
        private String normPrice;
        private String otcId;
        private String otcName;
        private int rate;
        private String remark;
        private String salesName;
        private String skuId;
        private String statusStore;
        private String statusUp;
        private String upPrice;
        private String updTime;
        private String updUser;

        public String getMedicalInsuranceCode() {
            return medicalInsuranceCode;
        }

        public void setMedicalInsuranceCode(String medicalInsuranceCode) {
            this.medicalInsuranceCode = medicalInsuranceCode;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        /**
         * creTime : 1473062320000
         * creUser : 扎西德勒
         * fileSize : 2780
         * fileType : image/jpeg
         * hPixel : 0
         * imageId : 1a39f66c3019496b878bc997a8c8ff78
         * imagePath : http://ebao-zsyd.ebaonet.cn/uploadimages/4.jpg
         * origFileName : 4.jpg
         * remark : 
         * thumbPath : http://ebao-zsyd.ebaonet.cn/uploadimages/4.jpg
         * updTime : 1473062320000
         * updUser : 扎西德勒
         * vPixel : 0
         * validFlag : true
         */
        
        private List<ImagesBean> images;

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

        public Object getDimImage() {
            return dimImage;
        }

        public void setDimImage(Object dimImage) {
            this.dimImage = dimImage;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Object getDrDrugBase() {
            return drDrugBase;
        }

        public void setDrDrugBase(Object drDrugBase) {
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

        public String getNormPrice() {
            return normPrice;
        }

        public void setNormPrice(String normPrice) {
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

        public String getUpPrice() {
            return upPrice;
        }

        public void setUpPrice(String upPrice) {
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

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
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
    }
}
