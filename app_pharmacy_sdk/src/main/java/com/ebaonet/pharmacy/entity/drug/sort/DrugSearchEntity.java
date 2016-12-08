package com.ebaonet.pharmacy.entity.drug.sort;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * Created by zhaojun.gao on 2016/10/14.
 */
public class DrugSearchEntity extends BaseEntity {

    /**
     * drugDsId : 5
     * imagePath : http://ebao-zsyd.ebaonet.cn/uploadimages/5.jpg
     * indications : 活血散瘀，消肿止痛，祛风除湿。用于跌打损伤，瘀血肿痛，风湿疼痛。
     * normPrice : 17.66
     * norms : 6.5cm*10cm*5片/盒
     * standardName : 云南白药膏
     * upPrice : 17
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String drugDsId;
        private String medicalInsuranceCode;

        public String getMedicalInsuranceCode() {
            return medicalInsuranceCode;
        }

        public void setMedicalInsuranceCode(String medicalInsuranceCode) {
            this.medicalInsuranceCode = medicalInsuranceCode;
        }

        private String imagePath;
        private String indications;
        private String normPrice;
        private String norms;
        private String standardName;
        private String  upPrice;

        public String getDrugDsId() {
            return drugDsId;
        }

        public void setDrugDsId(String drugDsId) {
            this.drugDsId = drugDsId;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getIndications() {
            return indications;
        }

        public void setIndications(String indications) {
            this.indications = indications;
        }

        public String getNormPrice() {
            return normPrice;
        }

        public void setNormPrice(String normPrice) {
            this.normPrice = normPrice;
        }

        public String getNorms() {
            return norms;
        }

        public void setNorms(String norms) {
            this.norms = norms;
        }

        public String getStandardName() {
            return standardName;
        }

        public void setStandardName(String standardName) {
            this.standardName = standardName;
        }

        public String getUpPrice() {
            return upPrice;
        }

        public void setUpPrice(String upPrice) {
            this.upPrice = upPrice;
        }
    }
}
