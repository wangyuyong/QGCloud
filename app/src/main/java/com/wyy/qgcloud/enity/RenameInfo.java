package com.wyy.qgcloud.enity;

public class RenameInfo {

    /**
     * status : 重命名成功为 true 失败为false
     * message : 失败返回失败的原因 成功返回null
     * data : 返回null
     */

    private boolean status;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
