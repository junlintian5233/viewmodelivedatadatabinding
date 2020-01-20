package com.junzhitian.basicapplib.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:
 */
public abstract class BaseRetrofitClient {

    /**
     * 初始化Retrofit
     *
     * @param baseUrl
     * @return
     */
    public Retrofit initRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        //支持返回Call<String>
        builder.addConverterFactory(ScalarsConverterFactory.create());
        //支持直接格式化json返回Bean对象
        builder.addConverterFactory(GsonConverterFactory.create());
        //支持RxJava
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        //baseUrl总是以/结束，@URL不要以/开头
        builder.baseUrl(baseUrl);
        OkHttpClient client = createOkHttpClient();
        if (client != null) {
            builder.client(client);
        }
        return builder.build();
    }

    /**
     * 设置OkHttpClient，添加拦截器等
     *
     * @return 可以返回为null
     */
    public abstract OkHttpClient createOkHttpClient();
}
