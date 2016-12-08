package com.ebaonet.pharmacy.entity.order.orderlist;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * Created by yao.feng on 2016/10/13.
 */
public class OrderManagerListInfo extends BaseEntity {


    /**
     * addrId : fc18b40822774a29acf259b07b67c529
     * address : {"addr":"交道口南15号院3层","addrId":"fc18b40822774a29acf259b07b67c529","biotopeId":"","biotopeName":"交道口东街15号","cityId":"","cityName":"开封市","creTime":1476170192000,"districtId":"410288","districtName":"龙亭区","latitude":"114.752410","longitude":"34.751236","pGendId":false,"provId":"","provName":"河南省","receiveName":"sdghs","receivePhone":"18752366698","statusDefault":false,"tmUserId":"123","updTime":1476170192000,"zoneId":"","zoneName":""}
     * creTime : 2016-10-11 14:48:17.0
     * creUser : 303
     * delFlag : 0
     * deliveryDesc : 
     * deliveryType : 1
     * drug : [{"creTime":"2016-09-27 10:24:47.0","creUser":"1","dimImage":{"creTime":1473822221000,"creUser":"超级管理员","fileSize":780831,"fileType":"image/pjpeg","hPixel":0,"imageId":"f4f0e55d8c7a4d649a51309fe0df784b","imagePath":"http://ebao-zsyd.ebaonet.cn/uploadimages/Koala.jpg","origFileName":"Koala.jpg","remark":"","thumbPath":"http://ebao-zsyd.ebaonet.cn/uploadimages/Koala.jpg","updTime":1473822221000,"updUser":"超级管理员","vPixel":0,"validFlag":true},"displayName":"天津天士力 丹参滴丸 60粒/瓶","drDrugBase":{"approved":"国药准字H41021856","barcode":"69**********9","brand":"天士力","brandId":"BRAND_KEY27","creTime":1474942000000,"creUser":"","domesticImports":"国产","drImageCarouselList":[],"drImeageManualList":[],"drugCategory":"","drugForm":"水丸","drugFormId":"FORM_KEY4","generalName":"天士力","indications":"用于治疗胸闷气短理气化瘀","insuranceId":"甲","listImageId":"f4f0e55d8c7a4d649a51309fe0df784b","manufacturer":"天津天士力现代中药集团","norms":"60粒/瓶","otcId":1,"otcName":"甲","packMaterial":"空","skuId":"186325","specId":"","standardName":"丹参滴丸","updTime":1474942000000,"updUser":"1","validFlag":true},"drugCode":"186325","drugDsId":"18","drugId":"G695286","drugNum":1,"drugStoreId":"1","drugStoreName":"开封百氏康大药店","handler":{},"images":[],"normPrice":45.6,"otcId":"","otcName":"","rate":0,"remark":"","salesName":"","skuId":"186325","statusStore":"","statusUp":"1","upPrice":45.6,"updTime":"2016-09-27 15:43:26.0","updUser":"13"}]
     * drugStoreId : 1
     * drugSubStoreId : 04f74e70581a4951928ea2c74be8d850
     * handler : {}
     * invoice : {"delFlag":0,"invoiceContent":"","invoiceHead":"刘燕燕","invoiceId":"1","invoiceType":1,"remark":"","sort":1}
     * invoiceFlag : 1
     * invoiceId : 1
     * logs : []
     * normalPrice : 22.8
     * orderCode : bsk20161011236582
     * orderFreight : 6.0
     * orderId : 14
     * orderPrice : 16.5
     * orderStatus : 2
     * orderStatusDesc : 
     * preferentialPrice : 0
     * reason : 
     * remark : []
     * sort : 0
     * updTime : 2016-10-11 14:48:17.0
     * updUser : 13
     */

    private List<OrderManagerInfo> data;

    public List<OrderManagerInfo> getData() {
        return data;
    }

    public void setData(List<OrderManagerInfo> data) {
        this.data = data;
    }

    
}
