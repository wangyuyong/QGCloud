package com.wyy.qgcloud.enity;

public class FilePathMessge {
    /**
     * 用户Id
     */
    private int userId;

    /**
     * 文件全路径
     */
    private String filePath;

    public FilePathMessge(int userId, String filePath) {
        this.userId = userId;
        this.filePath = filePath;
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
