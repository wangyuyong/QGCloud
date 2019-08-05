package com.wyy.qgcloud.net;

import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.FileValidInfo;
import com.wyy.qgcloud.enity.GetEmailCodeInfo;
import com.wyy.qgcloud.enity.MemberChangeInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;
import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.RenameInfo;
import com.wyy.qgcloud.enity.SetNewPasswordInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;

import java.util.List;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

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
     * 用户注册时发送网络请求获取验证码图片
     * @return Observable<ValidateCodeInfo>
     */
    @GET("user/requestCode")
    Observable<ValidateCodeInfo> getValidateCodeInfo();


    /**
     * 用户进行注册
     * @param partList
     * @return
     */
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


    /**
     * 用户忘记密码重新设置
     * @param code
     * @param email
     * @param password
     * @return
     */
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
    @POST("user/modifyPassword")
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
    @POST("user/updateUser")
    Observable<ChangePasswordInfo> getChangeMsgInfo(@Field("userId")int userId,
                                                    @Field("email")String email,
                                                    @Field("phone")String phone);

    /**
     * 一次性加载通讯录所有数据
     * @return
     */
    @GET("user/findAll")
    Observable<GroupInfo> getGroupInfo();


    /**
     * 在通讯录界面对用户进行分组
     * @param userId
     * @param toUserId
     * @param groupId
     * @return
     */
    @FormUrlEncoded
    @POST("group/joinGroup")
    Observable<MemberChangeInfo> getJoinGroupInfo(@Field("userId")int userId,
                                                  @Field("toUserId")int toUserId,
                                                  @Field("groupId")int groupId);


    /**
     * 通讯录界面修改用户身份
     * @param userId
     * @param toUserId
     * @param role
     * @return
     */
    @FormUrlEncoded
    @POST("user/setUser")
    Observable<MemberChangeInfo> getUserPositionInfo(@Field("userId")int userId,
                                                     @Field("toUserId")int toUserId,
                                                     @Field("role")int role);


    /**
     * 用户针对某个文件对某个用户赋予某种权限
     * @param userId 执行修改权限用户的ID
     * @param fileId  文件ID
     * @param authority  权限类型
     * @param told  被赋予权限的用户的ID
     * @return  设置是否成功
     */
    @FormUrlEncoded
    @POST("file/updateAuthority")
    Observable<ChangePasswordInfo> getUpdateAuthorityInfo(@Field("userId")int userId,
                                                          @Field("fileId")int fileId,
                                                          @Field("authority")int authority,
                                                          @Field("told")int told);

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


    /**
     * 重新命名文件夹
     * @param userId 用户Id
     * @param fileId 文件Id
     * @param fileName 文件名
     * @return Observable<RenameInfo>
     */
    @FormUrlEncoded
    @POST("file/updateFile")
    Observable<RenameInfo> getRenameInfo(@Field("userId")int userId,@Field("fileId")int fileId,@Field("fileName")String fileName);


    /**
     * 上传文件夹
     * @param parts 文件
     * @return Observable<FileValidInfo>
     */

    @Multipart
    @POST("file/upload")
    Observable<FileValidInfo> uploadFile(@Header("Range")String length,@Part List<MultipartBody.Part> parts);


    /**
     * 删除文件
     * @param userId 用户Id
     * @param fileId 文件Id
     * @return Observable<FileValidInfo>
     */
    @FormUrlEncoded
    @POST("file/delete")
    Observable<FileValidInfo> deleteFile(@Field("userId")int userId,@Field("fileId")int fileId);
}

