package com.ebaonet.pharmacy.entity.drug.sort.level2;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 分类页面：二级分类实体类
 * Created by zhaojun.gao on 2016/8/21.
 */
public class CateleveltwoInfo extends BaseEntity {
    private String cateId;//分类ID
    private String cateImageId;
    private String cateName;//分类名字 中西药品
    private String level;//
    private String parentId;
    private List<SonCateInfos> sonCateInfos;//里面存放三级列表里的数据

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateImageId() {
        return cateImageId;
    }

    public void setCateImageId(String cateImageId) {
        this.cateImageId = cateImageId;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<SonCateInfos> getSonCateInfos() {
        return sonCateInfos;
    }

    public void setSonCateInfos(List<SonCateInfos> sonCateInfos) {
        this.sonCateInfos = sonCateInfos;
    }

    public static class SonCateInfos implements Serializable {
        private String thumbPath;
        private String cateId;//分类ID

        public String getThumbPath() {
            return thumbPath;
        }

        public void setThumbPath(String thumbPath) {
            this.thumbPath = thumbPath;
        }

        private String cateImageId;
        private String cateName;//分类名字 中药1
        /* private String sonLength;//子分类长度 0*/
        private String level;//分类等级 3
        private String parentId;
       /* private ArrayList<> sonCateInfos;*///目前返回的是空,因为没有四级列表了


        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getCateImageId() {
            return cateImageId;
        }

        public void setCateImageId(String cateImageId) {
            this.cateImageId = cateImageId;
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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
