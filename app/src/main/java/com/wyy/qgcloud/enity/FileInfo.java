package com.wyy.qgcloud.enity;

import java.util.List;

/**
 * 查看文件夹返回的信息，status为权限状态
 */
public class FileInfo {

    private String status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fileId : 文件id
         * fileName : 文件名
         * fileType : 文件类型
         * filePath : 文件路径(全路径)
         * uploadTime : 上传时间
         * userName : 所属用户的名称
         * downloadCount : 文件下载次数
         * level : 文件夹(目录)等级
         * folderId : 文件夹父目录
         */

        private int fileId;
        private String fileName;
        private String fileType;
        private String filePath;
        private String uploadTime;
        private String userName;
        private int downloadCount;
        private int level;
        private int folderId;

        public int getFileId() {
            return fileId;
        }

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(String uploadTime) {
            this.uploadTime = uploadTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getDownloadCount() {
            return downloadCount;
        }

        public void setDownloadCount(int downloadCount) {
            this.downloadCount = downloadCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getFolderId() {
            return folderId;
        }

        public void setFolderId(int folderId) {
            this.folderId = folderId;
        }
    }
}
