package com.ebaonet.pharmacy.entity.shoppingcar;

import com.ebaonet.pharmacy.entity.BaseEntity;

/**
 * Created by peng.dong on 2016/8/30.
 */
public class DrugQuantity extends BaseEntity {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int quantity;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
