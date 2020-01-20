package com.junzhitian.viewmodelivedatadatabinding.http;


import com.junzhitian.basicapplib.http.BaseRetrofitClient;

import okhttp3.OkHttpClient;

public class Api extends BaseRetrofitClient {

    @Override
    public OkHttpClient createOkHttpClient() {
        return null;
    }

    /**
     * 静态内部类单例
     */
    private static class ApiHolder {
        private static       Api        api              = new Api();
        private final static ApiService apiService       = api.initRetrofit(ApiService.BASE_URL)
                .create(ApiService.class);
        private final static ApiService JueJinApiService = api.initRetrofit(ApiService.JUE_JIN_BASE_URL)
                .create(ApiService.class);
    }

    public static ApiService getInstance() {
        return ApiHolder.apiService;
    }

    public static ApiService getJueJinInstance() {
        return ApiHolder.JueJinApiService;
    }


}
