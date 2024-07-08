package com.example.mvc.base;


import com.example.mvc.api.Api;
import com.example.mvc.utils.HttpsUtils;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.BuildConfig;

/**
 * Description: 初始化请求工具类
 */
public class BaseRetrofit {

    private static volatile BaseRetrofit br;
    private static OkHttpClient.Builder Builder = new OkHttpClient.Builder();
    public  static OkHttpClient client;

    public static BaseRetrofit getInstance() {
        if (br == null) {
            synchronized (BaseRetrofit.class) {
                if (br == null) {
                    br = new BaseRetrofit();
                    client =  getOkhttp();
                }
            }
        }
        return br;
    }

    public Api getObservable(final BaseApi bastApi) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(bastApi.getBUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Api observable = bastApi.getObservable(retrofit);
        return observable;
    }

    public  static OkHttpClient getOkhttp() {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        /**
         * Log信息拦截器
         */
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            Builder.addInterceptor(loggingInterceptor);
        }

        /**
         *设置cookie
         */
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        Builder.cookieJar(new JavaNetCookieJar(cookieManager));

        //手动创建一个OkHttpClient并设置超时时间
        Builder.connectTimeout(BaseApi.CONNECTTIMEOUT, TimeUnit.SECONDS);
        Builder.writeTimeout(BaseApi.WRITETIMEOUT, TimeUnit.SECONDS);
        Builder.readTimeout(BaseApi.READTIMEOUT, TimeUnit.SECONDS);
        /**
         * 错误重连
         */
        Builder.retryOnConnectionFailure(true);

        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        Builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        OkHttpClient build = Builder.build();

        return build;
    }
}
