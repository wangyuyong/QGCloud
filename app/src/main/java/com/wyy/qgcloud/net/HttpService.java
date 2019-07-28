package com.wyy.qgcloud.net;

import android.content.Context;

import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;
import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;

import java.io.File;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpService {

    /**
     * 用户登录时输入邮箱后，用getEmailInfo发送网络请求，判断用户邮箱是否存在
     * @param email 用户邮箱
     * @return Observable<EmailInfo>
     */
    @FormUrlEncoded
    @POST(".")
    Observable<EmailInfo> getEmailInfo(@Field("email")String email);

    /**
     * 用户输入所有信息后，用getLoginInfo发送网络请求，判断用户信息是否正确
     * @param email 登录邮箱
     * @param password 登录密码
     * @return Observable<LoginInfo>
     */
    @FormUrlEncoded
    @POST(".")
    Observable<LoginInfo> getLoginInfo(@Field("email")String email, @Field("password")String password);

    /**
     * 用户注册时点击右侧框，发送网络请求获取验证码图片
     * @return Observable<ValidateCodeInfo>
     */
    @FormUrlEncoded
    @POST(".")
    Observable<ValidateCodeInfo> getValidateCodeInfo();

    /**
     * 用户输入所有信息后点击进行注册，发送请求获取数据
     *
     * @param context
     * @param email  注册邮箱
     * @param password 注册密码
     * @param icon 用户头像
     * @param userName 用户姓名
     * @param phone 用户手机号
     * @param code 注册验证码
     * @return Observable<RegisterInfo>
     */
    @FormUrlEncoded
    @POST(".")
    Observable<RegisterInfo> getRegisterInfo(Context context, @Field("email") String email,
                                             @Field("password") String password,
                                             @Field("icon") File icon,
                                             @Field("userName") String userName,
                                             @Field("phone") String phone,
                                             @Field("code") String code);



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
    @POST(".")
    Observable<MakeDirInfo> getMakeDirInfo(@Field("userId")int userId,@Field("filePath")String filePath,@Field("fileName")String fileName);
}
