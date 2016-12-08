package com.ebaonet.pharmacy.entity.drug.sort.level1;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 分类页面：一级分类实体类
 * Created by zhaojun.gao on 2016/8/19.
 */
public class CateleveloneInfo extends BaseEntity {
    private String cateId;//分类ID
    private String cateName;//分类名字 中西药品
    private String level;//
    private List<SonCateInfos> sonCateInfos;//
    public List<SonCateInfos> getSonCateInfos() {
        return sonCateInfos;
    }

    public void setSonCateInfos(List<SonCateInfos> sonCateInfos) {
        this.sonCateInfos = sonCateInfos;
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

   

    public static class SonCateInfos{
        public boolean isChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }

        private boolean isChecked;
        private String cateId;//分类ID
        private String cateName;//分类名字 中西药品
        private String level;//分类等级
        /*private ArrayList<> sonCateInfos;//返回的都是[]*/
        private String sonLength;//子分类长度

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

        public String getSonLength() {
            return sonLength;
        }

        public void setSonLength(String sonLength) {
            this.sonLength = sonLength;
        }
    }
}
