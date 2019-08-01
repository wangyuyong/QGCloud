package com.wyy.qgcloud.enity;

public class UploadFileMessage {

    private String uploadFile;

    private String fileName;

    private String fileUploadTime;

    private int userId;

    private String filePath;

    public UploadFileMessage(){}

    public UploadFileMessage(String uploadFile, String fileName, String fileUploadTime, int userId,String filePath) {
        this.uploadFile = uploadFile;
        this.fileName = fileName;
        this.userId = userId;
        this.filePath = filePath;
        this.fileUploadTime = fileUploadTime;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUploadTime() {
        return fileUploadTime;
    }

    public void setFileUploadTime(String fileUploadTime) {
        this.fileUploadTime = fileUploadTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
