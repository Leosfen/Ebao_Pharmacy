package com.ebaonet.pharmacy.entity.order;

import com.ebaonet.pharmacy.entity.BaseEntity;

/**
 * Created by peng.dong on 2016/10/11.
 */
public class SubmitOrderEntry extends BaseEntity {


    /**
     * orderCode : bsk20160919141267
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String orderCode;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }
    }
}
