package com.wyy.qgcloud.enity;

public class RegisterInfo {


    /**
     * status : 注册的状态
     * message : 失败的原因，注册成功返回为null
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
