package com.quickly.okhttp;

import com.quickly.okhttp.https.HttpsUtils;
import com.quickly.okhttp.listener.DisposeDataHandle;
import com.quickly.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 请求发送，请求参数配置， https支持
 */
public class CommonOkHttpClient {



    private static final int TIME_OUT = 30; //超时时间

    private static OkHttpClient mOkHttpClient;


    static {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        //设置超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        //允许服务器重定向或者转发
        okHttpBuilder.followRedirects(true);
        //okHttpBuilder.followSslRedirects(true);

        //https 支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });


        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(),HttpsUtils.initTrustManager());

        //生成Client对象
        mOkHttpClient = okHttpBuilder.build();


    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param handle
     * @return Call对象
     */
    public static Call sendRequest(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }


    public static OkHttpClient getInstancce(){
        return mOkHttpClient;
    }

}
