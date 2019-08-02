package com.wyy.qgcloud.enity;

public class MemberChangeInfo {


    /**
     * status : true 加入成功  false 加入失败
     * message : 加入结果的声明
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
