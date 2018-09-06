package com.quickly.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.quickly.R;
import com.quickly.imageloader.ImageLoaderManager;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public  class HomeBannerViewHolder implements MZViewHolder<String> {

        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner_item,null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String url) {
            // 数据绑定
            ImageLoaderManager.getInstance(context).displayImage(mImageView,url);
        }
    }