package com.ebaonet.pharmacy.entity.config;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * Created by peng.dong on 2016/10/10.
 */
public class PharmacyConfig extends BaseEntity {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : 1
         * label : 上门自提
         */

        private List<DeliveryTypeBean> deliveryType;
        /**
         * value : 1
         * label : 个人
         */

        private List<InvoiceTypeBean> invoiceType;

        public List<DeliveryTypeBean> getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(List<DeliveryTypeBean> deliveryType) {
            this.deliveryType = deliveryType;
        }

        public List<InvoiceTypeBean> getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(List<InvoiceTypeBean> invoiceType) {
            this.invoiceType = invoiceType;
        }

        public static class DeliveryTypeBean {
            private String value;
            private String label;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }

        public static class InvoiceTypeBean {
            private String value;
            private String label;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }
}
