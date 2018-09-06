package com.quickly;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import static android.app.Notification.VISIBILITY_SECRET;

public class NotificationOManager {

    private Context mContext;

    private static NotificationManager mNotificationManager;


    private static NotificationOManager mNotificationOManager;

    private NotificationOManager(Context context){

        mContext = context;
        mNotificationManager =(NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

    }



    public static NotificationOManager getInstance(Context context){


        if(mNotificationOManager == null){

            synchronized (NotificationManager.class) {

                if(mNotificationOManager == null){

                    mNotificationOManager = new NotificationOManager(context);
                }

            }
        }

        return mNotificationOManager;

    }





    public Notification.Builder getNotification(){
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

            mNotificationManager.createNotificationChannel(channel);
        }

        return new Notification.Builder(mContext)
                .setAutoCancel(true)
                .setChannelId("channel_id")
                .setContentTitle("新消息")
                .setContentText("明天 是走日")
                .setSmallIcon(R.mipmap.ic_launcher);
    }



}
