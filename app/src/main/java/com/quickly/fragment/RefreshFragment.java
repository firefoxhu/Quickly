package com.quickly.fragment;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.quickly.MainActivity;
import com.quickly.R;
import com.quickly.fragment.base.BaseFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import static android.app.Notification.VISIBILITY_SECRET;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefreshFragment extends BaseFragment {


    private NotificationManager manager;


    public RefreshFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_refresh;
    }

    @Override
    public void init(Bundle savedInstanceState) {


        final Toolbar toolbar = getmRootView().findViewById(R.id.toolbar);


        RefreshLayout refreshLayout =getmRootView().findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "加载跟多", Toast.LENGTH_SHORT).show();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });



        //状态栏透明和间距处理
        //状态栏透明和间距处理
//        StatusBarUtil.immersive(getActivity());
//        StatusBarUtil.setPaddingSmart(getActivity(), toolbar);

        toolbar.setBackgroundColor(Color.BLUE);


        manager =(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        //send1();

        send2();

    }




    private void send2(){
        Notification.Builder builder = getNotification2();


        RemoteViews remoteViews = new RemoteViews(getActivity().getPackageName(),R.layout.notification_download_layout);

        remoteViews.setImageViewResource(R.id.img,R.mipmap.home_banner_02);

        remoteViews.setTextViewText(R.id.txt,"这个是自定义的通知栏布局");

        // 即将发生 可以取消 更新
        PendingIntent pendingIntent =PendingIntent.getActivity(getActivity(),-1,new Intent(getActivity(), MainActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.notifi_btn,pendingIntent);

        builder.setCustomContentView(remoteViews);

        manager.notify(3,builder.build());
    }


    private void send1(){
        Notification.Builder builder = getNotification2();

        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);




        manager.notify(2,builder.build());


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100 ; i++){
                    try {
                        Thread.sleep(1000);

                        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
                        builder.setProgress(100,i,false);

                        manager.notify(2,builder.build());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }


    private Notification.Builder  getNotification2(){

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

            manager.createNotificationChannel(channel);
        }

        return new Notification.Builder(getActivity())
                .setAutoCancel(true)
                .setChannelId("channel_id")
                .setContentTitle("新消息")
                .setContentText("明天 是走日")
                .setSmallIcon(R.mipmap.ic_launcher);


    }




    private Notification  getNotification1(){

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

            manager.createNotificationChannel(channel);
        }

        return new Notification.Builder(getActivity())
                .setAutoCancel(true)
                .setChannelId("channel_id")
                .setContentTitle("新消息")
                .setContentText("明天 是走日")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();


    }
}
