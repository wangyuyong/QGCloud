package com.wyy.qgcloud.net;

import com.wyy.qgcloud.enity.GroupAllInfo;
import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.GetEmailCodeInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;
import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.SetNewPasswordInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;

import java.util.List;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface HttpService {

    /**
     * 用户登录时输入邮箱后，用getEmailInfo发送网络请求，判断用户邮箱是否存在
     * @param email 用户邮箱
     * @return Observable<EmailInfo>
     */
    @FormUrlEncoded
    @POST("user/findUserEmail")
    Observable<EmailInfo> getEmailInfo(@Field("email")String email);

    /**
     * 用户输入所有信息后，用getLoginInfo发送网络请求，判断用户信息是否正确
     * @param email 登录邮箱
     * @param password 登录密码
     * @return Observable<LoginInfo>
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginInfo> getLoginInfo(@Field("email")String email,
                                       @Field("password")String password);

    /**
     * 用户注册时点击右侧框，发送网络请求获取验证码图片
     * @return Observable<ValidateCodeInfo>
     */
    @GET("user/requestCode")
    Observable<ValidateCodeInfo> getValidateCodeInfo();


    @Multipart
    @POST("user/register")
    Observable<RegisterInfo> getRegisterInfo(@Part List<MultipartBody.Part> partList);


    /**
     * 用户忘记密码时输入邮箱，发送请求以获得验证码
     * @param email
     * @return
     */
    @FormUrlEncoded
    @POST("user/forgetPassword")
    Observable<GetEmailCodeInfo> getEmailCodeInfo(@Field("email")String email);


    @FormUrlEncoded
    @POST("user/doForget")
    Observable<SetNewPasswordInfo> getNewPasswordInfo(@Field("code")String code,
                                                      @Field("email")String email,
                                                      @Field("password")String password);


    /**
     *用户登录后修改密码
     * @param userId  用户ID
     * @param oldPassword  用户旧密码
     * @param newPassword  用户新密码
     * @return  修改是否成功
     */
    @FormUrlEncoded
    @POST("/user/modifyPassword")
    Observable<ChangePasswordInfo> getChangePasswordInfo(@Field("userId")int userId,
                                                         @Field("oldPassword")String oldPassword,
                                                         @Field("newPassword")String newPassword);


    /**
     * 用户在个人信息页面修改信息（暂只有手机号）
     * @param userId
     * @param email
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("/user/updateUser")
    Observable<ChangePasswordInfo> getChangeMsgInfo(@Field("userId")int userId,
                                                    @Field("email")String email,
                                                    @Field("phone")String phone);

    /**
     * 一次性加载所有数据
     * @return
     */
    @FormUrlEncoded
    @GET("/user/findAll")
    Observable<GroupAllInfo> getGroupAllInfo();


    /**
     * 询问是否有对文件进行读或写的权限
     * @param userId 用户Id
     * @param fileId 文件Id
     * @param operation 操作类型
     * @param filePath 文件路径
     * @return
     */
    @FormUrlEncoded
    @POST(".")
    Observable<LoginInfo> getRWInfo(@Field("userId")String userId,
                                    @Field("fileId")String fileId,
                                    @Field("operation")String operation,
                                    @Field("filePath")String filePath);

    /**
     * 请求创建文件
     * @param userId 用户Id
     * @param filePath 文件全路径
     * @param fileName 文件夹名称
     * @return Observable<MakeDirInfo>
     */
    @FormUrlEncoded
    @POST("file/createFile")
    Observable<MakeDirInfo> getMakeDirInfo(@Field("userId")int userId,@Field("filePath")String filePath,@Field("fileName")String fileName);

    /**
     * 获取是否可以打开用户文件夹的信息
     * @param userId 用户ID
     * @param fileId 文件夹Id
     * @return Observable<FileInfo>
     */

    @GET("file/findAll")
    Observable<FileInfo> getFileInfi(@Query("userId") int userId,@Query("fileId") int fileId);
}

