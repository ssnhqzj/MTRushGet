package com.qzj.rush.mt.http;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 为每个请求添加固定的Header
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        /*if (UserUtils.getUser().getToken() != null) {
            builder.addHeader("X-Access-Token", UserUtils.getUser().getToken());
        }*/
        return chain.proceed(builder.build());
    }

}
