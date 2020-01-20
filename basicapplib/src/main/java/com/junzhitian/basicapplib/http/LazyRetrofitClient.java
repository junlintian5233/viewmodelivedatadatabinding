package com.junzhitian.basicapplib.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:
 */
public class LazyRetrofitClient extends BaseRetrofitClient {
    @Override
    public OkHttpClient createOkHttpClient() {

        //log拦截器  打印所有的log
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("X-HB-Client-Type", "ayb-android")
                        .addHeader("Content-Type", "multipart/form-data; boundary=7db372eb000e2")
                        .addHeader("Accept", "*/*")
                        .build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)// 15秒连接超时
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build();
        return client;
    }
}
