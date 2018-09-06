package com.quickly.fragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.quickly.BannerDetailActivity;
import com.quickly.R;
import com.quickly.adapter.HomeBannerViewHolder;
import com.quickly.adapter.HomeCategoryAdapter;
import com.quickly.adapter.HomeRecommendAdapter;
import com.quickly.application.SyApplication;
import com.quickly.entity.dto.CategoryItem;
import com.quickly.entity.dto.NewsArticleItem;
import com.quickly.fragment.base.BaseFragment;
import com.quickly.listener.OnItemClickListener;
import com.quickly.network.RequestCenter;
import com.quickly.okhttp.listener.DisposeDataListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.app.Notification.VISIBILITY_SECRET;

public class HomeFragment extends BaseFragment {


    private MZBannerView homeBanner;
    private List<String> bannerLists = new ArrayList<>();

    private RecyclerView homeCategory;

    private RecyclerView homeRecommend;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        homeBanner = getmRootView().findViewById(R.id.home_banner);

        homeCategory = getmRootView().findViewById(R.id.home_category);

        homeRecommend = getmRootView().findViewById(R.id.home_recommend);



        initData();

        initView();

    }


    public  void initView(){

        if(homeBanner  != null){

            //设置BannerView 的切换时间间隔
            homeBanner.setDelayedTime(3000);

            //设置ViewPager（Banner）切换速度
            homeBanner.setDuration(800);

            homeBanner.setBannerPageClickListener((view,position)->{


                Intent intent = new Intent(getActivity(),BannerDetailActivity.class);

                intent.putExtra("key","value");
                // 启动模式
                    //singleTop
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // singleTask

                startActivity(intent);
            });

            // 设置数据
            homeBanner.setPages(bannerLists, ()->new HomeBannerViewHolder());

        }

        if(homeCategory != null) {

            homeCategory.setLayoutManager(
                    new GridLayoutManager(getActivity(),4)
            );

        }




        if(homeRecommend != null){
            homeRecommend.setLayoutManager(
                    new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false)
            );


        }




    }


    public void initData(){

        bannerLists.add("http://image.luosen365.com/016e8bbee88a4ce0bf9daca6cc3261e4.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_01.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_02.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_03.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_04.jpg");


        RequestCenter.categoryList(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                homeCategory.setAdapter(
                        new HomeCategoryAdapter(getActivity(),((CategoryItem)responseObj).getData().getList())
                );
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i("GETs", "onSuccess: "+reasonObj.toString());
            }
        });


        RequestCenter.recommendList(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                HomeRecommendAdapter homeRecommendAdapter = new HomeRecommendAdapter(getActivity(), ((NewsArticleItem) responseObj).getData().getList());

                homeRecommend.setAdapter(homeRecommendAdapter);

                homeCategory.addView(homeRecommend);
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i("GETs", "onFailure: "+reasonObj.toString());
            }
        });








    }




    @Override
    public void onPause() {
        super.onPause();
        if(homeBanner != null){
            homeBanner.pause();//暂停轮播
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(homeBanner != null){
            homeBanner.start();//开始轮播
        }
    }

}
