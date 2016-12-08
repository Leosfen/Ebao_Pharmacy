package com.ebaonet.pharmacy.entity.config;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 配置信息查询
 * Created by zhaojun.gao on 2016/10/11.
 */
public class ConfigInfo extends BaseEntity {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String freightFreePrice;

        public String getFreightFreePrice() {
            return freightFreePrice;
        }

        public void setFreightFreePrice(String freightFreePrice) {
            this.freightFreePrice = freightFreePrice;
        }

        private List<DeliveryTypeBean> deliveryType;//配送方式列表
        private List<InvoiceTypeBean> invoiceType;//发票类型列表
        private List<ServiceTelBean> serviceTel;//服务电话


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

        public List<ServiceTelBean> getServiceTel() {
            return serviceTel;
        }

        public void setServiceTel(List<ServiceTelBean> serviceTel) {
            this.serviceTel = serviceTel;
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
        public static class ServiceTelBean {
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
