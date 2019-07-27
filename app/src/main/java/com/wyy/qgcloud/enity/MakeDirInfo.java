package com.wyy.qgcloud.enity;

/**
 * 创建文件夹返回的信息
 */
public class MakeDirInfo {
    /**
     * status : 成功为true 失败为false
     * message : 失败返回失败的原因，成功返回null
     * data : {"fileId":"创建的文件夹ID"}
     */

    private String status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fileId : 创建的文件夹ID
         */

        private int fileId;

        public int getFileId() {
            return fileId;
        }

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }
    }
}
