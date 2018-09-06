package com.quickly.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.quickly.R;
import com.quickly.entity.dto.CategoryItem;
import com.quickly.imageloader.ImageLoaderManager;
import com.yalantis.contextmenu.lib.Utils;

import java.util.List;

public class HomeCategoryAdapter   extends RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryMenuViewHolder> {

    private List<CategoryItem.DataBean.ListBean> mItemList;

    private Context mContent;

    private ImageLoaderManager imageLoaderManager;

    private LayoutInflater mInflate;

    public HomeCategoryAdapter(Context context,List<CategoryItem.DataBean.ListBean> list){
        mContent = context;
        mItemList = list;
        imageLoaderManager =ImageLoaderManager.getInstance(mContent);
        mInflate = LayoutInflater.from(mContent);
    }


    @NonNull
    @Override
    public HomeCategoryMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //  可以动态切花多个布局方式
        return new HomeCategoryMenuViewHolder(mInflate.inflate(R.layout.home_category_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryMenuViewHolder holder, int i) {
        CategoryItem.DataBean.ListBean item = mItemList.get(i);
        imageLoaderManager.displayCircleImage(holder.categoryImg,item.getImgUrl(),100);
        holder.categoryTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    class HomeCategoryMenuViewHolder extends RecyclerView.ViewHolder {


        public ImageView categoryImg;

        public TextView categoryTitle;


        public HomeCategoryMenuViewHolder(View view){

            super(view);

            categoryImg  = view.findViewById(R.id.category_img);

            categoryTitle = view.findViewById(R.id.category_title);
            view.setOnClickListener(v->{

                Toast.makeText(view.getContext(), "点击了！", Toast.LENGTH_SHORT).show();

            });
        }
    }



    private ImageView  createImageView(){
        ImageView imageView = new ImageView(mContent);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.leftMargin  =  5;


        imageView.setLayoutParams(params);
        return imageView;
    }
}
