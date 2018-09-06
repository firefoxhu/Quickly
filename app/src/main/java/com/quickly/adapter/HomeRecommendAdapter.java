package com.quickly.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quickly.R;
import com.quickly.activity.PhotoViewActivity;
import com.quickly.entity.dto.CategoryItem;
import com.quickly.entity.dto.NewsArticleItem;
import com.quickly.imageloader.ImageLoaderManager;
import com.quickly.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.HomeRecommendViewHolder>{

    private List<NewsArticleItem.DataBean.ListBean> mItemList;

    private Context mContent;

    private ImageLoaderManager imageLoaderManager;

    private LayoutInflater mInflate;



    public HomeRecommendAdapter(Context context, List<NewsArticleItem.DataBean.ListBean> list){
        mContent = context;
        mItemList = list;
        imageLoaderManager =ImageLoaderManager.getInstance(mContent);
        mInflate = LayoutInflater.from(mContent);
    }


    @NonNull
    @Override
    public HomeRecommendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //  可以动态切花多个布局方式
        View inflate = mInflate.inflate(R.layout.home_recommend_item, viewGroup, false);
        return new HomeRecommendViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecommendViewHolder holder, int i) {

        NewsArticleItem.DataBean.ListBean item = mItemList.get(i);

        Log.i("onBindViewHolder", "onBindViewHolder: "+item.getImages().get(0));

        imageLoaderManager.displayImage(holder.recommendImg,item.getImages().get(0));




        //holder.recommendImg.setImageResource(R.mipmap.ic_launcher);
        holder.recommendTitle.setText(item.getTitle());
        holder.recommendOrigin.setText(item.getResource());
        holder.recommendViews.setText(item.getViews());
        holder.recommendZan.setText(item.getFabulous());

        holder.recommendImg.setOnClickListener(v->{

            Intent intent = new Intent(mContent, PhotoViewActivity.class);
            intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_LIST, (ArrayList<String>) item.getImages());
            mContent.startActivity(intent);
        });

        holder.itemView.setOnClickListener(v->{

        });
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class HomeRecommendViewHolder extends RecyclerView.ViewHolder {


        private ImageView recommendImg;

        private TextView recommendTitle;

        private TextView recommendOrigin;

        private TextView  recommendViews;

        private TextView recommendZan;


        public HomeRecommendViewHolder(View view){

            super(view);

            recommendImg  = view.findViewById(R.id.recommend_img);

            recommendTitle = view.findViewById(R.id.recommend_title);

            recommendOrigin = view.findViewById(R.id.recommend_origin);

            recommendViews = view.findViewById(R.id.recommend_views);

            recommendZan = view.findViewById(R.id.recommend_zan);

        }
    }

}
