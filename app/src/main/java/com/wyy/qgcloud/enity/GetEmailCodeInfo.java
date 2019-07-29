package com.wyy.qgcloud.enity;

public class GetEmailCodeInfo {


    /**
     * status : false 表示处理失败 不存在这个邮箱  true表示发送邮箱成功
     * message : 失败的原因
     * data : null
     */

    private boolean status;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
