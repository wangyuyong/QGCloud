package com.wyy.qgcloud.enity;

/**
 * 用户输入邮箱后返回的信息
 */
public class EmailInfo {

    /**
     * status : 用户输入的邮箱
     * message : 客户端处理
     */

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
