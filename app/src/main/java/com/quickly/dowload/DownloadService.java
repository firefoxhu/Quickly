package com.quickly.dowload;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.quickly.MainActivity;
import com.quickly.R;
import java.io.File;
import static android.app.Notification.VISIBILITY_SECRET;

public class DownloadService extends Service {

    // 下载 一步操作类
    private DownloadFileTask downloadFileTask;

    // 下载地址
    private String downloadUrl;

    // 下载状态的回调
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("正在下载",progress));
        }

        @Override
        public void onSuccess() {
            downloadFileTask = null;
            //下载 成功是将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载成功",-1));
            Toast.makeText(DownloadService.this, "下载成功", Toast.LENGTH_SHORT).show();

            // TODO 调用自动安装宝 安装   或者点击 下载完成的通知也安装


        }

        @Override
        public void onFailed() {
            downloadFileTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载失败！",-1));
            Toast.makeText(DownloadService.this, "下载失败!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadFileTask = null;
            Toast.makeText(DownloadService.this, "下载暂停", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadFileTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "下载取消", Toast.LENGTH_SHORT).show();

        }
    };


    private DownloadBinder mBinder = new DownloadBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    public class DownloadBinder extends Binder {

        // 开始下载
        public void startDownload(String url) {
            if (downloadFileTask == null) {
                downloadUrl = url;
                downloadFileTask = new DownloadFileTask(listener);
                downloadFileTask.execute(downloadUrl);
                startForeground(1, getNotification("正在获取数据中...", 0));
                Toast.makeText(DownloadService.this, "Downloading", Toast.LENGTH_SHORT).show();
            }
        }

        // 暂停下载
        public void pauseDownload() {
            if (downloadFileTask != null) {
                downloadFileTask.pauseDownload();
            }
        }

        // 取消下载
        public void cancelDownload() {
            if (downloadFileTask != null) {
                downloadFileTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
                    // 先暂停后取消   取消下载时需将文件删除，并通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }








    /**
     * 获取通知栏管理器
     *
     * @return
     */
    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }



    /**
     * 设置通知栏的样式 并获取通知栏的实例
     *
     * @param title
     * @param progress
     * @return
     */
    private Notification getNotification(String title, int progress) {


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id","channel_name",NotificationManager.IMPORTANCE_DEFAULT);

            channel.canBypassDnd();// 可否绕过请勿打扰模式

            channel.enableLights(true);// 呼吸灯

            channel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏通知

            channel.setLightColor(Color.RED); // 灯光颜色
            channel.canShowBadge();//桌面ic_luncer显示角标
            channel.enableVibration(true); //震动
            channel.getAudioAttributes();// 获取声音
            channel.getGroup();// 获取通知渠道
            channel.setBypassDnd(true); // 可绕过勿打扰
            channel.setVibrationPattern(new long[]{100,100,200});//震动的模式
            channel.shouldShowLights();///是否出现灯光

            getNotificationManager().createNotificationChannel(channel);
        }

        Intent[] intents = new Intent[]{(new Intent(this, MainActivity.class))};
        PendingIntent pi = PendingIntent.getActivities(this, 0, intents, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setAutoCancel(true);
        builder.setChannelId("channel_id");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }



        return builder.build();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
