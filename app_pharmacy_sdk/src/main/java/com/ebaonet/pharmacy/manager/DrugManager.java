package com.ebaonet.pharmacy.manager;

import com.ebaonet.pharmacy.entity.drug.sort.DrugDetailEntry;
import com.ebaonet.pharmacy.entity.drug.sort.DrugDispEntry;
import com.ebaonet.pharmacy.entity.drug.sort.DrugSearchEntity;
import com.ebaonet.pharmacy.entity.drug.sort.filter.DrugFilterCondition;
import com.ebaonet.pharmacy.entity.drug.sort.level1.DrugLevelOneData;
import com.ebaonet.pharmacy.entity.drug.sort.level2.DrugLevelTwoData;
import com.ebaonet.pharmacy.entity.drug.sort.level3.DrugLevelThreeList;
import com.ebaonet.pharmacy.entity.drug.sort.level3.DrugTotalQuantity;
import com.ebaonet.pharmacy.entity.shoppingcar.DrugQuantity;
import com.ebaonet.pharmacy.manager.abs.AbsDrug;
import com.ebaonet.pharmacy.manager.config.DrugConfig;
import com.ebaonet.pharmacy.request.PharmacyUrlConst;
import com.ebaonet.pharmacy.request.RequestManager;
import com.ebaonet.pharmacy.request.params.RequestParams;

/**
 * Created by zhaojun.gao on 2016/8/19.
 */
public class DrugManager extends AbsDrug {


    private DrugManager() {
    }

    private static DrugManager mInstance;

    public static DrugManager getInstance() {
        if (mInstance == null) {
            mInstance = new DrugManager();
        }
        return mInstance;
    }

    /***
     * 获取分类页面二级分类列表
     */
    @Override
    public void getCatelevelone() {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_LEVEL_ONE, DrugConfig.GETCATELEVELONE, null,
                this, DrugLevelOneData.class);
    }

    /***
     * 获取分类页面二级分类列表
     */
    @Override
    public void getCateleveltwo(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_LEVEL_TWO, DrugConfig.GETCATELEVELTWO, params,
                this, DrugLevelTwoData.class);
    }

    /**
     * 获取三级分类-药品列表
     *
     * @param params
     */
    public void getDruglevelthree(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_LEVEL_THREE, DrugConfig.GETDRUGLEVELTHREE, params,
                this, DrugLevelThreeList.class);
    }

    /**
     * 药品关键字
     *
     * @param params
     */
    public void getDrugSearch(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_SEARCH, DrugConfig.GETDRUGSEARCH, params,
                this, DrugSearchEntity.class);
    }

    /**
     * 药品列表--点击提交需求（向购物车内添加药品）
     *
     * @param params
     */
    public void addCartitem(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_ADDCARTITEM, DrugConfig.ADDCARTITEM, params,
                this, DrugTotalQuantity.class);
    }

    /**
     * 药品详情--获取药品详情信息
     *
     * @param params
     */
    @Override
    public void getDrugInfo(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.GET_DRUG_INFO, DrugConfig.GET_DRUG_INFO, params,
                this, DrugTotalQuantity.class);
    }

    @Override
    public void getDrugDetail(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.GET_DRUG_DETAIL, DrugConfig.GET_DRUG_DETAIL, params,
                this, DrugDetailEntry.class);
    }

    /**
     * 药品列表筛选条件查询
     *
     * @param params
     */
    @Override
    public void getDrugFilterConditions(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_FILTER_CONDITIONS, DrugConfig.GET_DRUG_FILTER_CONDITIONS,
                params, this, DrugFilterCondition.class);
    }

    /**
     * 药品活动列表查询
     *
     * @param params
     */
    @Override
    public void getDrugAcvityList(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.DRUG_ACITVITY_LIST, DrugConfig.GET_DRUG_ACTIVITY_LIST,
                params, this, DrugSearchEntity.class);
    }

    /**
     * 药品说明书查询
     *
     * @param params
     */
    @Override
    public void getDrugDisp(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.GET_DRUG_DISP, DrugConfig.GET_DRUG_DISP, params,
                this, DrugDispEntry.class);
    }

    /**
     * 购物车内药品条目数量查询
     *
     * @param params
     */
    @Override
    public void getQuantity(RequestParams params) {
        startCallBack();
        RequestManager.post(PharmacyUrlConst.GET_QUANTITY, DrugConfig.GET_QUANTITY, params,
                this, DrugQuantity.class);
    }
}
