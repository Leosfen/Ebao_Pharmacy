package com.ebaonet.pharmacy.entity.order;

import com.ebaonet.pharmacy.entity.BaseEntity;

/**
 * Created by peng.dong on 2016/10/11.
 */
public class DeliveryEntity extends BaseEntity {

    /**
     * orderPrice : 23.89
     * orderFreight  : 5.0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String orderPrice;
        private String orderFreight;

        public String getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getOrderFreight() {
            return orderFreight;
        }

        public void setOrderFreight(String orderFreight) {
            this.orderFreight = orderFreight;
        }
    }
}
