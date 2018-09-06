package com.quickly.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.quickly.R;
import com.quickly.adapter.PhotoViewPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager photoPager;

    private CircleIndicator photoIndicator;


    public static final String PHOTO_LIST = "photo_list";


    // TODO  用这个优化内存  启动模式改为singleTop
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_layout);

        photoPager = findViewById(R.id.photo_view_pager);

        photoIndicator = findViewById(R.id.photo_view_pager_indicator);

        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(getIntent().getStringArrayListExtra(PHOTO_LIST),PhotoViewActivity.this);
        photoPager.setAdapter(photoViewPagerAdapter);
        photoViewPagerAdapter.registerDataSetObserver(photoIndicator.getDataSetObserver());
        photoIndicator.setViewPager(photoPager);

    }
}
