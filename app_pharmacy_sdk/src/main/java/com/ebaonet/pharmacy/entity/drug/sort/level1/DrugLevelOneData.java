package com.ebaonet.pharmacy.entity.drug.sort.level1;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * Created by zhaojun.gao on 2016/8/19.
 */
public class DrugLevelOneData extends BaseEntity {

    /*private CateleveloneInfo data;

    public CateleveloneInfo getData() {
        return data;
    }

    public void setData(CateleveloneInfo data) {
        this.data = data;
    }*/
    private List<SonCateInfos> data;

    public List<SonCateInfos> getData() {
        return data;
    }

    public void setData(List<SonCateInfos> data) {
        this.data = data;
    }

    public static class SonCateInfos {
        private boolean isChecked;

        public String getCateImageId() {
            return cateImageId;
        }

        public void setCateImageId(String cateImageId) {
            this.cateImageId = cateImageId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        private String cateId;//分类ID
        private String cateImageId;
        private String cateName;//分类名字 中西药品
        private String level;//分类等级
        /*private List<sonCateInfos2> sonCateInfos2;//返回的都是[]*/
        private String parentId;
        private String sonLength;//子分类长度

        public String getSonLength() {
            return sonLength;
        }

        public void setSonLength(String sonLength) {
            this.sonLength = sonLength;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}
