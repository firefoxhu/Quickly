package com.quickly.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.quickly.R;
import com.quickly.VideoConstant;
import com.quickly.imageloader.ImageLoaderManager;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class AdapterRecyclerViewVideo   extends RecyclerView.Adapter<AdapterRecyclerViewVideo.MyViewHolder>{


    public static final String TAG = "AdapterRecyclerViewVideo";
    int[] videoIndexs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Context mContext;

    public AdapterRecyclerViewVideo(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_videoview, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.jzVideoPlayer.setUp(VideoConstant.videoUrls[0][position], JZVideoPlayer.SCREEN_WINDOW_LIST,
                VideoConstant.videoTitles[0][position]);

        ImageLoaderManager.getInstance(mContext).displayImage(holder.jzVideoPlayer.thumbImageView, VideoConstant.videoThumbs[0][position]);
        //holder.jzVideoPlayer.
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return videoIndexs.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        JZVideoPlayerStandard jzVideoPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jzVideoPlayer = itemView.findViewById(R.id.videoplayer);
        }
    }
}
