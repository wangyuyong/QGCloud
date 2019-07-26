package com.wyy.qgcloud.net;

import com.wyy.qgcloud.enity.StatusInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpService {

    /**
     *
     * @param email 登录邮箱
     * @param password 登录密码
     * @param code 验证码
     * @return rxjava的被观察者，携带MessageInfo实体类
     */
    @FormUrlEncoded
    @POST(".")
    Observable<StatusInfo> getLoginInfo(@Field("email")String email, @Field("password")String password, @Field("code")String code);
}
