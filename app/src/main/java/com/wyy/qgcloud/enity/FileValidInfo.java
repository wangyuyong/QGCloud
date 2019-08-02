package com.wyy.qgcloud.enity;

public class FileValidInfo {


    /**
     * status : 上传的状态
     * message : 上传失败的原因  上传成功 返回null
     * data : 返回 null
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
