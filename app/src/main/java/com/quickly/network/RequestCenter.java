package com.quickly.network;

import com.quickly.entity.dto.CategoryItem;
import com.quickly.entity.dto.NewsArticleItem;
import com.quickly.okhttp.CommonOkHttpClient;
import com.quickly.okhttp.listener.DisposeDataHandle;
import com.quickly.okhttp.listener.DisposeDataListener;
import com.quickly.okhttp.request.CommonRequest;
import com.quickly.okhttp.request.RequestParams;

public class RequestCenter {

    //根据参数发送所有的get请求
    private static void getRequest(String url, RequestParams params,DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest(url, params),new DisposeDataHandle(listener,clazz));
    }


    private static void postRequest(String url,RequestParams params,DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.sendRequest(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(listener,clazz));
    }




    /**
     * 真正的发送我们首页的请求 咨询类别
     * @param listener
     */
    public static void categoryList(DisposeDataListener listener){
        RequestCenter.getRequest(HttpConstants.CATEGORY_LIST,null,listener, CategoryItem.class);
    }



    /**
     * 真正的发送我们首页的请求 咨询类别
     * @param listener
     */
    public static void recommendList(DisposeDataListener listener){
        RequestCenter.getRequest(HttpConstants.RECOMMEND_LIST,null,listener, NewsArticleItem.class);
    }

}
