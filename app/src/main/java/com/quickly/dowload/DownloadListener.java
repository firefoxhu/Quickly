package com.quickly.dowload;

/**
 * https://blog.csdn.net/MagicMHD/article/details/80861333
 */
public interface DownloadListener {

    // 通知下载进度
    void onProgress(int progress);


    // 通知下载成功
    void onSuccess();


    // 通知下载失败
    void onFailed();


    // 通知下载暂停
    void onPaused();


    //通知下载失败
    void onCanceled();
}