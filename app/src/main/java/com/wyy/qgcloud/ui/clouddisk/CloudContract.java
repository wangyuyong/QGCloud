package com.wyy.qgcloud.ui.clouddisk;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;

import java.util.List;

import io.reactivex.Observable;

public interface CloudContract {
    interface CloudView extends BaseView{

        /**
         * 进入文件夹
         * @param fileList 文件列表
         */
        void updataDir(List<FileInfo.DataBean> fileList);

        /**
         * 展示错误信息
         * @param message 错误信息
         */
        void showError(String message);

        /**
         * 新建一个文件夹
         * @param dir 文件夹信息
         */
        void makeDir(FileInfo.DataBean dir);

        /**
         * 隐藏返回键
         */
        void hideHomePage();

        /**
         * 显示返回按钮
         */
        void showHomePage();
    }

    interface CloudPresent extends BasePresent<CloudView>{

        /**
         * 返回根目录
         * @param userId 用户Id
         */
        void displayRoot(int userId);

        /**
         * 打开文件夹
         * @param userId 用户Id
         * @param position 文件夹位置
         */
        void openDir(int userId,int position);

        /**
         * 创建文件夹
         * @param userId 用户Id
         * @param fileName 文件名称
         */
        void makeDir(int userId,String fileName);

        /**
         * 返回上一级目录
         * @param userId 用户Id
         */
        void back(int userId);
    }

    interface CloudModel{
        /**
         * 请求打开文件夹
         * @param userId 用户Id
         * @param fileId 文件夹Id
         */
        Observable<FileInfo> requestOpenDir(int userId, int fileId);

        /**
         * 请求打开文件夹
         * @param userId 用户Id
         * @param filePath 用户路径
         * @param fileName 文件名称
         * @return Observable<MakeDirInfo>
         */
        Observable<MakeDirInfo> requestMakeDir(int userId,String filePath,String fileName);
    }
}
