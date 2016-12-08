package com.ebaonet.pharmacy.request;

/**
 * Created by yao.feng on 2016/8/19.
 */
public class PharmacyUrlConst {

//    public static final String PHARMACY_USER_ID = "gzj";

    public static final String TEST_URL = "http://10.10.10.187:9001/pharmacies-api";

    public static final String BASE_URL = TEST_URL;

    public static final String DRUG_LEVEL_ONE = BASE_URL + "/cate/catelevelone.htm";

    public static final String DRUG_LEVEL_TWO = BASE_URL + "/cate/cateleveltwo.htm";

    public static final String DRUG_LEVEL_THREE = BASE_URL + "/drug/druglevelthree.htm";

    public static final String DRUG_SEARCH = BASE_URL + "/drug/fulltextsearch.htm";

    public static final String DRUG_ADDCARTITEM = BASE_URL + "/cart/addcartitem.htm";

    public static final String DRUG_FILTER_CONDITIONS = BASE_URL + "/filter/drugFilterConditions.htm";

    public static final String GET_DRUG_DETAIL = BASE_URL + "/drug/drugDetail.htm";

    public static final String GET_DRUG_INFO = BASE_URL + "?????";

    public static final String ADDRESS_LIST = BASE_URL + "/address/list.htm";

    public static final String SAVE_ADDRESS = BASE_URL + "/address/saveAddress.htm";

    public static final String SAVE_DEFAULT_ADDRESS = BASE_URL + "/address/saveDefaultAddr.htm";

    public static final String DELETE_ADDRESS = BASE_URL + "/address/delAddress.htm";

    public static final String GET_CART = BASE_URL + "/cart/findCartItemList.htm";

    public static final String CHANGE_CART_ITEM = BASE_URL + "/cart/modifycartitemquantity.htm";

    public static final String DELETE_CART_ITEM = BASE_URL + "/cart/batchremovecartitem.htm";

    public static final String COMMIT_CART = BASE_URL + "/cart/calculate.htm";

    public static final String CREATE_ORDER = BASE_URL + "/order/createOrder.htm";

    public static final String SUBMIT_ORDER = BASE_URL + "/order/submitOrders.htm";

    public static final String DELETE_ORDER = BASE_URL + "/order/delete.htm";

    public static final String ORDER_PROGRESS = BASE_URL + "/order/queryOrderProgress.htm";

    public static final String CHANGE_DELIVERY = BASE_URL + "/order/changeDelivery.htm";

    public static final String CONFIGINFO = BASE_URL + "/config/list.htm";

    public static final String GET_ORDER_MANAGER_LIST = BASE_URL + "/order/queryOrder.htm";

    public static final String GET_ORDER_DOING_COUNT = BASE_URL + "/order/queryOrderCount.htm";

    public static final String INDEXINFO = BASE_URL + "/homePage/query.htm";

    public static final String DRUG_ACITVITY_LIST = BASE_URL + "/drug/drugAcvityList.htm";

    public static final String GET_DRUG_DISP = BASE_URL + "/drug/drugDisp.htm";

    public static final String GET_QUANTITY = BASE_URL + "/cart/findquantity.htm";

}
