package com.wyy.qgcloud.net;

import com.google.gson.Gson;
import com.wyy.qgcloud.constant.Api;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 线程安全的单例类,用于请求网络
 */
public class RetrofitManager {

    private Retrofit retrofit;
    private HttpService service;

    private static class ManagerHolder{
        public final static RetrofitManager manager = new RetrofitManager();
    }

    private RetrofitManager(){
        //创建okHttpCilent
        OkHttpClient cilent = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<String,List<Cookie>> cookieStore = new HashMap<>();
                    @Override
                    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                        cookieStore.put(httpUrl.host(),list);
                    }

                    @NotNull
                    @Override
                    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .writeTimeout(8000,TimeUnit.MILLISECONDS)
                .readTimeout(8000,TimeUnit.MILLISECONDS)
                .build();

        //创建Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.CONST_BASE_URL)
                .client(cilent)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    /**
     * 获取网络管理的manager
     * @return 该单例类
     */
    public static RetrofitManager getInstance(){
        return ManagerHolder.manager;
    }

    /**
     * 获取访问http的service
     * @return HttpService
     */
    public HttpService getHttpService() {
        if (service == null){
            service = retrofit.create(HttpService.class);
        }

        return service;
    }
}
