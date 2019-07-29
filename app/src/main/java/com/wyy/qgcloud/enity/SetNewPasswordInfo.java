package com.wyy.qgcloud.enity;

public class SetNewPasswordInfo {


    /**
     * status : 成功返回true 错误返回false
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
