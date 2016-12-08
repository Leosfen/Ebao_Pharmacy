package com.ebaonet.pharmacy.entity.order.orderlist;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yao.feng on 2016/10/13.
 */
public class OrderDrugInfo implements Serializable {
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
     * creTime : 1473822221000
     * creUser : 超级管理员
     * fileSize : 780831
     * fileType : image/pjpeg
     * hPixel : 0
     * imageId : f4f0e55d8c7a4d649a51309fe0df784b
     * imagePath : http://ebao-zsyd.ebaonet.cn/uploadimages/Koala.jpg
     * origFileName : Koala.jpg
     * remark :
     * thumbPath : http://ebao-zsyd.ebaonet.cn/uploadimages/Koala.jpg
     * updTime : 1473822221000
     * updUser : 超级管理员
     * vPixel : 0
     * validFlag : true
     */

    private DimImageBean dimImage;
    private String displayName;
    /**
     * approved : 国药准字H41021856
     * barcode : 69**********9
     * brand : 天士力
     * brandId : BRAND_KEY27
     * creTime : 1474942000000
     * creUser :
     * domesticImports : 国产
     * drImageCarouselList : []
     * drImeageManualList : []
     * drugCategory :
     * drugForm : 水丸
     * drugFormId : FORM_KEY4
     * generalName : 天士力
     * indications : 用于治疗胸闷气短理气化瘀
     * insuranceId : 甲
     * listImageId : f4f0e55d8c7a4d649a51309fe0df784b
     * manufacturer : 天津天士力现代中药集团
     * norms : 60粒/瓶
     * otcId : 1
     * otcName : 甲
     * packMaterial : 空
     * skuId : 186325
     * specId :
     * standardName : 丹参滴丸
     * updTime : 1474942000000
     * updUser : 1
     * validFlag : true
     */

    private DrDrugBaseBean drDrugBase;
    private String drugCode;
    private String drugDsId;
    private String drugId;
    private int drugNum;
    private String drugStoreId;
    private String drugStoreName;
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

    public static class DimImageBean implements Serializable {
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

    public static class DrDrugBaseBean implements Serializable {
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
}
