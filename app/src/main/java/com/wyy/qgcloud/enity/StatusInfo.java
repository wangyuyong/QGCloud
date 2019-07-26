package com.wyy.qgcloud.enity;

public class StatusInfo {

    /**
     * status : 登录状态
     * message : 失败的原因，成功则为null
     * data : 返回null
     */

    private String status;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
