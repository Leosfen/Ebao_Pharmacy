package com.ebaonet.pharmacy.entity.order;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 订单进度实体类
 * Created by zhaojun.gao on 2016/10/13.
 */
public class OrderProgressEntity extends BaseEntity {

    /**
     * creTime : 2016-09-12 14:43:23.0
     * handler : {}
     * logs : [{"delFlag":0,"operInfo":"订单已分配到百氏康杨寨店(不支持)，请等待分店确认。","operTime":"2016-09-23 10:36:34","operUser":"1","orderId":"1","orderOperLogId":"8d565604b63e4899b192316e0be4731e","orderOperStatus":"2","orderStatusName":"已分配分店","remark":"","sort":2,"storeName":"","userName":"超级管理员"},{"delFlag":0,"operInfo":"订单分店已确认，订单已经开始生产。","operTime":"2016-09-21 14:44:15","operUser":"14","orderId":"1","orderOperLogId":"72ae90a2fc204509b582f4086e6b3920","orderOperStatus":"3","orderStatusName":"分店已确认","remark":"","sort":3,"storeName":"百氏康东苑店(支持)","userName":"分店用户"},{"delFlag":0,"operInfo":"订单已经拣货完成，已由百氏康东苑店(支持)出库待取货。联系电话：[0371-23322091]","operTime":"2016-09-22 11:16:44","operUser":"14","orderId":"1","orderOperLogId":"3f9a58987d1145b48b12a7c000e50eb2","orderOperStatus":"4","orderStatusName":"待收货","remark":"","sort":4,"storeName":"百氏康东苑店(支持)","userName":"分店用户"},{"delFlag":0,"operInfo":"订单已收货完成。","operTime":"2016-09-22 11:29:03","operUser":"14","orderId":"1","orderOperLogId":"e17448f04d4c40009587372e121f6041","orderOperStatus":"5","orderStatusName":"已完成","remark":"","sort":5,"storeName":"百氏康东苑店(支持)","userName":"分店用户"},{"delFlag":0,"operInfo":"订单已取消，原因：其他原因(客户打电话沟通客户去出差了不在家所以不想要了经过核实并非出差而是恶意vdshsdklglsdhlshdlghsdagds；矮冬瓜烦得很地方好地方干豆腐花多少等会等会的山东省等身份和东方红豆腐花多少多少等方式地方很多等方式等身份和对方水电费水电费山东省房价等方式等方式或第三方斯蒂芬或多或少都会多少度等身份和对方说对方说多少等身份和第三方和东方红松岛枫)","operTime":"2016-09-21 15:06:48","operUser":"14","orderId":"1","orderOperLogId":"9dc91fc5a52a4058bbb2314cab5ee5b3","orderOperStatus":"6","orderStatusName":"已取消","remark":"","sort":6,"storeName":"百氏康东苑店(支持)","userName":"分店用户"}]
     * orderCode : bsk20160919141267
     * orderId : 1
     * orderStatus : 2
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String creTime;
        private String orderCode;
        private String orderId;
        private String orderStatus;
        /**
         * delFlag : 0
         * operInfo : 订单已分配到百氏康杨寨店(不支持)，请等待分店确认。
         * operTime : 2016-09-23 10:36:34
         * operUser : 1
         * orderId : 1
         * orderOperLogId : 8d565604b63e4899b192316e0be4731e
         * orderOperStatus : 2
         * orderStatusName : 已分配分店
         * remark :
         * sort : 2
         * storeName :
         * userName : 超级管理员
         */

        private List<LogsBean> logs;

        public String getCreTime() {
            return creTime;
        }

        public void setCreTime(String creTime) {
            this.creTime = creTime;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public List<LogsBean> getLogs() {
            return logs;
        }

        public void setLogs(List<LogsBean> logs) {
            this.logs = logs;
        }

        public static class LogsBean {
            private int delFlag;
            private String operInfo;
            private String operTime;
            private String operUser;
            private String orderId;
            private String orderOperLogId;
            private String orderOperStatus;
            private String orderStatusName;
            private String remark;
            private int sort;
            private String storeName;
            private String userName;
            
            //新增字段
            private String orderPrompt;

            public String getOrderPrompt() {
                return orderPrompt;
            }

            public void setOrderPrompt(String orderPrompt) {
                this.orderPrompt = orderPrompt;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getOperInfo() {
                return operInfo;
            }

            public void setOperInfo(String operInfo) {
                this.operInfo = operInfo;
            }

            public String getOperTime() {
                return operTime;
            }

            public void setOperTime(String operTime) {
                this.operTime = operTime;
            }

            public String getOperUser() {
                return operUser;
            }

            public void setOperUser(String operUser) {
                this.operUser = operUser;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getOrderOperLogId() {
                return orderOperLogId;
            }

            public void setOrderOperLogId(String orderOperLogId) {
                this.orderOperLogId = orderOperLogId;
            }

            public String getOrderOperStatus() {
                return orderOperStatus;
            }

            public void setOrderOperStatus(String orderOperStatus) {
                this.orderOperStatus = orderOperStatus;
            }

            public String getOrderStatusName() {
                return orderStatusName;
            }

            public void setOrderStatusName(String orderStatusName) {
                this.orderStatusName = orderStatusName;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
