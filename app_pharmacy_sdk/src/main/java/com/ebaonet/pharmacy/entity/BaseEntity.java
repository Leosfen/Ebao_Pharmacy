/**
 *
 */
package com.ebaonet.pharmacy.entity;

import java.io.Serializable;

/**
 * 基本实体，包括最基本的接口返回数据
 *
 * @author xf.guan
 */
public class BaseEntity implements Serializable {

    private int code;
    private String message;
    private long timestamp;

    public BaseEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
