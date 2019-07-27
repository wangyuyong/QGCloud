package com.wyy.qgcloud.enity;

/**
 * 用户输入邮箱后返回的信息
 */
public class EmailInfo {

    /**
     * status : 用户输入的邮箱
     * message : 客户端处理
     */

    private boolean status;
    private String message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
