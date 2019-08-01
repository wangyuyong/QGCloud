package com.wyy.qgcloud.enity;

public class FileMessage {

    private String fileName;

    private String fileUploadTime;

    private int fileId;

    private int userId;

    public FileMessage(){

    }

    public FileMessage(String fileName, String fileUploadTime, int fileId, int userId) {
        this.fileName = fileName;
        this.fileUploadTime = fileUploadTime;
        this.fileId = fileId;
        this.userId = userId;
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

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
