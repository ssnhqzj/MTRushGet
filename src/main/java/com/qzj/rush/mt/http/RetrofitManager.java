package com.qzj.rush.mt.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * Retrofit管理器
 */
public class RetrofitManager {

    public static Retrofit retrofit = null;
    public static Retrofit httpsRetrofit = null;

    /**
     * 初始化Retrofit
     */
    private static void init(String host) {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new HeaderInterceptor())
                            .readTimeout(30, TimeUnit.SECONDS)
                            .connectTimeout(10,TimeUnit.SECONDS)
                            .writeTimeout(30,TimeUnit.SECONDS)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(host)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                 }
            }
        }
    }

    /**
     * 初始化https Retrofit
     */
    private static void initHttps(String host) {
        if (httpsRetrofit == null) {
            synchronized (RetrofitManager.class) {
                if (httpsRetrofit == null) {

                    // Create a trust manager that does not validate certificate chains
                    final TrustManager[] trustAllCerts = new TrustManager[] {
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                                }

                                @Override
                                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                                }

                                @Override
                                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                    return new java.security.cert.X509Certificate[]{};
                                }
                            }
                    };

                    try {
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        // Create an ssl socket factory with our all-trusting manager
                        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .readTimeout(30, TimeUnit.SECONDS)
                                .sslSocketFactory(sslSocketFactory)
                                .hostnameVerifier(new HostnameVerifier() {
                                    @Override
                                    public boolean verify(String hostname, SSLSession session) {
                                        return true;
                                    }
                                })
                                .build();

                        httpsRetrofit = new Retrofit.Builder()
                                .baseUrl(host)
                                .client(okHttpClient)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                    } catch (NoSuchAlgorithmException |KeyManagementException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获取Api调用接口
     */
    public static <T> T getApiService(String host, Class<T> clazz) {
        if (retrofit == null) {
            init(host);
        }

        return  retrofit.create(clazz);
    }

    /**
     * 获取https Api调用接口
     */
    public static <T> T getHttpsApiService(String host, Class<T> clazz) {
        if (httpsRetrofit == null) {
            initHttps(host);
        }

        return  httpsRetrofit.create(clazz);
    }

}
