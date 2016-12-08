package com.ebaonet.pharmacy.entity.index;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 首页数据实体类
 * Created by zhaojun.gao on 2016/10/21.
 */
public class IndexInfoEntity extends BaseEntity {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RollImageConfigBean> rollImageConfigList;
        private List<ClassesConfigBean> classesConfigList;
        private List<SpecailConfigBean> specailConfigList;
        private List<GundongConfigBean> gundongConfigList;
        private List<SalesConfigBean> salesConfigList;
        private List<RecommendConfigBean> recommendConfigList;

        public List<RecommendConfigBean> getRecommendConfigList() {
            return recommendConfigList;
        }

        public void setRecommendConfigList(List<RecommendConfigBean> recommendConfigList) {
            this.recommendConfigList = recommendConfigList;
        }

        public List<ClassesConfigBean> getClassesConfigList() {
            return classesConfigList;
        }

        public void setClassesConfigList(List<ClassesConfigBean> classesConfigList) {
            this.classesConfigList = classesConfigList;
        }

        public List<SpecailConfigBean> getSpecailConfigList() {
            return specailConfigList;
        }

        public void setSpecailConfigList(List<SpecailConfigBean> specailConfigList) {
            this.specailConfigList = specailConfigList;
        }

        public List<GundongConfigBean> getGundongConfigList() {
            return gundongConfigList;
        }

        public void setGundongConfigList(List<GundongConfigBean> gundongConfigList) {
            this.gundongConfigList = gundongConfigList;
        }

        public List<SalesConfigBean> getSalesConfigList() {
            return salesConfigList;
        }

        public void setSalesConfigList(List<SalesConfigBean> salesConfigList) {
            this.salesConfigList = salesConfigList;
        }

        public List<RollImageConfigBean> getRollImageConfigList() {
            return rollImageConfigList;
        }

        public void setRollImageConfigList(List<RollImageConfigBean> rollImageConfigList) {
            this.rollImageConfigList = rollImageConfigList;
        }

        public static class RollImageConfigBean {
            private String imagePath;
            private String jumpType;
            private String jumpTypeVal;
            private String configId;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public String getJumpType() {
                return jumpType;
            }

            public void setJumpType(String jumpType) {
                this.jumpType = jumpType;
            }

            public String getJumpTypeVal() {
                return jumpTypeVal;
            }

            public void setJumpTypeVal(String jumpTypeVal) {
                this.jumpTypeVal = jumpTypeVal;
            }

            public String getConfigId() {
                return configId;
            }

            public void setConfigId(String configId) {
                this.configId = configId;
            }


        }

        public static class ClassesConfigBean {
            private String imagePath;
            private String jumpType;
            private String jumpTypeVal;
            private String configId;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }


            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public String getJumpType() {
                return jumpType;
            }

            public void setJumpType(String jumpType) {
                this.jumpType = jumpType;
            }
            public String getJumpTypeVal() {
                return jumpTypeVal;
            }

            public void setJumpTypeVal(String jumpTypeVal) {
                this.jumpTypeVal = jumpTypeVal;
            }

            public String getConfigId() {
                return configId;
            }

            public void setConfigId(String configId) {
                this.configId = configId;
            }
        }

        public static class SpecailConfigBean {
            private String imagePath;
            private String jumpType;
            private String jumpTypeVal;
            private String configId;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public String getJumpType() {
                return jumpType;
            }

            public void setJumpType(String jumpType) {
                this.jumpType = jumpType;
            }

            public String getJumpTypeVal() {
                return jumpTypeVal;
            }

            public void setJumpTypeVal(String jumpTypeVal) {
                this.jumpTypeVal = jumpTypeVal;
            }

            public String getConfigId() {
                return configId;
            }

            public void setConfigId(String configId) {
                this.configId = configId;
            }
        }

        public static class GundongConfigBean {
            private String imagePath;
            private String jumpType;
            private String jumpTypeVal;
            private String configId;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public String getJumpType() {
                return jumpType;
            }

            public void setJumpType(String jumpType) {
                this.jumpType = jumpType;
            }

            public String getJumpTypeVal() {
                return jumpTypeVal;
            }

            public void setJumpTypeVal(String jumpTypeVal) {
                this.jumpTypeVal = jumpTypeVal;
            }

            public String getConfigId() {
                return configId;
            }

            public void setConfigId(String configId) {
                this.configId = configId;
            }
        }

        public static class SalesConfigBean {
            private String imagePath;
            private String prSowId;
            private List<DrugDsBean> drugDsList;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public String getPrSowId() {
                return prSowId;
            }

            public void setPrSowId(String prSowId) {
                this.prSowId = prSowId;
            }

            public List<DrugDsBean> getDrugDsList() {
                return drugDsList;
            }

            public void setDrugDsList(List<DrugDsBean> drugDsList) {
                this.drugDsList = drugDsList;
            }

            public static class DrugDsBean {
                private String medicalInsuranceCode;
                private String drugDsId;
                private String imagePath;
                private String standardName;
                private String norms;
                private String indications;
                private String upPrice;
                private String normPrice;
                
                public String getMedicalInsuranceCode() {
                    return medicalInsuranceCode;
                }

                public void setMedicalInsuranceCode(String medicalInsuranceCode) {
                    this.medicalInsuranceCode = medicalInsuranceCode;
                }

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

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }

                public String getNorms() {
                    return norms;
                }

                public void setNorms(String norms) {
                    this.norms = norms;
                }

                public String getIndications() {
                    return indications;
                }

                public void setIndications(String indications) {
                    this.indications = indications;
                }

                public String getUpPrice() {
                    return upPrice;
                }

                public void setUpPrice(String upPrice) {
                    this.upPrice = upPrice;
                }

                public String getNormPrice() {
                    return normPrice;
                }

                public void setNormPrice(String normPrice) {
                    this.normPrice = normPrice;
                }
            }
        }

        public static class RecommendConfigBean {
            private String drugDsId;
            private String imagePath;
            private String standardName;
            private String norms;
            private String indications;
            private String upPrice;
            private String normPrice;
            private String medicalInsuranceCode;

            public String getMedicalInsuranceCode() {
                return medicalInsuranceCode;
            }

            public void setMedicalInsuranceCode(String medicalInsuranceCode) {
                this.medicalInsuranceCode = medicalInsuranceCode;
            }

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

            public String getStandardName() {
                return standardName;
            }

            public void setStandardName(String standardName) {
                this.standardName = standardName;
            }

            public String getNorms() {
                return norms;
            }

            public void setNorms(String norms) {
                this.norms = norms;
            }

            public String getIndications() {
                return indications;
            }

            public void setIndications(String indications) {
                this.indications = indications;
            }

            public String getUpPrice() {
                return upPrice;
            }

            public void setUpPrice(String upPrice) {
                this.upPrice = upPrice;
            }

            public String getNormPrice() {
                return normPrice;
            }

            public void setNormPrice(String normPrice) {
                this.normPrice = normPrice;
            }
        }

    }

}
