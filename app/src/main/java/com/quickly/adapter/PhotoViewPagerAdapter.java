package com.quickly.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.quickly.imageloader.ImageLoaderManager;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {

    private List<String> mPhotoUrls;

    private Context mContext;

    private ImageLoaderManager imageLoader;


    public PhotoViewPagerAdapter(List<String> mPhotoUrls, Context mContext) {
        this.mPhotoUrls = mPhotoUrls;
        this.mContext = mContext;

        this.imageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mPhotoUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        ImageView photo = new PhotoView(mContext);

        imageLoader.displayImage(photo,mPhotoUrls.get(position));

        container.addView(photo,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);


        return photo;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
